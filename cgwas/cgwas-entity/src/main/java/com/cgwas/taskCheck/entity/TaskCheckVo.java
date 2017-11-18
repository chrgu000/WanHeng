package com.cgwas.taskCheck.entity;
/**
 * @author yangjun <br/>
 *         表名： a_task_check <br/>
 *         描述：任务审核表 <br/>
 */
 @SuppressWarnings("serial")
public class TaskCheckVo extends TaskCheck {

	public TaskCheckVo() {
		super();
	}

	public TaskCheckVo(Long id) {
		super();
		this.tid = id;
	}


}
