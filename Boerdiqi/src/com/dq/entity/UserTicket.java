package com.dq.entity;

public class UserTicket {
private Integer id;
private Integer ticket_id;
private Ticket ticket;
private Integer number;
private Integer use_num;
private Integer isUsed;


public Ticket getTicket() {
	return ticket;
}
public void setTicket(Ticket ticket) {
	this.ticket = ticket;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public Integer getTicket_id() {
	return ticket_id;
}
public void setTicket_id(Integer ticketId) {
	ticket_id = ticketId;
}
public Integer getNumber() {
	return number;
}
public void setNumber(Integer number) {
	this.number = number;
}
public Integer getUse_num() {
	return use_num;
}
public void setUse_num(Integer useNum) {
	use_num = useNum;
}
public Integer getIsUsed() {
	return isUsed;
}
public void setIsUsed(Integer isUsed) {
	this.isUsed = isUsed;
}
@Override
public String toString() {
	return "UserTicket [id=" + id + ", isUsed=" + isUsed + ", number=" + number
			+ ", ticket=" + ticket + ", ticket_id=" + ticket_id + ", use_num="
			+ use_num + "]";
}

}
