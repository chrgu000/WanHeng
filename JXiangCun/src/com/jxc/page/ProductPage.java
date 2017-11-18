package com.jxc.page;

import java.util.List;

public class ProductPage extends Page {
private String title;
private Integer merchant_id;
private Integer title_id;
private List<Integer> merchantIds;

public List<Integer> getMerchantIds() {
	return merchantIds;
}
public void setMerchantIds(List<Integer> merchantIds) {
	this.merchantIds = merchantIds;
}
public Integer getTitle_id() {
	return title_id;
}
public void setTitle_id(Integer title_id) {
	this.title_id = title_id;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public Integer getMerchant_id() {
	return merchant_id;
}
public void setMerchant_id(Integer merchant_id) {
	this.merchant_id = merchant_id;
}

}
