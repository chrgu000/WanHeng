package com.dq.dao;

import java.util.List;

import com.dq.entity.ShopCar;

public interface ShopCarDao extends BaseDao<ShopCar> {
	List<ShopCar> getShopCars(Integer user_id);
}
