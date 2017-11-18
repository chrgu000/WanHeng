package com.yingtong.dao;

import java.util.List;

import com.yingtong.entity.Vehicle;

public interface VehicleDao {
List<Vehicle> findAllVehicle();

Integer findRows();
}
