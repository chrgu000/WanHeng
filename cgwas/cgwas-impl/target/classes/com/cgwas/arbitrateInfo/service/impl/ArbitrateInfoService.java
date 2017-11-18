package com.cgwas.arbitrateInfo.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.animationlighttask.entity.AnimationLightTaskVo;
import com.cgwas.animationlighttask.service.api.IAnimationLightTaskService;
import com.cgwas.arbitrateInfo.dao.api.IArbitrateInfoDao;
import com.cgwas.arbitrateInfo.entity.ArbitrateInfo;
import com.cgwas.arbitrateInfo.entity.ArbitrateInfoDetail;
import com.cgwas.arbitrateInfo.entity.ArbitrateInfoHead;
import com.cgwas.arbitrateInfo.entity.ArbitrateInfoVo;
import com.cgwas.arbitrateInfo.service.api.IArbitrateInfoService;
import com.cgwas.arbitrateUserInfo.entity.ArbitrateImage;
import com.cgwas.arbitrateUserInfo.entity.ArbitrateUserInfo;
import com.cgwas.arbitrateUserInfo.service.api.IArbitrateUserInfoService;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.imgInfo.entity.ImgInfo;
import com.cgwas.imgInfo.service.api.IImgInfoService;
import com.cgwas.modeltask.entity.ModelTaskVo;
import com.cgwas.modeltask.service.api.IModelTaskService;
import com.cgwas.userCompany.entity.UserCompany;
import com.cgwas.userCompany.service.api.IUserCompanyService;

@Service
public class ArbitrateInfoService implements IArbitrateInfoService {
	private IArbitrateInfoDao arbitrateInfoDao;
	@Autowired
	private IModelTaskService modelTaskService;
	@Autowired
	private IAnimationLightTaskService animationLightTaskService;
	@Autowired
	private IArbitrateUserInfoService arbitrateUserInfoService = null;
	@Autowired
	private IUserCompanyService userCompanyService;
	@Autowired
	private IImgInfoService imgInfoService;
	public Serializable save(ArbitrateInfo arbitrateInfo) {
		return arbitrateInfoDao.save(arbitrateInfo);
	}

	public void delete(ArbitrateInfo arbitrateInfo) {
		arbitrateInfoDao.delete(arbitrateInfo);
	}

	public void deleteByExample(ArbitrateInfo arbitrateInfo) {
		arbitrateInfoDao.deleteByExample(arbitrateInfo);
	}

	public void update(ArbitrateInfo arbitrateInfo) {
		arbitrateInfoDao.update(arbitrateInfo);
	}

	public void updateIgnoreNull(ArbitrateInfo arbitrateInfo) {
		arbitrateInfoDao.updateIgnoreNull(arbitrateInfo);
	}

	public void updateByExample(ArbitrateInfo arbitrateInfo) {
		arbitrateInfoDao.update("updateByExampleArbitrateInfo", arbitrateInfo);
	}

	public ArbitrateInfoVo load(ArbitrateInfo arbitrateInfo) {
		return (ArbitrateInfoVo) arbitrateInfoDao.reload(arbitrateInfo);
	}

	public ArbitrateInfoVo selectForObject(ArbitrateInfo arbitrateInfo) {
		return (ArbitrateInfoVo) arbitrateInfoDao.selectForObject(
				"selectArbitrateInfo", arbitrateInfo);
	}

	public List<ArbitrateInfoVo> selectForList(ArbitrateInfo arbitrateInfo) {
		return (List<ArbitrateInfoVo>) arbitrateInfoDao.selectForList(
				"selectArbitrateInfo", arbitrateInfo);
	}

	public Page page(Page page, ArbitrateInfo arbitrateInfo) {
		return arbitrateInfoDao.page(page, arbitrateInfo);
	}

	@Autowired
	public void setIArbitrateInfoDao(
			@Qualifier("arbitrateInfoDao") IArbitrateInfoDao arbitrateInfoDao) {
		this.arbitrateInfoDao = arbitrateInfoDao;
	}

	@Override
	public Long getArbitrateInfoCount(String arbitrate_state) {
		// 输入查询参数
		ArbitrateInfo arbitrateInfo = new ArbitrateInfo();
		arbitrateInfo.setArbitrate_state(arbitrate_state);
		return (Long) arbitrateInfoDao.selectForObject("getArbitrateInfoCount",
				arbitrateInfo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ArbitrateInfoDetail> getArbitrateInfoList(
			ArbitrateInfoDetail arbitrateInfoDetail, Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", page);
		map.put("arbitrateInfoDetail", arbitrateInfoDetail);
		return (List<ArbitrateInfoDetail>) arbitrateInfoDao.selectForList(
				"getArbitrateInfoList", map);
	}
	
	

	@Override
	public Long getArbitrateInfoListCount(
			ArbitrateInfoDetail arbitrateInfoDetail) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("arbitrateInfoDetail", arbitrateInfoDetail);
		return (Long) arbitrateInfoDao.selectForObject(
				"getArbitrateInfoListCount", map);
	}

	@Override
	public void finishArbitrate(String flag, long id) {
		ArbitrateInfo arbitrateInfo = new ArbitrateInfo();
		ArbitrateUserInfo arbitrateUserInfo=new ArbitrateUserInfo();
		arbitrateUserInfo.setArbitrate_id(id);
		
		
		arbitrateInfo.setId(id);
		arbitrateInfo.setAudit_time(new Date());
		if ("left".equals(flag)) { // 原告胜利
			arbitrateUserInfo.setUser_type("A");  //把原告设为Y
			arbitrateUserInfo.setIs_pass('Y');
			arbitrateUserInfoService.updateIsPass(arbitrateUserInfo);
			
			arbitrateUserInfo.setUser_type("D");  //把被告设为N
			arbitrateUserInfo.setIs_pass('N');
			arbitrateUserInfoService.updateIsPass(arbitrateUserInfo);
			arbitrateInfo.setArbitrate_state("结束");
			
			
		} else { // 被告胜利
			arbitrateUserInfo.setUser_type("A");  //把原告设为N
			arbitrateUserInfo.setIs_pass('N');
			arbitrateUserInfoService.updateIsPass(arbitrateUserInfo);
			
			arbitrateUserInfo.setUser_type("D");  //把被告设为Y
			arbitrateUserInfo.setIs_pass('Y');
			arbitrateUserInfoService.updateIsPass(arbitrateUserInfo);
			arbitrateInfo.setArbitrate_state("结束");
		}
		arbitrateInfoDao.update("finishArbitrate", arbitrateInfo);
	}

	@Override
	public ArbitrateInfoHead getArbitrateInfo(Long id, String task_type) {
		if ("建模".equals(task_type)) {
			return (ArbitrateInfoHead) arbitrateInfoDao.selectForObject(
					"getArbitrateInfoM", id);
		} else {
			return (ArbitrateInfoHead) arbitrateInfoDao.selectForObject(
					"getArbitrateInfoA", id);
		}
	}
	
	/**
	 * 获取仲裁图片
	 * @param abitrateUser_id
	 * @return
	 */
	@Override
	public List<ArbitrateImage> getArbitrateImgByAUserId(Long abitrateUser_id) {
		return (List<ArbitrateImage>) arbitrateInfoDao.selectForList(
				"selectArbitrateImage", abitrateUser_id);
		
	}
	/**
	 * 创建仲裁信息
	 * @param arbitrateInfo
	 * @param userId
	 * @param sponsor 
	 * @param arbitrate_content
	 * @param request
	 * @return
	 */
	@Override
	public String createdArbitrateInfo(ArbitrateInfo arbitrateInfo,Long userId,String sponsor,String arbitrate_content,List<String> imgPath, HttpServletRequest request){
		
		HttpSession session = request.getSession(); 
		
		Long company_id = 0L;
		Long user_id = 0L;
		// 获取任务信息
				if ("1".equals(arbitrateInfo.getTask_type())||"建模".equals(arbitrateInfo.getTask_type())) { // 建模
					// 获取任务
					ModelTaskVo modelTaskVo = new ModelTaskVo();
					modelTaskVo.setId(arbitrateInfo.getTask_id());
					modelTaskVo = modelTaskService.getModelTaskById(modelTaskVo);
					// 获取公司及人员id
					user_id = modelTaskVo.getMaker().getId();
					company_id = modelTaskVo.getCompany_id();
				} else {// 灯光
					// 获取任务
					AnimationLightTaskVo aTask = new AnimationLightTaskVo();
					aTask.setId(arbitrateInfo.getTask_id());
					aTask=animationLightTaskService.getAnimationLightTaskById(aTask);
					// 获取公司及人员id
					user_id = aTask.getMaker().getId();
					company_id = aTask.getCompany_id();
				}

				String adminFlag = (String) session.getAttribute("adminFlag");

				ArbitrateUserInfo arbitrateUserInfo = new ArbitrateUserInfo(); // 发起方信息

				if ("ADMIN".equals(adminFlag)) { // 管理员可选择发起方
					if ("company".equals(sponsor)) {
						return "-2"; // 管理员创建仲裁
					} else {
						return "-2"; // 管理员创建仲裁
					}

				} else if ("COMPANY".equals(adminFlag)) { // 公司发起
					UserCompany us = userCompanyService.companygetUser(company_id);

					if (!userId.equals(us.getUse_id())) {
						return "RY0075";// 非法人
					}
					arbitrateUserInfo.setUser_id(user_id);
					arbitrateUserInfo.setCompany_id(company_id);
					arbitrateUserInfo.setUser_type("2");
					arbitrateUserInfo.setIs_pass('N');// 默认通过
				} else { // 人员发起
					if (!userId.equals(user_id)) {
						return "RY0076";// 非法人
					}
					arbitrateUserInfo.setUser_id(user_id);
					arbitrateUserInfo.setCompany_id(company_id);
					arbitrateUserInfo.setUser_type("1");
					arbitrateUserInfo.setIs_pass('N');// 默认通过
					
				}

				// 初始化信息
				arbitrateInfo.setArbitrate_state("1");
				arbitrateInfo.setCreate_time(new Date());
				this.save(arbitrateInfo);
				arbitrateUserInfo.setArbitrate_id(arbitrateInfo.getId());
				arbitrateUserInfo.setArbitrate_content(arbitrate_content);
				arbitrateUserInfoService.save(arbitrateUserInfo);
				
				
				ImgInfo imgInfo = new ImgInfo();
				imgInfo.setImg_title("任务仲裁图片");
				imgInfo.setImg_type("8");
				imgInfo.setCreate_time(new Date());
				imgInfo.setFor_id(arbitrateUserInfo.getId());
				imgInfoService.saveBatch(imgPath, imgInfo);
				
				
				return "1"; // 正常
		
	}

}
