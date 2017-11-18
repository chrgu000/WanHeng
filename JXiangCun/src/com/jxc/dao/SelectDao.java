package com.jxc.dao;

import java.util.List;

import com.jxc.entity.Distance;
import com.jxc.entity.Mark;
import com.jxc.entity.Price;
import com.jxc.entity.Sequence;

public interface SelectDao {
List<Price> findAllPrice();

List<Mark> findMarksByTitleId(Integer title_id);

List<Distance> findAllDistance();

List<Sequence>findAllSequence();
}
