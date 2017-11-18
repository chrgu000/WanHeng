package com.kg.quartz;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.kg.dao.BabyDao;
import com.kg.dao.FootprintDao;
import com.kg.entity.Baby;
import com.kg.entity.Footprint;
import com.kg.util.BabyUtil;

@Controller
@Scope("prototype")
public class DS {
	@Resource
	private FootprintDao fdao;
	@Resource
	private BabyDao bdao;
	private static final Logger log = Logger.getLogger(DS.class);// 日志文件

	public void work() {
		try {
			
			List<Baby> babys1 = BabyUtil.getBabys(bdao.findAllBaby());
			for (Baby baby : babys1) {
				bdao.updateBaby(baby);
			}
			List<Baby> babys = bdao.findAllBabyIsStudy();
			for (Baby baby : babys) {
				Footprint footprint = new Footprint();
				footprint.setBaby_id(baby.getId());
				footprint.setTeacher_id(baby.getTeacher_id());
				footprint.setGarden_id(baby.getGarden_id());
				footprint.setDate(new java.sql.Date(System.currentTimeMillis())
						.toString());
				footprint.setIsShit("否");
				
				System.out.println("ok");
				footprint.setIsSiesta("是");
				footprint.setSiesta("正常");
				footprint.setState("0");
				fdao.addFootprint(footprint);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
