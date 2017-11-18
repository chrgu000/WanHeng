package com.yingtong.dao;

import java.util.List;

import com.yingtong.entity.Price;

public interface PriceDao {
List<Price> findAllPrice();

Integer findRows();
}
