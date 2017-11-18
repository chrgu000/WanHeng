package com.cgwas.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cgwas.animationlighttask.entity.AnimationLightTaskVo;
import com.cgwas.modeltask.entity.ModelTaskVo;
import com.cgwas.project.entity.ProjectVo;
import com.cgwas.subproject.entity.SubProjectVo;

public class ExcelImportUtil {
	public static void main(String[] args) {
		List<Map<String,Object>> list=ExcelImportUtil.importSubProjectInfo("E:/Desktop/cgwas/sub_project.xlsx");
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
	}
	public static List<Map<String, Object>> importProjectInfo(String filePath) {
		ExcelParse parse = new ExcelParse();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			// 加载excel文件
			parse.loadExcel(filePath);
			int rows = parse.getRowCount(1);
			for (int i = 2; i <= rows + 1; i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				ProjectVo project = new ProjectVo();
				project.setName(parse.readExcelByRowAndCell(1, i, 1));
				project.setProject_no(parse.readExcelByRowAndCell(1, i, 2));
				String status = parse.readExcelByRowAndCell(1, i, 3);
				String type = parse.readExcelByRowAndCell(1, i, 4);
				project.setCover_path(parse.readExcelByRowAndCell(1, i, 5));
				project.setIntroduce(parse.readExcelByRowAndCell(1, i, 6));
				project.setTotal(Integer.parseInt(parse.readExcelByRowAndCell(
						1, i, 7)));
				project.setColor(parse.readExcelByRowAndCell(1, i, 8));
				String frameRate = parse.readExcelByRowAndCell(1, i, 9);
				String resolution = parse.readExcelByRowAndCell(1, i, 10);
				String software = parse.readExcelByRowAndCell(1, i, 11);
				String plugin = parse.readExcelByRowAndCell(1, i, 12);
				project.setProject_folder(parse.readExcelByRowAndCell(1, i, 13));
				project.setBudget(Long.parseLong(parse.readExcelByRowAndCell(1,
						i, 14)));
				String createName = parse.readExcelByRowAndCell(1, i, 17);
				project.setCreater_name(createName);
				String principals = parse.readExcelByRowAndCell(1, i, 18);
				String directors = parse.readExcelByRowAndCell(1, i, 19);
				String screenwriters = parse.readExcelByRowAndCell(1, i, 20);
				map.put("begin_time", parse.readExcelByRowAndCell(1,
						i, 15));
				map.put("end_time",parse.readExcelByRowAndCell(1, i,
						16));
				map.put("project", project);
				map.put("status", status);
				map.put("type", type);
				map.put("frameRate", frameRate);
				map.put("resolution", resolution);
				map.put("software", software);
				map.put("plugin", plugin);
				map.put("createName", createName);
				map.put("principals", principals);
				map.put("directors", directors);
				map.put("screenwriters", screenwriters);
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			parse.close();
		}
		return list;
	}
	public static List<Map<String, Object>> importSubProjectInfo(String filePath) {
		ExcelParse parse = new ExcelParse();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			// 加载excel文件
			parse.loadExcel(filePath);
			int rows = parse.getRowCount(1);
			for (int i = 2; i <= rows + 1; i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				SubProjectVo project = new SubProjectVo();
			
				String status = parse.readExcelByRowAndCell(1, i, 1);
				project.setTotal(Integer.parseInt(parse.readExcelByRowAndCell(1, i, 2)));
				project.setDuration(parse.readExcelByRowAndCell(1, i, 3));
				project.setCover_path(parse.readExcelByRowAndCell(1, i, 4));
				project.setIntroduce((parse.readExcelByRowAndCell(1, i, 5)));
				project.setProject_folder((parse.readExcelByRowAndCell(1, i, 6)));
				project.setName(parse.readExcelByRowAndCell(1, i, 10));
				String principals = parse.readExcelByRowAndCell(1, i, 7);
				String directors = parse.readExcelByRowAndCell(1, i, 8);
				String screenwriters = parse.readExcelByRowAndCell(1, i, 9);
				map.put("begin_time", parse.readExcelByRowAndCell(1,
						i, 11));
				map.put("end_time",parse.readExcelByRowAndCell(1, i,
						12));
				map.put("project", project);
				map.put("status", status);
				map.put("principals", principals);
				map.put("directors", directors);
				map.put("screenwriters", screenwriters);
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			parse.close();
		}
		return list;
	}
	public static List<Map<String, Object>> importModelTask(String filePath) {
		ExcelParse parse = new ExcelParse();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			// 加载excel文件
			parse.loadExcel(filePath);
			int rows = parse.getRowCount(1);
			for (int i = 2; i <= rows + 1; i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				ModelTaskVo task = new ModelTaskVo();
			    task.setName(parse.readExcelByRowAndCell(1, i, 1));
			    task.setPattern_number(parse.readExcelByRowAndCell(1, i, 2));
			    map.put("model_type", parse.readExcelByRowAndCell(1, i,3));
			    map.put("publish_type", parse.readExcelByRowAndCell(1, i, 4));
			    task.setMaking_requirement(parse.readExcelByRowAndCell(1, i, 5));
			    task.setReference_material(parse.readExcelByRowAndCell(1, i, 6));
			    map.put("software", parse.readExcelByRowAndCell(1, i, 7));
			    task.setPlan_time(parse.readExcelByRowAndCell(1, i, 8));
			    task.setPrice(parse.readExcelByRowAndCell(1, i, 9));
			    map.put("beginTime", parse.readExcelByRowAndCell(1, i, 10));
			    map.put("endTime", parse.readExcelByRowAndCell(1, i, 11));
			    task.setRemark(parse.readExcelByRowAndCell(1, i, 12));
			    task.setCommit_path(parse.readExcelByRowAndCell(1, i, 13));
			    task.setExtraction_path(parse.readExcelByRowAndCell(1, i, 14));
			    map.put("degree", parse.readExcelByRowAndCell(1, i, 15));
				map.put("task", task);
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			parse.close();
		}
		return list;
	}
	public static List<Map<String, Object>> importAnimationLightTask(
			String filePath) {
		ExcelParse parse = new ExcelParse();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			// 加载excel文件
			parse.loadExcel(filePath);
			int rows = parse.getRowCount(1);
			for (int i = 2; i <= rows + 1; i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				AnimationLightTaskVo task = new AnimationLightTaskVo();
			    task.setPattern_number(parse.readExcelByRowAndCell(1, i, 1));
			    task.setCart(parse.readExcelByRowAndCell(1, i, 2));
			    task.setTime(parse.readExcelByRowAndCell(1, i, 3));
			    task.setMaking_requirement(parse.readExcelByRowAndCell(1, i, 4));
			    task.setReference_material(parse.readExcelByRowAndCell(1, i, 5));
			    task.setPlan_time(parse.readExcelByRowAndCell(1, i, 6));
			    task.setPrice(parse.readExcelByRowAndCell(1, i, 7));
			    map.put("beginTime", parse.readExcelByRowAndCell(1, i, 8));
			    map.put("endTime", parse.readExcelByRowAndCell(1, i, 9));
			    map.put("software",parse.readExcelByRowAndCell(1, i,10));
			    task.setRemark(parse.readExcelByRowAndCell(1, i, 11));
			    task.setCommit_path(parse.readExcelByRowAndCell(1, i, 12));
			    task.setExtraction_path(parse.readExcelByRowAndCell(1, i, 13));
			    map.put("degree", parse.readExcelByRowAndCell(1, i, 14));
			    map.put("publish_type", parse.readExcelByRowAndCell(1, i, 15));
				map.put("task", task);
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			parse.close();
		}
		return list;
	}
	 
}
