package com.cgwas.common.json.entity;


import java.io.Serializable;
import java.util.Date;

/**
 * 银行卡提现
 */
public class LmWithdraw implements Serializable{
    protected Long id;
    protected Long userId;
    protected String buscod;//业务类别
    protected String yurref;//业务参考号
    protected Double trsamt;//交易金额。每次最高5万
    protected String crtacc;//收方帐号
    protected String crtnam;//收方户名
    protected String bnkflg;//Y：招行；N：非招行；
    protected String crtbnk;//收方开户行;跨行支付（BNKFLG=N）必填
    protected String cdtbrd;//收款行行号


    /**
     * 支付结果
     */
    protected String sqrnbr;//流水号
    protected String reqnbr;//流程实例号
    protected String retcod;//返回代码
    protected String reqsts;//业务请求状态
    protected String rtnflg;//业务处理结果.返回的每笔信息中REQSTS如果不等于’FIN’表示该笔支付银行还在处理中，
    // REQSTS=’FIN’时再判断RTNFLG，RTNFLG为'W'等待处理，为’S’时表示成功，’B’表示退票，其他作为失败处理
    protected String errcod;//错误码
    protected String rtnnar;//结果摘要
    protected String rjcrsn;//拒绝原因

    protected Date gmtPay;//支付完成时间
    protected Date gmtCreate;
    protected Date gmtModified;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getBuscod() {
		return buscod;
	}
	public void setBuscod(String buscod) {
		this.buscod = buscod;
	}
	public String getYurref() {
		return yurref;
	}
	public void setYurref(String yurref) {
		this.yurref = yurref;
	}
	public Double getTrsamt() {
		return trsamt;
	}
	public void setTrsamt(Double trsamt) {
		this.trsamt = trsamt;
	}
	public String getCrtacc() {
		return crtacc;
	}
	public void setCrtacc(String crtacc) {
		this.crtacc = crtacc;
	}
	public String getCrtnam() {
		return crtnam;
	}
	public void setCrtnam(String crtnam) {
		this.crtnam = crtnam;
	}
	public String getBnkflg() {
		return bnkflg;
	}
	public void setBnkflg(String bnkflg) {
		this.bnkflg = bnkflg;
	}
	public String getCrtbnk() {
		return crtbnk;
	}
	public void setCrtbnk(String crtbnk) {
		this.crtbnk = crtbnk;
	}
	public String getCdtbrd() {
		return cdtbrd;
	}
	public void setCdtbrd(String cdtbrd) {
		this.cdtbrd = cdtbrd;
	}
	public String getSqrnbr() {
		return sqrnbr;
	}
	public void setSqrnbr(String sqrnbr) {
		this.sqrnbr = sqrnbr;
	}
	public String getReqnbr() {
		return reqnbr;
	}
	public void setReqnbr(String reqnbr) {
		this.reqnbr = reqnbr;
	}
	public String getRetcod() {
		return retcod;
	}
	public void setRetcod(String retcod) {
		this.retcod = retcod;
	}
	public String getReqsts() {
		return reqsts;
	}
	public void setReqsts(String reqsts) {
		this.reqsts = reqsts;
	}
	public String getRtnflg() {
		return rtnflg;
	}
	public void setRtnflg(String rtnflg) {
		this.rtnflg = rtnflg;
	}
	public String getErrcod() {
		return errcod;
	}
	public void setErrcod(String errcod) {
		this.errcod = errcod;
	}
	public String getRtnnar() {
		return rtnnar;
	}
	public void setRtnnar(String rtnnar) {
		this.rtnnar = rtnnar;
	}
	public String getRjcrsn() {
		return rjcrsn;
	}
	public void setRjcrsn(String rjcrsn) {
		this.rjcrsn = rjcrsn;
	}
	public Date getGmtPay() {
		return gmtPay;
	}
	public void setGmtPay(Date gmtPay) {
		this.gmtPay = gmtPay;
	}
	public Date getGmtCreate() {
		return gmtCreate;
	}
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	public Date getGmtModified() {
		return gmtModified;
	}
	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
    
    
    
}
