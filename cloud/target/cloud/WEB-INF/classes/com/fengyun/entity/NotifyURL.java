package com.fengyun.entity;

import java.io.Serializable;

public class NotifyURL implements Serializable{
	private String time;//unix 时间戳
	private String usrargs;//用户推流的参数
	private String action;//	publish 表示推流，publish_done 表示断流
	private String app;	//默认为自定义的推流域名，如果未绑定推流域名即为播放域名
	private String appname;//	应用名称
	private String id;//	流名称
	private String node;//	CDN 接受流的节点或者机器名
	private String IP;//	推流的客户端 IP
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getUsrargs() {
		return usrargs;
	}
	public void setUsrargs(String usrargs) {
		this.usrargs = usrargs;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getApp() {
		return app;
	}
	public void setApp(String app) {
		this.app = app;
	}
	public String getAppname() {
		return appname;
	}
	public void setAppname(String appname) {
		this.appname = appname;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNode() {
		return node;
	}
	public void setNode(String node) {
		this.node = node;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
	}
	@Override
	public String toString() {
		return "NotifyURL [time=" + time + ", usrargs=" + usrargs + ", action="
				+ action + ", app=" + app + ", appname=" + appname + ", id="
				+ id + ", node=" + node + ", IP=" + IP + "]";
	}
	
}
