package com.cgwas.freezeRecord.entity;
 
@SuppressWarnings("serial")
public class TradeStatistics {
    protected String mon;
    protected double trade_price;
 
    public String getMon() {
        return mon;
    }
 
    public void setMon(String mon) {
        this.mon = mon;
    }
 
    public double getTrade_price() {
        return trade_price;
    }
 
    public void setTrade_price(double trade_price) {
        this.trade_price = trade_price;
    }
 
}