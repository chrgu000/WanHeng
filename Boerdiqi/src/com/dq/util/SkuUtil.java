package com.dq.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.dq.entity.Skuinfo;

public class SkuUtil {
public static Skuinfo getSku(List<Skuinfo> skuinfos){
	Collections.sort(skuinfos, new SkuOrder());
	return skuinfos.get(0);
}
}
class SkuOrder implements Comparator<Skuinfo>{
	public int compare(Skuinfo o1, Skuinfo o2) {
		return (int) (o1.getFavourable_price()-o2.getFavourable_price());
	}

	 
	
}