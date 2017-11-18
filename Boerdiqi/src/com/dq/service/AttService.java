package com.dq.service;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.dq.entity.Att;

public interface AttService extends BaseService1<Att> {

	List<Att> getByPro(Map<String, Object> map);

	void delAtt(String a, String dataid, String datatitle, String callback, HttpServletResponse response)
			throws Exception;

	List<Att> getAtt(Map<String, Object> map);

}
