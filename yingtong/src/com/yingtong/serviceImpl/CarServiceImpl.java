package com.yingtong.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yingtong.dao.CarDao;
import com.yingtong.entity.Car;
import com.yingtong.page.Page;
import com.yingtong.page.SelectCar;
import com.yingtong.service.CarService;

@Service("carService")
@Transactional
public class CarServiceImpl implements CarService {
	@Resource
	private CarDao dao;

	public List<Car> findAllCarByPageAndTitleAddrId(Page page) {

		return dao.findAllCarByPageAndTitleAddrId(page);
	}

	public Integer findRowsByTitleAddrId(Page page) {

		return dao.findRowsByTitleAddrId(page);
	}

	public boolean addCar(Car car) {

		return dao.addCar(car);
	}

	public boolean updateCar(Car car) {

		return dao.updateCar(car);
	}

	public boolean deleteCarById(Integer id) {

		return dao.deleteCarById(id);
	}

	public Car findCarById(Integer id) {

		return dao.findCarById(id);
	}

	public List<Car> findIndexCarByState(String state) {
	 
		return dao.findIndexCarByState(state);
	}

	public List<Car> findCarsBySelectCar(SelectCar car) {
		 
		return dao.findCarsBySelectCar(car);
	}

	public boolean updateCarRentStatus(Car car) {
		 
		return dao.updateCarRentStatus(car);
	}

}
