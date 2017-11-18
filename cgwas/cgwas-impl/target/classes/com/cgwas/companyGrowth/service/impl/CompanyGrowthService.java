package com.cgwas.companyGrowth.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.companyGrowth.dao.api.ICompanyGrowthDao;
import com.cgwas.companyGrowth.entity.CompanyGrowth;
import com.cgwas.companyGrowth.entity.CompanyGrowthVo;
import com.cgwas.companyGrowth.service.api.ICompanyGrowthService;
import com.cgwas.rewardsRecord.entity.RewardsRecord;
import com.cgwas.rewardsRecord.service.api.IRewardsRecordService;

@Service
public class CompanyGrowthService implements ICompanyGrowthService {
	private ICompanyGrowthDao companyGrowthDao;
	private IRewardsRecordService rewardsRecordService;

	public Serializable save(CompanyGrowth companyGrowth) {
		return companyGrowthDao.save(companyGrowth);
	}

	public void delete(CompanyGrowth companyGrowth) {
		companyGrowthDao.delete(companyGrowth);
	}

	public void deleteByExample(CompanyGrowth companyGrowth) {
		companyGrowthDao.deleteByExample(companyGrowth);
	}

	public void update(CompanyGrowth companyGrowth) {
		companyGrowthDao.update(companyGrowth);
	}

	public void updateIgnoreNull(CompanyGrowth companyGrowth) {
		companyGrowthDao.updateIgnoreNull(companyGrowth);
	}

	public void updateByExample(CompanyGrowth companyGrowth) {
		companyGrowthDao.update("updateByExampleCompanyGrowth", companyGrowth);
	}

	public CompanyGrowthVo load(CompanyGrowth companyGrowth) {
		return (CompanyGrowthVo) companyGrowthDao.reload(companyGrowth);
	}

	public CompanyGrowthVo selectForObject(CompanyGrowth companyGrowth) {
		return (CompanyGrowthVo) companyGrowthDao.selectForObject(
				"selectCompanyGrowth", companyGrowth);
	}

	public List<CompanyGrowthVo> selectForList(CompanyGrowth companyGrowth) {
		return (List<CompanyGrowthVo>) companyGrowthDao.selectForList(
				"selectCompanyGrowth", companyGrowth);
	}

	public Page page(Page page, CompanyGrowth companyGrowth) {
		return companyGrowthDao.page(page, companyGrowth);
	}

	@Autowired
	public void setICompanyGrowthDao(
			@Qualifier("companyGrowthDao") ICompanyGrowthDao companyGrowthDao) {
		this.companyGrowthDao = companyGrowthDao;
	}

	@Override
	public void reduceOrAddGrowthCompany(String action, Integer growthNum,
			Long companyId, RewardsRecord rewardsRecord) {
		CompanyGrowth companyGrowth = new CompanyGrowth();
		companyGrowth=this.getCompanyGrowthByCompanyId(companyId); // 查询公司积分状况
		
		Integer changeGrowthNum = growthNum; // 改变的积分数量
		// 更改积分赋值
		
		companyGrowth.setCompany_id(companyId);
		// 记录赋值
		rewardsRecord.setCompany_id(companyId);
		rewardsRecord.setFlat(growthNum);
		rewardsRecord.setTime(new Date());
		// 威望值惩罚
		Integer wwz = rewardsRecord.getPrestige();
		/*if(wwz==null||wwz==0){
			wwz = 0;
		}
		*/
		
		if ("reduce".equals(action)) {// 根据操作改变积分
			
			if( companyGrowth.getFlat()-changeGrowthNum<=0){
				changeGrowthNum = 0;
			}else {
				changeGrowthNum = -changeGrowthNum;
			}
			
			if( Integer.parseInt(companyGrowth.getContribute())-wwz<=0){

				wwz = 0;
			} else {
				wwz = -wwz;
			}

			companyGrowth.setFlat(changeGrowthNum);
			companyGrowth.setContribute( String.valueOf(wwz));
			rewardsRecord.setType("罚");
			rewardsRecord.setContribute(-wwz);
		} else if ("clear".equals(action)) {
			companyGrowthDao.update("clearGrowthCompany", companyGrowth);
			rewardsRecord.setType("清");
			rewardsRecord.setFlat(0);
			// 记录
			rewardsRecordService.save(rewardsRecord);
			return;
		} else {
			companyGrowth.setFlat(changeGrowthNum);
			companyGrowth.setContribute(String.valueOf(wwz));
			rewardsRecord.setType("奖");
			rewardsRecord.setContribute(wwz);
		}
		// 记录
		rewardsRecordService.save(rewardsRecord);
		// 加减积分
		companyGrowthDao.update("reduceOrAddGrowthCompany", companyGrowth);
	}

	@Autowired(required = true)
	public void setRewardsRecordService(
			@Qualifier("rewardsRecordService") IRewardsRecordService rewardsRecordService) {
		this.rewardsRecordService = rewardsRecordService;
	}

	@Override
	public CompanyGrowth getCompanyGrowthByCompanyId(Long company_id) {
		CompanyGrowth out =  (CompanyGrowth) companyGrowthDao.selectForObject(
				"getCompanyGrowthByCompanyId", company_id);
		if(out==null){ // 如果没有则创建一条
			out = new CompanyGrowth();
			out.setCompany_id(company_id);
			out.setFlat(0);
			out.setContribute("0");
			companyGrowthDao.save(out);
		}
		return out;
	}
}
