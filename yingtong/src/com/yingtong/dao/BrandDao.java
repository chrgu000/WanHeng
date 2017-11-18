package com.yingtong.dao;

import java.util.List;

import com.yingtong.entity.Brand;
import com.yingtong.page.Page;

public interface BrandDao {
List<Brand> findAllBrand();

List<Brand> findAllBrandByPage(Page page);

Integer findRows(Page page);

Integer findRow();

Brand findBrandById(Integer id);

boolean addBrand(Brand brand);

boolean updateBrand(Brand brand);

boolean deleteBrandById(Integer id);
}
