package com.jxc.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jxc.dao.PowerDao;
import com.jxc.entity.Power;
import com.jxc.service.PowerService;
@Service("powerService")
@Transactional
public class PowerServiceImpl implements PowerService {
@Resource 
private PowerDao dao; 
	public List<Power> findAllPower() {
		return dao.findAllPower();
	}

	public boolean addPower(Power power) {
		 		return dao.addPower(power);
	}

	public boolean deletePower(Integer id) {
	 
		return dao.deletePower(id);
	}

	public Power findPowerById(Integer id) {
		 		return dao.findPowerById(id);
	}

}
