package com.dq.dao;

import java.util.List;

import com.dq.entity.BigType;

public interface BigTypeDao extends BaseDao<BigType> {
	List<BigType> getAllBigType();
}
