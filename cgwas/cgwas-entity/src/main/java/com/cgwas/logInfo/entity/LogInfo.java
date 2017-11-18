package com.cgwas.logInfo.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author yubangqiong 模版生成 <br/>
 *         表名： s_log_info <br/>
 *         描述：日志表 <br/>
 */
@SuppressWarnings("serial")
public class LogInfo implements Serializable {
	protected Long id;// 主键
	protected String user_name;// 用户名称
	protected String content;// 操作者的动作
	protected String table_name;// 表名
	protected String log_type;// 操作类型
	protected Long user_id;// 操作者id
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date access_time;// 操作者的时间

	public LogInfo() {
		super();
	}

	public LogInfo(Long id) {
		super();
		this.id = id;
	}
	
	@Id// 主键
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	
	public String getLog_type() {
		return log_type;
	}
	public void setLog_type(String log_type) {
		this.log_type = log_type;
	}
	
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
	public Date getAccess_time() {
		return access_time;
	}
	public void setAccess_time(Date access_time) {
		this.access_time = access_time;
	}
}
