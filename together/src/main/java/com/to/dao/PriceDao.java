package com.to.dao;

import com.to.entity.Price;

import java.util.List;

/**
 * @Author :yangjun on 2017/3/28 0028.
 */
public interface PriceDao extends BaseDao<Price>{
    List<Price> getAllPrice();
}
