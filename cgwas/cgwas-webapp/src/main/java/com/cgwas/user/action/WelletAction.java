package com.cgwas.user.action;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cgwas.animationlighttask.service.api.IAnimationLightTaskService;
import com.cgwas.bankInfo.entity.BankInfo;
import com.cgwas.bankInfo.service.api.IBankInfoService;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageUtils;
import com.cgwas.common.json.entity.BankResult;
import com.cgwas.common.json.entity.Result;
import com.cgwas.common.utils.BankUtil;
import com.cgwas.common.utils.CommonUtil;
import com.cgwas.common.utils.ConstantUtil;
import com.cgwas.common.utils.EncryptUtil;
import com.cgwas.common.utils.PinyinUtil;
import com.cgwas.common.utils.PropertiesUtil;
import com.cgwas.common.utils.bank.VerifyBankCard;
import com.cgwas.freezeRecord.entity.FreezeRecord;
import com.cgwas.freezeRecord.entity.FreezeRecordVo;
import com.cgwas.freezeRecord.service.api.IFreezeRecordService;
import com.cgwas.message.entity.MessageVo;
import com.cgwas.modeltask.service.api.IModelTaskService;
import com.cgwas.tradeRecord.entity.TradeRecord;
import com.cgwas.tradeRecord.entity.TradeRecordVo;
import com.cgwas.tradeRecord.service.api.ITradeRecordService;
import com.cgwas.user.entity.User;
import com.cgwas.userAuthInfo.entity.UserAuthInfo;
import com.cgwas.userAuthInfo.service.api.IUserAuthInfoService;
import com.cgwas.userInfo.entity.UserInfo;
import com.cgwas.userInfo.service.api.IUserInfoService;
import com.cgwas.walletInfo.entity.WalletInfo;
import com.cgwas.walletInfo.service.api.IWalletInfoService;

@Controller
@RequestMapping("cgwas/userAction")
public class WelletAction {
	@Autowired
	private IFreezeRecordService freezeRecordService;
	@Autowired
	private IWalletInfoService walletInfoService;
	EncryptUtil encryptUtil = new EncryptUtil(); // 加密类
	@Autowired
	ITradeRecordService tradeRecordService;
	@Autowired
	IBankInfoService bankInfoService;
	@Autowired
	IUserInfoService userInfoService;
	@Autowired
	private IUserAuthInfoService userAuthInfoService;
	@Autowired
	private IModelTaskService taskService;
	@Autowired
	private IAnimationLightTaskService animationLightTaskService;
	// 获取参数
	Properties props = new Properties();
	Resource resource = new ClassPathResource("bank.properties");

	/**
	 * 用户充值金额（R71）
	 * 
	 * @param money
	 * @param request
	 * @param response
	 * @return
	 * @throws UnknownHostException
	 */
	@ResponseBody
	@RequestMapping("rechargeMoney")
	public Result rechargeMoney(int money, String password,
			HttpServletRequest request, HttpServletResponse response) {

		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		// 判断支付密码
		WalletInfo retn = walletInfoService.getUserWallet(loginUser.getId());
		if (!retn.getPay_password().equals(encryptUtil.getEncryptMsg(password))) {
			return new Result("RY0044", null); // 支付密码输入错误
		}

		// 保存充值记录
		TradeRecord tradeRecord = new TradeRecord();
		tradeRecord.setTrade_content("在【" + CommonUtil.getIpAddr(request)
				+ "】充值了【" + money + "】元");
		tradeRecord.setTrade_type("1");
		tradeRecord.setTrade_price((double) money);
		tradeRecord.setUser_id(loginUser.getId());
		tradeRecord.setTrade_time(new Date());
		// 保存充值记录
		tradeRecordService.save(tradeRecord);
		WalletInfo walletInfo = new WalletInfo();
		walletInfo.setUser_id(loginUser.getId());
		walletInfo.setRemaining_sum((double) money);
		walletInfoService.accessUserMoney(walletInfo, "save");
		return new Result(Boolean.TRUE, "充值成功！", null);

	}

	/**
	 * 用户提现金额（R72）
	 * 
	 * @param money
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("withdrawMoney")
	public Result withdrawMoney(int money, String password, Long bank_id,
			HttpServletRequest request, HttpServletResponse response) {

		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		// 判断支付密码
		WalletInfo retn = walletInfoService.getUserWallet(loginUser.getId());
		if (!retn.getPay_password().equals(encryptUtil.getEncryptMsg(password))) {
			return new Result("RY0044", null); // 支付密码输入错误
		}
		// 得到银行信息
		List<BankInfo> retnBanks = bankInfoService.getUserBankList(loginUser
				.getId());
		if (retnBanks == null) { // 判断是否为有银行卡
			return new Result("RY0045", null); // 请添加银行卡
		}
		BankInfo bankInfo = new BankInfo();
		for (int i = 0; i < retnBanks.size(); i++) {
			if (retnBanks.get(i).getId().equals(bank_id)) {
				bankInfo = retnBanks.get(i);
				break;
			}
		}
		if (bankInfo.getBank_num() == null) {// 判断银行卡编号是否存在
			return new Result("RY0046", null); // 没有这张银行卡
		}

		// 查询冻结信息
		FreezeRecord freezeRecord = new FreezeRecord();
		freezeRecord.setUser_id(loginUser.getId());
		double sumPrice = freezeRecordService.getUserFreezePrice(freezeRecord);
		// 查询余额是否充足
		if (retn.getRemaining_sum() - sumPrice < money) {// 判断余额是否充足
			return new Result("RY0032", null); // 余额不足
		}
		// 保存提现记录
		TradeRecord tradeRecord = new TradeRecord();
		tradeRecord.setTrade_content("在【" + CommonUtil.getIpAddr(request)
				+ "】提现到银行卡【" + bankInfo.getBank_num() + "】【"
				+ money + "】元");
		tradeRecord.setTrade_type("2");
		tradeRecord.setTrade_price((double) money);
		tradeRecord.setUser_id(loginUser.getId());
		tradeRecord.setTrade_time(new Date());
		tradeRecordService.save(tradeRecord);

		// 提现
		WalletInfo walletInfo = new WalletInfo();
		walletInfo.setUser_id(loginUser.getId());
		walletInfo.setRemaining_sum((double) money);
		walletInfoService.accessUserMoney(walletInfo, "get");
		return new Result(Boolean.TRUE, "提现已审请！", null);

	}

	/**
	 * 获取用户冻结金额信息(用户)(R75)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getUserFreezePrice")
	public Result getUserFreezePrice(HttpServletRequest request,
			HttpServletResponse response) {
		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		FreezeRecord freezeRecord = new FreezeRecord();
		freezeRecord.setUser_id(loginUser.getId());
		double sumPrice = freezeRecordService.getUserFreezePrice(freezeRecord);
		// 获取可用金额
		WalletInfo retnUser = walletInfoService
				.getUserWallet(loginUser.getId());
		double usePrice = retnUser.getRemaining_sum() - sumPrice;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sumPrice", sumPrice);
		map.put("usePrice", usePrice);
		return new Result(Boolean.TRUE, "获取成功！", map);
	}

	/**
	 * 用户转账金额(R112)
	 * 
	 * @param money
	 * @param password
	 * @param bank_id
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("transferMoney")
	public Result transferMoney(int money, String password, Long to_id,
			String to_name, String to_message, HttpServletRequest request,
			HttpServletResponse response) {

		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		// 判断支付密码
		WalletInfo retn = walletInfoService.getUserWallet(loginUser.getId());
		if (!retn.getPay_password().equals(encryptUtil.getEncryptMsg(password))) {
			return new Result("RY0044", null); // 支付密码输入错误
		}

		// 判断名称是否输入正确
		List<UserInfo> userinfoList = (List<UserInfo>) userInfoService
				.getUserInfoById(to_id);
		if (userinfoList.size() == 0) {
			return new Result("RY0047", null); // 该用户不存在
		}
		UserInfo retnUserInfo = userinfoList.get(0);
		if (!to_name.equals(retnUserInfo.getName())) {
			return new Result("RY0048", null); // 对方姓名检测失败
		}

		// 查询冻结信息
		FreezeRecord freezeRecord = new FreezeRecord();
		freezeRecord.setUser_id(loginUser.getId());
		double sumPrice = freezeRecordService.getUserFreezePrice(freezeRecord);
		// 查询余额是否充足
		if (retn.getRemaining_sum() - sumPrice < money) {// 判断余额是否充足
			return new Result("RY0032", null); // 余额不足
		}

		// 转账发起方
		// 保存提现记录
		TradeRecord tradeRecord = new TradeRecord();
		String hideName = to_name.substring(1, to_name.length()); // 隐藏姓名
		StringBuffer buffer = new StringBuffer();
		buffer.append("转账给【" + loginUser.getNickname() + "(*" + hideName
				+ ")】【" + money + "】元.");
		if (to_message != null) {
			buffer.append("您说:" + to_message);
		}

		tradeRecord.setTrade_content(buffer.toString());
		tradeRecord.setTrade_type("3");
		tradeRecord.setTrade_price((double) money);
		tradeRecord.setUser_id(loginUser.getId());
		tradeRecord.setTrade_time(new Date());
		tradeRecordService.save(tradeRecord);

		// 转账
		WalletInfo walletInfo = new WalletInfo();
		walletInfo.setUser_id(loginUser.getId());
		walletInfo.setRemaining_sum((double) money);
		walletInfoService.accessUserMoney(walletInfo, "get");

		// 转账接收方
		TradeRecord tradeRecord2 = new TradeRecord();
		tradeRecord2.setTrade_content(to_message == null ? "对方没有传话给您！"
				: to_message);
		tradeRecord2.setTrade_price((double) money);
		tradeRecord2.setUser_id(to_id);
		tradeRecord2.setTrade_time(new Date());
		tradeRecord2.setTrade_type("4");
		tradeRecord2.setFor_id(loginUser.getId());
		tradeRecordService.save(tradeRecord2);

		WalletInfo walletInfo2 = new WalletInfo();
		walletInfo2.setUser_id(to_id);
		walletInfo2.setRemaining_sum((double) money);
		walletInfoService.accessUserMoney(walletInfo2, "save");

		return new Result(Boolean.TRUE, "已经转账！", null);

	}

	/**
	 * 用户购买商品(R113)
	 * 
	 * @param password
	 * @param goodsId
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("payMoneyForGoods")
	public Result payMoneyForGoods(String password, Long goodsId,
			Integer goodsNum, HttpServletRequest request,
			HttpServletResponse response) {

		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		// 判断支付密码
		WalletInfo retn = walletInfoService.getUserWallet(loginUser.getId());
		if (!retn.getPay_password().equals(encryptUtil.getEncryptMsg(password))) {
			return new Result("RY0044", null); // 支付密码输入错误
		}
		// 查询商品信息和价格 --------------
		if (goodsNum == null) { // 数量非空

			goodsNum = 1;
		}
		String goods_name = "福袋";
		double goods_price = 188 * goodsNum;
		// 查询冻结信息
		FreezeRecord freezeRecord = new FreezeRecord();
		freezeRecord.setUser_id(loginUser.getId());
		double sumPrice = freezeRecordService.getUserFreezePrice(freezeRecord);
		// 查询余额是否充足
		if (retn.getRemaining_sum() - sumPrice < goods_price) {// 判断余额是否充足
			return new Result("RY0032", null); // 余额不足
		}
		// 保存提现记录
		TradeRecord tradeRecord = new TradeRecord();
		tradeRecord.setTrade_content("购买了本公司的【" + goods_name + "】数量为:【"
				+ goodsNum + "】");
		tradeRecord.setTrade_type("5");
		tradeRecord.setTrade_price(goods_price);
		tradeRecord.setUser_id(loginUser.getId());
		tradeRecord.setTrade_time(new Date());
		tradeRecordService.save(tradeRecord);

		// 提现
		WalletInfo walletInfo = new WalletInfo();
		walletInfo.setUser_id(loginUser.getId());
		walletInfo.setRemaining_sum(goods_price);
		walletInfoService.accessUserMoney(walletInfo, "get");

		return new Result(Boolean.TRUE, "购买成功！", null);
	}

	/**
	 * 获取自己银行卡列表(R119)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getMyBankList")
	public Result getMyBankList(HttpServletRequest request,
			HttpServletResponse response) {
		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		List<BankInfo> bankList = bankInfoService.getUserBankList(loginUser
				.getId());
		return new Result(Boolean.TRUE, "获取成功！", bankList);
	}

	/**
	 * 获取用户银行卡列表(人员)(R120)
	 * 
	 * @param user_id
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getUserBankListAdmin")
	public Result getUserBankListAdmin(Long user_id,
			HttpServletRequest request, HttpServletResponse response) {
		List<BankInfo> bankList = bankInfoService.getUserBankList(user_id);
		return new Result(Boolean.TRUE, "获取成功！", bankList);
	}

	/**
	 * 添加用户银行卡(人员)(R121)
	 * 
	 * @param bankInfo
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addUserBankCard")
	public Result addUserBankCard(BankInfo bankInfo, String password,
			HttpServletRequest request, HttpServletResponse response,
			String action, String code) {
		HttpSession session = request.getSession();
		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}

		// 判断支付密码
		WalletInfo retn = walletInfoService.getUserWallet(loginUser.getId());
		if (!retn.getPay_password().equals(encryptUtil.getEncryptMsg(password))) {
			return new Result("RY0044", null); // 支付密码输入错误
		}
		// 新手机验证
		String tels = (String) session.getAttribute(action + "Tel");
		String codes = (String) session.getAttribute(action + "Code"); // session

		if (!bankInfo.getPreinstall_phone().equals(tels)) {
			return new Result("RY0067", null); // 手机号错误
		}
		if (!code.equals(codes)) {
			return new Result("RY0068", null); // 验证码错误
		}
		// 银行卡四要素验证 ---------------
		// 获取用户信息
		String verBankFlag = PropertiesUtil.load("bank.properties",
				"bankVerFlag");
		String result=null;
		if ("true".equals(verBankFlag)) { // 控制是否校验银行卡
			UserInfo userInfo = userInfoService.getUserInfoById(
					loginUser.getId()).get(0);
			UserAuthInfo userAuthInfo = userAuthInfoService
					.getUserAuthInfoById(loginUser.getId()).get(0);
		/*	BankResult bResult = VerifyBankCard.verBankCard(
					bankInfo.getBank_num(), userInfo.getName(),
					userAuthInfo.getIdcard(), bankInfo.getPreinstall_phone());*/
			result=VerifyBankCard.verBankCard(
					 userInfo.getName(), bankInfo.getBank_num(),
					userAuthInfo.getIdcard(), bankInfo.getPreinstall_phone());
			if (!"BK0000".equals(result)) {
				return new Result(result, null);
			}
		}

		bankInfo.setStatus("1");
		bankInfo.setUser_id(loginUser.getId());
		bankInfo.setCreate_date(new Date());
		bankInfo.setBank__name(BankUtil.getNameOfBank(bankInfo.getBank_num())
				.split("·")[0]);
		bankInfo.setBank_ico("cgwas/images/sc/bankIcon/"
				+ PinyinUtil.getFullSpell(bankInfo.getBank__name()) + ".png");
		bankInfoService.save(bankInfo);

		// 清空缓存
		session.setAttribute(action + "Tel", null);
		session.setAttribute(action + "Code", null);
		return new Result(Boolean.TRUE, "添加成功！", null);
	}

	/**
	 * 删除银行卡(管理员)(R122)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("deleteBankInfo")
	public Result deleteBankInfo(Long id, String password,
			HttpServletRequest request, HttpServletResponse response) {
		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		// 判断支付密码
		WalletInfo retn = walletInfoService.getUserWallet(loginUser.getId());
		if (!retn.getPay_password().equals(encryptUtil.getEncryptMsg(password))) {
			return new Result("RY0044", null); // 支付密码输入错误
		}
		bankInfoService.updateStatusBankById("2", id, loginUser.getId());

		return new Result(Boolean.TRUE, "删除成功！", null);
	}

	/**
	 * 更改银行卡(管理员)(R123)
	 * 
	 * @param bankInfo
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("changeMyBankCard")
	public Result changeMyBankCard(BankInfo bankInfo,
			HttpServletRequest request, HttpServletResponse response,
			String action, String code) {
		HttpSession session = request.getSession();
		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		bankInfo.setBank__name(BankUtil.getNameOfBank(bankInfo.getBank_num())
				.split("·")[0]);
		bankInfo.setBank_ico("cgwas/images/sc/bankIcon/"
				+ PinyinUtil.getFullSpell(bankInfo.getBank__name()) + ".png");

		// 验证实名
		UserAuthInfo authInfo = this.getAuthInfoByUserId(loginUser.getId());
		if (authInfo == null) {
			return new Result("RY0053", null); // 实名认证未通过，请提交实名认证先关资料
		}
		// 新手机验证
		String tels = (String) session.getAttribute(action + "Tel");
		String codes = (String) session.getAttribute(action + "Code"); // session

		if (!bankInfo.getPreinstall_phone().equals(tels)) {
			return new Result("RY0067", null); // 手机号错误
		}
		if (!code.equals(codes)) {
			return new Result("RY0068", null); // 验证码错误
		}

		// 银行卡四要素验证 ---------------
		// 获取用户信息
		String verBankFlag = PropertiesUtil.load("bank.properties",
				"bankVerFlag");
		String result=null;
		if ("true".equals(verBankFlag)) { // 控制是否校验银行卡
			UserInfo userInfo = userInfoService.getUserInfoById(
					loginUser.getId()).get(0);
			UserAuthInfo userAuthInfo = userAuthInfoService
					.getUserAuthInfoById(loginUser.getId()).get(0);
			result=VerifyBankCard.verBankCard(
					 userInfo.getName(), bankInfo.getBank_num(),
					userAuthInfo.getIdcard(), bankInfo.getPreinstall_phone());
			if (!"BK0000".equals(result)) {
				return new Result(result, null);
			}
		}
		bankInfo.setUser_id(loginUser.getId());
		bankInfoService.updateBankById(bankInfo);

		// 清空缓存
		session.setAttribute(action + "Tel", null);
		session.setAttribute(action + "Code", null);
		return new Result(Boolean.TRUE, "修改成功！", null);
	}

	/**
	 * 获取用户银行卡列表(人员)(R124)
	 * 
	 * @param user_id
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getUserBankListUser")
	public Result getUserBankListUser(HttpServletRequest request,
			HttpServletResponse response) {
		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		List<BankInfo> bankList = bankInfoService.getUserBankList(loginUser
				.getId());
		return new Result(Boolean.TRUE, "获取成功！", bankList);
	}

	/**
	 * 获取银行名字（R125）
	 * 
	 * @param bankNum
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getBankName")
	public Result getBankName(String bankNum) {
		String bankName = BankUtil.getNameOfBank(bankNum);
		if ("".equals(bankName)) {
			bankName = "其它银行";
		}
		return new Result(Boolean.TRUE, "获取成功！", bankName);
	}

	/**
	 * 创建冻结金额信息（R134）
	 * 
	 * @param freezeRecord
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addFreezeRecord")
	public Result addFreezeRecord(FreezeRecord freezeRecord,
			HttpServletRequest request, HttpServletResponse response) {

		// User loginUser = this.getLoginUser(request);
		// if (loginUser == null) {
		// return new Result("RY0008", null); // 登录过期
		// }
		//
		// // 保存搜索条件
		// freezeRecord.setUser_id(loginUser.getId());
		//
		// WalletInfo retn = walletInfoService.getUserWallet(loginUser.getId());
		// // 查询冻结信息
		// FreezeRecord saveFreezeRecord = freezeRecord;
		// saveFreezeRecord.setUser_id(loginUser.getId());
		// saveFreezeRecord.setFreeze_state("Y"); // 冻结状态
		// saveFreezeRecord.setConsume_state("N"); // 消费状态
		// saveFreezeRecord.setCreate_time(new Date()); // 创建时间
		// List<Double> userPrice = freezeRecordService
		// .getUserFreezePrice(freezeRecord);
		// double sumPrice = 0.0;
		// for (Double price : userPrice) {
		// sumPrice += price;
		// }
		// // 取绝对值
		// freezeRecord.setFreeze_price(Math.abs(freezeRecord.getFreeze_price()));
		// // 查询余额是否充足
		// if (retn.getRemaining_sum() - sumPrice <
		// freezeRecord.getFreeze_price()) {// 判断余额是否充足
		// return new Result("RY0032", null); // 余额不足
		// }
		//
		// freezeRecord.setCreate_time(new Date());
		// freezeRecordService.save(saveFreezeRecord);
		return new Result(Boolean.TRUE, "冻结成功！", null);
	}

	/**
	 * 根据任务id解冻金额（R135）
	 * 
	 * @param taskId
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("thawFreezeRecord")
	public Result thawFreezeRecord(Long taskId, Long task_type,
			String password, HttpServletRequest request,
			HttpServletResponse response) {
		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		String retn = freezeRecordService.releaseFreezeRecord(taskId,
				task_type, password, loginUser.getId(), "8", "2", 13);
		if ("1".equals(retn)) {
			return new Result(Boolean.TRUE, "已经转账！", null);

		} else {

			return new Result(retn, null);
		}

	}

	/**
	 * 查询冻结记录（自己）(R136)
	 * 
	 * @param start
	 * @param pageFlag
	 * @param freezeRecord
	 * @param request
	 * @param response
	 * @param sortColumn
	 * @param sortType
	 * @param allFlag
	 * @return
	 */
	@ResponseBody
	@RequestMapping("selectFreezeRecordList")
	public Result selectFreezeRecordList(Long start, String pageFlag,
			FreezeRecord freezeRecord, HttpServletRequest request,
			HttpServletResponse response, String sortColumn, String sortType,
			String allFlag) {
		Page page = null;
		Long total = 0l;
		if (pageFlag == null) { // 判断是否是翻页操作
			Long startPage = start;
			if (startPage == null) { // 当开始页数为空时 赋值为0
				startPage = 0L;
			}
			page = new Page(startPage);// 页码参数
			total = freezeRecordService
					.selectFreezeRecordListCount(freezeRecord);
			// 设置记录总数
			page.setTotal(total);
		} else {
			page = new Page(Page.DEFAULT_LIMIT * (start - 1));// 获取开始页面
			total = freezeRecordService
					.selectFreezeRecordListCount(freezeRecord);
			// 设置记录总数
			page.setTotal(total);
			if ("next".equals(pageFlag)) { // 上一页下一页
				page.nextPage();
			} else {
				page.prePage();
			}
		}
		// 排序参数
		page.setSortColumn(sortColumn);
		page.setSortType(sortType);
		// 获取冻结信息列表
		List<FreezeRecordVo> freezeRecordList = freezeRecordService
				.selectFreezeRecordList(page, freezeRecord, allFlag);
		Map<String, Object> retn = new HashMap<String, Object>();
		retn.put("start", page.getStart()); // 当前记录数
		retn.put("freezeRecordList", freezeRecordList); // 数据
		retn.put("pageNum", page.getPageCount()); // 页面数量
		retn.put("total", total); // 数量
		return new Result(Boolean.TRUE, "查询成功！", retn);
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
	 * 查询交易记录数（R143）
	 * 
	 * @param tradeRecord
	 * @param start
	 * @param limit
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("gettradeRecordList")
	public Result gettradeRecordList(String start,
			String limit, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		TradeRecordVo tradeRecord = new TradeRecordVo();
		// 获取登录用户信息
		User user = this.getLoginUser(request);
		if(user==null){
			user= new User();
			user.setId(41l);
		}
		Page page=null;
		Map<String, String> params = new HashMap<String, String>();
		// parent_id为null的时候说明是资产管理第一层级，初始值为0
		params.put("pageSize", limit);
		params.put("pageNo", start);
		page = PageUtils.createPage(params);
		/**
		 * 查询当前用户的交易记录信息
		 */
		tradeRecord.setUser_id(user.getId());
		page = tradeRecordService.page(page, tradeRecord);
		map = new HashMap<String, Object>();
		map.put("count", page.getTotal());
		map.put("pageSize", page.getLimit());
		map.put("pageNo", page.getCurrentPage());
		map.put("tradeRecordList", page.getDataList());
		return new Result(Boolean.TRUE, "成功", map);
	}

	/**
	 * 获取认证信息
	 * 
	 * @param userId
	 * @return
	 */
	private UserAuthInfo getAuthInfoByUserId(Long userId) {
		List<UserAuthInfo> authList = userAuthInfoService
				.getUserAuthInfoById(userId);
		if (authList.size() < 1) {
			return null;
		}

		if ("已认证".equals(authList.get(0).getStatus())) {
			return authList.get(0);
		} else {
			return null;
		}

	}

}
