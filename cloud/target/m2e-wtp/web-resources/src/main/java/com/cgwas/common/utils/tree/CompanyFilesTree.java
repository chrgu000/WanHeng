package com.cgwas.common.utils.tree;  
  
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/** 
 * 多叉树类 
*/  
@SuppressWarnings({ "rawtypes", "unchecked" })
public class CompanyFilesTree {  
 public static JSONObject getNodeModel(List dataList) {   
	  // 节点列表（散列表，用于临时存储节点对象）  
	  HashMap nodeList = new HashMap();  
	  // 根节点  
	  Node2 root = null;  
	  // 根据结果集构造节点列表（存入散列表）  
	  for (Iterator it = dataList.iterator(); it.hasNext();) {  
	   Map dataRecord = (Map) it.next();  
	   Node2 node = new Node2();  
	   node.id =(Long)dataRecord.get("id");  
	   node.file_name = (String) dataRecord.get("file_name");  
	   node.parent_id = (Long) dataRecord.get("parent_id");
	   nodeList.put(node.id, node);  
	  }  
	  // 构造无序的多叉树  
	  Set entrySet = nodeList.entrySet();  
	  for (Iterator it = entrySet.iterator(); it.hasNext();) {  
	   Node2 node = (Node2) ((Map.Entry) it.next()).getValue();  
	   if (node.parent_id == null || node.parent_id==0) {  
	    root = node;  
	   } else {  
		   ((Node2) nodeList.get(node.parent_id)).addChild(node);
	   }  
	  }  
	  // 输出无序的树形菜单的JSON字符串  
	  //System.out.println(root.toString());  
	  return JSON.parseObject(root.toString());
	  // 对多叉树进行横向排序  
	  //root.sortChildren();  
	  // 输出有序的树形菜单的JSON字符串  
	  //System.out.println(root.toString());      
	    
	 } 
}  
  
  
/** 
* 节点类 
*/  
 class Node2 {  
 /** 
  * 节点编号 
  */  
 public Long id;  
 /** 
  * 节点内容 
  */  
 public String file_name;  
 
 /** 
  * 父节点编号 
  */  
 public Long parent_id;  
 
 /** 
  * 孩子节点列表 
  */  
 private Children2 children = new Children2();  
   
 // 先序遍历，拼接JSON字符串  
 public String toString() {    
  String result = "{'id' : " + id + ", 'file_name' : '" + file_name + "', 'parent_id' : " + parent_id + "";  
    
  if (children != null && children.getSize() != 0) {  
   result += ", children : " + children.toString();  
  } 
      
  return result + "}";  
 }  
   
 // 兄弟节点横向排序  
 public void sortChildren() {  
  if (children != null && children.getSize() != 0) {  
   children.sortChildren();  
  }  
 }  
   
 // 添加孩子节点  
 public void addChild(Node2 node) {  
  this.children.addChild(node);  
 }  
}  
  
/** 
* 孩子列表类 
*/  
@SuppressWarnings({ "rawtypes", "unchecked" })
class Children2 {  
 private List list = new ArrayList();  
   
 public int getSize() {  
  return list.size();  
 }  
   
 public void addChild(Node2 node) {  
  list.add(node);  
 }  
   
 // 拼接孩子节点的JSON字符串  
 public String toString() {  
  String result = "[";    
  for (Iterator it = list.iterator(); it.hasNext();) {  
   result += ((Node2) it.next()).toString();  
   result += ",";  
  }  
  result = result.substring(0, result.length() - 1);  
  result += "]";  
  return result;  
 }  
   
 // 孩子节点排序  
 public void sortChildren() {  
  // 对本层节点进行排序  
  // 可根据不同的排序属性，传入不同的比较器，这里传入ID比较器  
  Collections.sort(list, new NodeIDComparator());  
  // 对每个节点的下一层节点进行排序  
  for (Iterator it = list.iterator(); it.hasNext();) {  
   ((Node2) it.next()).sortChildren();  
  }  
 }  
}  
  
/** 
 * 节点比较器 
 */  
@SuppressWarnings({ "rawtypes" })
class Node2IDComparator2 implements Comparator {  
 // 按照节点编号比较  
 public int compare(Object o1, Object o2) {  
  Long j1 = ((Node2)o1).id;  
  Long j2 = ((Node2)o2).id;  
     return (j1 < j2 ? -1 : (j1 == j2 ? 0 : 1));  
 }   
}  
  
/** 
 * 构造虚拟的层次数据 
 */  
@SuppressWarnings({ "rawtypes", "unchecked" })
class VirtualDataGenerator2 {  
 // 构造无序的结果集列表，实际应用中，该数据应该从数据库中查询获得；  
public static List getVirtualResult() {      
  List dataList = new ArrayList();  
    
  HashMap dataRecord1 = new HashMap();  
  dataRecord1.put("id", 112000l);  
  dataRecord1.put("privilege_name", "廊坊银行解放道支行");
  dataRecord1.put("layer", 1l); 
  dataRecord1.put("flag", false); 
  dataRecord1.put("parent_id", 110000l);  
    
  HashMap dataRecord2 = new HashMap();  
  dataRecord2.put("id", 112200l);  
  dataRecord2.put("privilege_name", "廊坊银行三大街支行");  
  dataRecord2.put("parent_id", 112000l);  
  dataRecord2.put("layer", 1l); 
  dataRecord2.put("flag", false);
    
  HashMap dataRecord3 = new HashMap();  
  dataRecord3.put("id", 112100l);  
  dataRecord3.put("privilege_name", "廊坊银行广阳道支行");  
  dataRecord3.put("parent_id", 112000l);  
  dataRecord3.put("layer", 1l); 
  dataRecord3.put("flag", false);
        
  HashMap dataRecord4 = new HashMap();  
  dataRecord4.put("id", 113000l);  
  dataRecord4.put("privilege_name", "廊坊银行开发区支行");  
  dataRecord4.put("parent_id", 110000l);  
  dataRecord4.put("layer", 1l); 
  dataRecord4.put("flag", false);
        
  HashMap dataRecord5 = new HashMap();  
  dataRecord5.put("id", 100000l);  
  dataRecord5.put("privilege_name", "廊坊银行总行");  
  dataRecord5.put("parent_id", 10l); 
  dataRecord5.put("layer", 1l); 
  dataRecord5.put("flag", false);
  
  HashMap dataRecord6 = new HashMap();  
  dataRecord6.put("id", 110000l);  
  dataRecord6.put("privilege_name", "廊坊分行");  
  dataRecord6.put("parent_id", 100000l);  
  dataRecord6.put("layer", 1l); 
  dataRecord6.put("flag", false);
    
  HashMap dataRecord7 = new HashMap();  
  dataRecord7.put("id", 111000l);  
  dataRecord7.put("privilege_name", "廊坊银行金光道支行");  
  dataRecord7.put("parent_id", 110000l);  
  dataRecord7.put("layer", 1l); 
  dataRecord7.put("flag", false);
  
  HashMap dataRecord8 = new HashMap();  
  dataRecord8.put("id", 200000l);  
  dataRecord8.put("privilege_name", "廊坊银行总行1");  
  dataRecord8.put("parent_id", 10l);
  dataRecord8.put("layer", 1l); 
  dataRecord8.put("flag", false);
  
  HashMap dataRecord9 = new HashMap();  
  dataRecord9.put("id", 100001l);  
  dataRecord9.put("privilege_name", "廊坊分行1");  
  dataRecord9.put("parent_id", 200000l);
  dataRecord9.put("layer", 1l); 
  dataRecord9.put("flag", false);
      
  dataList.add(dataRecord1);  
  dataList.add(dataRecord2);  
  dataList.add(dataRecord3);  
  dataList.add(dataRecord4);  
  dataList.add(dataRecord5);  
  dataList.add(dataRecord6);  
  dataList.add(dataRecord7); 
  dataList.add(dataRecord8); 
  dataList.add(dataRecord9); 
    
  return dataList;  
 }   
}  