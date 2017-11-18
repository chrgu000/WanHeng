package com.cgwas.arbitrateInfo.service.api;

import java.util.List;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import com.cgwas.arbitrateInfo.entity.ArbitrateInfo;
import com.cgwas.arbitrateInfo.entity.ArbitrateInfoDetail;
import com.cgwas.arbitrateInfo.entity.ArbitrateInfoHead;
import com.cgwas.arbitrateInfo.entity.ArbitrateInfoVo;
import com.cgwas.arbitrateUserInfo.entity.ArbitrateImage;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.imgInfo.entity.ImgInfo;

public interface IArbitrateInfoService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(ArbitrateInfo arbitrateInfo);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(ArbitrateInfo arbitrateInfo);

	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(ArbitrateInfo arbitrateInfo);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(ArbitrateInfo arbitrateInfo);

	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(ArbitrateInfo arbitrateInfo);

	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(ArbitrateInfo arbitrateInfo);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract ArbitrateInfoVo load(ArbitrateInfo arbitrateInfo);

	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract ArbitrateInfoVo selectForObject(ArbitrateInfo arbitrateInfo);

	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<ArbitrateInfoVo> selectForList(
			ArbitrateInfo arbitrateInfo);

	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, ArbitrateInfo arbitrateInfo);

	/**
	 * 获取仲裁总数
	 * 
	 * @param arbitrate_state
	 * @return
	 */
	public abstract Long getArbitrateInfoCount(String arbitrate_state);

	/**
	 * 条件搜索仲裁内容
	 * 
	 * @param arbitrateInfoDetail
	 * @param page
	 * @return
	 */
	public abstract List<ArbitrateInfoDetail> getArbitrateInfoList(
			ArbitrateInfoDetail arbitrateInfoDetail, Page page);
	
	/**
	 * 条件搜索仲裁内容(数量)
	 * @param arbitrateInfoDetail
	 * @return
	 */
	public abstract Long getArbitrateInfoListCount(ArbitrateInfoDetail arbitrateInfoDetail);
	
	/**
	 * 判决仲裁
	 * @param flag
	 */
	public abstract void finishArbitrate(String flag,long id);
	/**
	 * 获取仲裁详情
	 * @param id
	 * @return
	 */
	public abstract ArbitrateInfoHead  getArbitrateInfo(Long id,String task_type);
	/**
	 * 创建仲裁信息（业务层）
	 * @param arbitrateInfo
	 * @param userId
	 * @param sponsor
	 * @param arbitrate_content
	 * @param request
	 * @return
	 */
	public abstract String createdArbitrateInfo(ArbitrateInfo arbitrateInfo,Long userId,String sponsor,String arbitrate_content,List<String> imgInfo, HttpServletRequest request);
	
	/**
	 * 获取仲裁图片
	 * @param abitrateUser_id
	 * @return
	 */
	public abstract List<ArbitrateImage> getArbitrateImgByAUserId(Long abitrateUser_id);

}