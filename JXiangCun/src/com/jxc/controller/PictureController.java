package com.jxc.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.jxc.entity.Merchant;
import com.jxc.entity.Picture;
import com.jxc.page.PicturePage;
import com.jxc.service.MerchantService;
import com.jxc.service.PictureService;

@Controller
@RequestMapping("/picture")
@Scope("prototype")
@SessionAttributes("picturePage")
public class PictureController {
	@Resource
	private PictureService service;
@Resource 
private MerchantService mservice;

	@RequestMapping("/pictureList.do")
	public String findAllAction(PicturePage page, ModelMap map) {
		Integer rows = service.findRows(page);// 获取数据表的数据行数
		page.setRows(rows);
		map.addAttribute("picturePage", page);
		List<Picture> pictures = service.findAllPictureByPage(page);// 分页查询品牌信息
		map.put("pictures", pictures);
		return "admin/picture";// 转发到品牌首页
	}

	@RequestMapping("/toAddPicture.do")
	public String toAddAction(ModelMap map) {
		List<Merchant> merchants=mservice.findAllMerchant();
		map.put("merchants", merchants);
		return "admin/picture_add";// 转发到品牌添加页面
	}

	@RequestMapping("/addPicture.do")
	public String addAction(Picture picture) {
		service.addPicture(picture);// 添加品牌信息
		return "redirect:../picture/pictureList.do";
	}

	@RequestMapping("/toUpdatePicture.do")
	public String toUpdateAction(ModelMap map, Integer id) {
		Picture picture= service.findPictureById(id); 
		List<Merchant> merchants=mservice.findAllMerchant();
		map.put("merchants", merchants);
		map.put("picture",picture);
		return "admin/picture_update"; 
	}

	@RequestMapping("/updatePicture.do")
	public String updateAction(Picture picture) {
		service.updatePicture(picture);// 修改品牌信息
		return "redirect:../picture/pictureList.do";// 重定向到品牌首页
	}

	@RequestMapping("/deletePicture.do")
	public String deleteAction(Integer id) {
		service.deletePictureById(id);// 根据id删除品牌
		return "redirect:../picture/pictureList.do";// 重定向到品牌首页
	}
}
