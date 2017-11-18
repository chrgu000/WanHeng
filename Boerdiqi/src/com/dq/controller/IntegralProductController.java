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

import com.dq.entity.IntegralProduct;
import com.dq.page.ProductPage;
import com.dq.service.IntegralProductService;
import com.dq.util.ResponseUtil;

@Controller
@RequestMapping("/integral_product")
@Scope("prototype")
public class IntegralProductController {
	private static final Logger log = Logger.getLogger(IntegralProductController.class);
	@Resource
	private IntegralProductService service;
	@RequestMapping("/loadProduct.do")
	public void loadIntegralProduct(Integer pageIndex, Integer limit,
			ProductPage page, HttpServletResponse response,Integer s) throws Exception {
		page.setCurrentPage(pageIndex + 1);
		page.setPageSize(limit);
		List<IntegralProduct> integralProducts = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Integer rows = service.getRows(page);
			page.setRows(rows);
			integralProducts = service.getByPage(page);
			map.put("rows", integralProducts);
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
			integralProducts = null;
			map = null;
			object = null;
		}
	}

	@RequestMapping("/updateProduct.do")
	public void updateIntegralProduct(IntegralProduct integralProduct,HttpServletResponse response)
			throws Exception {
		service.update(integralProduct, response);
	}

	@RequestMapping("/addProduct.do")
	public void addIntegralProduct(IntegralProduct integralProduct, String  types, HttpServletResponse response)
			throws Exception {
		service.add(integralProduct, response);
	}

	@RequestMapping("/deleteProduct.do")
	public void deleteIntegralProduct(String ids, HttpServletResponse response)
			throws Exception {
		service.delete(ids, response);
	}

@RequestMapping("/getProductById.do")
public void getIntegralProductById(HttpServletResponse response,Integer integralProduct_id) throws Exception{
	IntegralProduct integralProduct=service.getById(integralProduct_id);
	JSONObject obj=JSONObject.fromObject(integralProduct);
	ResponseUtil.write(response, obj);
}
	 
}
