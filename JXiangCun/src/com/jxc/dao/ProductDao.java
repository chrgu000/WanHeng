package com.jxc.dao;

import java.util.List;
import java.util.Map;

import com.jxc.entity.Product;
import com.jxc.page.Page;


public interface ProductDao {
	List<Product> findAllProduct();

	List<Product> findAllProductByPage(Page page);

	Integer findRows(Page page);
	
	List<Product> findProductsByMerchantId(Integer merchant_id);

	Product findProductById(Integer id);
	
	boolean addProductTitle(Map<String,Integer> map);
	
	boolean deleteProductTitle(Integer product_id);
	
	boolean addProduct(Product product);

	boolean updateProduct(Product product);

	boolean deleteProductById(Integer id);
	
	double getPrice(Map<String,Object> map);
}
