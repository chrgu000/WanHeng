package com.yingtong.service;

import java.util.List;

import com.yingtong.entity.Car;
import com.yingtong.page.Page;
import com.yingtong.page.SelectCar;

public interface CarService {
	List<Car> findAllCarByPageAndTitleAddrId(Page page);

	Integer findRowsByTitleAddrId(Page page);

	List<Car> findIndexCarByState(String state);
	
	List<Car> findCarsBySelectCar(SelectCar car);

   boolean updateCarRentStatus(Car car);

	boolean addCar(Car car);

	boolean updateCar(Car car);

	boolean deleteCarById(Integer id);

	Car findCarById(Integer id);
}
