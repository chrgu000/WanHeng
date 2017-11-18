package com.yingtong.dao;

import java.util.List;

import com.yingtong.entity.TitleAddr;

public interface TitleAddrDao {
List<TitleAddr> findAllTitleAddr();

TitleAddr findTitleAddrById(Integer id);
}
