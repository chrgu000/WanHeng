package com.dq.service;

import java.util.List;

import com.dq.entity.BigType;

public interface BigTypeService extends BaseService<BigType>{
	List<BigType> getAllBigType();
}
