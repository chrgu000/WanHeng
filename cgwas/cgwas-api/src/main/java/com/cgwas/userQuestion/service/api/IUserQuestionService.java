package com.cgwas.userQuestion.service.api;



import java.util.List;
import java.io.Serializable;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.user.entity.User;
import com.cgwas.userQuestion.entity.UserQuestion;
import com.cgwas.userQuestion.entity.UserQuestionVo;


public interface IUserQuestionService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(UserQuestion userQuestion);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(UserQuestion userQuestion);
	
	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(UserQuestion userQuestion);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(UserQuestion userQuestion);
	
	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(UserQuestion userQuestion);
	
	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(UserQuestion userQuestion);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract UserQuestionVo load(UserQuestion userQuestion);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract UserQuestionVo selectForObject(UserQuestion userQuestion);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<UserQuestionVo> selectForList(UserQuestion userQuestion);
	
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, UserQuestion userQuestion);
	
	/**
	 * 根据用户id更改密保问题
	 * @param userQuestion
	 */
	public abstract void updateUserQuestionByUserId(UserQuestion userQuestion);
	/**
	 * 匹配密保问题
	 * @param user
	 * @param answers
	 * @return
	 */
	public abstract boolean checkQuestion(User user,String answers);
	/**
	 * 得到密保问题
	 * @param user
	 * @return
	 */
	public abstract UserQuestion getUserQuestionByUser(User user);

}