package com.jxc.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.jxc.entity.Mark;
import com.jxc.entity.Merchant;
import com.jxc.entity.Picture;
import com.jxc.entity.Point;
import com.jxc.entity.SightSpot;
import com.jxc.page.MerchantPage;
import com.jxc.service.MarkService;
import com.jxc.service.MerchantService;
import com.jxc.service.PictureService;
import com.jxc.service.SightSpotService;
import com.jxc.util.MerchantUtil;

@Controller
@RequestMapping("/find")
@Scope("prototype")
public class FindController {
	@Resource
private MarkService mservice;
	@Resource
	private SightSpotService service;
	@Resource
	private MerchantService merservice;
	@Resource
	private PictureService pservice;
@RequestMapping("/find.do")
public String findAction(ModelMap map,HttpSession session,MerchantPage page){
	List<Mark> marks=mservice.findFiveMarks();
	List<Merchant> merchants=merservice.getMerchantsByPage(page);
	List<Merchant> merchants1=MerchantUtil.getMerchantsByPointAndDistanceId(merchants, (Point)session.getAttribute("point"), null);
	List<SightSpot> sightSpots=service.findAllSightSpot();
	List<Picture> pictures=pservice.findAllPicture(3);
	map.put("pictures", pictures);
	map.put("marks", marks);
	map.put("merchants", merchants1);
	map.put("sightSpots", sightSpots);
	return "mobile/find";
}
}
