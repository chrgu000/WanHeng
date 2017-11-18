package com.cgwas.repair.entity;

import java.util.List;

import com.cgwas.repairimage.entity.RepairImage;

/**
 * @author yangjun <br/>
 *         表名： p_repair <br/>
 *         描述：返修表 <br/>
 */
 @SuppressWarnings("serial")
public class RepairVo extends Repair {
private List<RepairImage> repairImages;

	public List<RepairImage> getRepairImages() {
	return repairImages;
}

public void setRepairImages(List<RepairImage> repairImages) {
	this.repairImages = repairImages;
}

	public RepairVo() {
		super();
	}

	public RepairVo(Long id) {
		super();
		this.id = id;
	}


}
