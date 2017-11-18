package com.dq.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * ' 收藏实体类
 * 
 * @author 杨俊
 * 
 */
public class Collect implements Serializable {
	private Integer id;
	private Integer user_id;//用户id
	private User user;//用户对象
	private Integer product_id;//产品id
	private Product product;//产品对象
    private Timestamp join_time;
    
    public String getJoin_time() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(join_time==null)
			return null;
		return sdf.format(join_time);
	}
	public void setJoin_time(Timestamp joinTime) {
		join_time = joinTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer userId) {
		user_id = userId;
	}

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer productId) {
		product_id = productId;
	}

 
	@Override
	public String toString() {
		return "Collect [id=" + id + ", join_time=" + join_time
				+ ", product_id=" + product_id + ", user_id=" + user_id + "]";
	}

}
