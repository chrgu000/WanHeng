package com.jxc.dao;

import java.util.List;

import com.jxc.entity.Power;

public interface PowerDao {
	List<Power> findAllPower();

	boolean addPower(Power power);

	boolean deletePower(Integer id);

	Power findPowerById(Integer id);
}
