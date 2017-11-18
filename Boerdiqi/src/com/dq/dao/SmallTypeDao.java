package com.dq.dao;

import java.util.List;

import com.dq.entity.SmallType;

public interface SmallTypeDao extends BaseDao<SmallType> {
List<SmallType> getSmallTypeByBigTypeId(Integer big_type_id);
}
