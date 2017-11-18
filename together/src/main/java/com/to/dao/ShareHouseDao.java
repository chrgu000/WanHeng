package com.to.dao;

import com.to.entity.ShareHouse;

import java.util.List;

/**
 * @Author :yangjun on 2017/3/28 0028.
 */
public interface ShareHouseDao extends BaseDao<ShareHouse>{
    List<ShareHouse> getAllShareHouse();
}
