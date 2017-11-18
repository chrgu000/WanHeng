package com.to.dao;

import com.to.entity.PayWay;

import java.util.List;

/**
 * @Author :yangjun on 2017/3/28 0028.
 */
public interface PayWayDao extends BaseDao<PayWay> {
    List<PayWay> getAllPayWay();
}
