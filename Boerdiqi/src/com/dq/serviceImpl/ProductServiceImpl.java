package com.dq.serviceImpl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dq.dao.AttDao;
import com.dq.dao.AttrDao;
import com.dq.dao.ProattDao;
import com.dq.dao.ProattrDao;
import com.dq.dao.ProductDao;
import com.dq.dao.SkuinfoDao;
import com.dq.entity.Att;
import com.dq.entity.Attr;
import com.dq.entity.Proatt;
import com.dq.entity.Proattr;
import com.dq.entity.Product;
import com.dq.entity.Skuinfo;
import com.dq.entity.util.ReturnInfo;
import com.dq.page.Page;
import com.dq.service.ProductService;
import com.dq.util.DateUtil;
import com.dq.util.ResponseUtil;
import com.dq.util.SkuUtil;

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {
	@Resource
	private ProductDao dao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ProattDao proattDao;
	@Autowired
	private ProattrDao proattrDao;
	@Autowired
	private AttDao attDao;
	@Autowired
	private AttrDao attrDao;
	@Autowired
	private SkuinfoDao skuinfoDao;
	public List<Product> getByPage(Page page) {
		return dao.getAllByPage(page);
	}

	public Integer getRows(Page page) {
		return dao.getRows(page);
	}

	public void update(Product product, HttpServletResponse response)
			throws Exception {
		ReturnInfo returnInfo = new ReturnInfo();
		JSONObject object = null;
		try {
			dao.update(product);
			returnInfo.setHasError(false);
			returnInfo.setErrInfo("");
		} catch (Exception e) {
			e.printStackTrace();
			returnInfo.setHasError(true);
			returnInfo.setErrInfo("系统异常");
		} finally {
			object = JSONObject.fromObject(returnInfo);
			ResponseUtil.write(response, object);
			returnInfo = null;
			object = null;
		}
	}

	public void add(Product product, HttpServletResponse response)
			throws Exception {
		ReturnInfo returnInfo = new ReturnInfo();
		JSONObject object = null;
		try {
			product.setNums(0);
			product.setDelflag(1);
			product.setJoin_time(new Timestamp(System.currentTimeMillis()));
			dao.save(product);
			returnInfo.setHasError(false);
			returnInfo.setErrInfo("");
		} catch (Exception e) {
			e.printStackTrace();
			returnInfo.setHasError(true);
			returnInfo.setErrInfo("系统异常");
		} finally {
			object = JSONObject.fromObject(returnInfo);
			ResponseUtil.write(response, object);
			returnInfo = null;
			object = null;
		}
	}

	public void delete(String ids, HttpServletResponse response)
			throws Exception {
		ReturnInfo returnInfo = new ReturnInfo();
		JSONObject object = null;
		try {
			Map<String, String[]> map = new HashMap<String, String[]>();
			String[] Ids = ids.split(",");
			map.put("ids", Ids);
			dao.updateByIds(map);
			returnInfo.setHasError(false);
			returnInfo.setErrInfo("");
		} catch (Exception e) {
			returnInfo.setHasError(true);
			returnInfo.setErrInfo("该信息有级联数据,不能删除");
		} finally {
			object = JSONObject.fromObject(returnInfo);
			ResponseUtil.write(response, object);
			returnInfo = null;
			object = null;
		}
	}

	public Product getById(Integer id) {
		return dao.getById(id);
	}
	public void addProduct(String a, String id, Product product, String skuinfo, String proatt, String proattr,
			String callback, HttpServletResponse response) throws Exception {
		System.out.println("ok");
	 		Map<String, Object> map = new HashMap<String, Object>();
		ReturnInfo returnError = new ReturnInfo();
		JSONObject jsonObject = null;
		JSONArray skus = null, proatts = null, proattrs = null;
		JSONObject jsonStrObject = null;
		Proatt proattBean = null;
		Proattr proattrBean = null;
		Att att = null;
		Attr attr = null;
		Skuinfo skuinfoBean = null;
		skus = JSONArray.fromObject(skuinfo);
		List<Skuinfo> skuinfos=(List<Skuinfo>) JSONArray.toCollection(skus, Skuinfo.class);
		Skuinfo sku=SkuUtil.getSku(skuinfos);
		product.setO_price(sku.getOriginal_price());
		product.setF_price(sku.getFavourable_price());
		try {
			String time = DateUtil.getCurrentDateStr();
			Integer pid;
			System.out.println("id:"+product.getId()+":"+id);
			if ("m".equals(a)) {
				productDao.update(product);
				pid = product.getId();
			} else {
				product.setDelflag(1);
				product.setJoin_time(new Timestamp(System.currentTimeMillis()));
				productDao.save(product);
				pid = product.getId();
			}
			proatts = JSONArray.fromObject(proatt);
			proattrs = JSONArray.fromObject(proattr);
			proattDao.delByPid(pid);
			for (int i = 0; i < proatts.size(); i++) {
				jsonStrObject = JSONObject.fromObject(proatts.get(i));
				String dataid = jsonStrObject.getString("dataid");
				String datatitle = jsonStrObject.getString("datatitle");
				proattBean = new Proatt();
				proattBean.setDelflag(1);
				if (dataid.startsWith("new")) {
					map.remove("attr_title");
					map.put("attr_title", datatitle);
					att = attDao.findByMap(map);
					proattBean.setAtt_id(att.getAtt_id());
				} else {
					proattBean.setAtt_id(Integer.parseInt(dataid));
				}
				proattBean.setPid(pid);
				proattBean.setSort(1 + i);
				proattBean.setAddtime(time);
				proattDao.add(proattBean);
			}
			proattrDao.delByPid(pid);
			for (int i = 0; i < proattrs.size(); i++) {
				jsonStrObject = JSONObject.fromObject(proattrs.get(i));
				String dataid = jsonStrObject.getString("dataid");
				attr = attrDao.findById(Integer.valueOf(dataid));
				proattrBean = new Proattr();
				proattrBean.setPid(pid);
				proattrBean.setAtt_id(attr.getAtt_id());
				proattrBean.setAttr_id(Integer.parseInt(dataid));
				proattrBean.setDelflag(1);
				proattrBean.setAddtime(time);
				proattrDao.add(proattrBean);
			}
			map.clear();
			map.put("delflag", 2);
			map.put("pid", pid);
			skuinfoDao.delByPid(map);
			map.remove("delflag");
			for (int i = 0; i < skus.size(); i++) {
				map.remove("attr_ids");
				jsonStrObject = JSONObject.fromObject(skus.get(i));
				String dataid = jsonStrObject.getString("dataid");
				String datatitle = jsonStrObject.getString("datatitle");
				Integer number = jsonStrObject.getInt("number");
				Integer limit_number = jsonStrObject.getInt("limit_number");
				Double favourable_price = jsonStrObject.getDouble("favourable_price");
				Double original_price = jsonStrObject.getDouble("original_price");
				Integer integral=jsonStrObject.getInt("integral");
				String code = jsonStrObject.getString("code");
				map.put("attr_ids", dataid);
				skuinfoBean = skuinfoDao.findByMap(map);
				if (skuinfoBean != null) {
					skuinfoBean.setAttr_titles(datatitle);
					skuinfoBean.setNumber(number);
					skuinfoBean.setLimit_number(limit_number);
					skuinfoBean.setFavourable_price(favourable_price);
					skuinfoBean.setOriginal_price(original_price);
					skuinfoBean.setIntegral(integral);
					skuinfoBean.setCode(code);
					skuinfoBean.setDelflag(1);
					skuinfoDao.upd(skuinfoBean);
				} else {
					skuinfoBean = new Skuinfo();
					skuinfoBean.setAttr_ids(dataid);
					skuinfoBean.setAttr_titles(datatitle);
					skuinfoBean.setPid(pid);
					skuinfoBean.setNumber(number);
					skuinfoBean.setLimit_number(limit_number);
					skuinfoBean.setFavourable_price(favourable_price);
					skuinfoBean.setOriginal_price(original_price);
					skuinfoBean.setIntegral(integral);
					skuinfoBean.setCode(code);
					skuinfoBean.setDelflag(1);
					skuinfoBean.setAddtime(time);
					skuinfoDao.add(skuinfoBean);
				}
			}
			returnError.setHasError(false);
			returnError.setErrType("success");
			returnError.setErrInfo("保存成功");
			jsonObject = JSONObject.fromObject(returnError);
			ResponseUtil.write(response, callback, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
			returnError.setHasError(true);
			returnError.setErrType("error");
			returnError.setErrInfo("系统异常");
			jsonObject = JSONObject.fromObject(returnError);
			ResponseUtil.write(response, callback, jsonObject);
//			throw new RuntimeException();
		} finally {
			map = null;
			returnError = null;
			jsonObject = null;
			skus = null;
			proatts = null;
			proattrs = null;
			jsonStrObject = null;
		}
	}
}
