package com.dq.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dq.dao.ProductDao;
import com.dq.entity.Product;
import com.dq.entity.util.ReturnInfo;
import com.dq.page.ProductPage;
import com.dq.service.ProductService;
import com.dq.util.ResponseUtil;

@Controller
@RequestMapping("/product")
@Scope("prototype")
public class ProductController {
	private static final Logger log = Logger.getLogger(ProductController.class);
	@Resource
	private ProductService service;
	@Resource
	private ProductDao dao;

	@RequestMapping("/loadProduct.do")
	public void loadProduct(Integer pageIndex, Integer limit, Integer b_type_id,Integer s_type_id,Integer st_id,
			ProductPage page, HttpServletResponse response,Integer s) throws Exception {
		page.setCurrentPage(pageIndex + 1);
		page.setPageSize(limit);
		if(b_type_id!=null){
			page.setBig_type_id(b_type_id);
		}
		if(s_type_id!=null){
			page.setSmall_type_id(s_type_id);
		}
		if(st_id!=null){
			page.setStandard_id(st_id);
		}
		List<Product> products = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Integer rows = service.getRows(page);
			page.setRows(rows);
			products = service.getByPage(page);
			map.put("rows", products);
			map.put("results", rows);
			map.put("hasError", false);
			map.put("error", "");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("hasError", true);
			map.put("error", "error");
			map.put("rows", "");
			map.put("results", 0);
		} finally {
			JSONObject object = JSONObject.fromObject(map);
			ResponseUtil.write(response, object);
			products = null;
			map = null;
			object = null;
		}
	}

	@RequestMapping("/updateProduct.do")
	public void updateProduct(Product product,HttpServletResponse response)
			throws Exception {
		service.update(product, response);
	}

	@RequestMapping("/addProduct.do")
	public void addProduct(Product product, String  types, HttpServletResponse response)
			throws Exception {
		service.add(product, response);
	}

	@RequestMapping("/deleteProduct.do")
	public void deleteProduct(String ids, HttpServletResponse response)
			throws Exception {
		service.delete(ids, response);
	}

@RequestMapping("/getProductById.do")
public void getProductById(HttpServletResponse response,Integer product_id) throws Exception{
	Product product=service.getById(product_id);
	JSONObject obj=JSONObject.fromObject(product);
	ResponseUtil.write(response, obj);
}
	 
}
