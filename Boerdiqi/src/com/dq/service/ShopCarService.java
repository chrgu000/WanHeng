package com.dq.service;

import java.util.List;

import com.dq.entity.ShopCar;

public interface ShopCarService {
	List<ShopCar> getShopCars(Integer user_id);
}
