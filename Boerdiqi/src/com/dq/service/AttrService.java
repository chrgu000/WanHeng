package com.dq.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.dq.entity.Attr;

public interface AttrService extends BaseService1<Attr> {

	List<Attr> getByPro(Map<String, Object> map);

	void addAttr(String dataid, String title, String v, String callback, HttpServletResponse response) throws Exception;

	void delAttr(String a, String dataid, String datatitle, String callback, HttpServletResponse response)
			throws Exception;

	List<Attr> getAttr(Map<String, Object> map);

}
