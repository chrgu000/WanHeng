package com.to.dao;

import com.to.entity.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public interface CompactDao extends BaseDao<Compact> {
    List<Compact> getCompactByStatus(Map<String, Integer> map);

    Compact getCompactByHouseId(Integer houseId);

    void addGas(Gas gas);

    void addCondo(Condo condo);

    void addWater(Water water);

    void addPower(Power power);

    void updatePower(Fee fee);

    void updateCondo(Fee fee);

    void updateGas(Fee fee);

    void updateWater(Fee fee);

    List<Compact> getCompactByStatusAndUser(Map<String, String> map);

    List<Price> getAllPrice();
}
