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
public class MultipleTree {  
 public static void main(String[] args) {  
  // 读取层次数据结果集列表   
  List dataList = VirtualDataGenerator.getVirtualResult();    
    
  // 节点列表（散列表，用于临时存储节点对象）  
  HashMap nodeList = new HashMap();  
  // 根节点  
  Node root = null;  
  // 根据结果集构造节点列表（存入散列表）  
  for (Iterator it = dataList.iterator(); it.hasNext();) {  
   Map dataRecord = (Map) it.next();  
   Node node = new Node();  
   node.id =(Long)dataRecord.get("id");  
   node.role_name = (String) dataRecord.get("role_name");  
   node.for_id = (Long) dataRecord.get("for_id");  
   nodeList.put(node.id, node);  
  }  
  // 构造无序的多叉树  
  Set entrySet = nodeList.entrySet();  
  for (Iterator it = entrySet.iterator(); it.hasNext();) {  
   Node node = (Node) ((Map.Entry) it.next()).getValue();  
   if (node.for_id == null || node.for_id.equals("")) {  
    root = node;  
   } else {  
    ((Node) nodeList.get(node.for_id)).addChild(node);  
   }  
  }  
  // 输出无序的树形菜单的JSON字符串  
  System.out.println(root.toString());     
  // 对多叉树进行横向排序  
  //root.sortChildren();  
  // 输出有序的树形菜单的JSON字符串  
  //System.out.println(root.toString());      
    
 }
 
 public static JSONObject getNodeModel(List dataList) {   
	  // 节点列表（散列表，用于临时存储节点对象）  
	  HashMap nodeList = new HashMap();  
	  // 根节点  
	  Node root = null;  
	  // 根据结果集构造节点列表（存入散列表）  
	  for (Iterator it = dataList.iterator(); it.hasNext();) {  
	   Map dataRecord = (Map) it.next();  
	   Node node = new Node();  
	   node.id =(Long)dataRecord.get("id");  
	   node.role_name = (String) dataRecord.get("role_name");  
	   node.for_id = (Long) dataRecord.get("for_id");
	   node.people_num = (Long) dataRecord.get("people_num");
	   node.head_pic_path = (String) dataRecord.get("head_pic_path");
	   nodeList.put(node.id, node);  
	  }  
	  // 构造无序的多叉树  
	  Set entrySet = nodeList.entrySet();  
	  for (Iterator it = entrySet.iterator(); it.hasNext();) {  
	   Node node = (Node) ((Map.Entry) it.next()).getValue();  
	   if (node.for_id == -1) {  
	    root = node;  
	   } else {  
		   ((Node) nodeList.get(node.for_id)).addChild(node);
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
 class Node {  
 /** 
  * 节点编号 
  */  
 public Long id;  
 /** 
  * 节点内容 
  */  
 public String role_name;  
 
 public Long people_num;
 /**
  * 头像
  */
 public String head_pic_path;
 /** 
  * 父节点编号 
  */  
 public Long for_id;  
 /** 
  * 孩子节点列表 
  */  
 private Children children = new Children();  
   
 // 先序遍历，拼接JSON字符串  
 public String toString() {    
  String result = "{"  
   + "'id' : " + id + ""  
   + ", 'role_name' : '" + role_name + "'" + ", 'head_pic_path' : '" + head_pic_path + "'" + ", 'people_num' : " + people_num + ""+ ", 'for_id' : " + for_id + "";  
    
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
 public void addChild(Node node) {  
  this.children.addChild(node);  
 }  
}  
  
/** 
* 孩子列表类 
*/  
@SuppressWarnings({ "rawtypes", "unchecked" })
class Children {  
 private List list = new ArrayList();  
   
 public int getSize() {  
  return list.size();  
 }  
   
 public void addChild(Node node) {  
  list.add(node);  
 }  
   
 // 拼接孩子节点的JSON字符串  
 public String toString() {  
  String result = "[";    
  for (Iterator it = list.iterator(); it.hasNext();) {  
   result += ((Node) it.next()).toString();  
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
   ((Node) it.next()).sortChildren();  
  }  
 }  
}  
  
/** 
 * 节点比较器 
 */  
@SuppressWarnings({ "rawtypes" })
class NodeIDComparator implements Comparator {  
 // 按照节点编号比较  
 public int compare(Object o1, Object o2) {  
  Long j1 = ((Node)o1).id;  
  Long j2 = ((Node)o2).id;  
     return (j1 < j2 ? -1 : (j1 == j2 ? 0 : 1));  
 }   
}  
  
/** 
 * 构造虚拟的层次数据 
 */  
@SuppressWarnings({ "rawtypes", "unchecked" })
class VirtualDataGenerator {  
 // 构造无序的结果集列表，实际应用中，该数据应该从数据库中查询获得；  
public static List getVirtualResult() {      
  List dataList = new ArrayList();  
    
  HashMap dataRecord1 = new HashMap();  
  dataRecord1.put("id", 112000l);  
  dataRecord1.put("role_name", "廊坊银行解放道支行");  
  dataRecord1.put("for_id", 110000l);  
    
  HashMap dataRecord2 = new HashMap();  
  dataRecord2.put("id", 112200l);  
  dataRecord2.put("role_name", "廊坊银行三大街支行");  
  dataRecord2.put("for_id", 112000l);  
    
  HashMap dataRecord3 = new HashMap();  
  dataRecord3.put("id", 112100l);  
  dataRecord3.put("role_name", "廊坊银行广阳道支行");  
  dataRecord3.put("for_id", 112000l);  
        
  HashMap dataRecord4 = new HashMap();  
  dataRecord4.put("id", 113000l);  
  dataRecord4.put("role_name", "廊坊银行开发区支行");  
  dataRecord4.put("for_id", 110000l);  
        
  HashMap dataRecord5 = new HashMap();  
  dataRecord5.put("id", 100000l);  
  dataRecord5.put("role_name", "廊坊银行总行");  
  dataRecord5.put("for_id", null); 
  
  HashMap dataRecord6 = new HashMap();  
  dataRecord6.put("id", 110000l);  
  dataRecord6.put("role_name", "廊坊分行");  
  dataRecord6.put("for_id", 100000l);  
    
  HashMap dataRecord7 = new HashMap();  
  dataRecord7.put("id", 111000l);  
  dataRecord7.put("role_name", "廊坊银行金光道支行");  
  dataRecord7.put("for_id", 110000l);  
  
  HashMap dataRecord8 = new HashMap();  
  dataRecord8.put("id", 200000l);  
  dataRecord8.put("role_name", "廊坊银行总行1");  
  dataRecord8.put("for_id", null);
  
  HashMap dataRecord9 = new HashMap();  
  dataRecord9.put("id", 100001l);  
  dataRecord9.put("role_name", "廊坊分行1");  
  dataRecord9.put("for_id", 200000l);
      
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