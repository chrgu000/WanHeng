package com.jxc.service;

import java.util.List;

import com.jxc.entity.Power;

public interface PowerService {
	List<Power> findAllPower();

	boolean addPower(Power power);

	boolean deletePower(Integer id);

	Power findPowerById(Integer id);
}
