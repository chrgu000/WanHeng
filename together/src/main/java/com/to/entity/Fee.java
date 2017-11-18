package com.to.entity;

/**
 * @Author :yangjun on 2017/4/15 0015.
 */

import lombok.Data;

 
public class Fee {
    private Integer condoId;
    private double condoPrice;
    private Integer powerId;
    private double powerPrice;
    private Integer gasId;
    private double gasPrice;
    private Integer waterId;
    private double waterPrice;
	public Integer getCondoId() {
		return condoId;
	}
	public void setCondoId(Integer condoId) {
		this.condoId = condoId;
	}
	public double getCondoPrice() {
		return condoPrice;
	}
	public void setCondoPrice(double condoPrice) {
		this.condoPrice = condoPrice;
	}
	public Integer getPowerId() {
		return powerId;
	}
	public void setPowerId(Integer powerId) {
		this.powerId = powerId;
	}
	public double getPowerPrice() {
		return powerPrice;
	}
	public void setPowerPrice(double powerPrice) {
		this.powerPrice = powerPrice;
	}
	public Integer getGasId() {
		return gasId;
	}
	public void setGasId(Integer gasId) {
		this.gasId = gasId;
	}
	public double getGasPrice() {
		return gasPrice;
	}
	public void setGasPrice(double gasPrice) {
		this.gasPrice = gasPrice;
	}
	public Integer getWaterId() {
		return waterId;
	}
	public void setWaterId(Integer waterId) {
		this.waterId = waterId;
	}
	public double getWaterPrice() {
		return waterPrice;
	}
	public void setWaterPrice(double waterPrice) {
		this.waterPrice = waterPrice;
	}
    
}
