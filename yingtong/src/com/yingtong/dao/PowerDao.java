package com.yingtong.dao;

import java.util.List;

import com.yingtong.entity.Power;

public interface PowerDao {
	List<Power> findAllPower();

	boolean addPower(Power power);

	boolean deletePower(Integer id);

	Power findPowerById(Integer id);
}
