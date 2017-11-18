package com.dq.dao;

public interface CountDao {

	Integer getTradeCount();

	Integer getAmount();

	Integer getTradeCountToday(String date);

	Integer getAmountToday(String date);

	Integer getOrderCount();

	Integer getOrderCountToday(String date);

	Integer getOrderCountFaHuo();

	Integer getOrderCountDaiFaHuo();

	Integer getYiTuiKuanCount();

	Integer getYiTuiKuans();

	Integer getDaiTuiKuanCount();

	Integer getDaiTuiKuans();

	Integer getIntegralOrderCount();

	Integer getIntegralCountToday(String date);

	Integer getIntegralOrderCountFaHuo();

	Integer getIntegralOrderCountDaiFaHuo();
 
}
