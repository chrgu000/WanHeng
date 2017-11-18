package com.cgwas.userEvaluation.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.userEvaluation.dao.api.IUserEvaluationDao;
import com.cgwas.userEvaluation.entity.UserEvaluation;
import com.cgwas.userEvaluation.entity.UserEvaluationVo;
import com.cgwas.userEvaluation.service.api.IUserEvaluationService;

@Service
public class UserEvaluationService implements IUserEvaluationService {
	private IUserEvaluationDao userEvaluationDao;

	public Serializable save(UserEvaluation userEvaluation) {
		return userEvaluationDao.save(userEvaluation);
	}

	public void delete(UserEvaluation userEvaluation) {
		userEvaluationDao.delete(userEvaluation);
	}

	public void deleteByExample(UserEvaluation userEvaluation) {
		userEvaluationDao.deleteByExample(userEvaluation);
	}

	public void update(UserEvaluation userEvaluation) {
		userEvaluationDao.update(userEvaluation);
	}

	public void updateIgnoreNull(UserEvaluation userEvaluation) {
		userEvaluationDao.updateIgnoreNull(userEvaluation);
	}

	public void updateByExample(UserEvaluation userEvaluation) {
		userEvaluationDao.update("updateByExampleUserEvaluation",
				userEvaluation);
	}

	public UserEvaluationVo load(UserEvaluation userEvaluation) {
		return (UserEvaluationVo) userEvaluationDao.reload(userEvaluation);
	}

	public UserEvaluationVo selectForObject(UserEvaluation userEvaluation) {
		return (UserEvaluationVo) userEvaluationDao.selectForObject(
				"selectUserEvaluation", userEvaluation);
	}

	public List<UserEvaluationVo> selectForList(UserEvaluation userEvaluation) {
		return (List<UserEvaluationVo>) userEvaluationDao.selectForList(
				"selectUserEvaluation", userEvaluation);
	}

	public Page page(Page page, UserEvaluation userEvaluation) {
		return userEvaluationDao.page(page, userEvaluation);
	}

	@Autowired
	public void setIUserEvaluationDao(
			@Qualifier("userEvaluationDao") IUserEvaluationDao userEvaluationDao) {
		this.userEvaluationDao = userEvaluationDao;
	}

	@Override
	public Long getUserEvaluationVListCount(UserEvaluation userEvaluation) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sEvaluation", userEvaluation);

		return (Long) userEvaluationDao.selectForObject(
				"getUserEvaluationVListCount", map);
	}

	@Override
	public List<UserEvaluationVo> getUserEvaluationVList(
			UserEvaluation userEvaluation, Page page, String allFlag) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sEvaluation", userEvaluation);
		map.put("page", page);

		if ("any".equals(allFlag)) {
			map.put("flag", "any");
		} else {
			map.put("flag", "other");
		}
		return (List<UserEvaluationVo>) userEvaluationDao.selectForList(
				"getUserEvaluationVList", map);
	}

	@Override
	public List<UserEvaluationVo> getUserTag(Long user_id) {
		return  (List<UserEvaluationVo>)userEvaluationDao.selectForList(
				"getUserTag", user_id);
	}

	@Override
	public Long getGCBEvaluationCount(Long user_id, Long evaluate_type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);
		map.put("evaluate_type", evaluate_type);
		return (Long) userEvaluationDao.selectForObject(
				"selectUserEvaluationCount", map);
	}

}
