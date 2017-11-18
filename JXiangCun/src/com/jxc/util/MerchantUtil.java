package com.jxc.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.jxc.entity.Merchant;
import com.jxc.entity.Point;

public class MerchantUtil {
	public static void main(String[] args) {
		List<Merchant> merchants = new ArrayList<Merchant>();
		Merchant merchant1 = new Merchant();
		merchant1.setLatitude(120.285875);
		merchant1.setLongitude(30.25689);
		Merchant merchant2 = new Merchant();
		merchant2.setLatitude(120.587899);
		merchant2.setLongitude(30.214589);
		Merchant merchant3 = new Merchant();
		merchant3.setLatitude(120.2587);
		merchant3.setLongitude(30.257874556);
		merchants.add(merchant1);
		merchants.add(merchant2);
		merchants.add(merchant3);
		Point point = new Point();
		point.setLatitude(120.214585);
		point.setLongitude(30.21317);
		 List<Merchant> merchans=getMerchantsByPointAndDistanceId(merchants, point,null);
		 for (Merchant merchant : merchans) {
		 System.out.println(merchant.getDistance());
		 }
	}
	@SuppressWarnings("unchecked")
	public static List<Merchant> getMerchantsByPointAndDistanceId(
			List<Merchant> merchants, Point point, Integer distanceId) {
		for (Merchant merchant : merchants) {
			merchant.setDistance(DistanceUtil.distance(merchant.getLongitude(),
					merchant.getLatitude(), point.getLongitude(),
					point.getLatitude()));
		}
		Collections.sort(merchants, new ComparatorMerchant());
		List<Merchant> merchans=new ArrayList<Merchant>();
	 
		if(distanceId!=null&&distanceId==1){
			for (Merchant merchant : merchants) {
				if(merchant.getDistance()<=100){
					merchans.add(merchant);
				}
			}
			return merchans;
		}else if(distanceId!=null&&distanceId==2){
			for (Merchant merchant : merchants) {
				if(merchant.getDistance()>100&&merchant.getDistance()<=200){
					merchans.add(merchant);
				}
			}
			return merchans;
		}else if(distanceId!=null&&distanceId==3){
			for (Merchant merchant : merchants) {
				if(merchant.getDistance()>200&&merchant.getDistance()<=300){
					merchans.add(merchant);
				}
			}
			return merchans;
		}else if(distanceId!=null&&distanceId==4){
			for (Merchant merchant : merchants) {
				if(merchant.getDistance()>300){
					merchans.add(merchant);
				}
			}
			return merchans;
		}
		return merchants;
}
}

class ComparatorMerchant implements Comparator {
	public int compare(Object arg0, Object arg1) {
		Merchant m1 = (Merchant) arg0;
		Merchant m2 = (Merchant) arg1;
		double flag = m1.getDistance() - m2.getDistance();
		if (flag > 0) {
			return 1;
		} else if (flag < 0) {
			return -1;
		} else {
			return 0;
		}
	}
}