package com.dq.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dq.entity.Att;
import com.dq.entity.Attr;
import com.dq.entity.Picture;
import com.dq.entity.Proatt;
import com.dq.entity.Proattr;
import com.dq.entity.Product;
import com.dq.entity.Skuinfo;
import com.dq.service.AttService;
import com.dq.service.AttrService;
import com.dq.service.PictureService;
import com.dq.service.ProattService;
import com.dq.service.ProattrService;
import com.dq.service.ProductService;
import com.dq.service.SkuinfoService;
import com.dq.util.ResponseUtil;

@Controller
@RequestMapping(value = "/skuc")
@Scope("prototype")
public class SkuController {
	private static byte[] lock = new byte[0]; // 特殊的instance变量
	private static final Logger log = Logger.getLogger(SkuController.class);// 日志文件
	@Autowired
	private AttService attService;
	@Autowired
	private AttrService attrService;
	@Autowired
	private ProattService proattService;
	@Autowired
	private ProattrService proattrService;
	@Autowired
	private SkuinfoService skuinfoService;
	@Autowired
	private ProductService productService;
	@Autowired
	private PictureService pictureService;

	/**
	 * 
	 * @param a
	 *            获取信息的方式，a=m为编辑产品
	 * @param id
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getInfo", method = { RequestMethod.POST, RequestMethod.GET })
	public void getInfo(String a, String id, String callback, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> resmap = new HashMap<String, Object>();
		List<Att> attr_list = Collections.synchronizedList(new ArrayList<Att>());
		List<Attr> attrs = null;
		List<Proatt> pro_attr_list = Collections.synchronizedList(new ArrayList<Proatt>());
		List<Proattr> pro_attrs = Collections.synchronizedList(new ArrayList<Proattr>());
		List<Skuinfo> sku_list = Collections.synchronizedList(new ArrayList<Skuinfo>());
		List<Proatt> att_list = Collections.synchronizedList(new ArrayList<Proatt>());
		List<Att> attr_list2 = Collections.synchronizedList(new ArrayList<Att>());
		Product product = null;
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[] { "addtime", "delflag", "attr_vs", "check" });
		try {
			map.put("delflag", 1);
			if (a != null && "m".equals(a) && id != null && !"".equals(id.trim())) {
				product = productService.getById(Integer.valueOf(id));
				product.setId(Integer.parseInt(id));
				if (product == null) {
					attr_list = attService.getByPage(map);
					for (Att att : attr_list) {
						map.remove("att_id");
						map.put("att_id", att.getAtt_id());
						attrs = Collections.synchronizedList(new ArrayList<Attr>());
						attrs = attrService.getByPage(map);
						att.setAttr_vs(attrs);
					}
					resmap.put("hasError", true);
					resmap.put("errType", "nopro");
					resmap.put("attr_list", attr_list);
					resmap.put("sku_list", sku_list);
					resmap.put("att_list", att_list);
					resmap.put("error", "没有产品信息");
					ResponseUtil.write(response, callback, resmap, null);
					return;
				}
				// 全部skuinfo
				sku_list = skuinfoService.getByPage(map);
				// 全部的规格
				map.put("pid", id);
				attr_list = attService.getByPro(map);
				// 获取该产品选择的规格
				// attr_list2 = proattrService.getByAtt(map);
				pro_attr_list = proattService.getByPage(map);
				for (Att att : attr_list) {
					map.remove("att_id");
					map.put("att_id", att.getAtt_id());
					attrs = Collections.synchronizedList(new ArrayList<Attr>());
					// 全部的规格子属性
					attrs = attrService.getByPro(map);
					// 获取该产品选择的规格子属性
					pro_attrs = proattrService.getByPage(map);
					/*
					 * for (Proatt proatt : pro_attr_list) { // 判断产品是否拥有该规格 if
					 * (att.getAtt_id() == proatt.getAtt_id()) {
					 * att.setCheck(true); } }
					 */
					for (Attr attr : attrs) {
						for (Proattr proattr : pro_attrs) {
							// 判断产品是否拥有该规格子属性
							if (attr.getAttr_id() == proattr.getAttr_id()) {
								attr.setCheck(true);
							}
						}
					}
					att.setAttr_vs(attrs);
				}
				for (Proatt proatt : pro_attr_list) {
					// System.out.println(proatt.getAtt().getAttr_title());
					attr_list2.add(proatt.getAtt());
				}
				resmap.put("product", product);
				resmap.put("attr_list", attr_list);
				resmap.put("sku_list", sku_list);
				resmap.put("att_list", JSONArray.fromObject(JSONArray.fromObject(attr_list2, jsonConfig).toString()
						.replace("attr_title", "text").replace("att_id", "dataid")));
			} else {
				attr_list = attService.getByPage(map);
				for (Att att : attr_list) {
					map.remove("att_id");
					map.put("att_id", att.getAtt_id());
					attrs = Collections.synchronizedList(new ArrayList<Attr>());
					attrs = attrService.getByPage(map);
					att.setAttr_vs(attrs);
				}
				resmap.put("attr_list", attr_list);
				resmap.put("sku_list", sku_list);
				resmap.put("att_list", att_list);
			}
			resmap.put("hasError", false);
			ResponseUtil.write(response, callback, resmap, null);
		} catch (Exception e) {
			e.printStackTrace();
			resmap.put("hasError", true);
			resmap.put("error", "error");
			ResponseUtil.write(response, callback, resmap, null);
			throw new RuntimeException();
		} finally {
			resmap = null;
		}
	}

	@RequestMapping(value = "/addAttr", method = RequestMethod.POST)
	public void addAttr(String dataid, String title, String v, String callback, HttpServletResponse response)
			throws Exception {
		attrService.addAttr(dataid, title, v, callback, response);
	}

	@RequestMapping(value = "/delAtt", method = RequestMethod.POST)
	public void delAtt(String a, String dataid, String datatitle, String callback, HttpServletResponse response)
			throws Exception {
		attService.delAtt(a, dataid, datatitle, callback, response);
	}

	@RequestMapping(value = "/delAttr", method = RequestMethod.POST)
	public void delAttr(String a, String dataid, String datatitle, String callback, HttpServletResponse response)
			throws Exception {
		attrService.delAttr(a, dataid, datatitle, callback, response);
	}

	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public void addProduct(String a, String id, Product product, String skuinfo, String proatt, String proattr,
			String callback, HttpServletResponse response) throws Exception {
		productService.addProduct(a, id, product, skuinfo, proatt, proattr, callback, response);
	}

	/**
	 * 前端用户浏览商品详情
	 * 
	 * @param id
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getProduct", method = RequestMethod.POST)
	public void getProduct(String id, String callback, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> resmap = new HashMap<String, Object>();
		Product product = null;
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[] { "addtime", "delflag", "check", "sell", "picid" });
		List<Att> atts = Collections.synchronizedList(new ArrayList<Att>());
		List<Attr> attrs = null;
		List<Skuinfo> skuinfos = Collections.synchronizedList(new ArrayList<Skuinfo>());
		List<Picture> pictures = null;
		try {
			product = productService.getById(Integer.valueOf(id));
//			if (product == null || product.getDelflag() != 1) {
//				resmap.put("hasError", true);
//				resmap.put("errType", "nopro");
//				resmap.put("error", "商品不存在");
//				ResponseUtil.write(response, callback, resmap, null);
//				return;
//			}
			map.put("pid", product.getId());
			map.put("type", 2);
//			pictures = pictureService.getByPage(map);
			map.remove("type");

			map.put("delflag", 1);
			atts = attService.getAtt(map);
			for (Att att : atts) {
				map.remove("att_id");
				map.put("att_id", att.getAtt_id());
				attrs = attrService.getAttr(map);
				att.setAttr_vs(attrs);
			}

			map.put("stockh", "y");
			skuinfos = skuinfoService.getByPage(map);

			resmap.put("hasError", false);
			resmap.put("banners", pictures);
			resmap.put("attr_list", atts);
			resmap.put("sku_list", skuinfos);
			ResponseUtil.write(response, callback, resmap, jsonConfig);
		} catch (Exception e) {
			e.printStackTrace();
			resmap.put("hasError", true);
			resmap.put("errType", "error");
			resmap.put("error", "系统异常");
			ResponseUtil.write(response, callback, resmap, null);
			throw new RuntimeException();
		} finally {
			map = null;
			resmap = null;
			product = null;
			jsonConfig = null;
			atts = null;
			attrs = null;
			skuinfos = null;
			pictures = null;
		}
	}
}
