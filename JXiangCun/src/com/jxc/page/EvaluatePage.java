package com.jxc.page;

public class EvaluatePage extends Page{
private Integer merchant_id;
private String nickname;
public Integer getMerchant_id() {
	return merchant_id;
}
public void setMerchant_id(Integer merchant_id) {
	this.merchant_id = merchant_id;
}
public String getNickname() {
	return nickname;
}
public void setNickname(String nickname) {
	this.nickname = nickname;
}
@Override
public String toString() {
	return "EvaluatePage [merchant_id=" + merchant_id + ", nickname="
			+ nickname + "]";
}

}
