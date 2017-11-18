package com.jxc.serviceImpl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jxc.dao.ProductDao;
import com.jxc.entity.Product;
import com.jxc.page.Page;
import com.jxc.service.ProductService;

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {
	@Resource
	private ProductDao dao;

	public List<Product> findAllProduct() {

		return dao.findAllProduct();
	}

	public List<Product> findAllProductByPage(Page page) {

		return dao.findAllProductByPage(page);
	}

	public Integer findRows(Page page) {

		return dao.findRows(page);
	}

	public Product findProductById(Integer id) {

		return dao.findProductById(id);
	}

	public boolean addProduct(Product product) {
		return dao.addProduct(product);
	}

	public boolean updateProduct(Product product) {
		return dao.updateProduct(product);
	}

	public boolean deleteProductById(Integer id) {

		return dao.deleteProductById(id);
	}

	public List<Product> findProductsByMerchantId(Integer merchant_id) {
		return dao.findProductsByMerchantId(merchant_id);
	}

	public double getPrice(Map<String, Object> map) {
		return dao.getPrice(map);
	}

	public boolean addProductTitle(Map<String, Integer> map) {
		return dao.addProductTitle(map);
	}

	public boolean deleteProductTitle(Integer productId) {
		return dao.deleteProductTitle(productId);
	}

}
