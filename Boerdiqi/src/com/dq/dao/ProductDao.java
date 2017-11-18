package com.dq.dao;

import java.util.Map;

import com.dq.entity.Product;

public interface ProductDao extends BaseDao<Product>{
	void updateByIds(Map<String, String[]> map);
}
