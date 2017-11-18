package com.dq.dao;

import com.dq.entity.Proattr;

public interface ProattrDao extends BaseDao1<Proattr> {

	int delByPid(Integer pid);

}
