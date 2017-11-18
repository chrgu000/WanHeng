package com.cgwas.common.utils.bank;

import com.cgwas.common.json.entity.BankResult;
import com.cgwas.common.json.entity.Result;
import com.cgwas.common.utils.PropertiesUtil;

import net.sf.json.JSONObject;

/**
 * 银行卡四元素校验接口
 */

public class VerifyBankCard {
//	public static final String appcode = "9c2f91344dd743749c14ea2ba9d43ecf";
	public static final String URL = "http://ali-bankcard4.showapi.com/bank4";
/*	public static final String bankcard = "6212261204000520897";
	public static final String realname = "凌伟豪";
	public static final String idcard = "330482199506211811";
	public static final String mobile = "15658078363";*/

/*	public static void main(String[] args) {
		String result = null;
		String url = URL + "?bankcard=" + bankcard + "&idcard=" + idcard
				+ "&mobile=" + mobile + "&realname=" + realname;

		try {
			result = HttpUtil.sendGet(url, "utf-8", appcode);
			System.out.println(result);
			JSONObject json = JSONObject.fromObject(result);
			if (json.getInt("status") != 0) {
				System.out.println(json.getString("msg"));
			} else {
				JSONObject resultarr = json.optJSONObject("result");
				String bankcard = resultarr.getString("bankcard");
				String realname = resultarr.getString("realname");
				String idcard = resultarr.getString("idcard");
				String mobile = resultarr.getString("mobile");
				String verifystatus = resultarr.getString("verifystatus");
				String verifymsg = resultarr.getString("verifymsg");
				System.out.println(bankcard + " " + realname + " " + idcard
						+ " " + mobile + " " + verifystatus + " " + verifymsg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * 验证银行卡一致性
	 * 
	 * @param bankcard
	 * @param realname
	 * @param idcard
	 * @param mobile
	 * @return
	 */
	public static String  verBankCard( String realname,String bankcard,
			String idcard, String mobile) {
		
		String appcode = PropertiesUtil.load("bank.properties", "bankVerAppCode");
		
		System.out.println(appcode);
		BankResult retn = new BankResult();
		String result = null;
		String cert_type="01";

		String url = URL +  "?acct_name=" + realname+ "&acct_pan=" + bankcard+"&cert_id=" + idcard+"&cert_type=" + cert_type +"&phone_num=" + mobile;
		
//		System.out.println(url);
		String code=null;
		try {
			result = HttpUtil.sendGet(url, "utf-8", appcode);
//			System.out.println(result);
			if (result == null || "".equals(result)) {
				retn.setErrorNo("-1");
				retn.setErrorMsg("未知错误");
			}
			JSONObject json = JSONObject.fromObject(result);
			
//			System.out.println(json);
			
			JSONObject resultarr = json.optJSONObject("showapi_res_body");
//			retn.setErrorNo(json.getString("showapi_res_code"));  //成功为0
			code=resultarr.getString("code");
			System.out.println(code);
			
//			retn.setErrorMsg(json.getString("showapi_res_error"));
//			System.out.println("1showapi_res_error"+json.getString("showapi_res_error"));
//		
//			System.out.println("2showapi_res_code"+json.getInt("showapi_res_code"));
//			System.out.println("3msg"+resultarr.getString("msg"));
//			System.out.println("4code"+resultarr.getString("code"));
//			System.out.println("5ret_code"+resultarr.getString("ret_code"));
//			System.out.println("6"+resultarr.getString("error"));
			
			
			/*if(0==json.getInt("showapi_res_code")){
				
				retn.setErrorNo(resultarr.getString("code"));   //成功为0
				 retn.setErrorMsg(resultarr.getString("msg"));    //资料匹配,账号正常
				
			}*/
			// return retn;
			// 201 Bank card number is empty 银行卡号为空
			// 202 Real name is empty 真实姓名为空
			// 203 Bank card number is not correct 银行卡号不正确
			// 204 Real name contains special characters 真实姓名包含特殊字符
			// 205 ID is not correct 身份证不正确
			// 210 No information 没有信息
		} catch (Exception e) {
			e.printStackTrace();
		}
		switch (code) {
		case "0":
			result ="BK0000"; // 绑定成功
			break;
		case "1":
			result="BK0001"; // 交易失败,请联系发卡行
			break;
		case "4":
			result="BK0004"; // 此卡被没收,请于发卡方联系
			break;
		case "5":
			result="BK0005"; // 持卡人认证失败
			break;
		case "14":
			result="BK0014"; // 无效卡号
			break;
		case "15":
			result="BK0015"; //此卡无对应发卡方
			break;
		case "21":
			result="BK0021"; // 该卡未初始化或睡眠卡
			break;
		case "34":
			result="BK0034"; //作弊卡,吞卡
			break;
		case "40":
			result="BK0040"; // 发卡方不支持的交易
			break;
		case "41":
			result="BK0041"; // 此卡已经挂失
			break;
		case "43":
			result="BK0043"; // 此卡被没收
			break;
		case "54":
			result="BK0054"; // 该卡已过期
			break;
		case "57":
			result="BK0057"; // 发卡方不允许此交易
			break;
		case "62":
			result="BK0062"; // 受限制的卡
			break;
		case "75":
			result="BK0075"; // 密码错误次数超限
			break;
		case "82":
			result="BK0082"; // 身份证号码有误
			break;
		case "83":
			result="BK0083"; // 银行卡号码有误
			break;
		case "84":
			result="BK0084"; // 手机号码不合法
			break;
		case "86":
			result="BK0086"; // 持卡人信息有误
			break;
		case "87":
			result="BK0087"; // 未开通无卡支付
			break;
		case "96":
			result="BK0096"; // 交易失败请重试
			break;
		
		}
		return result;

	}

}
