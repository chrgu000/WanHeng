package com.jxc.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.jxc.dao.TitleDao;
import com.jxc.entity.DatePrice;
import com.jxc.entity.Merchant;
import com.jxc.entity.Product;
import com.jxc.entity.Title;
import com.jxc.page.ProductPage;
import com.jxc.page.Select;
import com.jxc.service.DatePriceService;
import com.jxc.service.MerchantService;
import com.jxc.service.ProductService;
import com.jxc.util.PriceUtil;

@Controller
@RequestMapping("/product")
@Scope("prototype")
@SessionAttributes("productPage")
public class ProductControlle {
	@Resource
	private ProductService service;
	@Resource
	private MerchantService mservice;
    @Resource
    private DatePriceService dservice;
    @Resource
    private TitleDao dao;
	@RequestMapping("/productList.do")
	public String findAllAction(ProductPage page, ModelMap map,
			HttpSession session, Select select) {
		List<Integer> merchantIds = new ArrayList<Integer>();
		List<Merchant> merchants = mservice.findMerchantsBySelect(select);
		for (Merchant merchant : merchants) {
			merchantIds.add(merchant.getId());
		}
		page.setMerchantIds(merchantIds);
		Integer rows = service.findRows(page);// 获取数据表的数据行数
		page.setRows(rows);
		map.addAttribute("productPage", page);
		List<Product> products = service.findAllProductByPage(page);// 分页查询品牌信息
		for (Product product : products) {
			List<DatePrice> datePrices=product.getDatePrices();
			List<DatePrice> dateprices=PriceUtil.getDatePrice(datePrices, product.getPointDate());
			product.setDatePrices(dateprices);
		}
		map.put("merchants", merchants);
		map.put("products", products);
		return "admin/product"; 
	}

	@RequestMapping("/toAddProduct.do")
	public String toAddAction(ModelMap map, HttpSession session, Select select) {
		List<Merchant> merchants = mservice.findMerchantsBySelect(select);
		map.put("merchants", merchants);
		return "admin/product_add";// 转发到品牌添加页面
	}

	@RequestMapping("/toUpdateProduct.do")
	public String toUpdateAction(ModelMap map, Integer id, HttpSession session,
			Select select) {
		Product product = service.findProductById(id);
		List<DatePrice> dateprices=PriceUtil.getDatePrice(product.getDatePrices(),product.getPointDate());
		product.setDatePrices(dateprices);
		List<Merchant> merchants = mservice.findMerchantsBySelect(select);
		map.put("titles", product.getMerchant().getTitles());
		map.put("merchants", merchants);
		map.put("product", product);
		return "admin/product_update";
	}
	@RequestMapping("/addProduct.do")
	public String addAction(Product product, HttpSession session) {
		System.out.println(product.getOriginal_price()+":"+product.getFavourable_price()+":"+product.getDay1_favourable_price()+":"+product.getDay1_original_price()+":"+product.getDay5_favourable_price()+":"+product.getDay5_original_price());
		product.setIsUse("0");
		service.addProduct(product);
		Integer product_id=product.getId();
		List<Integer> titleIds=product.getTitleIds();
		for (Integer id : titleIds) {
			Map<String,Integer> map=new HashMap<String,Integer>();
			map.put("product_id", product_id);
			map.put("title_id", id);
			service.addProductTitle(map);
		}
		if(product.getIsFree().equals("0")){
			Date pointDate = product.getPointDate();
			String date = pointDate.toString();
			Integer month = Integer.parseInt(date.substring(5, 7));
			List<Map<String, Object>> maps = PriceUtil.getPrice(month - 1);
			Integer firstweek = Integer.parseInt( maps.get(0).get(
					"firstweek").toString());
			for (int i = 1; i <= Integer.parseInt(maps.get(0).get(
					"lastday").toString()); i++, firstweek++) {
				DatePrice dp=new DatePrice();
				String firstdate = maps.get(0).get("firstdate").toString() + i;
				Date d = Date.valueOf(firstdate);
				dp.setDate(d);
				dp.setProduct_id(product_id);
				if (d.equals(pointDate)) {
					dp.setOriginal_price(product.getOriginal_price());
					dp.setFavourable_price(product.getFavourable_price());
					dservice.addDatePrice(dp);
					continue;
				}
				if (firstweek % 7 >= 1 && firstweek % 7 <= 4) {
					dp.setOriginal_price(product.getDay1_original_price());
					dp.setFavourable_price(product.getDay1_favourable_price());
					dservice.addDatePrice(dp);
				} else {
					dp.setOriginal_price(product.getDay5_original_price());
					dp.setFavourable_price(product.getDay5_favourable_price());
					dservice.addDatePrice(dp);
				}
			}
			Integer firstweek1 = Integer.parseInt(maps.get(1).get(
					"firstweek").toString());
			for (int j = 1; j <= Integer.parseInt(maps.get(1).get(
					"lastday").toString()); j++, firstweek1++) {
				DatePrice dp=new DatePrice();
				String firstdate = maps.get(1).get("firstdate").toString()+ j;
				Date d = Date.valueOf(firstdate);
				dp.setDate(d);
				dp.setProduct_id(product_id);
				if (d.equals(pointDate)) {
					dp.setOriginal_price(product.getOriginal_price());
					dp.setFavourable_price(product.getFavourable_price());
					dservice.addDatePrice(dp);
					continue;
				}
				if (firstweek1 % 7 >= 1 && firstweek1 % 7 <= 4) {
					dp.setOriginal_price(product.getDay1_original_price());
					dp.setFavourable_price(product.getDay1_favourable_price());
					dservice.addDatePrice(dp);
				} else {
					dp.setOriginal_price(product.getDay5_original_price());
					dp.setFavourable_price(product.getDay5_favourable_price());
					dservice.addDatePrice(dp);
				}
			}
			Integer firstweek2= Integer.parseInt(maps.get(2).get(
					"firstweek").toString());
			for (int k= 1; k <= Integer.parseInt(maps.get(2).get(
					"lastday").toString()); k++, firstweek2++) {
				DatePrice dp=new DatePrice();
				String firstdate = maps.get(2).get("firstdate").toString()+ k;
				Date d = Date.valueOf(firstdate);
				dp.setDate(d);
				dp.setProduct_id(product_id);
				if (d.equals(pointDate)) {
					dp.setOriginal_price(product.getOriginal_price());
					dp.setFavourable_price(product.getFavourable_price());
					dservice.addDatePrice(dp);
					continue;
				}
				if (firstweek2 % 7 >= 1 && firstweek2 % 7 <= 4) {
					dp.setOriginal_price(product.getDay1_original_price());
					dp.setFavourable_price(product.getDay1_favourable_price());
					dservice.addDatePrice(dp);
				} else {
					dp.setOriginal_price(product.getDay5_original_price());
					dp.setFavourable_price(product.getDay5_favourable_price());
					dservice.addDatePrice(dp);
				}
			}
		}
		return "redirect:../product/productList.do";
	}

	@RequestMapping("/updateProduct.do")
	public String updateAction(Product product) throws ParseException {
		Integer product_id=product.getId();
		service.deleteProductTitle(product_id);
		List<Integer> titleIds=product.getTitleIds();
		for (Integer id : titleIds) {
			Map<String,Integer> map=new HashMap<String,Integer>();
			map.put("product_id", product_id);
			map.put("title_id", id);
			service.addProductTitle(map);
		}
		dservice.deleteDatePrice(product_id);
		String isFree = product.getIsFree();
		if (isFree != null && isFree.equals("1")) {
			product.setPointDate(null);
		} else if (isFree != null && isFree.equals("0")) {
			product.setEndDate(null);
			product.setFree_num(null);
			Date pointDate = product.getPointDate();
			String date = pointDate.toString();
			Integer month = Integer.parseInt(date.substring(5, 7));
			List<Map<String, Object>> maps = PriceUtil.getPrice(month - 1);
			Integer firstweek = Integer.parseInt(maps.get(0).get(
					"firstweek").toString());
			for (int i = 1; i <= Integer.parseInt(maps.get(0).get(
					"lastday").toString()); i++, firstweek++) {
				DatePrice dp=new DatePrice();
				String firstdate =maps.get(0).get("firstdate").toString() + i;
				Date d = Date.valueOf(firstdate);
				dp.setDate(d);
				dp.setProduct_id(product_id);
				if (d.equals(pointDate)) {
					dp.setOriginal_price(product.getOriginal_price());
					dp.setFavourable_price(product.getFavourable_price());
					dservice.addDatePrice(dp);
					continue;
				}
				if (firstweek % 7 >= 1 && firstweek % 7 <= 4) {
					dp.setOriginal_price(product.getDay1_original_price());
					dp.setFavourable_price(product.getDay1_favourable_price());
					dservice.addDatePrice(dp);
				} else {
					dp.setOriginal_price(product.getDay5_original_price());
					dp.setFavourable_price(product.getDay5_favourable_price());
					dservice.addDatePrice(dp);
				}
			}
			Integer firstweek1 = Integer.parseInt(maps.get(1).get(
					"firstweek").toString());
			for (int j = 1; j <= Integer.parseInt(maps.get(1).get(
					"lastday").toString()); j++, firstweek1++) {
				DatePrice dp=new DatePrice();
				String firstdate = maps.get(1).get("firstdate").toString() + j;
				Date d = Date.valueOf(firstdate);
				dp.setDate(d);
				dp.setProduct_id(product_id);
				if (d.equals(pointDate)) {
					dp.setOriginal_price(product.getOriginal_price());
					dp.setFavourable_price(product.getFavourable_price());
					dservice.addDatePrice(dp);
					continue;
				}
				if (firstweek1 % 7 >= 1 && firstweek1 % 7 <= 4) {
					dp.setOriginal_price(product.getDay1_original_price());
					dp.setFavourable_price(product.getDay1_favourable_price());
					dservice.addDatePrice(dp);
				} else {
					dp.setOriginal_price(product.getDay5_original_price());
					dp.setFavourable_price(product.getDay5_favourable_price());
					dservice.addDatePrice(dp);
				}
			}
			Integer firstweek2= Integer.parseInt(maps.get(2).get(
					"firstweek").toString());
			for (int k= 1; k <= Integer.parseInt(maps.get(2).get(
					"lastday").toString()); k++, firstweek2++) {
				DatePrice dp=new DatePrice();
				String firstdate = maps.get(2).get("firstdate").toString()+ k;
				Date d = Date.valueOf(firstdate);
				dp.setDate(d);
				dp.setProduct_id(product_id);
				if (d.equals(pointDate)) {
					dp.setOriginal_price(product.getOriginal_price());
					dp.setFavourable_price(product.getFavourable_price());
					dservice.addDatePrice(dp);
					continue;
				}
				if (firstweek2 % 7 >= 1 && firstweek2 % 7 <= 4) {
					dp.setOriginal_price(product.getDay1_original_price());
					dp.setFavourable_price(product.getDay1_favourable_price());
					dservice.addDatePrice(dp);
				} else {
					dp.setOriginal_price(product.getDay5_original_price());
					dp.setFavourable_price(product.getDay5_favourable_price());
					dservice.addDatePrice(dp);
				}
			}
		}
		service.updateProduct(product);
		return "redirect:../product/productList.do";// 重定向到品牌首页
	}

	@RequestMapping("/deleteProduct.do")
	public String deleteAction(Integer id) {
		service.deleteProductById(id);// 根据id删除品牌
		service.deleteProductTitle(id);
		dservice.deleteDatePrice(id);
		return "redirect:../product/productList.do";// 重定向到品牌首页
	}

	@RequestMapping("/getProductsByMerchantId.do")
	public String getProductsAction(ModelMap map, Integer merchant_id) {
		List<Product> products = service.findProductsByMerchantId(merchant_id);
		Merchant merchant = mservice.findMerchantById(merchant_id);
		map.put("products", products);
		map.put("merchant", merchant);
		return "mobile/merchant";
	}
	@RequestMapping("/getPrice.json")
	public void getPriceAction(String beginTime,String endTime,Integer product_id,HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("utf-8");
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("beginTime",beginTime);
		map.put("endTime", endTime);
		map.put("product_id", product_id);
		double total_price=service.getPrice(map);
		response.getWriter().print(total_price);
	}
	@RequestMapping("/getTitles.json")
	public void getTitlesAction(Integer merchant_id,HttpServletResponse response) throws IOException{
		System.out.println(merchant_id);
		List<Title> titles=mservice.findTitlesByMerchantId(merchant_id);
		response.setCharacterEncoding("utf-8");
		JSONArray arr=JSONArray.fromObject(titles);
		response.getWriter().print(arr);
	}
}
