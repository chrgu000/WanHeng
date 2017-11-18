package com.dq.dao;

import java.util.List;
import java.util.Map;

import com.dq.entity.Attr;

public interface AttrDao extends BaseDao1<Attr> {

	List<Attr> getByPro(Map<String, Object> map);

	int updByTitle(Map<String, Object> map);

	List<Attr> getAttr(Map<String, Object> map);

}
