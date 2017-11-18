package com.cgwas.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.sourceforge.pinyin4j.PinyinHelper;

import com.cgwas.companySoftware.entity.CompanySoftwareVo;
import com.cgwas.project.entity.ProjectVo;

public class SoftwareComparatorUtil {
	public static List<ProjectVo> sortProjectVo(List<ProjectVo> vos, String sort) {
		if(sort.equals("asc")){
			Collections.sort(vos, new SoftwareComparator1());
		}else if(sort.equals("desc")){
			Collections.sort(vos, new SoftwareComparator2());
		}
		return vos;
	}

	public static void main(String[] args) {
		List<ProjectVo> project = new ArrayList<ProjectVo>();
		ProjectVo v1 = new ProjectVo();
		List<CompanySoftwareVo> list1 = new ArrayList<CompanySoftwareVo>();
		CompanySoftwareVo c1 = new CompanySoftwareVo();
		CompanySoftwareVo c2 = new CompanySoftwareVo();
		CompanySoftwareVo c3 = new CompanySoftwareVo();
		c1.setSoftware_name("杨俊");
		list1.add(c1);
		v1.setCompanySoftwares(list1);
		v1.setId(1L);
		ProjectVo v2 = new ProjectVo();
		List<CompanySoftwareVo> list2 = new ArrayList<CompanySoftwareVo>();
		CompanySoftwareVo c4 = new CompanySoftwareVo();
		CompanySoftwareVo c5 = new CompanySoftwareVo();
		CompanySoftwareVo c6 = new CompanySoftwareVo();
		c4.setSoftware_name("余帮琼");
		list2.add(c4);
		v2.setCompanySoftwares(list2);
		v2.setId(2L);
		ProjectVo v3 = new ProjectVo();
		List<CompanySoftwareVo> list3 = new ArrayList<CompanySoftwareVo>();
		CompanySoftwareVo c7 = new CompanySoftwareVo();
		CompanySoftwareVo c8 = new CompanySoftwareVo();
		CompanySoftwareVo c9 = new CompanySoftwareVo();
		c8.setSoftware_name("张月峰");
		list3.add(c8);
		v3.setCompanySoftwares(list3);
		v3.setId(3L);
		project.add(v1);
		project.add(v2);
		project.add(v3);
		for (ProjectVo p : project) {
			System.out.println(p.getId());
		}
		 sortProjectVo(project, "desc");
		for (ProjectVo p : project) {
			System.out.println(p.getId());
		}
	}
}
class SoftwareComparator1 implements Comparator<ProjectVo> {
	@Override
	public int compare(ProjectVo o1, ProjectVo o2) {
		String softwareName1 = "";
		String softwareName2 = "";
		List<CompanySoftwareVo> s1 = o1.getCompanySoftwares();
		for (CompanySoftwareVo v : s1) {
			softwareName1 += v.getSoftware_name();
		}
		List<CompanySoftwareVo> s2 = o2.getCompanySoftwares();
		for (CompanySoftwareVo v : s2) {
			softwareName2 += v.getSoftware_name();
		}
		char[] c1 = softwareName1.toCharArray();
		char[] c2 = softwareName2.toCharArray();
		if (c1.length >= c2.length) {
			int result = 0;
			for (int i = 0; i < c2.length; i++) {
				result = concatPinyinStringArray(
						PinyinHelper.toHanyuPinyinStringArray(c1[i]))
						.compareTo(
								concatPinyinStringArray(PinyinHelper
										.toHanyuPinyinStringArray(c2[i])));
				if (result != 0) {
					return result;
				}
			}
			return 1;
		} else if (c1.length < c2.length) {
			int result = 0;
			for (int i = 0; i < c1.length; i++) {
				result = concatPinyinStringArray(
						PinyinHelper.toHanyuPinyinStringArray(c1[i]))
						.compareTo(
								concatPinyinStringArray(PinyinHelper
										.toHanyuPinyinStringArray(c2[i])));
				if (result != 0) {
					return result;
				}
			}
			return 1;
		}
		return 1;

	}

	private String concatPinyinStringArray(String[] pinyinArray) {
		StringBuffer pinyinSbf = new StringBuffer();
		if ((pinyinArray != null) && (pinyinArray.length > 0)) {
			for (int i = 0; i < pinyinArray.length; i++) {
				pinyinSbf.append(pinyinArray[i]);
			}
		}
		return pinyinSbf.toString();
	}
}
class SoftwareComparator2 implements Comparator<ProjectVo> {
	@Override
	public int compare(ProjectVo o1, ProjectVo o2) {
		String softwareName1 = "";
		String softwareName2 = "";
		List<CompanySoftwareVo> s1 = o1.getCompanySoftwares();
		for (CompanySoftwareVo v : s1) {
			softwareName1 += v.getSoftware_name();
		}
		List<CompanySoftwareVo> s2 = o2.getCompanySoftwares();
		for (CompanySoftwareVo v : s2) {
			softwareName2 += v.getSoftware_name();
		}
		char[] c1 = softwareName2.toCharArray();
		char[] c2 = softwareName1.toCharArray();
		if (c1.length >= c2.length) {
			int result = 0;
			for (int i = 0; i < c2.length; i++) {
				result = concatPinyinStringArray(
						PinyinHelper.toHanyuPinyinStringArray(c1[i]))
						.compareTo(
								concatPinyinStringArray(PinyinHelper
										.toHanyuPinyinStringArray(c2[i])));
				if (result != 0) {
					return result;
				}
			}
			return -1;
		} else if (c1.length < c2.length) {
			int result = 0;
			for (int i = 0; i < c1.length; i++) {
				result = concatPinyinStringArray(
						PinyinHelper.toHanyuPinyinStringArray(c1[i]))
						.compareTo(
								concatPinyinStringArray(PinyinHelper
										.toHanyuPinyinStringArray(c2[i])));
				if (result != 0) {
					return result;
				}
			}
			return -1;
		}
		return -1;

	}

	private String concatPinyinStringArray(String[] pinyinArray) {
		StringBuffer pinyinSbf = new StringBuffer();
		if ((pinyinArray != null) && (pinyinArray.length > 0)) {
			for (int i = 0; i < pinyinArray.length; i++) {
				pinyinSbf.append(pinyinArray[i]);
			}
		}
		return pinyinSbf.toString();
	}
}