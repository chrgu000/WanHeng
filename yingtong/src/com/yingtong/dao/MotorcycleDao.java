package com.yingtong.dao;

import java.util.List;

import com.yingtong.entity.Motorcycle;
import com.yingtong.page.Page;

public interface MotorcycleDao {
List<Motorcycle> findAllMotorcycleByPage(Page page);

Integer findRows(Page page);

List<Motorcycle> findMotorcyclesByBrandId(Integer brand_id);

Motorcycle findMotorcycleById(Integer id);

boolean addMotorcycle(Motorcycle motorcycle);

boolean updateMotorcycle(Motorcycle motorcycle);

boolean deleteMotorcycleById(Integer id);
}
