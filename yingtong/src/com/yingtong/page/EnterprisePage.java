package com.yingtong.page;

public class EnterprisePage extends Page {
private String account;
private String name;
private String relation_person;
public String getAccount() {
	return account;
}
public void setAccount(String account) {
	this.account = account.trim();
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name.trim();
}
public String getRelation_person() {
	return relation_person;
}
public void setRelation_person(String relation_person) {
	this.relation_person = relation_person.trim();
}
@Override
public String toString() {
	return "EnterprisePage [account=" + account + ", name=" + name
			+ ", relation_person=" + relation_person + "]";
}

}
