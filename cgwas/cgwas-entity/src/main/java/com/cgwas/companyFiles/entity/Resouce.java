package com.cgwas.companyFiles.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonInclude(value=Include.NON_NULL)
public class Resouce {
	private Long currentSpaceTotalByte;//占用空间字节大小
	private String currentSpaceTotal;//占用空间大小
	private Long currentTotalByte;//占用字节大小
	private String currentTotal;//占用大小
	private String totalTolume;//总容量大小
	private String occupySpace;//占用空间
	private String percentage;//百分比
	private String surplusSpace;//剩余空间
	private String isFile;//是否是文件夹
	private Date crteTime;//创建时间
	private Date lastModified;//最后修改时间
	private String fileUrl;//文件夹路径
	private long files;//文件夹
	private long document;//文件
	public Long getCurrentSpaceTotalByte() {
		return currentSpaceTotalByte;
	}
	public void setCurrentSpaceTotalByte(Long currentSpaceTotalByte) {
		this.currentSpaceTotalByte = currentSpaceTotalByte;
	}
	public String getCurrentSpaceTotal() {
		return currentSpaceTotal;
	}
	public void setCurrentSpaceTotal(String currentSpaceTotal) {
		this.currentSpaceTotal = currentSpaceTotal;
	}
	public Long getCurrentTotalByte() {
		return currentTotalByte;
	}
	public void setCurrentTotalByte(Long currentTotalByte) {
		this.currentTotalByte = currentTotalByte;
	}
	public String getCurrentTotal() {
		return currentTotal;
	}
	public void setCurrentTotal(String currentTotal) {
		this.currentTotal = currentTotal;
	}
	public String getTotalTolume() {
		return totalTolume;
	}
	public void setTotalTolume(String totalTolume) {
		this.totalTolume = totalTolume;
	}
	public String getOccupySpace() {
		return occupySpace;
	}
	public void setOccupySpace(String occupySpace) {
		this.occupySpace = occupySpace;
	}
	public String getPercentage() {
		return percentage;
	}
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
	public String getIsFile() {
		return isFile;
	}
	public void setIsFile(String isFile) {
		this.isFile = isFile;
	}
	public Date getCrteTime() {
		return crteTime;
	}
	public void setCrteTime(Date crteTime) {
		this.crteTime = crteTime;
	}
	public String getSurplusSpace() {
		return surplusSpace;
	}
	public void setSurplusSpace(String surplusSpace) {
		this.surplusSpace = surplusSpace;
	}
	public long getFiles() {
		return files;
	}
	public void setFiles(long files) {
		this.files = files;
	}
	public long getDocument() {
		return document;
	}
	public void setDocument(long document) {
		this.document = document;
	}
	public Date getLastModified() {
		return lastModified;
	}
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	
}
