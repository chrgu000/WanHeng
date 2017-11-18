package com.to.dao;

import com.to.entity.SupportingFacility;

import java.util.List;

/**
 * @Author :yangjun on 2017/3/28 0028.
 */
public interface SupportingFacilityDao extends BaseDao<SupportingFacility>{
    List<SupportingFacility> getAllSupportingFacility();

    List<SupportingFacility> getAllSupportingFacilityByHouseId(Integer houseId);
}
