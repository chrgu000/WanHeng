package com.to.dao;

import com.to.entity.Orientation;

import java.util.List;

/**
 * @Author :yangjun on 2017/3/28 0028.
 */
public interface OrientationDao extends BaseDao<Orientation>{
    List<Orientation> getAllOrientation();
}
