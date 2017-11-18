package com.dq.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dq.dao.ShopCarDao;
import com.dq.entity.ShopCar;
import com.dq.service.ShopCarService;

@Service("shopcarService")
@Transactional
public class ShopCarServiceImpl implements ShopCarService {
	@Resource
	private ShopCarDao dao;

	public List<ShopCar> getShopCars(Integer user_id) {
		return dao.getShopCars(user_id);
	}
}
