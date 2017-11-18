package com.cgwas.user.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cgwas.common.json.entity.Result;
import com.cgwas.common.utils.CommonUtil;
import com.cgwas.tradeRecord.entity.TradeRecord;
import com.cgwas.tradeRecord.service.api.ITradeRecordService;
import com.cgwas.user.entity.User;
import com.cgwas.user.service.api.IUserService;
import com.cgwas.util.pay.LLPayUtil;
import com.cgwas.util.pay.OrderInfo;
import com.cgwas.util.pay.PartnerConfig;
import com.cgwas.util.pay.PayDataBean;
import com.cgwas.util.pay.PaymentInfo;
import com.cgwas.util.pay.RetBean;
import com.cgwas.util.pay.ServerURLConfig;
import com.cgwas.walletInfo.entity.WalletInfo;
import com.cgwas.walletInfo.service.api.IWalletInfoService;

@Controller
@RequestMapping("cgwas/userAction")
public class PayAction {

	@Autowired
	private IUserService userService;
	@Autowired
	ITradeRecordService tradeRecordService;
	@Autowired
	private IWalletInfoService walletInfoService;

	/**
	 * 创建支付表单(S2)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("createdPayForm")
	public Result createdPayForm(OrderInfo order, String paymod,
			HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession(); // session
		User loginUser = (User) session.getAttribute("loginUser");
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		order.setUser_uuid(loginUser.getUuid());
		// 创建订单
		order = this.createOrder(order, request);
		PaymentInfo paymentInfo = new PaymentInfo();
		if ("plain".equals(paymod)) {
			paymentInfo = plainPay(request, order, loginUser.getUuid());
		} else {
			paymentInfo = prepositPay(request, order, loginUser.getUuid(),
					"126");
		}
		paymentInfo.setReq_url(ServerURLConfig.PAY_URL);
		return new Result(Boolean.TRUE, "成功!", paymentInfo); // 返回用户详细信息

	}

	/**
	 * 异步订单查看(S3)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("checkNotify")
	public Map<String, Object> checkNotify(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("进入支付异步通知数据接收处理");
		RetBean retBean = new RetBean();
		String reqStr = LLPayUtil.readReqStr(request);
		if (LLPayUtil.isnull(reqStr)) {
			retBean.setRet_code("9999");
			retBean.setRet_msg("交易失败");

			return null; // 返回用户详细信息
		}
		System.out.println("接收支付异步通知数据：【" + reqStr + "】");
		try {
			if (!LLPayUtil.checkSign(reqStr, PartnerConfig.YT_PUB_KEY,
					PartnerConfig.MD5_KEY)) {
				retBean.setRet_code("9999");
				retBean.setRet_msg("交易失败");
				System.out.println("支付异步通知验签失败" + retBean.getRet_code());
				return null; // 返回用户详细信息
			}

		} catch (Exception e) {
			System.out.println("异步通知报文解析异常：" + e);
			retBean.setRet_code("9999");
			retBean.setRet_msg("交易失败");
			return null; // 返回用户详细信息
		}
		retBean.setRet_code("0000");
		retBean.setRet_msg("交易成功");
		System.out.println("支付异步通知数据接收处理成功!" + retBean.getRet_code());
		// 解析异步通知对象
		PayDataBean retn = JSON.parseObject(reqStr, PayDataBean.class);
		// TODO:更新订单，发货等后续处理

		System.out.println("支付异步通知数据接收处理成功!" + retBean);
		// 表单info
		List<String> infoList = extractMessageByRegular(retn.getInfo_order());

		User user = userService.getUserByUUID(infoList.get(0));

		Long count = tradeRecordService.checkRechargeTrade(user.getId(), "【"
				+ retn.getNo_order() + "】");
		if (count > 0) { // 已经入账不重复
			return null;
		}

		// 保存充值记录
		TradeRecord tradeRecord = new TradeRecord();
		tradeRecord
				.setTrade_content("在【" + infoList.get(1) + "】充值了【"
						+ retn.getMoney_order() + "】元");
		tradeRecord.setTrade_type("1");
		tradeRecord.setTrade_order_no(retn.getNo_order());
		tradeRecord.setTrade_price(Double.valueOf(retn.getMoney_order()));
		tradeRecord.setUser_id(user.getId());
		tradeRecord.setTrade_time(new Date());
		// 保存充值记录
		tradeRecordService.save(tradeRecord);
		WalletInfo walletInfo = new WalletInfo();
		walletInfo.setUser_id(user.getId());
		walletInfo.setRemaining_sum(Double.valueOf(retn.getMoney_order()));
		walletInfoService.accessUserMoney(walletInfo, "save");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ret_code", "0000");
		map.put("ret_msg", "交易成功");
		return map;
		// return new Result(Boolean.TRUE, "充值成功！", null);

	}

	/**
	 * 模拟商户创建订单
	 * 
	 * @param req
	 * @return
	 */
	private OrderInfo createOrder(OrderInfo order, HttpServletRequest request) {
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setNo_order(LLPayUtil.getCurrentDateTimeStr());
		orderInfo.setDt_order(LLPayUtil.getCurrentDateTimeStr());
		orderInfo.setMoney_order(order.getMoney_order());
		orderInfo.setName_goods(order.getName_goods());
		orderInfo
				.setInfo_order("用户[" + order.getUser_uuid() + "] 于["
						+ CommonUtil.getIpAddr(request) + "]购买"
						+ order.getName_goods());
		return orderInfo;
	}

	/**
	 * 普通支付处理
	 * 
	 * @param req
	 * @param order
	 */
	private PaymentInfo plainPay(HttpServletRequest req, OrderInfo order,
			String user_id) {
		
		User user = userService.getUserByUUID(user_id);
		// 构造支付请求对象
		PaymentInfo paymentInfo = new PaymentInfo();
		paymentInfo.setVersion(PartnerConfig.VERSION);
		paymentInfo.setOid_partner(PartnerConfig.OID_PARTNER);
		paymentInfo.setUser_id(user_id);
		paymentInfo.setSign_type(PartnerConfig.SIGN_TYPE);
		paymentInfo.setBusi_partner(PartnerConfig.BUSI_PARTNER);
		paymentInfo.setNo_order(order.getNo_order());
		paymentInfo.setDt_order(order.getDt_order());
		paymentInfo.setName_goods(order.getName_goods());
		paymentInfo.setInfo_order(order.getInfo_order());
		paymentInfo.setMoney_order(order.getMoney_order());
		paymentInfo.setNotify_url(PartnerConfig.NOTIFY_URL);
		paymentInfo.setUrl_return(PartnerConfig.URL_RETURN);
		paymentInfo.setUserreq_ip(CommonUtil.getIpAddr(req));
		paymentInfo.setUrl_order("");
		paymentInfo.setValid_order("10080");// 单位分钟，可以为空，默认7天
		paymentInfo.setTimestamp(LLPayUtil.getCurrentDateTimeStr());
		

		paymentInfo.setRisk_item(createRiskItem(user.getUuid(),user.getTel(), user.getRegist_time()));
		// 加签名
		String sign = LLPayUtil.addSign(
				JSON.parseObject(JSON.toJSONString(paymentInfo)),
				PartnerConfig.TRADER_PRI_KEY, PartnerConfig.MD5_KEY);
		paymentInfo.setSign(sign);
		paymentInfo.setReq_url(ServerURLConfig.PAY_URL);
		// req.setAttribute("version", paymentInfo.getVersion());
		// req.setAttribute("oid_partner", paymentInfo.getOid_partner());
		// req.setAttribute("user_id", paymentInfo.getUser_id());
		// req.setAttribute("sign_type", paymentInfo.getSign_type());
		// req.setAttribute("busi_partner", paymentInfo.getBusi_partner());
		// req.setAttribute("no_order", paymentInfo.getNo_order());
		// req.setAttribute("dt_order", paymentInfo.getDt_order());
		// req.setAttribute("name_goods", paymentInfo.getName_goods());
		// req.setAttribute("info_order", paymentInfo.getInfo_order());
		// req.setAttribute("money_order", paymentInfo.getMoney_order());
		// req.setAttribute("notify_url", paymentInfo.getNotify_url());
		// req.setAttribute("url_return", paymentInfo.getUrl_return());
		// req.setAttribute("userreq_ip", paymentInfo.getUserreq_ip());
		// req.setAttribute("url_order", paymentInfo.getUrl_order());
		// req.setAttribute("valid_order", paymentInfo.getValid_order());
		// req.setAttribute("timestamp", paymentInfo.getTimestamp());
		// req.setAttribute("sign", paymentInfo.getSign());
		// req.setAttribute("risk_item", paymentInfo.getRisk_item());
		// req.setAttribute("req_url", ServerURLConfig.PAY_URL);
		return paymentInfo;

	}

	/**
	 * 根据连连支付风控部门要求的参数进行构造风控参数
	 * 
	 * @return
	 */
	private String createRiskItem(String uuid, String tel, Date date) {
		
		String dateString = CommonUtil.stampToDate(date);
		JSONObject riskItemObj = new JSONObject(); 
		riskItemObj.put("frms_ware_category", "1004");
		riskItemObj.put("user_info_mercht_userno", uuid);
		riskItemObj.put("user_info_bind_phone", tel);
		riskItemObj.put("user_info_dt_register", dateString);
		return riskItemObj.toString();
	}

	/**
	 * 卡前置支付处理
	 * 
	 * @param req
	 * @param order
	 */
	private PaymentInfo prepositPay(HttpServletRequest req, OrderInfo order,
			String user_id, String no_agree) {
		User user = userService.getUserById( Long.valueOf(user_id));
		// 构造支付请求对象
		PaymentInfo paymentInfo = new PaymentInfo();
		paymentInfo.setVersion(PartnerConfig.VERSION);
		paymentInfo.setOid_partner(PartnerConfig.OID_PARTNER);
		paymentInfo.setUser_id(user_id);
		paymentInfo.setSign_type(PartnerConfig.SIGN_TYPE);
		paymentInfo.setBusi_partner(PartnerConfig.BUSI_PARTNER);
		paymentInfo.setNo_order(order.getNo_order());
		paymentInfo.setDt_order(order.getDt_order());
		paymentInfo.setName_goods(order.getName_goods());
		paymentInfo.setInfo_order(order.getInfo_order());
		paymentInfo.setMoney_order(order.getMoney_order());
		paymentInfo.setNotify_url(PartnerConfig.NOTIFY_URL);
		paymentInfo.setUrl_return(PartnerConfig.URL_RETURN);
		paymentInfo.setUserreq_ip(LLPayUtil.getIpAddr(req));
		paymentInfo.setUrl_order("");
		paymentInfo.setValid_order("10080");// 单位分钟，可以为空，默认7天
		paymentInfo.setRisk_item(createRiskItem(user.getUuid(),user.getTel(),user.getRegist_time()));
		paymentInfo.setTimestamp(LLPayUtil.getCurrentDateTimeStr());
		if (!LLPayUtil.isnull(no_agree)) {
			paymentInfo.setNo_agree(no_agree);
			paymentInfo.setBack_url("http://www.lianlianpay.com/");
		} else {
			// 从系统中获取用户身份信息
			paymentInfo.setId_type("0");
			paymentInfo.setId_no("410782198912151334");
			paymentInfo.setAcct_name("连连");
			paymentInfo.setFlag_modify("1");
			paymentInfo.setCard_no(req.getParameter("card_no"));
			paymentInfo.setBack_url("http://www.lianlianpay.com/");
		}
		// 加签名
		String sign = LLPayUtil.addSign(
				JSON.parseObject(JSON.toJSONString(paymentInfo)),
				PartnerConfig.TRADER_PRI_KEY, PartnerConfig.MD5_KEY);
		paymentInfo.setSign(sign);
		paymentInfo.setReq_url(ServerURLConfig.PAY_URL);
		return paymentInfo;
		// req.setAttribute("version", paymentInfo.getVersion());
		// req.setAttribute("oid_partner", paymentInfo.getOid_partner());
		// req.setAttribute("user_id", paymentInfo.getUser_id());
		// req.setAttribute("sign_type", paymentInfo.getSign_type());
		// req.setAttribute("busi_partner", paymentInfo.getBusi_partner());
		// req.setAttribute("no_order", paymentInfo.getNo_order());
		// req.setAttribute("dt_order", paymentInfo.getDt_order());
		// req.setAttribute("name_goods", paymentInfo.getName_goods());
		// req.setAttribute("info_order", paymentInfo.getInfo_order());
		// req.setAttribute("money_order", paymentInfo.getMoney_order());
		// req.setAttribute("notify_url", paymentInfo.getNotify_url());
		// req.setAttribute("url_return", paymentInfo.getUrl_return());
		// req.setAttribute("userreq_ip", paymentInfo.getUserreq_ip());
		// req.setAttribute("url_order", paymentInfo.getUrl_order());
		// req.setAttribute("valid_order", paymentInfo.getValid_order());
		// req.setAttribute("timestamp", paymentInfo.getTimestamp());
		// req.setAttribute("sign", paymentInfo.getSign());
		// req.setAttribute("risk_item", paymentInfo.getRisk_item());
		// req.setAttribute("no_agree", paymentInfo.getNo_agree());
		// req.setAttribute("id_type", paymentInfo.getId_type());
		// req.setAttribute("id_no", paymentInfo.getId_no());
		// req.setAttribute("acct_name", paymentInfo.getAcct_name());
		// req.setAttribute("flag_modify", paymentInfo.getFlag_modify());
		// req.setAttribute("card_no", paymentInfo.getCard_no());
		// req.setAttribute("back_url", paymentInfo.getBack_url());
		// req.setAttribute("req_url", ServerURLConfig.PAY_URL);

	}

	/**
	 * 检测登录状态
	 * 
	 * @param request
	 * @return
	 */
	private User getLoginUser(HttpServletRequest request) {
		HttpSession session = request.getSession(); // session
		return (User) session.getAttribute("loginUser");
	}

	/**
	 * 使用正则表达式提取中括号中的内容
	 * 
	 * @param msg
	 * @return
	 */
	public static List<String> extractMessageByRegular(String msg) {

		List<String> list = new ArrayList<String>();
		Pattern p = Pattern.compile("(\\[[^\\]]*\\])");
		Matcher m = p.matcher(msg);
		while (m.find()) {
			list.add(m.group().substring(1, m.group().length() - 1));
		}
		return list;
	}

}
