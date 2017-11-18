package com.cgwas.user.action;

import com.cgwas.bankInfo.entity.BankInfo;
import com.cgwas.bankInfo.service.api.IBankInfoService;
import com.cgwas.common.json.entity.LmWithdraw;
import com.cgwas.common.json.entity.Result;
import com.cgwas.common.utils.CommonUtil;
import com.cgwas.common.utils.EncryptUtil;
import com.cgwas.freezeRecord.entity.FreezeRecord;
import com.cgwas.freezeRecord.service.api.IFreezeRecordService;
import com.cgwas.tradeRecord.service.api.ITradeRecordService;
import com.cgwas.user.entity.User;
import com.cgwas.userInfo.entity.UserInfo;
import com.cgwas.userInfo.service.api.IUserInfoService;
import com.cgwas.util.WithdrawThread;
import com.cgwas.util.withdraw.IdGenerator;
import com.cgwas.walletInfo.entity.WalletInfo;
import com.cgwas.walletInfo.service.api.IWalletInfoService;
import com.cgwas.withdraw.service.api.IWithdrawService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.math.BigDecimal;
import java.util.*;

/**
 * 用户提现
 */
@Controller
@RequestMapping("/cgwas/userAction")
public class WithdrawController {
	@Autowired
	private IBankInfoService bankInfoService;
	@Autowired
	private IUserInfoService userInfoService;
	@Autowired
	private IWithdrawService withdrawService;
	@Autowired
	private ITradeRecordService tradeRecordService;
	@Autowired
	private IWalletInfoService walletInfoService;
	@Autowired
	private IFreezeRecordService freezeRecordService;

	/**
	 * @param crtacc
	 *            收方帐号
	 * @param crtnam
	 *            收款户名
	 * @param trsamt
	 *            交易金额。每次最高5万
	 * @param crtbnk
	 *            收方开户行
	 */
	@ResponseBody
	@RequestMapping("withdraw")
	public Result withdraw(Long bankid, Double trsamt, String bankNo,
			String password, HttpServletResponse response,
			HttpServletRequest request, HttpSession hs) throws Exception {
		/**
		 * 检查登录
		 */
		User loginUser = this.getLoginUser(request);
		/**
		 * 判断提交金额是否大于5万
		 */
		if(trsamt>50000  || trsamt <=0){
			return new Result("RY0080", null);
		}
		List<BankInfo> bankList = bankInfoService.getUserBankList(loginUser
				.getId());
		if (bankList == null) {
			return new Result("RY0079", null);
		}
		// 判断银行卡是否存在
		// 获取银行卡信息
		BankInfo bankInfo = new BankInfo();
		bankInfo.setUser_id(loginUser.getId());
		bankInfo.setId(bankid);
		bankInfo = bankInfoService.getBankInfoByUserId(bankInfo);
		if (bankInfo == null) {
			return new Result("RY0082", null);
		}
		// 获取用户信息
		List<UserInfo> retnList = userInfoService.getUserInfoById(loginUser
				.getId());
		if (retnList.size() == 0) {
			return new Result("RY0079", null);
		}
		UserInfo userInfo = retnList.get(0);
		LmWithdraw lmWithdraw = new LmWithdraw();
		lmWithdraw.setUserId(loginUser.getId());
		if ("招商银行".equals(bankInfo.getBank__name())) {
			// 判断是否为招行账户
			lmWithdraw.setBnkflg("Y");
			// 再判断个人户或企业户
			// 直接支付
			lmWithdraw.setBuscod("N02031");
		} else {
			// 网银互联
			lmWithdraw.setBuscod("N31010");
			lmWithdraw.setBnkflg("N");
		}
		EncryptUtil encryptUtil = new EncryptUtil();
		// 获取钱包信息
		WalletInfo retn = walletInfoService.getUserWallet(loginUser.getId());
		// 判断支付密码
		if (!retn.getPay_password().equals(
				encryptUtil.getEncryptMsg(password))) {
			return new Result("RY0044", null); // 支付密码输入错误
		}
		// 查询冻结信息
		FreezeRecord freezeRecord = new FreezeRecord();
		freezeRecord.setUser_id(loginUser.getId());
		double sumPrice = freezeRecordService.getUserFreezePrice(freezeRecord);
		// 查询余额是否充足
		if (retn.getRemaining_sum() - sumPrice < trsamt) {// 判断余额是否充足
			return new Result("RY0032", null); // 余额不足
		}
		BigDecimal b = new BigDecimal(trsamt);
		double money = b.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
		// System.out.println(f1);
		lmWithdraw.setYurref(IdGenerator.getYURREF());
		lmWithdraw.setTrsamt(money);// 交易金额。每次最高5万
		lmWithdraw.setCrtacc(bankInfo.getBank_num());// 收方帐号
		lmWithdraw.setCrtnam(userInfo.getName());// 收方户名
		lmWithdraw.setCrtbnk(bankInfo.getBank__name());// 收方开户行
		lmWithdraw.setCdtbrd(bankNo);// 收款行行号
		lmWithdraw.setReqsts("W");// W为处理中
		lmWithdraw.setRtnflg("W");// W为处理中
		lmWithdraw.setGmtCreate(Calendar.getInstance().getTime());
		lmWithdraw.setGmtModified(lmWithdraw.getGmtCreate());
		WithdrawThread r= new WithdrawThread();
		r.setPassword(password);
		r.setLmWithdraw(lmWithdraw);
		r.setIpAddress(CommonUtil.getIpAddr(request));
		r.start();
		return new Result(Boolean.TRUE, "提现申请已提交等待银行处理!", null);
		 
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
