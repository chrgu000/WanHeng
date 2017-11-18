package com.jxc.entity;

public class Sequence {
private Integer id;
private String sequence;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getSequence() {
	return sequence;
}
public void setSequence(String sequence) {
	this.sequence = sequence;
}
@Override
public String toString() {
	return "Sequence [id=" + id + ", sequence=" + sequence + "]";
}

}
