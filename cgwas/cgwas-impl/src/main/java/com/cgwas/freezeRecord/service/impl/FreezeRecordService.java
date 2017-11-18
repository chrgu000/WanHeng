package com.cgwas.freezeRecord.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.animationlighttask.entity.AnimationLightTask;
import com.cgwas.animationlighttask.service.api.IAnimationLightTaskService;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.utils.CommonUtil;
import com.cgwas.common.utils.EncryptUtil;
import com.cgwas.company.entity.Company;
import com.cgwas.company.service.api.ICompanyService;
import com.cgwas.freezeRecord.dao.api.IFreezeRecordDao;
import com.cgwas.freezeRecord.entity.FreezeRecord;
import com.cgwas.freezeRecord.entity.FreezeRecordVo;
import com.cgwas.freezeRecord.service.api.IFreezeRecordService;
import com.cgwas.modeltask.entity.ModelTask;
import com.cgwas.modeltask.entity.ModelTaskVo;
import com.cgwas.modeltask.service.api.IModelTaskService;
import com.cgwas.tradeRecord.dao.api.ITradeRecordDao;
import com.cgwas.tradeRecord.entity.TradeRecord;
import com.cgwas.tradeRecord.service.api.ITradeRecordService;
import com.cgwas.user.entity.User;
import com.cgwas.user.service.api.IUserService;
import com.cgwas.userCompany.entity.UserCompany;
import com.cgwas.userCompany.service.api.IUserCompanyService;
import com.cgwas.walletInfo.entity.WalletInfo;
import com.cgwas.walletInfo.service.api.IWalletInfoService;

@Service
@SuppressWarnings("unchecked")
public class FreezeRecordService implements IFreezeRecordService {
	private IFreezeRecordDao freezeRecordDao;
	@Autowired
	private IWalletInfoService walletInfoService;

	@Autowired
	private IModelTaskService taskService;
	@Autowired
	private IAnimationLightTaskService animationLightTaskService;
	@Autowired
	ITradeRecordService tradeRecordService;
	@Autowired
	private ITradeRecordDao tradeRecordDao;

	@Autowired
	private ICompanyService companyService;

	@Autowired
	private IUserCompanyService userCompanyService;
	@Autowired
	private IUserService userService;

	public Serializable save(FreezeRecord freezeRecord) {
		return freezeRecordDao.save(freezeRecord);
	}

	public void delete(FreezeRecord freezeRecord) {
		freezeRecordDao.delete(freezeRecord);
	}

	public void deleteByExample(FreezeRecord freezeRecord) {
		freezeRecordDao.deleteByExample(freezeRecord);
	}

	public void update(FreezeRecord freezeRecord) {
		freezeRecordDao.update(freezeRecord);
	}

	public void updateIgnoreNull(FreezeRecord freezeRecord) {
		freezeRecordDao.updateIgnoreNull(freezeRecord);
	}

	public void updateByExample(FreezeRecord freezeRecord) {
		freezeRecordDao.update("updateByExampleFreezeRecord", freezeRecord);
	}

	public FreezeRecordVo load(FreezeRecord freezeRecord) {
		return (FreezeRecordVo) freezeRecordDao.reload(freezeRecord);
	}

	public FreezeRecordVo selectForObject(FreezeRecord freezeRecord) {
		return (FreezeRecordVo) freezeRecordDao.selectForObject(
				"selectFreezeRecord", freezeRecord);
	}

	public List<FreezeRecordVo> selectForList(FreezeRecord freezeRecord) {
		return (List<FreezeRecordVo>) freezeRecordDao.selectForList(
				"selectFreezeRecord", freezeRecord);
	}

	public Page page(Page page, FreezeRecord freezeRecord) {
		return freezeRecordDao.page(page, freezeRecord);
	}

	@Autowired
	public void setIFreezeRecordDao(
			@Qualifier("freezeRecordDao") IFreezeRecordDao freezeRecordDao) {
		this.freezeRecordDao = freezeRecordDao;
	}

	@Override
	public Double getUserFreezePrice(FreezeRecord freezeRecord) {
		List<Double> list=(List<Double>) freezeRecordDao.selectForList( "getUserFreezePrice", freezeRecord);
		double sumPrice = 0.0;
		for (Double price : list) {
			sumPrice += price;
		}
		return sumPrice;
	}

	@Override
	public void thawFreezeRecord(Long task_id, Long user_id, Long task_type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("task_id", task_id);
		map.put("user_id", user_id);
		map.put("task_type", task_type);

		freezeRecordDao.update("thawFreezeRecord", map);

	}

	@Override
	public FreezeRecordVo selectFreezeRecordByTask(Long task_id, Long user_id,
			Long task_type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("task_id", task_id);
		map.put("user_id", user_id);
		map.put("task_type", task_type);

		return (FreezeRecordVo) freezeRecordDao.selectForObject(
				"selectFreezeRecordByTask", map);
	}

	@Override
	public List<FreezeRecordVo> selectFreezeRecordList(Page page,
			FreezeRecord freezeRecord, String allFlag) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", page);
		map.put("fsreezeRecord", freezeRecord);
		map.put("allFlag", allFlag);
		return (List<FreezeRecordVo>) freezeRecordDao.selectForList(
				"selectFreezeRecordList", map);
	}

	@Override
	public Long selectFreezeRecordListCount(FreezeRecord freezeRecord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fsreezeRecord", freezeRecord);
		return (Long) freezeRecordDao.selectForObject(
				"selectFreezeRecordListCount", map);
	}

	@Override
	public String saveFreezeRecord(FreezeRecord freezeRecord, String password,
			String passwordFlag) {
		EncryptUtil encryptUtil = new EncryptUtil();
		// 判断支付密码
		WalletInfo retn = walletInfoService.getUserWallet(freezeRecord
				.getUser_id());
		if (!"pass".equals(passwordFlag)) {
			if (!retn.getPay_password().equals(
					encryptUtil.getEncryptMsg(password))) {
				return "RY0044"; // 支付密码输入错误
			}
		}

		// 查询任务是否被冻结

		if (this.checkFreezeRecordCount(freezeRecord) > 0) {
			return "RY0078"; // 已经冻结无需再冻结
		}

//		WalletInfo retns = walletInfoService.getUserWallet(freezeRecord
//				.getUser_id());
//		if (!retns.getPay_password()
//				.equals(encryptUtil.getEncryptMsg(password))) {
//			return "RY0044"; // 支付密码输入错误
//		}
		// 查询冻结信息
		FreezeRecord saveFreezeRecord = new FreezeRecord();
		saveFreezeRecord = freezeRecord;
		saveFreezeRecord.setUser_id(freezeRecord.getUser_id());
		saveFreezeRecord.setFreeze_state("Y"); // 冻结状态
		saveFreezeRecord.setConsume_state("N"); // 消费状态
		saveFreezeRecord.setCreate_time(new Date()); // 创建时间
		double sumPrice = this.getUserFreezePrice(freezeRecord);
		// 取绝对值
		saveFreezeRecord.setFreeze_price(Math.abs(freezeRecord
				.getFreeze_price()));
		// 查询余额是否充足
		if (retn.getRemaining_sum() - sumPrice < freezeRecord.getFreeze_price()) {// 判断余额是否充足
			return "RY0032"; // 余额不足
		}

		saveFreezeRecord.setCreate_time(new Date());
		freezeRecordDao.save(saveFreezeRecord);
		if (saveFreezeRecord.getId() != null) {
			return "1";
		} else {
			return "2";

		}

	}

	@Override
	public String releaseFreezeRecord(Long taskId, Long task_type,
			String password, Long userId, String type, String stageFlag,
			Integer custom) {
		EncryptUtil encryptUtil = new EncryptUtil();

		// 判断支付密码
		if ("1".equals(type)) { //支付时需要密码 
			WalletInfo retn = walletInfoService.getUserWallet(userId);
			if (!retn.getPay_password().equals(
					encryptUtil.getEncryptMsg(password))) {
				return "RY0044"; // 支付密码输入错误
			}
		}

		String messionName = ""; // 任务名字
		double messionPrice = 0.0; // 任务价格
		Long messionUserId = 0L; // 任务用户id
		Long messionCompanyId = 0L;// 任务公司id
		Long publish_type_id=0l;//发布类型
		if (task_type == 1) {
			ModelTaskVo inModelTask = new ModelTaskVo();
			inModelTask.setId(taskId);
			ModelTask searchModelTask = taskService
					.selectForObject(inModelTask);
			if (searchModelTask == null) {
				return "RY0061"; // 无此任务
			}
			// 赋值信息
			messionName = searchModelTask.getName();
			messionPrice = Double.valueOf(searchModelTask.getPrice());
			messionUserId = searchModelTask.getUser_id();
			messionCompanyId = searchModelTask.getCompany_id();
			publish_type_id=searchModelTask.getPublish_type_id();
		} else {
			AnimationLightTask inAnimationLightTask = new AnimationLightTask();
			inAnimationLightTask.setId(taskId);
			AnimationLightTask searchAnimationLightTask = animationLightTaskService
					.selectForObject(inAnimationLightTask);
			if (searchAnimationLightTask == null) {
				return "RY0061"; // 无此任务
			}
			// 赋值信息
			messionName = searchAnimationLightTask.getPattern_number();
			messionPrice = Double.valueOf(searchAnimationLightTask.getPrice());
			messionUserId = searchAnimationLightTask.getUser_id();
			messionCompanyId = searchAnimationLightTask.getCompany_id();
			publish_type_id=searchAnimationLightTask.getPublish_type_id();
		}

		if ("1".equals(type)) { // 普通公司交付
			// 获取用户信息
			User searchUser = userService.getUserById(messionUserId);
			// 得到冻结信息
			FreezeRecordVo freezeRecordVo = this.selectFreezeRecordByTask(
					taskId, userId, task_type);
			
			if (freezeRecordVo == null
					|| freezeRecordVo.getCompany_id() == null) {
				return "RY0073"; // 无此冻结信息
			}
			if(publish_type_id.longValue()!=3){
				// 得到冻结信息(用户)
				FreezeRecordVo freezeRecordVoU = this.selectFreezeRecordByTask(
						taskId, messionUserId, task_type);
				
				if (freezeRecordVoU == null) { // 判断是否存在和是否为公司禁用记录
					return "RY0062"; // 无此冻结信息
				}
			}

			// 保存转账记录
			TradeRecord tradeRecord = new TradeRecord();
			StringBuffer buffer = new StringBuffer();
			buffer.append("完成了【"
					+ freezeRecordVo.getCompany_name()
					+ "】的【"
					+ messionName
					+ "】任务,对方转账给您【"
					+ CommonUtil.getTaxMoney(Double.valueOf(messionPrice),
							"finish")
					+ "】元(平台收取佣金【"
					+ (messionPrice - CommonUtil.getTaxMoney(
							Double.valueOf(messionPrice), "finish")) + "】元)。");

			tradeRecord.setTrade_content(buffer.toString());
			tradeRecord.setTrade_type("4");
			tradeRecord.setTrade_price(CommonUtil.getTaxMoney(
					Double.valueOf(messionPrice), "finish"));
			tradeRecord.setUser_id(userId);
			tradeRecord.setTrade_time(new Date());
			tradeRecordService.save(tradeRecord);
			// 解冻金额
			this.thawFreezeRecord(taskId, userId, task_type);
			// 保存转账记录
			TradeRecord tradeRecordOut = new TradeRecord();
			StringBuffer bufferOut = new StringBuffer();
			bufferOut.append("【"
					+ searchUser.getNickname()
					+ "】完成了贵公司的【"
					+ messionName
					+ "】任务,贵公司支付任务金【"
					+ CommonUtil.getTaxMoney(Double.valueOf(messionPrice),
							"finish")
					+ "】元(平台收取佣金【"
					+ (messionPrice - CommonUtil.getTaxMoney(
							Double.valueOf(messionPrice), "finish")) + "】元)。");

			tradeRecordOut.setTrade_content(bufferOut.toString());
			tradeRecordOut.setTrade_type("5");
			tradeRecordOut.setTrade_price(CommonUtil.getTaxMoney(
					Double.valueOf(messionPrice), "finish"));
			tradeRecordOut.setUser_id(userId);
			tradeRecordOut.setTrade_time(new Date());
			tradeRecordService.save(tradeRecordOut);

			// 归还用户保证金
			TradeRecord tradeRecordU = new TradeRecord();
			StringBuffer bufferu = new StringBuffer();
			bufferu.append("完成了【" + freezeRecordVo.getCompany_name() + "】的【"
					+ messionName + "】任务,解冻了您的保证金【" + messionPrice * 0.25
					+ "】元。");

			tradeRecordU.setTrade_content(bufferu.toString());
			tradeRecordU.setTrade_type("6");
			tradeRecordU.setTrade_price(Double.valueOf(messionPrice * 0.25));
			tradeRecordU.setUser_id(messionUserId);
			tradeRecordU.setTrade_time(new Date());
			tradeRecordService.save(tradeRecordU);
			// 解冻金额
			this.thawFreezeRecord(taskId, messionUserId, task_type);
			// 转账（出金放）
			WalletInfo walletInfoOut = new WalletInfo();
			walletInfoOut.setUser_id(userId);
			walletInfoOut.setRemaining_sum(Double.valueOf(messionPrice));
			walletInfoService.accessUserMoney(walletInfoOut, "get");
			// 转账
			WalletInfo walletInfo2 = new WalletInfo();
			walletInfo2.setUser_id(messionUserId);
			walletInfo2.setRemaining_sum(CommonUtil.getTaxMoney(
					Double.valueOf(messionPrice), "finish"));
			walletInfoService.accessUserMoney(walletInfo2, "save");

			// 保证金解冻
			// WalletInfo walletInfou = new WalletInfo();
			// walletInfou.setUser_id(messionUserId);
			// walletInfou.setRemaining_sum(Double.valueOf(messionPrice *
			// 0.25));
			// walletInfoService.accessUserMoney(walletInfou, "save");
		} else if ("2".equals(type) || "7".equals(type)) { // 人员违约
			// 得到冻结信息（用户）
			FreezeRecordVo freezeRecordVo = this.selectFreezeRecordByTask(
					taskId, messionUserId, task_type);
			if (freezeRecordVo == null) {
				return "RY0062"; // 无此冻结信息
			}
			double priceBfb = 1; // 赔付百分比
			String priceBfbString = "100%";
			if (custom != null && (custom >= 0 && custom <= 25)) { // 协商解决
				priceBfb = (double) custom / 100;
				priceBfbString = custom + "%(协商比例)";
			} else { // 系统判定

				// 计算赔付百分比
				if ("1".equals(stageFlag)) {
					priceBfb = 0.2;
					priceBfbString = "5%";
				} else if ("2".equals(stageFlag)) {
					priceBfb = 0.8;
					priceBfbString = "20%";
				} else if ("3".equals(stageFlag)) {

					priceBfb = 1;
					priceBfbString = "25%";
				} else {

					return "RY0074"; // 请填写赔付阶段
				}
			}
			// 获取用户信息
			User searchUser = userService.getUserById(messionUserId);
			// 获取法人id
			UserCompany us = userCompanyService
					.companygetUser(messionCompanyId);

			// 保存转账记录
			TradeRecord tradeRecord = new TradeRecord();
			StringBuffer buffer = new StringBuffer();
			buffer.append("用户【" + searchUser.getNickname() + "】在执行本公司的【"
					+ messionName + "】任务时产生了违约行为,因此  对方转账给您【"
					+ freezeRecordVo.getFreeze_price() * priceBfb + "】元(任务的"
					+ priceBfbString + ")作为该任务的赔偿。");
			tradeRecord.setTrade_content(buffer.toString());
			tradeRecord.setTrade_type("4");
			tradeRecord.setTrade_price(freezeRecordVo.getFreeze_price()
					* priceBfb);// 违约承担
			tradeRecord.setUser_id(us.getUse_id());
			tradeRecord.setTrade_time(new Date());
			tradeRecordService.save(tradeRecord);

			// 解冻金额
			this.thawFreezeRecord(taskId, messionUserId, task_type);

			// 解冻任务金额返还公司 -----------------------------------------------
			FreezeRecordVo freezeRecordVo1 = this.selectFreezeRecordByTask(
					taskId, us.getUse_id(), task_type);
			if (freezeRecordVo1 == null
					|| freezeRecordVo1.getCompany_id() == null) { // 判断是否存在和是否为公司禁用记录
				return "RY0073"; // 无此冻结信息
			}

			// 保存转账记录
			TradeRecord tradeRecord1 = new TradeRecord();
			StringBuffer buffer1 = new StringBuffer();
			buffer1.append("用户【" + searchUser.getNickname() + "】在执行本公司的【"
					+ messionName + "】任务时产生了违约行为,因此  您的任务金额【"
					+ freezeRecordVo1.getFreeze_price() + "】元已经解冻。");
			tradeRecord1.setTrade_content(buffer1.toString());
			tradeRecord1.setTrade_type("6");
			tradeRecord1.setTrade_price(freezeRecordVo1.getFreeze_price());// 违约承担
			tradeRecord1.setUser_id(us.getUse_id());
			tradeRecord1.setTrade_time(new Date());
			tradeRecordService.save(tradeRecord1);

			// 解冻金额
			this.thawFreezeRecord(taskId, us.getUse_id(), task_type);

			// 转账(违约金)
			WalletInfo walletInfo2 = new WalletInfo();
			walletInfo2.setUser_id(us.getUse_id());
			walletInfo2.setRemaining_sum(freezeRecordVo.getFreeze_price()
					* priceBfb);
			walletInfoService.accessUserMoney(walletInfo2, "save");
			// 扣除违约金
			WalletInfo walletInfoOut = new WalletInfo();
			walletInfoOut.setUser_id(messionUserId);
			walletInfoOut.setRemaining_sum(freezeRecordVo.getFreeze_price()
					* priceBfb);
			walletInfoService.accessUserMoney(walletInfoOut, "get");

			// 转账(任务金)
			/*
			 * WalletInfo walletInfo3 = new WalletInfo();
			 * walletInfo3.setUser_id(us.getUse_id());
			 * walletInfo3.setRemaining_sum(freezeRecordVo1.getFreeze_price());
			 * walletInfoService.accessUserMoney(walletInfo3, "save");
			 */

			// 用户保证金找零
			// 得到公司信息
			Company retns = new Company();
			retns.setId(messionCompanyId);
			retns = companyService.selectCompanyById(retns);
			// 保存转账记录
			TradeRecord tradeRecordu = new TradeRecord();
			StringBuffer bufferu = new StringBuffer();
			bufferu.append("您在执行【" + retns.getCompany_name() + "】公司的【"
					+ messionName + "】任务时产生了违约行为,因此 您需要支付【"
					+ freezeRecordVo.getFreeze_price() * priceBfb + "】元(任务的"
					+ priceBfbString + ")作为该任务的赔偿");
			if (priceBfb < 1) {
				bufferu.append(",剩余的【" + freezeRecordVo.getFreeze_price()
						* (1 - priceBfb) + "】元已经解冻。");
			}
			tradeRecordu.setTrade_content(bufferu.toString());
			tradeRecordu.setTrade_type("5");
			tradeRecordu.setTrade_price(freezeRecordVo.getFreeze_price()
					* priceBfb);// 违约承担
			tradeRecordu.setUser_id(messionUserId);
			tradeRecordu.setTrade_time(new Date());
			tradeRecordService.save(tradeRecordu);

			// 转账(任务金)
			// WalletInfo walletInfou = new WalletInfo();
			// walletInfou.setUser_id(messionUserId);
			// walletInfou.setRemaining_sum(freezeRecordVo.getFreeze_price()
			// * (1 - priceBfb));
			// walletInfoService.accessUserMoney(walletInfou, "save");

		} else if ("3".equals(type) || "8".equals(type)) { // 公司违约
			// 获取法人id
			UserCompany us = userCompanyService
					.companygetUser(messionCompanyId);

			// 得到冻结信息
			FreezeRecordVo freezeRecordVo = this.selectFreezeRecordByTask(
					taskId, us.getUse_id(), task_type);
			if (freezeRecordVo == null
					|| freezeRecordVo.getCompany_id() == null) {
				return "RY0073"; // 无此冻结信息
			}
			double priceBfb = 1; // 赔付百分比
			String priceBfbString = "100%";
			if (custom != null && (custom >= 0 && custom <= 25)) { // 协商解决
				priceBfb = (double) custom / 100;
				priceBfbString = custom + "%(协商比例)";
			} else {
				// 计算佩服百分比
				if ("1".equals(stageFlag)) {
					priceBfb = 0.05;
					priceBfbString = "5%";
				} else if ("2".equals(stageFlag)) {
					priceBfb = 0.20;
					priceBfbString = "20%";
				} else if ("3".equals(stageFlag)) {

					priceBfb = 0.25;
					priceBfbString = "25%";
				} else {

					return "RY0074"; // 请填写赔付阶段
				}

			}
			// 得到公司信息
			Company company = new Company();
			company.setId(messionCompanyId);
			company = companyService.selectForObject(company);

			// 保存转账记录
			TradeRecord tradeRecord = new TradeRecord();
			StringBuffer buffer = new StringBuffer();
			buffer.append("【" + company.getCompany_name() + "】在执行发布的【"
					+ messionName + "】任务时产生了违约行为,因此  对方转账给您【"
					+ freezeRecordVo.getFreeze_price() * priceBfb + "】元(任务的"
					+ priceBfbString + ")作为该任务的赔偿。");
			tradeRecord.setTrade_content(buffer.toString());
			tradeRecord.setTrade_type("4");
			tradeRecord.setTrade_price(freezeRecordVo.getFreeze_price()
					* priceBfb);// 违约承担
			tradeRecord.setUser_id(messionUserId);
			tradeRecord.setTrade_time(new Date());
			tradeRecordService.save(tradeRecord);

			// 解冻金额
			this.thawFreezeRecord(taskId, us.getUse_id(), task_type);

			// 用户保证金返还用户 -----------------------------------------------
			FreezeRecordVo freezeRecordVo1 = this.selectFreezeRecordByTask(
					taskId, messionUserId, task_type);
			if (freezeRecordVo1 == null) { // 判断是否存在和是否为公司禁用记录
				return "RY0062"; // 无此冻结信息
			}

			// 保存转账记录
			TradeRecord tradeRecord1 = new TradeRecord();
			StringBuffer buffer1 = new StringBuffer();
			buffer1.append("【" + company.getCompany_name() + "】在执行发布的【"
					+ messionName + "】任务时产生了违约行为,因此您的保证金【"
					+ freezeRecordVo1.getFreeze_price() + "】元已经解冻");
			tradeRecord1.setTrade_content(buffer1.toString());
			tradeRecord1.setTrade_type("6");
			tradeRecord1.setTrade_price(freezeRecordVo1.getFreeze_price());// 违约承担
			tradeRecord1.setUser_id(messionUserId);
			tradeRecord1.setTrade_time(new Date());
			tradeRecordService.save(tradeRecord1);

			// 解冻金额
			this.thawFreezeRecord(taskId, messionUserId, task_type);
			// 转账(违约金)（公司）
			WalletInfo walletInfoOut = new WalletInfo();
			walletInfoOut.setUser_id(us.getUse_id());
			walletInfoOut.setRemaining_sum(freezeRecordVo.getFreeze_price()
					* priceBfb);
			walletInfoService.accessUserMoney(walletInfoOut, "get");
			// 转账(违约金)（人员）
			WalletInfo walletInfo2 = new WalletInfo();
			walletInfo2.setUser_id(messionUserId);
			walletInfo2.setRemaining_sum(freezeRecordVo.getFreeze_price()
					* priceBfb);
			walletInfoService.accessUserMoney(walletInfo2, "save");
			// 转账(人员保证金)
			// WalletInfo walletInfo3 = new WalletInfo();
			// walletInfo3.setUser_id(messionUserId);
			// walletInfo3.setRemaining_sum(freezeRecordVo1.getFreeze_price());
			// walletInfoService.accessUserMoney(walletInfo3, "save");

			// 公司保证金找零
			TradeRecord tradeRecordC = new TradeRecord();
			StringBuffer bufferC = new StringBuffer();
			bufferC.append("贵公司在执行发布的【" + messionName + "】任务时产生了违约行为,因此贵公司支付了【"
					+ freezeRecordVo.getFreeze_price() * priceBfb + "】元(任务的"
					+ priceBfbString + ")作为该任务的赔偿于任务执行者。剩余的【"
					+ freezeRecordVo.getFreeze_price() * (1 - priceBfb)
					+ "】元已经解冻。");
			tradeRecordC.setTrade_content(bufferC.toString());
			tradeRecordC.setTrade_type("5");
			tradeRecordC.setTrade_price(freezeRecordVo.getFreeze_price()
					* priceBfb);// 违约承担
			tradeRecordC.setUser_id(us.getUse_id());
			tradeRecordC.setTrade_time(new Date());
			tradeRecordService.save(tradeRecordC);

			// 解冻任务金
			// WalletInfo walletInfoC = new WalletInfo();
			// walletInfoC.setUser_id(us.getUse_id());
			// walletInfoC.setRemaining_sum(freezeRecordVo.getFreeze_price()
			// * (1 - priceBfb));
			//
			// walletInfoService.accessUserMoney(walletInfoC, "save");

		} else if ("5".equals(type)) { // 收回任务
			// 获取法人id
			UserCompany us = userCompanyService
					.companygetUser(messionCompanyId);

			// 得到冻结信息
			FreezeRecordVo freezeRecordVo = this.selectFreezeRecordByTask(
					taskId, us.getUse_id(), task_type);
			if (freezeRecordVo == null
					|| freezeRecordVo.getCompany_id() == null) {
				return "RY0073"; // 无此冻结信息
			}
			// 得到公司信息
			Company company = new Company();
			company.setId(messionCompanyId);
			company = companyService.selectForObject(company);
			// 保存转账记录
			TradeRecord tradeRecord = new TradeRecord();
			StringBuffer buffer = new StringBuffer();
			buffer.append("您收回了您公司发布的【" + messionName + "】任务,因此 该任务冻结的【"
					+ freezeRecordVo.getFreeze_price() + "】元,已经解冻。");
			tradeRecord.setTrade_content(buffer.toString());
			tradeRecord.setTrade_type("6");
			tradeRecord.setTrade_price(freezeRecordVo.getFreeze_price());// 违约承担
			tradeRecord.setUser_id(us.getUse_id());
			tradeRecord.setTrade_time(new Date());
			tradeRecordService.save(tradeRecord);

			// 解冻金额
			this.thawFreezeRecord(taskId, us.getUse_id(), task_type);

		} else if ("6".equals(type)) { // 确定任务解冻其他人

			// 获取任务接受集合　
			FreezeRecord tFreezeRecord = new FreezeRecord();
			tFreezeRecord.setUser_id(messionUserId);
			tFreezeRecord.setTask_id(taskId);
			List<Long> taskIds = this.searchUserIdByTaskId(tFreezeRecord);
			tFreezeRecord = this.selectFreezeRecordByTask(taskId,
					messionUserId, task_type);
			// 得到公司信息
			Company company = new Company();
			company.setId(messionCompanyId);
			company = companyService.selectForObject(company);
			for (int i = 0; i < taskIds.size(); i++) {
				if (taskIds.get(i).equals(messionUserId)) {
					continue;
				}

				// 保存转账记录
				TradeRecord tradeRecord = new TradeRecord();
				StringBuffer buffer = new StringBuffer();
				buffer.append("【" + company.getCompany_name() + "】已经确定了" + "【"
						+ messionName + "】任务执行者,因此 该任务冻结的【"
						+ tFreezeRecord.getFreeze_price() + "】元已经解冻。");
				tradeRecord.setTrade_content(buffer.toString());
				tradeRecord.setTrade_type("6");
				tradeRecord.setTrade_price(tFreezeRecord.getFreeze_price());// 违约承担
				tradeRecord.setUser_id(taskIds.get(i));
				tradeRecord.setTrade_time(new Date());
				tradeRecordService.save(tradeRecord);
			}

			this.thawReceiverFreezeRecord(tFreezeRecord);

		} else { // 默认公司任务交付
			// 获取用户信息
			User searchUser = userService.getUserById(messionUserId);
			// 得到冻结信息
			FreezeRecordVo freezeRecordVo = this.selectFreezeRecordByTask(
					taskId, userId, task_type);

			if (freezeRecordVo == null
					|| freezeRecordVo.getCompany_id() == null) {
				return "RY0073"; // 无此冻结信息
			}

			// 得到冻结信息(用户)
			FreezeRecordVo freezeRecordVoU = this.selectFreezeRecordByTask(
					taskId, messionUserId, task_type);

			if (freezeRecordVoU == null) { // 判断是否存在和是否为公司禁用记录
				return "RY0062"; // 无此冻结信息
			}

			// 保存转账记录
			TradeRecord tradeRecord = new TradeRecord();
			StringBuffer buffer = new StringBuffer();
			buffer.append("完成了【"
					+ freezeRecordVo.getCompany_name()
					+ "】的【"
					+ messionName
					+ "】任务,对方转账给您【"
					+ CommonUtil.getTaxMoney(Double.valueOf(messionPrice),
							"finish")
					+ "】元(平台收取佣金【"
					+ (messionPrice - CommonUtil.getTaxMoney(
							Double.valueOf(messionPrice), "finish")) + "】元)。");

			tradeRecord.setTrade_content(buffer.toString());
			tradeRecord.setTrade_type("4");
			tradeRecord.setTrade_price(CommonUtil.getTaxMoney(
					Double.valueOf(messionPrice), "finish"));
			tradeRecord.setUser_id(userId);
			tradeRecord.setTrade_time(new Date());
			tradeRecordService.save(tradeRecord);
			// 解冻金额
			this.thawFreezeRecord(taskId, userId, task_type);
			// 保存转账记录
			TradeRecord tradeRecordOut = new TradeRecord();
			StringBuffer bufferOut = new StringBuffer();
			bufferOut.append("【"
					+ searchUser.getNickname()
					+ "】完成了贵公司的【"
					+ messionName
					+ "】任务,贵公司支付任务金【"
					+ CommonUtil.getTaxMoney(Double.valueOf(messionPrice),
							"finish")
					+ "】元(平台收取佣金【"
					+ (messionPrice - CommonUtil.getTaxMoney(
							Double.valueOf(messionPrice), "finish")) + "】元)。");

			tradeRecordOut.setTrade_content(bufferOut.toString());
			tradeRecordOut.setTrade_type("5");
			tradeRecordOut.setTrade_price(CommonUtil.getTaxMoney(
					Double.valueOf(messionPrice), "finish"));
			tradeRecordOut.setUser_id(userId);
			tradeRecordOut.setTrade_time(new Date());
			tradeRecordService.save(tradeRecordOut);

			// 归还用户保证金
			TradeRecord tradeRecordU = new TradeRecord();
			StringBuffer bufferu = new StringBuffer();
			bufferu.append("完成了【" + freezeRecordVo.getCompany_name() + "】的【"
					+ messionName + "】任务,解冻了您的保证金【" + messionPrice * 0.25
					+ "】元。");
			tradeRecordU.setTrade_content(bufferu.toString());
			tradeRecordU.setTrade_type("6");
			tradeRecordU.setTrade_price(Double.valueOf(messionPrice * 0.25));
			tradeRecordU.setUser_id(messionUserId);
			tradeRecordU.setTrade_time(new Date());
			tradeRecordService.save(tradeRecordU);
			// 解冻金额
			this.thawFreezeRecord(taskId, messionUserId, task_type);
			// 转账（出金放）
			WalletInfo walletInfoOut = new WalletInfo();
			walletInfoOut.setUser_id(userId);
			walletInfoOut.setRemaining_sum(Double.valueOf(messionPrice));
			walletInfoService.accessUserMoney(walletInfoOut, "get");
			// 转账
			WalletInfo walletInfo2 = new WalletInfo();
			walletInfo2.setUser_id(messionUserId);
			walletInfo2.setRemaining_sum(CommonUtil.getTaxMoney(
					Double.valueOf(messionPrice), "finish"));
			walletInfoService.accessUserMoney(walletInfo2, "save");

			// 保证金解冻
			// WalletInfo walletInfou = new WalletInfo();
			// walletInfou.setUser_id(messionUserId);
			// walletInfou.setRemaining_sum(Double.valueOf(messionPrice *
			// 0.25));
			// walletInfoService.accessUserMoney(walletInfou, "save");

			// 保存平台记录

		}

		return "1";
	}

	@Override
	public Long checkFreezeRecordCount(FreezeRecord freezeRecord) {

		return (Long) freezeRecordDao.selectForObject("checkFreezeRecordCount",
				freezeRecord);
	}

	@Override
	public void thawReceiverFreezeRecord(FreezeRecord freezeRecord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("task_id", freezeRecord.getTask_id());
		map.put("user_id", freezeRecord.getUser_id());
		map.put("task_type", freezeRecord.getTask_type());

		freezeRecordDao.update("thawReceiverFreezeRecord", map);
	}

	@Override
	public List<Long> searchUserIdByTaskId(FreezeRecord freezeRecord) {

		return (List<Long>) freezeRecordDao.selectForList(
				"searchUserIdByTaskId", freezeRecord);
	}
}
