package com.dq.entity.util;

import java.util.List;

public class ReturnInfo {
	private boolean hasError;
	private String errInfo;
	private String errType;
	private List<Object> objects;
	private Object object;
	
	public String getErrType() {
		return errType;
	}
	public void setErrType(String errType) {
		this.errType = errType;
	}
	public boolean isHasError() {
		return hasError;
	}
	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}
	public String getErrInfo() {
		return errInfo;
	}
	public void setErrInfo(String errInfo) {
		this.errInfo = errInfo;
	}
	public List<Object> getObjects() {
		return objects;
	}
	public void setObjects(List<Object> objects) {
		this.objects = objects;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	@Override
	public String toString() {
		return "ReturnInfo [errInfo=" + errInfo + ", errType=" + errType
				+ ", hasError=" + hasError + ", object=" + object
				+ ", objects=" + objects + "]";
	}
	
}
