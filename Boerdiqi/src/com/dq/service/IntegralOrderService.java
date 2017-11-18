package com.dq.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.dq.entity.IntegralOrder;

public interface IntegralOrderService extends BaseService<IntegralOrder>{
	void updateByIds(Map<String, String[]> map,HttpServletResponse response)throws Exception;
}
