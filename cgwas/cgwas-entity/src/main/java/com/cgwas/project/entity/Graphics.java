package com.cgwas.project.entity;

import java.util.Arrays;

public class Graphics {
	private int[] free;//未分配
    private int[] underWay;//进行中
    private int[] finish;//已完成
	public int[] getFree() {
		return free;
	}
	public void setFree(int[] free) {
		this.free = free;
	}
	public int[] getUnderWay() {
		return underWay;
	}
	public void setUnderWay(int[] underWay) {
		this.underWay = underWay;
	}
	public int[] getFinish() {
		return finish;
	}
	public void setFinish(int[] finish) {
		this.finish = finish;
	}
	@Override
	public String toString() {
		return "Graphics [free=" + Arrays.toString(free) + ", underWay="
				+ Arrays.toString(underWay) + ", finish="
				+ Arrays.toString(finish) + "]";
	}
    

}
