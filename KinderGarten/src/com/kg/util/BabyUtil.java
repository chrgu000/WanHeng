package com.kg.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;

import com.kg.entity.Baby;

public class BabyUtil {
	public static List<Baby> getBabys(List<Baby> babys) {
		 for (Baby baby : babys) {
			try {
				if(isOutEndDate(baby)){
					baby.setIsStudy("0");
				}else{
					baby.setIsStudy("1");
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return babys;
	}

	public  static boolean isOutEndDate(Baby baby) throws ParseException {
		String endDate=baby.getEndDate();
		if(endDate==null){
			return false;
		}else{
			Date date=Date.valueOf(endDate);
			if(date.after(new Date(System.currentTimeMillis()))){
				return false;
			}else{
				return true;
			}
		}
	}
}
