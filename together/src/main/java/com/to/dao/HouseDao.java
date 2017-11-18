package com.to.dao;

import com.to.entity.House;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public interface HouseDao extends BaseDao<House>{
    void addHousePicture(Map<String, String> map);

    void addHouseSupportingFacility(Map<String, String> map);

    List<House> getHouseByArea(String area);

    List<House> getHousesByStatus(Map map);

    void deleteById(Integer id);

    void deleteHouseSupportingFacilityByHouseId(Integer houseId);

    List<House> getHousesByType(Map<String, String> map);

    List<House> getHousesByPrice(Map<String, Object> map);

    List<House> getHouseBySearch(Map<String,String> map);
}
