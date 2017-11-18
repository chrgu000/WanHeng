package com.dq.service;

import javax.servlet.http.HttpServletResponse;

import com.dq.entity.Product;

public interface ProductService extends BaseService<Product> {
	void addProduct(String a, String id, Product product, String skuinfo, String proatt, String proattr,
			String callback, HttpServletResponse response) throws Exception;
}
