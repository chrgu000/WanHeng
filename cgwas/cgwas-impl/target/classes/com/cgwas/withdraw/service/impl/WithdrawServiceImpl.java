package com.cgwas.withdraw.service.impl;

import java.util.Calendar;
import java.util.Date;

import org.json.JSONObject;
import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cgwas.common.json.entity.LmWithdraw;
import com.cgwas.common.utils.ConstantUtil;
import com.cgwas.common.utils.wUtil.DateUtil;
import com.cgwas.common.utils.wUtil.HttpClientUtil;
import com.cgwas.common.utils.wUtil.Util;
import com.cgwas.freezeRecord.entity.FreezeRecord;
import com.cgwas.freezeRecord.service.api.IFreezeRecordService;
import com.cgwas.schedule.entity.Schedule;
import com.cgwas.schedule.service.api.IScheduleService;
import com.cgwas.statement.dao.api.IStatementDao;
import com.cgwas.statement.entity.Statement;
import com.cgwas.statement.service.api.IStatementService;
import com.cgwas.tradeRecord.entity.TradeRecord;
import com.cgwas.tradeRecord.service.api.ITradeRecordService;
import com.cgwas.walletInfo.entity.WalletInfo;
import com.cgwas.walletInfo.service.api.IWalletInfoService;
import com.cgwas.withdraw.service.api.IWithdrawService;

/**
 * Created by Administrator on 2017/6/22.
 */
@Service("lmWithdrawService")
@Transactional
public class WithdrawServiceImpl implements IWithdrawService {
	private static final Logger LOGGER = LoggerFactory.getLogger(WithdrawServiceImpl.class);
	@Autowired
	private IStatementDao statementDao;
	@Autowired
	private IWalletInfoService walletInfoService;
	@Autowired
	private IFreezeRecordService freezeRecordService;
	@Autowired
	private IStatementService statementService;
	@Autowired
	private ITradeRecordService tradeRecordService;
	@Autowired
	private IScheduleService scheduleService;

	/**
	 * 添加提现记录
	 * 
	 * @param ub
	 * @param lmWithdraw
	 * @param lmUsercard
	 * @return
	 */
	public String addWithdraw(LmWithdraw lmWithdraw, String password,String ipAddress) {
		try {
			// 获取钱包信息
			WalletInfo retn = walletInfoService.getUserWallet(lmWithdraw.getUserId());
			// 查询冻结信息
			FreezeRecord freezeRecord = new FreezeRecord();
			freezeRecord.setUser_id(lmWithdraw.getUserId());
			double sumPrice = freezeRecordService.getUserFreezePrice(freezeRecord);
			// 点击提现先进行钱包扣款
			WalletInfo walletInfoOut = new WalletInfo();
			walletInfoOut.setUser_id(lmWithdraw.getUserId());
			walletInfoOut.setRemaining_sum(lmWithdraw.getTrsamt());
			walletInfoService.accessUserMoney(walletInfoOut, "get");
			//产生交易记录信息
			TradeRecord tradeRecord = new TradeRecord();
			StringBuffer buffer = new StringBuffer();
			buffer.append("在【" + ipAddress+ "】提现到您"+lmWithdraw.getCrtbnk()+"卡【" + lmWithdraw.getCrtacc() + "】【"
					+ lmWithdraw.getTrsamt() + "】元");
			tradeRecord.setTrade_content(buffer.toString());
			tradeRecord.setTrade_type(ConstantUtil.TRADE_WITHDRAW);
			tradeRecord.setTrade_price(lmWithdraw.getTrsamt());
			tradeRecord.setUser_id(lmWithdraw.getUserId());
			tradeRecord.setTrade_state(ConstantUtil.TRADE_PENDING);
			//订单号
			tradeRecord.setTrade_order_no(String.valueOf(new Date().getTime()));
			tradeRecord.setTrade_time(new Date());
			tradeRecordService.save(tradeRecord);
			/**
			 * 添加进度时间
			 */
			Schedule schedule=new Schedule();
			schedule.setTrade_record_id(tradeRecord.getId());
			schedule.setCreate_time(tradeRecord.getTrade_time());
			scheduleService.save(schedule);
			
			Statement statement = new Statement();
			statement.setBus_type("2");
			statement.setCreate_date(new Date());
			statement.setMoney_type("RMB");
			statement.setMoney(lmWithdraw.getTrsamt());
			statement.setBalance_money(retn.getRemaining_sum() - sumPrice
					- lmWithdraw.getTrsamt()); // 余额
			statement.setStatus("1");
			statement.setBank_num(lmWithdraw.getCrtacc());
			statement.setUser_id(lmWithdraw.getUserId());
			statementService.save(statement); // 保存流水记录
			String payRes = "";
			/**
			 * 添加银行处理进度时间
			 */
			schedule=new Schedule();
			schedule.setTrade_record_id(tradeRecord.getId());
			schedule.setCreate_time(new Date());
			scheduleService.save(schedule);
			// 调用银行转账接口
			if (lmWithdraw.getBnkflg().equals("N")) {
				// 跨行，使用网银互联
				payRes = PayN31010(lmWithdraw);
			} else {
				// 同行，直接支付
				payRes = PayN02031(lmWithdraw);
			}
			LOGGER.info("提现payRes：" + payRes);
			JSONObject jsonObject = XML.toJSONObject(payRes); // parseObject(payRes);
			// 判断本次通信是否成功
			Integer retcodI = jsonObject.getJSONObject("CMBSDKPGK").getJSONObject("INFO").getInt("RETCOD");
			String retcod = Integer.toString(retcodI);
			if ("0".equals(retcod)) {
				/**
				 * 添加提现成功进度信息
				 */
				schedule=new Schedule();
				schedule.setTrade_record_id(tradeRecord.getId());
				schedule.setCreate_time(new Date());
				scheduleService.save(schedule);
				/**
				 * 交易记录改为成功状态
				 */
				TradeRecord tradeRecordVo = new TradeRecord();
				tradeRecordVo.setId(tradeRecord.getId());
				tradeRecordVo.setTrade_state(ConstantUtil.TRADE_SUCCESS);
				tradeRecordService.updateIgnoreNull(tradeRecordVo);
//				String returnError = null;
//				if (lmWithdraw.getBnkflg().equals("Y")) {
//					returnError = updHandlerN02031(jsonObject, retcod,
//							lmWithdraw);
//				} 
				return "1";
			} else if ("-1".equals(retcod) || "-9".equals(retcod)) {
				LOGGER.info("可疑交易payRes：" + payRes);
				// 交易失败钱包扣款退回
				walletInfoOut = new WalletInfo();
				walletInfoOut.setUser_id(lmWithdraw.getUserId());
				walletInfoOut.setRemaining_sum(lmWithdraw.getTrsamt());
				walletInfoService.accessUserMoney(walletInfoOut, "save");
				/**
				 * 交易记录改为失败状态
				 */
				TradeRecord tradeRecordVo = new TradeRecord();
				tradeRecordVo.setId(tradeRecord.getId());
				tradeRecordVo.setTrade_state(ConstantUtil.TRADE_FAIL);
				tradeRecordService.updateIgnoreNull(tradeRecordVo);
				// 交易可疑
				lmWithdraw.setRetcod(retcod);
				if (lmWithdraw.getBnkflg().equals("Y")) {
					// -9 和-1 时，表示交易可疑，请查询支付结果。
					GetPaymentInfoN02031(lmWithdraw);
				} else {
					ntqryebpN31010(lmWithdraw);
				}
				return "2";
			} else {
				// 交易失败钱包扣款退回
				walletInfoOut = new WalletInfo();
				walletInfoOut.setUser_id(lmWithdraw.getUserId());
				walletInfoOut.setRemaining_sum(lmWithdraw.getTrsamt());
				walletInfoService.accessUserMoney(walletInfoOut, "save");
				/**
				 * 交易记录改为失败状态
				 */
				TradeRecord tradeRecordVo = new TradeRecord();
				tradeRecordVo.setId(tradeRecord.getId());
				tradeRecordVo.setTrade_state(ConstantUtil.TRADE_FAIL);
				tradeRecordService.updateIgnoreNull(tradeRecordVo);
				throw new RuntimeException("提现交易失败");
			}
		} catch (RuntimeException e) {
			System.out.println(e);
			throw e;
		} catch (Exception e) {
			System.out.println(e);
			throw e;
		}
	}

	public String updWithdraw_S(LmWithdraw lmWithdraw) { // 改变
		System.out
				.println("---------------调用函数提现成功---------------------------------------");
		// 记账
		TradeRecord tradeRecord = new TradeRecord();
		StringBuffer buffer = new StringBuffer();
		buffer.append("提现到您银行卡【" + lmWithdraw.getCrtacc() + "】【"
				+ lmWithdraw.getTrsamt() + "】元.");

		tradeRecord.setTrade_content(buffer.toString());
		tradeRecord.setTrade_type("2");
		tradeRecord.setTrade_price(lmWithdraw.getTrsamt());
		tradeRecord.setUser_id(lmWithdraw.getUserId());
		tradeRecord.setTrade_time(new Date());
		tradeRecordService.save(tradeRecord);
		return ""; 
	}

	public String updWithdraw_B(LmWithdraw lmWithdraw) { // 回改
		try {
			// 钱包减钱
			WalletInfo walletInfoOut = new WalletInfo();
			walletInfoOut.setUser_id(lmWithdraw.getUserId());
			walletInfoOut.setRemaining_sum(lmWithdraw.getTrsamt());
			walletInfoService.accessUserMoney(walletInfoOut, "save");
			return "";
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		// return null;
	}

	public String updWithdraw_O(LmWithdraw lmWithdraw) {
		// System.out.println(lmWithdraw);
		return updWithdraw_B(lmWithdraw);
	}

	/**
	 * //-9 和-1 时，表示交易可疑，请查询支付结果。
	 * 
	 * @param lmWithdraw
	 */
	private void ntqryebpN31010(LmWithdraw lmWithdraw) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_YEAR, -1);
		String xml = "<?xml version=\"1.0\" encoding =\"GBK\"?>"
				+ "<CMBSDKPGK>" + "<INFO>" + "<FUNNAM>NTQRYEBP</FUNNAM>"
				+ "<DATTYP>2</DATTYP>" + "<LGNNAM>风云直联</LGNNAM>" + "</INFO>"
				+ "<NTWAUEBPY>" + "<BUSCOD>N31010</BUSCOD>" + "<BGNDAT>"
				+ DateUtil.formatDate(c.getTime(), "yyyyMMdd") + "</BGNDAT>"
				+ "<ENDDAT>" + DateUtil.formatDate(new Date(), "yyyyMMdd")
				+ "</ENDDAT>" + "<YURREF>" + lmWithdraw.getYurref()
				+ "</YURREF>" + "</NTWAUEBPY>" + "</CMBSDKPGK>";
		String res = HttpClientUtil.postXml(Util.FRONT_END_COMPUTER, xml);
		JSONObject jsonObject = XML.toJSONObject(res);
		// String reqsts =
		// json.getJSONObject("CMBSDKPGK").getJSONObject("NTQPAYQYZ").getString("REQSTS");
		// String rtnflg =
		// json.getJSONObject("CMBSDKPGK").getJSONObject("NTQPAYQYZ").getString("RTNFLG");
		// updHandlerN31010(json,
		// json.getJSONObject("CMBSDKPGK").getJSONObject("INFO").getString("RETCOD"),
		// lmWithdraw);
	}

	/**
	 * //-9 和-1 时，表示交易可疑，请查询支付结果。
	 * 
	 * @param lmWithdraw
	 */
	private void GetPaymentInfoN02031(LmWithdraw lmWithdraw) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_YEAR, -1);
		String xml = "<?xml version=\"1.0\" encoding =\"GBK\"?>"
				+ "<CMBSDKPGK>" + "<INFO>" + "<FUNNAM>GetPaymentInfo</FUNNAM>"
				+ "<DATTYP>2</DATTYP>" + "<LGNNAM>风云直联</LGNNAM>" + "</INFO>"
				+ "<SDKPAYQYX>" + "<BUSCOD>N02031</BUSCOD>" + "<BGNDAT>"
				+ DateUtil.formatDate(c.getTime(), "yyyyMMdd") + "</BGNDAT>"
				+ "<ENDDAT>" + DateUtil.formatDate(new Date(), "yyyyMMdd")
				+ "</ENDDAT>" + "<YURREF>" + lmWithdraw.getYurref()
				+ "</YURREF>" + "</SDKPAYQYX>" + "</CMBSDKPGK>";
		String res = HttpClientUtil.postXml(Util.FRONT_END_COMPUTER, xml);
		System.out.println("----------------------" + res
				+ "----------------------------------");
		JSONObject jsonObject = XML.toJSONObject(res);
		// String reqsts =
		// json.getJSONObject("CMBSDKPGK").getJSONObject("NTQPAYQYZ").getString("REQSTS");
		// String rtnflg =
		// json.getJSONObject("CMBSDKPGK").getJSONObject("NTQPAYQYZ").getString("RTNFLG");
		// updHandlerN02031(json,
		// json.getJSONObject("CMBSDKPGK").getJSONObject("INFO").getString("RETCOD"),
		// lmWithdraw);
	}

	/**
	 * 处理直接支付请求结果
	 * 
	 * @param jsonObject
	 * @param retcod
	 * @param lmWithdraw
	 * @return
	 */
	private String updHandlerN02031(JSONObject jsonObject, String retcod,
			LmWithdraw lmWithdraw) {
		// 业务请求状态；REQSTS=‘FIN’并且RTNFLG=‘F’，
		// 表示支付失败；否则表示支付已被银行受理
		String reqsts = jsonObject.getJSONObject("CMBSDKPGK")
				.getJSONObject("NTQPAYRQZ").getString("REQSTS");
		lmWithdraw.setRetcod(retcod);
		// 流程实例号
		String reqnbr = Integer.toString( jsonObject.getJSONObject("CMBSDKPGK")
				.getJSONObject("NTQPAYRQZ").getInt("REQNBR"));
		lmWithdraw.setReqnbr(reqnbr);

		if ("FIN".equals(reqsts)) {
			String rtnflg = jsonObject.getJSONObject("CMBSDKPGK")
					.getJSONObject("NTQPAYRQZ").getString("RTNFLG");
			if (!"F".equals(rtnflg)) {
				// 流程号
//				String sqrnbr = jsonObject.getJSONObject("CMBSDKPGK")
//						.getJSONObject("NTQPAYRQZ").getString("SQRNBR");
//				lmWithdraw.setSqrnbr(sqrnbr);
//				lmWithdraw.setReqsts(reqsts);
//				lmWithdraw.setGmtModified(Calendar.getInstance().getTime());
				return "1";
			} else {
				// 返回错误文本
				throw new RuntimeException(jsonObject
						.getJSONObject("CMBSDKPGK").getJSONObject("NTQPAYRQZ")
						.getString("ERRTXT"));
			}
		}else if("NTE".equals(reqsts)){// 终审完毕 （lwh）
			return "1";
		}
		return "2"; 
	}

	/**
	 * 网银贷记：（网银互联）只能跨行
	 */
	private String PayN31010(LmWithdraw lmWithdraw) {
		String xml = "<?xml version=\"1.0\" encoding =\"GBK\"?>"
				+ "<CMBSDKPGK>" + "<INFO>" + "<FUNNAM>NTIBCOPR</FUNNAM>"
				+ "<DATTYP>2</DATTYP>" + "<LGNNAM>风云直联</LGNNAM>" + "</INFO>"
				+ "<NTOPRMODX>" + "<BUSMOD>00001</BUSMOD>" + "</NTOPRMODX>"
				+ "<NTIBCOPRX>" + "<SQRNBR>1</SQRNBR>" + "<BBKNBR>CB</BBKNBR>"
				+ "<ACCNBR>571910978710701</ACCNBR>"
				+ "<CNVNBR>0000002361</CNVNBR>" + "<YURREF>"
				+ lmWithdraw.getYurref()
				+ "</YURREF>"
				+ "<CCYNBR>10</CCYNBR>"
				+ "<TRSAMT>"
				+ lmWithdraw.getTrsamt()
				+ "</TRSAMT>"
				+ "<CDTNAM>"
				+ lmWithdraw.getCrtnam()
				+ "</CDTNAM>"
				+ "<CDTEAC>"
				+ lmWithdraw.getCrtacc()
				+ "</CDTEAC>"
				+ "<CDTBRD>"
				+ lmWithdraw.getCdtbrd()
				+ "</CDTBRD>"
				+ // 收款行行号
				"<TRSTYP>C200</TRSTYP>"
				+ "<TRSCAT>02001</TRSCAT>"
				+ "<RMKTXT>用户提现"
				+ lmWithdraw.getTrsamt()
				+ "</RMKTXT>"
				+ "</NTIBCOPRX>" + "</CMBSDKPGK>";
		LOGGER.info("网银互联请求：" + xml);
		String res = HttpClientUtil.postXml(Util.FRONT_END_COMPUTER, xml);
		return res;
	}

	/**
	 * 直接支付
	 * 
	 * @param lmWithdraw
	 * @return
	 */
	private String PayN02031(LmWithdraw lmWithdraw) {
		String xml = "<?xml version=\"1.0\" encoding =\"GBK\"?>"
				+ "<CMBSDKPGK>" + "<INFO>" + "<FUNNAM>DCPAYMNT</FUNNAM>"
				+ "<DATTYP>2</DATTYP>" + "<LGNNAM>风云直联</LGNNAM>" + "</INFO>"
				+ "<SDKPAYRQX>" + "<BUSCOD>N02031</BUSCOD>" + "</SDKPAYRQX>"
				+ "<DCOPDPAYX>" + "<YURREF>"
				+ lmWithdraw.getYurref()
				+ "</YURREF>"
				+ "<DBTACC>571910978710701</DBTACC>"
				+ "<DBTBBK>57</DBTBBK>"
				+ "<TRSAMT>"
				+ lmWithdraw.getTrsamt()
				+ "</TRSAMT>"
				+ "<CCYNBR>10</CCYNBR>"
				+ "<STLCHN>N</STLCHN>"
				+ "<NUSAGE>用户提现"
				+ lmWithdraw.getTrsamt()
				+ "</NUSAGE>"
				+ "<CRTACC>"
				+ lmWithdraw.getCrtacc()
				+ "</CRTACC>"
				+ "<CRTNAM>"
				+ lmWithdraw.getCrtnam()
				+ "</CRTNAM>"
				+ "<BNKFLG>"
				+ lmWithdraw.getBnkflg()
				+ "</BNKFLG>"
				+ "<CRTBNK>"
				+ lmWithdraw.getCdtbrd() + "</CRTBNK>" +
				// "<CRTADR>收方行地址</CRTADR>" +
				"<RCVCHK>1</RCVCHK>" + "</DCOPDPAYX>" + "</CMBSDKPGK>";
		// System.out.println(xml);
		LOGGER.info("直接支付请求：" + xml);
		String res = HttpClientUtil.postXml(Util.FRONT_END_COMPUTER, xml);
		return res;
	}
}
