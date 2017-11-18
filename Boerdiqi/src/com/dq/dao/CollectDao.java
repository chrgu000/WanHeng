package com.dq.dao;

import java.util.List;

import com.dq.entity.Collect;
public interface CollectDao {
	List<Collect> getCollects(Integer user_id);
}
