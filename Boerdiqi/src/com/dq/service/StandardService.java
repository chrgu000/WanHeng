package com.dq.service;

import java.util.List;

import com.dq.entity.BigType;
import com.dq.entity.Standard;

public interface StandardService extends BaseService<Standard>{
	List<Standard> getAllStandard();
}
