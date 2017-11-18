package com.kg.quartz;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.kg.dao.BabyDao;
import com.kg.dao.FootprintDao;
import com.kg.entity.Baby;
import com.kg.entity.Footprint;

@Controller
@Scope("prototype")
public class DSTwo {
	@Resource
	private FootprintDao fdao;
	@Resource
	private BabyDao bdao;
	private static final Logger log = Logger.getLogger(DS.class);// 日志文件
    public void work() throws ParseException{
    	List<Baby> babys = bdao.findAllBabyIsStudy();
    	for (Baby baby : babys) {
			int num = fdao.getCount(baby.getId());
			if (num >0) {
				String endDate = baby.getEndDate();
				SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
				Date date1 = s.parse(endDate);
				Calendar cal = Calendar.getInstance();
				cal.setTime(date1);
				cal.add(Calendar.DATE, num);
				Date date2 = cal.getTime();
				int days=getBetweenDays(date2);
				if(days==6||days==7){
					cal.add(Calendar.DATE, 2);
				}
				endDate = s.format(cal.getTime());
				baby.setEndDate(endDate);
				bdao.updateBaby(baby);
				Footprint footprint = new Footprint();
				footprint.setBaby_id(baby.getId());
				footprint.setState("1");
				fdao.updateByBabyId(footprint);
			}
		}
    }

	private int getBetweenDays(Date date) {
		SimpleDateFormat s = new SimpleDateFormat("E");
	    return changeNum(s.format(date));
	}

	private  int changeNum(String days) {
		if (days.equals("星期一")) {
			return 1;
		} else if (days.equals("星期二")) {
			return 2;
		} else if (days.equals("星期三")) {
			return 3;
		} else if (days.equals("星期四")) {
			return 4;
		} else if (days.equals("星期五")) {
			return 5;
		} else if (days.equals("星期六")) {
			return 6;
		} else {
			return 7;
		}
	}
}
