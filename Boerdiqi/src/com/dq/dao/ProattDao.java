package com.dq.dao;

import com.dq.entity.Proatt;

public interface ProattDao extends BaseDao1<Proatt> {

	int delByPid(Integer pid);

}
