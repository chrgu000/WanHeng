package com.to.entity;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Administrator on 2017/3/27 0027.
 * 合同
*/
@Data
public class Compact {
    private Integer id;
    private Integer houseId;
    private House house;
    private String compactNum;//合同编号
    private String houseNum;//房号
    private String hostName;//房东姓名
    private String hostTel;//房东手机号
    private String hostIdCardNum;//房东身份证号
    private String name;//租客姓名
    private String tel;//租客手机
    private String idCardNum;//租客身份证号
    private Date startTime;//租赁起始时间
    private Date endTime;//租赁结束时间
    private List<String> payTimes;//缴费时间集合
    private Short payWayId;//支付方式id
    private PayWay payWay;
    private Integer deposit;//押金
    private Integer rent;//租金
    private String powerWay;//电费缴费方式
    private List<Power> powers;
    private String waterWay;//水费缴费方式
    private List<Water> waters;
    private String condoWay;//物业费缴费方式
    private List<Condo> condos;
    private String gasWay;//天然气费缴费方式
    private List<Gas> gases;
    private Timestamp createTime;//合同添加时间
    private Timestamp modifiedTime;//合同最新编辑时间
    private Short delflag;//删除标记1 为删除2 为未删除
    private Short status;//合同状态
    private Integer userId;
    private String totalPrice;//最近一次的总费用
    private Short state;
    private String renter_img;
    private String user_img;
    private SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getHouseId() {
		return houseId;
	}
	public void setHouseId(Integer houseId) {
		this.houseId = houseId;
	}
	public House getHouse() {
		return house;
	}
	public void setHouse(House house) {
		this.house = house;
	}
	public String getCompactNum() {
		return compactNum;
	}
	public void setCompactNum(String compactNum) {
		this.compactNum = compactNum;
	}
	public String getHouseNum() {
		return houseNum;
	}
	public void setHouseNum(String houseNum) {
		this.houseNum = houseNum;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getHostTel() {
		return hostTel;
	}
	public void setHostTel(String hostTel) {
		this.hostTel = hostTel;
	}
	public String getHostIdCardNum() {
		return hostIdCardNum;
	}
	public void setHostIdCardNum(String hostIdCardNum) {
		this.hostIdCardNum = hostIdCardNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getIdCardNum() {
		return idCardNum;
	}
	public void setIdCardNum(String idCardNum) {
		this.idCardNum = idCardNum;
	}
	public List<String> getPayTimes() {
		return payTimes;
	}
	public void setPayTimes(List<String> payTimes) {
		this.payTimes = payTimes;
	}
	public Short getPayWayId() {
		return payWayId;
	}
	public void setPayWayId(Short payWayId) {
		this.payWayId = payWayId;
	}
	public PayWay getPayWay() {
		return payWay;
	}
	public void setPayWay(PayWay payWay) {
		this.payWay = payWay;
	}
	public Integer getDeposit() {
		return deposit;
	}
	public void setDeposit(Integer deposit) {
		this.deposit = deposit;
	}
	public Integer getRent() {
		return rent;
	}
	public void setRent(Integer rent) {
		this.rent = rent;
	}
	public String getPowerWay() {
		return powerWay;
	}
	public void setPowerWay(String powerWay) {
		this.powerWay = powerWay;
	}
	public List<Power> getPowers() {
		return powers;
	}
	public void setPowers(List<Power> powers) {
		this.powers = powers;
	}
	public String getWaterWay() {
		return waterWay;
	}
	public void setWaterWay(String waterWay) {
		this.waterWay = waterWay;
	}
	public List<Water> getWaters() {
		return waters;
	}
	public void setWaters(List<Water> waters) {
		this.waters = waters;
	}
	public String getCondoWay() {
		return condoWay;
	}
	public void setCondoWay(String condoWay) {
		this.condoWay = condoWay;
	}
	public List<Condo> getCondos() {
		return condos;
	}
	public void setCondos(List<Condo> condos) {
		this.condos = condos;
	}
	public String getGasWay() {
		return gasWay;
	}
	public void setGasWay(String gasWay) {
		this.gasWay = gasWay;
	}
	public List<Gas> getGases() {
		return gases;
	}
	public void setGases(List<Gas> gases) {
		this.gases = gases;
	}
	public Short getDelflag() {
		return delflag;
	}
	public void setDelflag(Short delflag) {
		this.delflag = delflag;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Short getState() {
		return state;
	}
	public void setState(Short state) {
		this.state = state;
	}
	public String getRenter_img() {
		return renter_img;
	}
	public void setRenter_img(String renter_img) {
		this.renter_img = renter_img;
	}
	public String getUser_img() {
		return user_img;
	}
	public void setUser_img(String user_img) {
		this.user_img = user_img;
	}
	public SimpleDateFormat getSdf1() {
		return sdf1;
	}
	public void setSdf1(SimpleDateFormat sdf1) {
		this.sdf1 = sdf1;
	}
	public SimpleDateFormat getSdf2() {
		return sdf2;
	}
	public void setSdf2(SimpleDateFormat sdf2) {
		this.sdf2 = sdf2;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public void setModifiedTime(Timestamp modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public String getStartTime() {
        if(startTime==null){
            return null;
        }else{
            return sdf1.format(startTime);
        }

    }
    public String getEndTime() {
        if(endTime==null){
            return null;
        }else{
            return sdf1.format(endTime);
        }

    }
    public String  getCreateTime() {
        if(createTime==null){
            return null;
        }else{
            return sdf2.format(createTime);
        }
    }
    public String getModifiedTime() {
        if(modifiedTime==null){
            return null;
        }else{
            return sdf2.format(modifiedTime);
        }

    }

}
