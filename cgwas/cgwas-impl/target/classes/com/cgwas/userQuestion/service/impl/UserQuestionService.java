package com.cgwas.userQuestion.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.utils.EncryptUtil;
import com.cgwas.user.entity.User;
import com.cgwas.user.service.api.IUserService;
import com.cgwas.userQuestion.dao.api.IUserQuestionDao;
import com.cgwas.userQuestion.entity.UserQuestion;
import com.cgwas.userQuestion.entity.UserQuestionVo;
import com.cgwas.userQuestion.service.api.IUserQuestionService;

@Service
public class UserQuestionService implements IUserQuestionService {
	private IUserQuestionDao userQuestionDao;
	private IUserService userService = null;

	public Serializable save(UserQuestion userQuestion) {
		return userQuestionDao.save(userQuestion);
	}

	public void delete(UserQuestion userQuestion) {
		userQuestionDao.delete(userQuestion);
	}

	public void deleteByExample(UserQuestion userQuestion) {
		userQuestionDao.deleteByExample(userQuestion);
	}

	public void update(UserQuestion userQuestion) {
		userQuestionDao.update(userQuestion);
	}

	public void updateIgnoreNull(UserQuestion userQuestion) {
		userQuestionDao.updateIgnoreNull(userQuestion);
	}

	public void updateByExample(UserQuestion userQuestion) {
		userQuestionDao.update("updateByExampleUserQuestion", userQuestion);
	}

	public UserQuestionVo load(UserQuestion userQuestion) {
		return (UserQuestionVo) userQuestionDao.reload(userQuestion);
	}

	public UserQuestionVo selectForObject(UserQuestion userQuestion) {
		return (UserQuestionVo) userQuestionDao.selectForObject(
				"selectUserQuestion", userQuestion);
	}

	public List<UserQuestionVo> selectForList(UserQuestion userQuestion) {
		return (List<UserQuestionVo>) userQuestionDao.selectForList(
				"selectUserQuestion", userQuestion);
	}

	public Page page(Page page, UserQuestion userQuestion) {
		return userQuestionDao.page(page, userQuestion);
	}

	@Autowired
	public void setIUserQuestionDao(
			@Qualifier("userQuestionDao") IUserQuestionDao userQuestionDao) {
		this.userQuestionDao = userQuestionDao;
	}

	@Override
	public void updateUserQuestionByUserId(UserQuestion userQuestion) {
		userQuestionDao.update("updateUserQuestionByUserId", userQuestion);
	}

	@Override
	public boolean checkQuestion(User user, String answers) {
		EncryptUtil encryptUtil = new EncryptUtil();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", user);

		String[] answerList = answers.split(",");
		int matchNum = 0;
		int answerNo = 1;
		for (String answer : answerList) { // 遍历查找匹配

			map.put("answerNo", answerNo);
			map.put("answer", encryptUtil.getEncryptMsg(answer)); // 加密答案

			if (userQuestionDao.selectForObject("checkQuestion", map) != null) { // 查询是否匹配
				matchNum++;
			}
			answerNo++;
		}
		if (matchNum >= 2) { // 匹配两条通过

			// 更改密码
			userService.updateUserByAccount(user);
			return true;

		}

		return false;
	}

	@Override
	public UserQuestion getUserQuestionByUser(User user) {

		return (UserQuestion) userQuestionDao.selectForObject("getUserQuestionByUser",
				user);
	}

	@Autowired(required = true)
	public void setUserService(
			@Qualifier("userService") IUserService userService) {
		this.userService = userService;
	}

}
