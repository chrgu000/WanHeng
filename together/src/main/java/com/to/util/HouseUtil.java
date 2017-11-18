package com.to.util;

import com.to.entity.House;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HouseUtil {
	public static List<House> getHousesByLocation(List<House> houses, double latitude, double longitude) {
		for (House house : houses) {
			house.setDistance(DistanceUtil.distance(house.getLongitude(),
					house.getLatitude(), longitude, latitude));
		}
		Collections.sort(houses, new ComparatorHouse());
		return houses;
	}
}
class ComparatorHouse implements Comparator<House> {
	public int compare(House h1, House h2) {
		double flag = h1.getDistance() - h2.getDistance();
		if (flag > 0) {
			return 1;
		} else if (flag < 0) {
			return -1;
		} else {
			return 0;
		}
	}
}