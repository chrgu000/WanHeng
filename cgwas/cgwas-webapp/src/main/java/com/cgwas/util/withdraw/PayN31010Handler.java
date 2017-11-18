package com.cgwas.util.withdraw;

import com.cgwas.common.json.entity.LmWithdraw;
import com.cgwas.common.utils.wUtil.DateUtil;
import com.cgwas.common.utils.wUtil.Util;
import com.cgwas.withdraw.service.impl.WithdrawServiceImpl;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.json.XML;

import java.util.Calendar;

/**
 * Created by Administrator on 2017/6/27.
 */
public class PayN31010Handler implements Runnable {
	private static final Logger LOGGER = Logger
			.getLogger(PayN31010Handler.class);// 日志文件
	protected com.alibaba.fastjson.JSONObject jsonObject;
	private WithdrawServiceImpl lmWithdrawService = new WithdrawServiceImpl();

	public PayN31010Handler(com.alibaba.fastjson.JSONObject jsonObject2,
			WithdrawServiceImpl lmWithdrawService) {
		this.jsonObject = jsonObject2;
		this.lmWithdrawService = lmWithdrawService;
	}

	/**
	 * 业务交易明细查询
	 * 
	 * @return
	 */
	private JSONObject getNTEBPINF(String reqnbr) {
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String xml = "<?xml version=\"1.0\" encoding =\"GBK\"?>"
				+ "<CMBSDKPGK>" + "<INFO>" + "<FUNNAM>NTEBPINF</FUNNAM>"
				+ "<DATTYP>2</DATTYP>" + "<LGNNAM>风云直联</LGNNAM>" + "</INFO>"
				+ "<NTEBPINFX>" + "<REQNBR>" + reqnbr + "</REQNBR>"
				+ "</NTEBPINFX>" + "</CMBSDKPGK>";
		String res = HttpClientUtil.postXml(Util.FRONT_END_COMPUTER, xml);
		JSONObject jsonObject = XML.toJSONObject(res);
		return jsonObject;
	}

	@Override
	public void run() {
		LmWithdraw lmWithdraw = null;
		String reqnbr = jsonObject.getString("REQNBR");
		String yurref = jsonObject.getString("YURREF");
		String reqsts = jsonObject.getString("REQSTS");
		String rtnflg = jsonObject.getString("RTNFLG");
		String oprdat = jsonObject.getString("OPRDAT");// 经办该笔业务的日期。
		Double trsamt = jsonObject.getDouble("TRSAMT");// 交易金额
		// 查询成功，返回数据判断方法：返回的每笔信息中 REQSTS 如果不等于’FIN’表示该笔
		// 支付银行还在处理中， REQSTS=’FIN’时再判断 RTNFLG， RTNFLG 为’S’时表示成功， ’B’
		// 表示退票，其他作为失败处理
		if ("FIN".equals(reqsts)) {
			lmWithdraw = new LmWithdraw();
			// 更改状态W
			if ("S".equals(rtnflg)) {
				// 支付完成，更改状态
				lmWithdraw.setReqnbr(reqnbr);
				lmWithdraw.setYurref(yurref);
				lmWithdraw.setReqsts(reqsts);
				lmWithdraw.setRtnflg(rtnflg);
				// lmWithdraw.setRtnnar(rtnnar);
				try {
					lmWithdraw.setGmtPay(DateUtil.formatString(DateUtil
							.formatDate(
									DateUtil.formatString(oprdat, "yyyyMMdd"),
									"yyyy-MM-dd HH:mm:ss"),
							"yyyy-MM-dd HH:mm:ss"));
				} catch (Exception e) {
					e.printStackTrace();
				}
				lmWithdraw.setGmtModified(Calendar.getInstance().getTime());
				try {
					lmWithdrawService.updWithdraw_S(lmWithdraw);
				} catch (Exception e) {
					LOGGER.error("变更成功状态异常", e);
				}
			} else if ("B".equals(rtnflg)) {
				// 变更退票，金额返还
				JSONObject jObject = getNTEBPINF(reqnbr);
				jObject = jObject.getJSONObject("CMBSDKPGK").getJSONObject(
						"NTEBPINFZ");
				String rjcrsn = jObject.getString("RJCRSN");
				String rtnnar = jObject.getString("RTNNAR");
				lmWithdraw.setReqnbr(reqnbr);
				lmWithdraw.setYurref(yurref);
				lmWithdraw.setReqsts(reqsts);
				lmWithdraw.setRtnflg(rtnflg);
				lmWithdraw.setRjcrsn(rjcrsn);
				lmWithdraw.setRtnnar(rtnnar);
				lmWithdraw.setTrsamt(trsamt);
				// lmWithdraw.setGmtPay(DateUtil.formatString(DateUtil.formatDate(DateUtil.formatString(oprdat,
				// "yyyyMMdd"), "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss"));
				try {
					lmWithdrawService.updWithdraw_B(lmWithdraw);
				} catch (Exception e) {
					LOGGER.error("变更退票，金额返还异常", e);
				}
			} else {
				// 失败，金额返还
				JSONObject jObject = getNTEBPINF(reqnbr);
				jObject = jObject.getJSONObject("CMBSDKPGK").getJSONObject(
						"NTEBPINFZ");
				String rjcrsn = jObject.getString("RJCRSN");
				String rtnnar = jObject.getString("RTNNAR");
				lmWithdraw.setReqnbr(reqnbr);
				lmWithdraw.setYurref(yurref);
				lmWithdraw.setReqsts(reqsts);
				lmWithdraw.setRtnflg(rtnflg);
				lmWithdraw.setRjcrsn(rjcrsn);
				lmWithdraw.setRtnnar(rtnnar);
				lmWithdraw.setTrsamt(trsamt);
				try {
					lmWithdrawService.updWithdraw_O(lmWithdraw);
				} catch (Exception e) {
					LOGGER.error("失败，金额返还异常", e);
				}
			}
		}
	}
}
