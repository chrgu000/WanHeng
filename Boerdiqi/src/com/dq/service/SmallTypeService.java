package com.dq.service;

import java.util.List;

import com.dq.entity.SmallType;

public interface SmallTypeService extends BaseService<SmallType>{
	List<SmallType> getSmallTypeByBigTypeId(Integer big_type_id);
}
