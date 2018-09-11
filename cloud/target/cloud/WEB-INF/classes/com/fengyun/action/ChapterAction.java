package com.fengyun.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageUtils;
import com.cgwas.common.json.entity.Result;
import com.fengyun.entity.Chapter;
import com.fengyun.entity.Course;
import com.fengyun.service.IChapterService;
import com.fengyun.service.ICourseService;
import com.fengyun.util.HTTPConfig;
import com.fengyun.util.ConvertVideo;
import com.fengyun.util.HttpUtil;
import com.fengyun.util.ReadVideo;
/**章节
*  Author yangjun
*/
@Controller
@RequestMapping(value="com/chapterAction",method={RequestMethod.POST,RequestMethod.GET})
@Transactional
public class ChapterAction {
	@Autowired 
	private IChapterService chapterService;
	@Autowired
	private ICourseService courseService;
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat timeSdf=new SimpleDateFormat("HH:mm:ss");
	@RequestMapping("/getOwnName")
	@ResponseBody
	//TODO抽离
	public String getOwnName(HttpSession session){
		//'loginuser:{"id":2,"username":"kefu","nickname":"客服审核","tel":"1391588****","head_pic_path":"cgwas/user/ab175dbf-23a1-4920-a84d-fd9528bccf0a/images/avater/user/head_pic_path_456364.jpg","is_delete":"\u0000","uuid":"ab175dbf-23a1-4920-a84d-fd9528bccf0a"}';
		//userInfo//{"sex":"2","birth":1513094400000,"nation":"汉","city":"330100","id":2,"area":"","weixin":"12121","address":"","email":"111111@qq.com","name":"金阳","head_pic_path":"cgwas/user/ab175dbf-23a1-4920-a84d-fd9528bccf0a/images/avater/user/head_pic_path_456364.jpg","province":"330000","user_id":2,"qq":"1212"}
	 
		JSONObject user=JSONObject.parseObject(session.getAttribute("loginUser").toString());
//		Long userId = user.getLong("id");
//		Map<String,Object>  query=new HashMap<String,Object>();
//		query.put("userId", userId);
//		String result1=HttpUtil.doPost(HttpConfig.HTTP_PREFIX+"/cgwas/cloud/getUserInfoById.action", query);
//		JSONArray users=JSONArray.parseObject(result1).getJSONArray("data");
//		JSONObject obj=users.getJSONObject(0);
		String nickname= user.getString("nickname");
		return nickname;
	}
	@RequestMapping("getSingleChatOwnName")
	@ResponseBody
	//TODO抽离
	public Map<String,Object> getSingleChatOwnName(HttpSession session){
		JSONObject user=JSONObject.parseObject(session.getAttribute("loginUser").toString());
		Long userId = user.getLong("id");
		Map<String,Object>  query=new HashMap<String,Object>();
		query.put("userId", userId);
		String result1=HttpUtil.doPost(HTTPConfig.HTTP_PREFIX+"/cgwas/cloud/getUserInfoById.action", query);
		JSONArray users=JSONArray.parseObject(result1).getJSONArray("data");
		JSONObject obj=users.getJSONObject(0);
		String name=obj.getString("name");
		if(name==null||name.isEmpty()){
			name=user.getString("nickname");
		}
		query.clear();
		query.put("name", name);
		query.put("user_id", userId);
		query.put("head_pic_url", obj.getString("head_pic_path"));
		query.put("time", timeSdf.format(new Date()));
		return query;
	}
	 
	 @RequestMapping("/converVideo")
	 @ResponseBody
	 public Result converVideo(String source,String target,String videoTimeLength,String filePath) {
		 System.out.println("source:"+source);
		 System.out.println("target:"+target);
		 System.out.println("videoTimeLength:"+videoTimeLength);
		 System.out.println("filePath:"+filePath);
		 ConvertVideo.ConvertVideoTest(source, target);
         new File(source).delete();
         Chapter chapter=new Chapter();
         String course_length=ReadVideo.getVideoTimeLength(videoTimeLength);
		 chapter.setCourse_length(course_length);
         chapter.setVideo_status("Y");
         chapter.setVedio_url(filePath);
         chapterService.updateVideoStatus(chapter);
         System.out.println("end");
		 return new Result(Boolean.TRUE, "成功", null);
		}
	@RequestMapping("/getChapterListByCourseId")
	@ResponseBody
	public Result getChapterListByCourseId(Integer pageSize,Integer pageNo,Chapter chapter,String startDate,String endDate){
		Map<String, Object> map = new HashMap<String, Object>();
		Page page=null;
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("pageSize", String.valueOf(pageSize));
			params.put("pageNo", String.valueOf(pageNo));
			if(startDate!=null&&!startDate.trim().equals("")){
				map.put("startDate", sdf.format(sdf.parse(startDate)));
			}
			if(endDate!=null&&!endDate.trim().equals("")){
				map.put("endDate", sdf.format(sdf.parse(endDate)));
			}
			chapter.setSearch(map);
			page = PageUtils.createPage(params);
			page = chapterService.page1(page, chapter);
			map.clear();
			map.put("total", page.getTotal());
			map.put("pageSize", page.getLimit());
			map.put("pageNo", page.getCurrentPage());
			map.put("dataList", page.getDataList());
			map.put("pageMax", Double.valueOf(Math.ceil(page.getTotal()*1.0/page.getLimit())).intValue());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("00001", map);
		}
		return new Result(Boolean.TRUE, "成功", map);
	}
	/**
     * 根据讲师用户id查询该讲师所讲的所有课程
     * @param pageSize
     * @param pageNo
     * @param course
     * @param chapter
     * @param startDate
     * @param endDate
     * @return
     */
	@RequestMapping("/getChapterListByUserMap")
	@ResponseBody
	public Result getChapterListByUserMap(Integer pageSize,Integer pageNo,Course course,Chapter chapter,String startDate,String endDate,HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page page=null;
		try {
			JSONObject user=JSONObject.parseObject(session.getAttribute("loginUser").toString());
			Long userId = user.getLong("id");
			Map<String, String> params = new HashMap<String, String>();
			params.put("pageSize", String.valueOf(pageSize));
			params.put("pageNo", String.valueOf(pageNo));
			if(startDate!=null&&!startDate.trim().equals("")){
				map.put("startDate", sdf.format(sdf.parse(startDate)));
			}
			if(endDate!=null&&!endDate.trim().equals("")){
				map.put("endDate", sdf.format(sdf.parse(endDate)));
			}
			map.put("course", course);
			map.put("user_id", userId);
			chapter.setSearch(map);
			page = PageUtils.createPage(params);
			page = chapterService.page(page, chapter);
			map.clear();
			map.put("total", page.getTotal());
			map.put("pageSize", page.getLimit());
			map.put("pageNo", page.getCurrentPage());
			map.put("dataList", page.getDataList());
			map.put("pageMax", Double.valueOf(Math.ceil(page.getTotal()*1.0/page.getLimit())).intValue());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("00001", map);
		}
		return new Result(Boolean.TRUE, "成功", map);
	}
	/**
     * 审核人员获取课程列表信息
     * @param pageSize
     * @param pageNo
     * @param course
     * @param chapter
     * @param startDate
     * @param endDate
     * @return
     */
	@RequestMapping("/getCheckChapterList")
	@ResponseBody
	public Result getCheckChapterList(Integer pageSize,Integer pageNo,Chapter chapter,Course course,String startDate,String endDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page page=null;
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("pageSize", String.valueOf(pageSize));
			params.put("pageNo", String.valueOf(pageNo));
			if(startDate!=null&&!startDate.trim().equals("")){
				map.put("startDate", sdf.format(sdf.parse(startDate)));
			}
			if(endDate!=null&&!endDate.trim().equals("")){
				map.put("endDate", sdf.format(sdf.parse(endDate)));
			}
			map.put("course", course);
			chapter.setSearch(map);
			page = PageUtils.createPage(params);
			page = chapterService.page(page, chapter);
			map.clear();
			map.put("pageMax", Double.valueOf(Math.ceil(page.getTotal()*1.0/page.getLimit())).intValue());
			map.put("total", page.getTotal());
			map.put("pageSize", page.getLimit());
			map.put("pageNo", page.getCurrentPage());
			map.put("dataList", page.getDataList());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("00001", map);
		}
		return new Result(Boolean.TRUE, "成功", map);
	}
	@RequestMapping("/create")
	public @ResponseBody Result create(Chapter chapter) {
		if (chapter != null) {
			chapterService.save(chapter);
			return new Result("保存成功!");
		} else {
			return new Result("数据传输失败!");
		}
	}
	@RequestMapping("/getChapterById")
	@ResponseBody
	public Result getChapterById(Long id){
		Chapter chapter=chapterService.getChapterById(id);
		return new Result(Result.SUCCESS,"成功!",chapter);
	}
	/**
	 * 平台审核调用该方法
	 * @param chapter
	 * @return
	 */
	@RequestMapping("/changeChapterOrder")
	@ResponseBody
	public Result changeChapterOrder(Long chapter_id,String type){
		 Chapter currentChapter=chapterService.getChapterById(chapter_id);
		 Integer currentOrder=currentChapter.getOrder_num();
		 Map<String,Object> map=new HashMap<String,Object>();
		 map.put("course_id", currentChapter.getCourse().getId());
		 map.put("type", type);
		 map.put("order_num", currentOrder);
		 Chapter nearChapter=chapterService.getNearChapter(map);
		 Integer nearOrder=nearChapter.getOrder_num();
		 Integer tmp=currentOrder;
		 currentOrder=nearOrder;
		 nearOrder=tmp;
		 currentChapter.setOrder_num(currentOrder);
		 nearChapter.setOrder_num(nearOrder);
		 chapterService.updateIgnoreNull(currentChapter);
		 chapterService.updateIgnoreNull(nearChapter);
		return new Result(Result.SUCCESS,"成功",null);
	}
	@RequestMapping("/update")
	public @ResponseBody Result update(@RequestBody Map<String,String> map,Chapter chapter,Course course) {
		if(map.get("chapter_id")!=null){
			chapter.setId(Long.valueOf(map.get("chapter_id")));
			chapter.setCourse_content(map.get("course_content"));
			chapter.setCheck_idea(map.get("check_idea"));
			chapter.setCheck_status(map.get("check_status"));
			chapter.setDelflag(map.get("delflag"));
			System.out.println(map);
			chapterService.updateIgnoreNull(chapter);
			Chapter c=chapterService.getChapterById(Long.valueOf(map.get("chapter_id")));
			Long course_id=c.getCourse().getId();
			List<Chapter> chapters=courseService.getChaptersByCourseIdOfCheck(course_id);
			if(chapters.size()==0){
				course.setCheck_delflag("Y");
			}else{
				course.setCheck_delflag("N");
			}
			Integer n=chapterService.getYesCheckChapterByCourseId(course_id);
			if("N".equals(c.getCourse().getIs_free())&&n.equals(0)){
				course.setCheck_delflag("Y");
				course.setIs_public("N");
			}
			course.setId(course_id);
			courseService.updateIgnoreNull(course);
			Course crs=courseService.getCourseById(course_id);
			if("Y".equals(chapter.getCheck_status())&&!"Y".equals(crs.getIs_apply())){
				course.setIs_public("Y");
				course.setId(course_id);
				courseService.updateIgnoreNull(course);
			}
		}
	    if(map.get("course_id")!=null){
	    	course.setId(Long.valueOf(map.get("course_id")));
			course.setCourse_overview(map.get("course_overview"));
			courseService.updateIgnoreNull(course);
	    }
		return new Result("保存成功!");
	}
	
	@RequestMapping("/delete")
	public @ResponseBody Result delete(Chapter chapter) {
		chapterService.delete(chapter);
		Chapter c=chapterService.getChapterById(chapter.getId());
		Long course_id=c.getCourse().getId();
		List<Chapter> chapters=courseService.getChaptersByCourseIdOfCheck(course_id);
		Course crs=courseService.getCourseInfoById(course_id);
		System.out.println(crs.getIs_free());
		
		Course course=new Course();
		course.setId(course_id);
		if(chapters.size()==0){
			course.setCheck_delflag("Y");
			course.setIs_public("N");
		}else{
			course.setCheck_delflag("N");
			course.setIs_public("Y");
		}
		
 		courseService.updateIgnoreNull(course);
		return new Result("删除成功!");
	}

}
