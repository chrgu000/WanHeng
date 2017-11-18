package com.dq.dao;

import java.util.Map;

import com.dq.entity.Skuinfo;

public interface SkuinfoDao extends BaseDao1<Skuinfo> {

	int delByPid(Map<String, Object> map);

}
