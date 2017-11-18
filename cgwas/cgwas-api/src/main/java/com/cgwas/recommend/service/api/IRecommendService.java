package com.cgwas.recommend.service.api;

import java.util.List;
import java.io.Serializable;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.recommend.entity.CompanyRecommend;
import com.cgwas.recommend.entity.Recommend;
import com.cgwas.recommend.entity.RecommendVo;
import com.cgwas.recommend.entity.UserRecommend;

public interface IRecommendService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(Recommend recommend);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(Recommend recommend);

	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(Recommend recommend);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(Recommend recommend);

	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(Recommend recommend);

	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(Recommend recommend);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract RecommendVo load(Recommend recommend);

	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract RecommendVo selectForObject(Recommend recommend);

	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<RecommendVo> selectForList(Recommend recommend);

	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, Recommend recommend);

	/**
	 * 更改用户推荐状态
	 * 
	 * @param recommend
	 */
	public abstract void updateUserAndCompanyRecommend(Recommend recommend);

	/**
	 * 得到修改用户推荐列表
	 * 
	 * @param page
	 * @return
	 */
	public abstract List<UserRecommend> getUserListForRecommend(UserRecommend userRecommend,Page page);
	/**
	 * 得到修改公司推荐列表
	 * 
	 * @param page
	 * @return
	 */
	public abstract List<CompanyRecommend> getCompanyListForRecommend(Page page,CompanyRecommend companyRecommend);
	/**
	 * 得到修改用户推荐列表（数量）
	 * @param recommend
	 * @return
	 */
	public abstract Long getUserListForRecommendCount(UserRecommend recommend);
	/**
	 * 得到修改公司推荐列表(数量)
	 * @param companyRecommend
	 * @return
	 */
	public abstract Long getCompanyListForRecommendCount(CompanyRecommend companyRecommend);
	
	/**
	 * 根据id得到推荐详情
	 * @param id
	 * @param type
	 * @return
	 */
	public abstract Recommend getRecommendById(Recommend recommend);

}