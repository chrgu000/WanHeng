package com.dq.service;


import java.util.List;
import java.util.Map;

import com.dq.entity.Att;
import com.dq.entity.Proattr;

public interface ProattrService extends BaseService1<Proattr>{

	List<Att> getByAtt(Map<String, Object> map);

}
