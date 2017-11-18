package com.to.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Administrator on 2017/3/27 0027.
 * 房子信息
 */
@Data
public class House {
    private Integer id;
    private String gardenName;//小区名称
    private String province;//省
    private String city;//市
    private String area;//区
    private String address;//详细地址
    private Short room;//室
    private Short office;//厅
    private Short defend;//卫
    private Short orientationId;//朝向id
    private Orientation orientation;
    private Short decorateSituationId;//装修情况id
    private DecorateSituation decorateSituation;
    private Integer acreage;//面积
    private Integer rent;//月租金
    private String title;//标题
    private String identity;//身份
    private String details;//描述
    private Short payWayId;//支付方式id
    private PayWay payWay;
    private String concactName;//联系人姓名
    private String tel;//手机号
    private String sex;//性别
    private Timestamp createTime;//添加时间
    private Timestamp modifiedTime;//最新编辑时间
    private String type;//房子类型
    private String module;//类型
    private Short delflag;//删除标记1为删除2为未删除
    private Short status;//房子的租赁状态
    private Short shareHouseId;//合组房子类型id
    private ShareHouse shareHouse;
    private Integer userId;
    private String imgUrl;
    private double latitude;
    private double longitude;
    private double distance;
    private List<SupportingFacility> supportingFacilitys;
    private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    public String getGardenName() {
		return gardenName;
	}
	public void setGardenName(String gardenName) {
		this.gardenName = gardenName;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Short getRoom() {
		return room;
	}
	public void setRoom(Short room) {
		this.room = room;
	}
	public Short getOffice() {
		return office;
	}
	public void setOffice(Short office) {
		this.office = office;
	}
	public Short getDefend() {
		return defend;
	}
	public void setDefend(Short defend) {
		this.defend = defend;
	}
	public Short getOrientationId() {
		return orientationId;
	}
	public void setOrientationId(Short orientationId) {
		this.orientationId = orientationId;
	}
	public Orientation getOrientation() {
		return orientation;
	}
	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}
	public Short getDecorateSituationId() {
		return decorateSituationId;
	}
	public void setDecorateSituationId(Short decorateSituationId) {
		this.decorateSituationId = decorateSituationId;
	}
	public DecorateSituation getDecorateSituation() {
		return decorateSituation;
	}
	public void setDecorateSituation(DecorateSituation decorateSituation) {
		this.decorateSituation = decorateSituation;
	}
	public Integer getAcreage() {
		return acreage;
	}
	public void setAcreage(Integer acreage) {
		this.acreage = acreage;
	}
	public Integer getRent() {
		return rent;
	}
	public void setRent(Integer rent) {
		this.rent = rent;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
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
	public String getConcactName() {
		return concactName;
	}
	public void setConcactName(String concactName) {
		this.concactName = concactName;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
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
	public Short getShareHouseId() {
		return shareHouseId;
	}
	public void setShareHouseId(Short shareHouseId) {
		this.shareHouseId = shareHouseId;
	}
	public ShareHouse getShareHouse() {
		return shareHouse;
	}
	public void setShareHouse(ShareHouse shareHouse) {
		this.shareHouse = shareHouse;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public List<SupportingFacility> getSupportingFacilitys() {
		return supportingFacilitys;
	}
	public void setSupportingFacilitys(List<SupportingFacility> supportingFacilitys) {
		this.supportingFacilitys = supportingFacilitys;
	}
	public SimpleDateFormat getSdf() {
		return sdf;
	}
	public void setSdf(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public void setModifiedTime(Timestamp modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public  Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id=id;
    }
    public String getCreateTime() {
        if(createTime==null){
            return null;
        }else{
            return sdf.format(createTime);
        }
    }
    public String getModifiedTime() {
        if(modifiedTime==null){
            return null;
        }else{
            return sdf.format(modifiedTime);
        }
    }
}
