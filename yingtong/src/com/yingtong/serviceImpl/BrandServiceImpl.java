package com.yingtong.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yingtong.dao.BrandDao;
import com.yingtong.entity.Brand;
import com.yingtong.page.Page;
import com.yingtong.service.BrandService;

@Service("brandService")
@Transactional
public class BrandServiceImpl implements BrandService {
	@Resource
	BrandDao dao;

	public List<Brand> findAllBrand() {

		return dao.findAllBrand();
	}

	public List<Brand> findAllBrandByPage(Page page) {

		return dao.findAllBrandByPage(page);
	}

	public Integer findRows(Page page) {

		return dao.findRows(page);
	}

	public Brand findBrandById(Integer id) {

		return dao.findBrandById(id);
	}

	public boolean addBrand(Brand brand) {

		return dao.addBrand(brand);
	}

	public boolean updateBrand(Brand brand) {

		return dao.updateBrand(brand);
	}

	public boolean deleteBrandById(Integer id) {

		return dao.deleteBrandById(id);
	}

}
