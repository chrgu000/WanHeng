package com.to.dao;

import com.to.entity.RentalInformation;

import java.util.List;

/**
 * @Author :yangjun on 2017/3/28 0028.
 */
public interface RentalInformationDao extends BaseDao<RentalInformation>{
    List<RentalInformation> getAllRentalInformation();

    List<RentalInformation> getAllRentalInformations();
}
