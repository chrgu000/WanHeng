package com.cgwas.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.sourceforge.pinyin4j.PinyinHelper;

import com.cgwas.director.entity.DirectorVo;
import com.cgwas.project.entity.ProjectVo;

public class DirectorComparatorUtil {
	public static List<ProjectVo> sortProjectVo(List<ProjectVo> vos, String sort) {
		if(sort.equals("asc")){
			Collections.sort(vos, new SoftwareComparator1());
		}else if(sort.equals("desc")){
			Collections.sort(vos, new SoftwareComparator2());
		}
		return vos;
	}
	class DirectorComparator1 implements Comparator<ProjectVo> {
		@Override
		public int compare(ProjectVo o1, ProjectVo o2) {
			String directorName1 = "";
			String directorName2 = "";
			List<DirectorVo> s1 = o1.getDirectors();
			for (DirectorVo v : s1) {
				directorName1 += v.getDirector_name();
			}
			List<DirectorVo> s2 = o2.getDirectors();
			for (DirectorVo v : s2) {
				directorName2 += v.getDirector_name();
			}
			char[] c1 = directorName1.toCharArray();
			char[] c2 = directorName2.toCharArray();
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
	class DirectorComparator2 implements Comparator<ProjectVo> {
		@Override
		public int compare(ProjectVo o1, ProjectVo o2) {
			String directorName1 = "";
			String directorName2 = "";
			List<DirectorVo> s1 = o1.getDirectors();
			for (DirectorVo v : s1) {
				directorName1 += v.getDirector_name();
			}
			List<DirectorVo> s2 = o2.getDirectors();
			for (DirectorVo v : s2) {
				directorName2 += v.getDirector_name();
			}
			char[] c1 = directorName2.toCharArray();
			char[] c2 = directorName1.toCharArray();
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
	}
}