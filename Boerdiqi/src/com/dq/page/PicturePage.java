package com.dq.page;

public class PicturePage extends Page{
private Integer product_id;

public Integer getProduct_id() {
	return product_id;
}

public void setProduct_id(Integer productId) {
	product_id = productId;
}

@Override
public String toString() {
	return "PicturePage [product_id=" + product_id + "]";
}

}
