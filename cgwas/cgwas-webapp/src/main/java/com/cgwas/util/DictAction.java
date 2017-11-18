package com.cgwas.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cgwas.common.json.entity.Result;
import com.cgwas.common.utils.CommonUtil;
import com.cgwas.dict.entity.Dicts;
import com.cgwas.dict.service.api.IDictService;
import com.cgwas.privilegeInfo.service.api.IPrivilegeInfoService;
import com.cgwas.tradeRecord.entity.TradeStatistics;
import com.cgwas.tradeRecord.service.api.ITradeRecordService;
import com.cgwas.user.entity.User;
import com.cgwas.visitor.entity.Visitor;
import com.cgwas.visitor.entity.VisitorVo;
import com.cgwas.visitor.service.api.IVisitorService;

@Controller
@RequestMapping("cgwas/systemAction")
public class DictAction {
	@Autowired
	private IDictService dictService;
	@Autowired
	private IVisitorService visitorService;

	@Autowired
	private IPrivilegeInfoService iPrivilegeInfoService;
	@Autowired
	private ITradeRecordService tradeRecordService;

	/**
	 * 获取字典（S1）
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getSystemDicts")
	@ResponseBody
	public Result getSystemDicts(HttpServletRequest request,
			HttpServletResponse response) {
		List<Dicts> dicts = dictService.getSystemDicts();
		if (dicts == null) {
			return new Result("UT0002", null); // 字典获取失败
		}
		return new Result(Boolean.TRUE, "成功!", dicts); // 返回用户成长信息
	}

	/**
	 * 获取session登录状态(S4)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getSessionLogin")
	@ResponseBody
	public Result getSessionLogin(HttpServletRequest request,
			HttpServletResponse response) {
		// 检测登录
		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		return new Result(Boolean.TRUE, "成功!", loginUser); // 返回用户成长信息

	}

	/**
	 * 保存访问信息（S5）
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/saveVisitorInfo")
	@ResponseBody
	public Result saveVisitorInfo(HttpServletRequest request,
			HttpServletResponse response) {
		CommonUtil.getIpAddr(request);

		try {
			String ip = CommonUtil.getIpAddr(request);
			System.out.println(ip);
			Visitor visitor = new Visitor();
			visitor = AddressUtils.getAddressByIp(ip);

			visitorService.getHaveIpSave(visitor);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new Result(Boolean.TRUE, "成功!", null); // 返回用户成长信息

	}

	/**
	 * 按条件得到访问数量（S6）
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getVisitorList")
	@ResponseBody
	public Result getVisitorList(VisitorVo visitorVo, String timeFlag,
			HttpServletRequest request, HttpServletResponse response) {

		if (timeFlag == null) {
			Long count = visitorService.getVisitorCount(visitorVo);
			return new Result(Boolean.TRUE, "成功!", count);
		}

		return new Result(Boolean.TRUE, "成功!", null);

	}
	/**
	 * 根据年份统计交易数量 （ S7）
	 * 
	 * @param type
	 * @return
	 */
	@RequestMapping("/selectYearTrade")
	@ResponseBody
	public Result selectYearTrade(String type) {
		List<TradeStatistics> retn = tradeRecordService.selectYearTrade(type);
		List<TradeStatistics> out = new ArrayList<TradeStatistics>();// 输出的列表

		TradeStatistics tradeStatistics = null;
		for (int i = 0; i < 12; i++) { // 建立空的十二个月数组
			tradeStatistics = new TradeStatistics();
			tradeStatistics.setMon(Integer.toString(i+1));
			tradeStatistics.setTrade_price("0");
			out.add(tradeStatistics);
		}
		for (int j = 0;j < retn.size()-1; j++) { // 若有则替换赋值
			out.remove(Integer.parseInt(retn.get(j).getMon()));
			out.add(retn.get(j));
		}

		return new Result(Boolean.TRUE, "成功!", out);

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

}
