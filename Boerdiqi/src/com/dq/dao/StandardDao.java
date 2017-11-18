package com.dq.dao;

import java.util.List;

import com.dq.entity.Standard;

public interface StandardDao extends BaseDao<Standard> {
	List<Standard> getAllStandard();
}
