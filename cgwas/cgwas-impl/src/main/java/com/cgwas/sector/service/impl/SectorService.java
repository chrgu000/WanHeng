package com.cgwas.sector.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.sector.dao.api.ISectorDao;
import com.cgwas.sector.entity.Sector;
import com.cgwas.sector.entity.SectorVo;
import com.cgwas.sector.service.api.ISectorService;

@Service
public class SectorService implements ISectorService {
	private ISectorDao sectorDao;

	public Serializable save(Sector sector) {
		return sectorDao.save(sector);
	}

	public void delete(Sector sector) {
		sectorDao.delete(sector);
	}

	public void deleteByExample(Sector sector) {
		sectorDao.deleteByExample(sector);
	}

	public void update(Sector sector) {
		sectorDao.update(sector);
	}

	public void updateIgnoreNull(Sector sector) {
		sectorDao.updateIgnoreNull(sector);
	}

	public void updateByExample(Sector sector) {
		sectorDao.update("updateByExampleSector", sector);
	}

	public SectorVo load(Sector sector) {
		return (SectorVo) sectorDao.reload(sector);
	}

	public SectorVo selectForObject(Sector sector) {
		return (SectorVo) sectorDao.selectForObject("selectSector", sector);
	}

	public List<SectorVo> selectForList(Sector sector) {
		return (List<SectorVo>) sectorDao.selectForList("selectSector", sector);
	}

	public Page page(Page page, Sector sector) {
		return sectorDao.page(page, sector);
	}

	@Autowired
	public void setISectorDao(@Qualifier("sectorDao") ISectorDao sectorDao) {
		this.sectorDao = sectorDao;
	}

	@Override
	public void updateSectorByCompanyId(Sector sector) {
		sectorDao.update("updateSectorByCompanyId", sector);
	}

	@Override
	public Sector selectSectorByCompanyId(Sector sector) {
		
		Sector sector2 = (Sector) sectorDao.selectForObject("selectSectorByCompanyId", sector);
		
		
		if(sector2==null){
			sector2 = new Sector();
			sector2.setCompany_id(sector.getCompany_id());
			this.save(sector2);
		}
		
		return sector2;
	}

}
