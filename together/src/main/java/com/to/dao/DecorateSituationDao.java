package com.to.dao;

import com.to.entity.DecorateSituation;

import java.util.List;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public interface DecorateSituationDao extends BaseDao<DecorateSituation>{
    List<DecorateSituation> getAllDecorateSituation();
}
