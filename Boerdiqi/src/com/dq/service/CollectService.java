package com.dq.service;

import java.util.List;

import com.dq.entity.Collect;

public interface CollectService {
	List<Collect> getCollects(Integer user_id);
}
