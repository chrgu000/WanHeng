package com.jxc.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.jxc.entity.Merchant;
import com.jxc.entity.Point;
import com.jxc.entity.SightSpot;

public class SightSpotUtil {
	@SuppressWarnings("unchecked")
	public static SightSpot getSightSpotByPoint(
			List<SightSpot> sightSpots, Point point) {
		for (SightSpot sightSpot : sightSpots) {
			sightSpot.setDistance(DistanceUtil.distance(sightSpot.getLongitude(),
					sightSpot.getLatitude(), point.getLongitude(),
					point.getLatitude()));
		}
		Collections.sort(sightSpots, new ComparatorSightSpot());
		return sightSpots.get(0);
}
}

class ComparatorSightSpot implements Comparator {
	public int compare(Object arg0, Object arg1) {
		SightSpot s1 = (SightSpot) arg0;
		SightSpot s2 = (SightSpot) arg1;
		double flag = s1.getDistance() - s2.getDistance();
		if (flag > 0) {
			return 1;
		} else if (flag < 0) {
			return -1;
		} else {
			return 0;
		}
	}
}
