package com.cgwas.imgInfo.service.api;

import java.util.List;
import java.io.Serializable;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.imgInfo.entity.ImgInfo;
import com.cgwas.imgInfo.entity.ImgInfoVo;

public interface IImgInfoService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(ImgInfo imgInfo);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(ImgInfo imgInfo);

	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(ImgInfo imgInfo);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(ImgInfo imgInfo);

	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(ImgInfo imgInfo);

	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(ImgInfo imgInfo);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract ImgInfoVo load(ImgInfo imgInfo);

	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract ImgInfoVo selectForObject(ImgInfo imgInfo);

	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<ImgInfoVo> selectForList(ImgInfo imgInfo);

	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, ImgInfo imgInfo);

	/**
	 * 根据类型和id获取图片
	 * 
	 * @param imgInfo
	 * @return
	 */
	public abstract List<ImgInfo> getImgInfoById(ImgInfo imgInfo, String ids);

	/**
	 * 批量添加图片
	 * 
	 * @param imgPath
	 * @return
	 */
	public abstract Serializable saveBatch(List<String> imgPath,ImgInfo imgInfo);

}