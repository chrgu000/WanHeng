package com.fengyun.action;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageUtils;
import com.cgwas.common.json.entity.Result;
import com.cgwas.common.utils.CommonUtil;
import com.cloopen.rest.sdk.utils.encoder.BASE64Encoder;
import com.fengyun.entity.Callback;
import com.fengyun.entity.Chapter;
import com.fengyun.entity.Course;
import com.fengyun.entity.InterestDirection;
import com.fengyun.entity.LearnerCourse;
import com.fengyun.entity.NotifyURL;
import com.fengyun.entity.Program;
import com.fengyun.entity.Software;
import com.fengyun.entity.Teacher;
import com.fengyun.entity.TradeSkill;
import com.fengyun.entity.Type;
import com.fengyun.service.IChapterService;
import com.fengyun.service.ICourseService;
import com.fengyun.service.IInterestDirectionService;
import com.fengyun.service.ILearnerCourseService;
import com.fengyun.service.ISoftwareService;
import com.fengyun.service.ITeacherService;
import com.fengyun.service.ITradeSkillService;
import com.fengyun.util.Base64Image;
import com.fengyun.util.CreateKey;
import com.fengyun.util.EncryptUtil;
import com.fengyun.util.HTTPConfig;
import com.fengyun.util.HttpUtil;
import com.fengyun.util.ImageUtil;
import com.fengyun.util.ReadVideo;
import com.fengyun.video.util.CreateVideoRecord;
import com.fengyun.video.util.DeleteVideoRecord;
/**课程
*  Author yangjun
*/
/**课程
*  Author yangjun
*/
@Controller
@Transactional
@RequestMapping("com/courseAction")
public class CourseAction {
	@Autowired
	private ICourseService courseService;//课程
	@Autowired
	private IChapterService chapterService;//章节
	@Autowired
	private ITeacherService teacherService;//讲师
    @Autowired 
    private IInterestDirectionService interestDirectionService;//兴趣方向
    @Autowired
    private ITradeSkillService tradeSkillService;//行业技能
    @Autowired
    private ISoftwareService softwareService;//软件
    @Autowired
    private ILearnerCourseService learnerCourseService;//我的课程
    private SimpleDateFormat dateSdf=new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat timeSdf=new SimpleDateFormat("HH:mm:ss");
    @RequestMapping("/uploadBase64")
    @ResponseBody
    public Result uploadBase64(String data){
    	data=ImageUtil.changeBase64(data);
    	return new Result(Result.SUCCESS,"成功",data);
    }
    @RequestMapping("/liveCallback")
    @ResponseBody
    public Result liveCallback(NotifyURL notifyURL,HttpSession session){
    	System.out.println("============================================>");
    	System.out.println("直播结束回掉函数");
    	String appName=notifyURL.getAppname();
    	String action=notifyURL.getAction();
    	System.out.println(notifyURL);
    	Long course_id=Long.valueOf(appName.substring(0, appName.indexOf("_")));
    	Long chapter_id=Long.valueOf(appName.substring(appName.indexOf("_")+1));
    	Course c=new Course();
    	c.setId(course_id);
    	if("publish".equals(action)){
    		c.setLive("Y");
    	}else if("publish_done".equals(action)){
    		String stream=notifyURL.getId();
    		Chapter chapter=new Chapter();
        	chapter.setId(Long.valueOf(stream));
        	chapter.setUnion_site("Y");
        	chapterService.updateIgnoreNull(chapter);
    		c.setLive("N");
    		session.getServletContext().removeAttribute(""+chapter_id);
    	}
    	courseService.updateIgnoreNull(c);
    	System.out.println("==============================================>");
    	return new Result(Result.SUCCESS,"成功","直播结束回掉函数");
    }
    /**
     * 查询单个录制索引文件
     * @param courseName
     * @param chapterName
     * @return
     */
    @RequestMapping("/recordCallback")
    @ResponseBody
    public Result searchSingleRecord(@RequestBody Callback callback){
    	System.out.println("============================================>");
    	System.out.println("录播结束回掉函数");
    	Integer duration=(int)(Double.valueOf(callback.getDuration())*1000);
    	Chapter c=new Chapter();
    	String url=callback.getUri();
    	c.setVedio_url(url);
    	c.setCourse_length(ReadVideo.secToTime(duration));
    	c.setId(Long.valueOf(callback.getStream()));
    	chapterService.updateIgnoreNull(c);
    	DeleteVideoRecord.doExeUrl(callback.getApp());
    	System.out.println(callback);
    	System.out.println("==============================================>");
    	
    	return new Result(Result.SUCCESS,"成功","录播结束回掉函数");
    }
     
	@RequestMapping("/getMySelfCourse")
	@ResponseBody
	public Result getMySelfCourse(String type,HttpSession session,Integer pageSize,Integer pageNo,Course course){
		JSONObject user=JSONObject.parseObject(session.getAttribute("loginUser").toString());
		Long userId = user.getLong("id");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", userId);
		map.put("type", type);
		course.setSearch(map);
		Page page=null;
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("pageSize", String.valueOf(pageSize));
			params.put("pageNo", String.valueOf(pageNo));
			course.setSearch(map);
			page = PageUtils.createPage(params);
			page = courseService.page2(page, course);
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
	@RequestMapping("/getRecommendCourse")
	@ResponseBody
	public Result getRecommendCourse(){
		List<Course> courses=courseService.getRecommendCourse();
		return new Result(Result.SUCCESS,"成功",courses);
	}
	@RequestMapping("/testHttpClient")
	@ResponseBody
	public void testHttpClient(){
	
	}		
		 
	@RequestMapping("/payCourse")
	@ResponseBody
	public Result payCourse(HttpSession session,String password,Long course_id,HttpServletRequest request){
		JSONObject user=JSONObject.parseObject(session.getAttribute("loginUser").toString());
		Long userId = user.getLong("id");
		EncryptUtil encryptUtil = new EncryptUtil();
		Map<String,Object>  query=new HashMap<String,Object>();
		query.put("userId", userId);
		String result=HttpUtil.doPost(HTTPConfig.HTTP_PREFIX+"/cgwas/cloud/getUserWallet.action",query);
		System.out.println("result:"+result);
		System.out.println("user_id:"+userId);
		JSONObject retn=JSONObject.parseObject(result).getJSONObject("data");
		
		if (!retn.getString("pay_password").equals(
				encryptUtil.getshaByMd5(password))) {
			return new Result(Result.FAILURE,"支付密码输入错误!",null);
		}
		JSONObject userPrice=JSONObject.parseObject(result).getJSONObject("data");
		Double u=userPrice.getDouble("userPrice");
		if(u==null) {
			u=0.0;
		}
		// 查询余额是否充足
		if (retn.getInteger("remaining_sum")==null||(retn.getInteger("remaining_sum") - u <= 0)) {// 判断余额是否充足
			return new Result(Result.FAILURE,"您的余额不足,请充值!",null);// 公司或用户余额不足
		}
		Course c=courseService.getCourseInfoById(course_id);
		// 转账-- 学员付款
		Map<String,Object> walletInfoOut=new HashMap<String,Object>();
		walletInfoOut.put("user_id",userId );
		walletInfoOut.put("remaining_sum", Double.valueOf(c.getPrice()));
		walletInfoOut.put("flag", "out");
		HttpUtil.doPost(HTTPConfig.HTTP_PREFIX+"/cgwas/cloud/accessUserMoney.action", walletInfoOut);
		// 转账--讲师收款
		Map<String,Object> walletInfo2=new HashMap<String,Object>();
		walletInfo2.put("user_id",c.getTeacher().getUser_id() );
		walletInfo2.put("remaining_sum", CommonUtil.FormetFileSize(Double.valueOf(c.getPrice()),0.95));
		walletInfo2.put("flag", "in");
		HttpUtil.doPost(HTTPConfig.HTTP_PREFIX+"/cgwas/cloud/accessUserMoney.action", walletInfo2);
		//转账--平台收钱
		Map<String,Object> walletInfoIn=new HashMap<String,Object>();
		walletInfoIn.put("user_id",1L);
		walletInfoIn.put("remaining_sum", CommonUtil.FormetFileSize(Double.valueOf(c.getPrice()),0.05));
		walletInfoIn.put("flag", "in");
		HttpUtil.doPost(HTTPConfig.HTTP_PREFIX+"/cgwas/cloud/accessUserMoney.action", walletInfoIn);
		String ip=CommonUtil.getIpAddr(request);
		String result1=HttpUtil.doPost(HTTPConfig.HTTP_PREFIX+"/cgwas/cloud/getUserInfoById.action", query);
		JSONArray users=JSONArray.parseObject(result1).getJSONArray("data");
		JSONObject obj=users.getJSONObject(0);
		String userName=(String) obj.get("name");
		
		
		Map<String,Object> tradeRecord = new HashMap<String,Object>();
		StringBuffer buffer = new StringBuffer();
		buffer.append("【"+userName+"学员】购买了您的【"+c.getCourse_name()+"】系列课程,对方转账给您【"
				+CommonUtil.FormetFileSize(Double.valueOf(c.getPrice()),0.95)+ "】元。");tradeRecord.put("trade_content",buffer.toString());
		tradeRecord.put("trade_content", buffer.toString());
		tradeRecord.put("trade_order_no",String.valueOf(new Date().getTime()));
		tradeRecord.put("trade_type","6");
		tradeRecord.put("trade_price",CommonUtil.FormetFileSize(Double.valueOf(c.getPrice()),0.95));
		tradeRecord.put("user_id",c.getTeacher().getUser_id());
		tradeRecord.put("for_id",course_id);
		tradeRecord.put("trade_state","TRADE_SUCCESS");
		tradeRecord.put("current_ip",ip);
		HttpUtil.doPost(HTTPConfig.HTTP_PREFIX+"/cgwas/cloud/addTradeRecord.action", tradeRecord);
		
		Map<String,Object> tradeRecordOut = new HashMap<String,Object>();
		StringBuffer bufferOut = new StringBuffer();
		bufferOut.append("您购买了【"+c.getTeacher().getName()+"讲师】的【"+c.getCourse_name()+"】系列课程，支付了【"+c.getPrice()+"】元");
		tradeRecordOut.put("trade_content",bufferOut.toString());
		tradeRecordOut.put("trade_order_no",String.valueOf(new Date().getTime()));
		tradeRecordOut.put("trade_type","7");
		tradeRecordOut.put("trade_price",CommonUtil.FormetFileSize(Double.valueOf(c.getPrice()),1.00));
		tradeRecordOut.put("user_id",userId);
		tradeRecordOut.put("for_id",c.getId());
		tradeRecordOut.put("trade_state","TRADE_SUCCESS");
		tradeRecordOut.put("current_ip",ip);
		HttpUtil.doPost(HTTPConfig.HTTP_PREFIX+"/cgwas/cloud/addTradeRecord.action", tradeRecordOut);
		
		
		Map<String,Object> tradeRecordIn = new HashMap<String,Object>();
		StringBuffer bufferIn = new StringBuffer();
		bufferIn.append("【"+userName+"学员】购买了贵平台中【"+c.getTeacher().getName()+"讲师】的【"+c.getCourse_name()+"】系列课程,付手续费【"
				+CommonUtil.FormetFileSize(Double.valueOf(c.getPrice()),0.05)+ "】元。");
		tradeRecordIn.put("trade_content",bufferIn.toString());
		tradeRecordIn.put("trade_order_no",String.valueOf(new Date().getTime()));
		tradeRecordIn.put("trade_type","6");
		tradeRecordIn.put("trade_price",CommonUtil.FormetFileSize(Double.valueOf(c.getPrice()),0.05));
		tradeRecordIn.put("user_id",1L);
		tradeRecordIn.put("for_id",c.getId());
		tradeRecordIn.put("trade_state","TRADE_SUCCESS");
		tradeRecordIn.put("current_ip",ip);
		HttpUtil.doPost(HTTPConfig.HTTP_PREFIX+"/cgwas/cloud/addTradeRecord.action", tradeRecordIn);
		
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("user_id", userId);
		map.put("course_id", course_id);
		map.put("type", "1");
		LearnerCourse learner_course=learnerCourseService.getLearnerCourseByUserMap(map);
		if(learner_course==null){
			learner_course=new LearnerCourse();
			learner_course.setCourse_id(course_id);
			learner_course.setType("1");
			learner_course.setCreate_date(new Date());
			learner_course.setDelflag("N");
			learner_course.setIs_free("N");
			learner_course.setLearn_degree(0);
			learner_course.setUser_id(userId);
			learnerCourseService.save(learner_course);
			Course course=courseService.getCourseById(course_id);
			if(course!=null){
				Integer join_nums=course.getJoin_nums()+1;
				course.setJoin_nums(join_nums);
				courseService.updateIgnoreNull(course);
			}
		}else{
			if("Y".equals(learner_course.getDelflag())){
				learner_course.setDelflag("N");
			}else{
				learner_course.setDelflag("Y");
			}
			learnerCourseService.updateIgnoreNull(learner_course);
		}
		return new Result(Result.SUCCESS,"支付成功",null);
	}
	 /**
	  * 获取所有分类
	  * @return
	  */
   @RequestMapping("/getAllType")
   @ResponseBody
   public Result getAllType(){
   	List<Type> types=courseService.getAllType();
   	return new Result(Result.SUCCESS,"成功",types);
   }
  
	 /**
	  * 获取所有兴趣方向
	  * @return
	  */
    @RequestMapping("/getAllInterestDirections")
    @ResponseBody
    public Result getAllInterestDirections(Integer type_id){
    	List<InterestDirection> interestDirections=interestDirectionService.getAllInterestDirections(type_id);
    	return new Result(Result.SUCCESS,"成功",interestDirections);
    }
   
    /**
     * 获取所有软件
     * @return
     */
    @RequestMapping("/getAllSoftwares")
    @ResponseBody
    public Result getAllSoftwares(Integer type_id){
    	List<Software> softwares=softwareService.getAllSoftwares(type_id);
    	return new Result(Result.SUCCESS,"成功",softwares);
    }
    @RequestMapping("/getSoftwaresByInterestDirectionId")
    @ResponseBody
    public Result getSoftwaresByInterestDirectionId(Integer interest_direction_id){
    	List<Software> softwares=softwareService.getSoftwaresByInterestDirectionId(interest_direction_id);
    	return new Result(Result.SUCCESS,"成功",softwares);
    }
    /**
     * 获取所有行业技能
     * @return
     */
    @RequestMapping("/getAllTradeSkills")
    @ResponseBody
    public Result getAllTradeSkills(Integer type_id){
    	List<TradeSkill> tadeSkills=tradeSkillService.getAllTradeSkills(type_id);
    	return new Result(Result.SUCCESS,"成功",tadeSkills);
    }
    @RequestMapping("/getTradeSkillsBySoftwareId")
    @ResponseBody
    public Result getTradeSkillsBySoftwareId(Integer software_id){
    	List<TradeSkill> tadeSkills=tradeSkillService.getTradeSkillsBySoftwareId(software_id);
    	return new Result(Result.SUCCESS,"成功",tadeSkills);
    }
    @RequestMapping("/getAllPrograms")
    @ResponseBody
    public Result getAllPrograms(){
    	List<Program> programs=courseService.getAllPrograms();
    	return new Result(Result.SUCCESS,"成功",programs);
    }
    //上传图片
	@ResponseBody
	@RequestMapping("/previewImgFile")
	public Result previewImgFile(MultipartFile userfile,
			HttpServletRequest request, HttpServletResponse response) {
		StringBuffer base64Data = new StringBuffer();
		if (!userfile.isEmpty()) {
			try {
				BASE64Encoder encoder = new BASE64Encoder();
				// 通过base64来转化图片
				base64Data = base64Data.append(encoder.encode(userfile.getBytes())) ;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if ("".equals(base64Data)) {
			return new Result("UT0001", null); // 图片上传失败
		} else {
			return new Result(Boolean.TRUE, "上传成功",base64Data); // 返回结果;
		}
	}
	@RequestMapping("/getCoursesByUserMap1")
	@ResponseBody
	public Result getCoursesByUserMap1(Integer pageSize,Integer pageNo,Course course,HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page page=null;
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("pageSize", String.valueOf(pageSize));
			params.put("pageNo", String.valueOf(pageNo));
			JSONObject user=JSONObject.parseObject(session.getAttribute("loginUser").toString());
			Long userId = user.getLong("id");
			course.setUser_id(userId);
			page = PageUtils.createPage(params);
			page = courseService.page4(page, course);
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
	/**
	 * 根据讲师id查询讲师已讲的课程,供新建课程用
	 * @param session
	 * @return
	 */
	@RequestMapping("/getCoursesByUserMap")
	public @ResponseBody Result getCoursesByUserMap(HttpSession session,String type) {
		Map<String,Object> map=new HashMap<String,Object>();
		JSONObject user=JSONObject.parseObject(session.getAttribute("loginUser").toString());
		Long userId = user.getLong("id");
		map.put("user_id", userId);
		map.put("type", type);
		 List<Course> courses=courseService.getCoursesByUserMap(map);
		return new Result(Result.SUCCESS,"成功",courses);
	}
	@RequestMapping("/searchCourseByName")
	@ResponseBody
	public Result searchCourseByName(Integer pageSize,Integer pageNo,Course course) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page page=null;
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("pageSize", String.valueOf(pageSize));
			params.put("pageNo", String.valueOf(pageNo));
			page = PageUtils.createPage(params);
			page = courseService.page3(page, course);
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
	@RequestMapping("/getCourseListByTeacher")
	@ResponseBody
	public Result getCourseListByTeacher(Integer pageSize,Integer pageNo,Course course,Long user_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page page=null;
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("pageSize", String.valueOf(pageSize));
			params.put("pageNo", String.valueOf(pageNo));
			map.put("user_id",user_id);
			course.setSearch(map);
			page = PageUtils.createPage(params);
			page = courseService.page1(page, course);
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
	/**
     * 课程首页搜索
     * @param pageSize
     * @param pageNo
     * @param course
     * @param chapter
     * @param startDate
     * @param endDate
     * @return
     */
	@RequestMapping("/searchCourseList")
	@ResponseBody
	public Result searchCourseList(Integer pageSize,Integer pageNo,Course course,Long trade_skill_id,Long software_id,Integer type_id,Integer program_id,String news,String mood) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page page=null;
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("pageSize", String.valueOf(pageSize));
			params.put("pageNo", String.valueOf(pageNo));
			map.put("trade_skill_id", trade_skill_id);
			map.put("software_id", software_id);
			map.put("type_id",type_id);
			map.put("program_id",program_id);
			map.put("news", news);
			map.put("mood",mood);
			course.setSearch(map);
			page = PageUtils.createPage(params);
			page = courseService.page(page, course);
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
	/**
	 * @return
	 */
	@RequestMapping("/getChapterOrderOfCourse")
	public @ResponseBody Result getChapterOrderOfCourse(Long course_id) {
		 List<Chapter> chapters=courseService.getChapterOrderOfCourse(course_id);
		return new Result(Result.SUCCESS,"成功",chapters);
	}
	/**
	 *  
	 * @return
	 */
	@RequestMapping("/getChaptersByCourseId")
	public @ResponseBody Result getChaptersByCourseId(Long course_id) {
		 List<Chapter> chapters=courseService.getChaptersByCourseId(course_id);
		return new Result(Result.SUCCESS,"成功",chapters);
	}
	@RequestMapping("/checkCourseIsMyOrNot")
	@ResponseBody
	public Result checkCourseIsMyOrNot(Long id,String paid,HttpSession session){
		JSONObject user=JSONObject.parseObject(session.getAttribute("loginUser").toString());
		Long userId = user.getLong("id");
		Map<String,Object> search=new HashMap<String,Object>();
		search.put("user_id", userId);
		search.put("course_id", id);
		search.put("paid", paid);
		Long learner_course_id=courseService.checkCourseIsMyOrNot(search);
		Long course_id=courseService.getsCourseIsMyOrNot(search);
		if(learner_course_id>0||course_id!=null){
			return new Result(Result.SUCCESS,"成功",null);
		}
		return new Result(Result.FAILURE,"成功",null);
	}
	@RequestMapping("/getCourseInfoById")
	@ResponseBody
	public Result getCourseInfoById(Long id){
		Course course=courseService.getCourseInfoById(id);
		Integer updateCourseNum=courseService.getUpdateCourseNum(id);
		if(updateCourseNum>0){
			course.setUpdateCourse(true);
		}else{
			course.setUpdateCourse(false);
		}
		return new Result(Result.SUCCESS,"成功",course);
	}
	@RequestMapping("/getCourseById")
	public @ResponseBody Result getCourseById(Long id) {
		 Course course=courseService.getCourseById(id);
		return new Result(Result.SUCCESS,"成功",course);
	}
	@RequestMapping("/uploadImg")
	@ResponseBody
	public Result uploadImg(@RequestBody Map<String,String> cover){
		String course_cover=cover.get("course_cover");
		String imgFileUrl="/usr/memory/cgwas/cloud/images/";
		if(course_cover!=null&&course_cover.length()>50){
			course_cover=course_cover.substring(course_cover.indexOf(",")+1);
			String imgFilePath=imgFileUrl+(new Random().nextInt(1000)+""+System.currentTimeMillis()+new Random().nextInt(1000))+".jpg";
			boolean b=Base64Image.GenerateImage(course_cover, imgFilePath);
			if(b){
				course_cover=imgFilePath.substring(11, imgFilePath.length());
				return new Result(Result.SUCCESS,"成功",course_cover);
			}
			return new Result(Result.SUCCESS,"成功",null);
		}else{
			return new Result(Result.SUCCESS,"成功",null);
		}
	}
	@RequestMapping("/createCourse")
	@Transactional
	public @ResponseBody Result createCourse(Course course,Chapter chapter,String tradeSkillIds,String softwareIds,HttpSession session,Integer type_id,Integer program_id,String program_content,String beginDate,String endDate,String onliveTime) throws Exception {
		if (course != null&&chapter!=null) {
			JSONObject user=JSONObject.parseObject(session.getAttribute("loginUser").toString());
			Long userId = user.getLong("id");
			if(chapter.getCourse_id()==null){
				Teacher teacher=teacherService.getTeacherByUsrId(userId);
				Integer getCourse_num=teacher.getCourse_num()+1;
				teacher.setCourse_num(getCourse_num);
				teacherService.updateIgnoreNull(teacher);
				course.setUser_id(userId);
				course.setTeacher_delflag("N");
				course.setLearner_delflag("N");
				course.setJoin_nums(0);
				if("Y".equals(course.getIs_free())&&!"Y".equals(course.getIs_apply())){
					course.setIs_public("Y");
				}else{
					course.setIs_public("N");
				}
				if("3".equals(course.getType())){
					course.setIs_public("Y");
				}
				Program p=new Program();
				p.setContent(program_content);
				if(null==program_id&&p.getContent()!=null&&!p.getContent().isEmpty()){
					courseService.addProgram(p);
					program_id=p.getId();
				}
				course.setProgram_id(program_id);
				course.setType_id(type_id);
				
				if(beginDate!=null&&!beginDate.isEmpty()){
					course.setBegin_date(dateSdf.parse(beginDate));
				}
				if(endDate!=null&&!endDate.isEmpty()){
					course.setEnd_date(dateSdf.parse(endDate));
				}
				if(onliveTime!=null&&!onliveTime.isEmpty()){
					course.setOnlive_time(new Time(timeSdf.parse(onliveTime).getTime()));
				}
				course.setCreate_date(new Date());
				courseService.save(course);
				chapter.setCourse_id(course.getId());
				chapter.setOrder_num(1);
				if(tradeSkillIds!=null&&!tradeSkillIds.trim().isEmpty()){
					String[] tradeskillIds=tradeSkillIds.split(",");
					for (String id : tradeskillIds) {
						Map<String,Object> map=new HashMap<String,Object>();
						map.put("course_id",course.getId());
						map.put("trade_skill_id",id);
						courseService.addCourseTradeSkill(map);
					}
				}
			    if(softwareIds!=null&&!softwareIds.trim().isEmpty()){
			    	String[] softwareids=softwareIds.split(",");
					for (String id : softwareids) {
						Map<String,Object> map=new HashMap<String,Object>();
						map.put("course_id",course.getId());
						map.put("software_id",id);
						courseService.addCourseSoftware(map);
						
					}
			    }
				
				
			}else{
				courseService.deleteCourseTradeSkill(chapter.getCourse_id());
				courseService.deleteCourseSoftware(chapter.getCourse_id());
				if(tradeSkillIds!=null&&!tradeSkillIds.trim().isEmpty()){
					String[] tradeskillIds=tradeSkillIds.split(",");
					for (String id : tradeskillIds) {
						Map<String,Object> map=new HashMap<String,Object>();
						map.put("course_id",chapter.getCourse_id());
						map.put("trade_skill_id",id);
						courseService.addCourseTradeSkill(map);
					}
				}
				if(softwareIds!=null&&!softwareIds.trim().isEmpty()){
					String[] softwareids=softwareIds.split(",");
					for (String id : softwareids) {
						Map<String,Object> map=new HashMap<String,Object>();
						map.put("course_id",chapter.getCourse_id());
						map.put("software_id",id);
						courseService.addCourseSoftware(map);
					}
				}
				course.setId(chapter.getCourse_id());
				course.setType_id(type_id);
				Program p=new Program();
				p.setContent(program_content);
				if(null==program_id&&p.getContent()!=null&&!p.getContent().isEmpty()){
					courseService.addProgram(p);
					program_id=p.getId();
				}
				course.setProgram_id(program_id);
				if(beginDate!=null&&!beginDate.isEmpty()){
					course.setBegin_date(dateSdf.parse(beginDate));
				}
				if(endDate!=null&&!endDate.isEmpty()){
					course.setEnd_date(dateSdf.parse(endDate));
				}
				if(onliveTime!=null&&!onliveTime.isEmpty()){
					course.setOnlive_time(new Time(timeSdf.parse(onliveTime).getTime()));
				}
				System.out.println(course.getOnlive_time());
				courseService.updateIgnoreNull(course);
				Integer maxOrder=chapterService.getMaxOrderByCourseId(chapter.getCourse_id());
				chapter.setOrder_num(++maxOrder);
			}
			if(course.getType().equals("1")){
				chapter.setVideo_status("N");
			}else{
				chapter.setVideo_status("Y");
			}
			if("3".equals(course.getType())){
				chapter.setCheck_status("Y");
			}else{
				chapter.setCheck_status("C");
			}
			
		    chapter.setDelflag("N");
			chapter.setApply_date(new Date());
			chapterService.save(chapter);
			return new Result(Result.SUCCESS,"保存成功!",chapter);
		} else {
			return new Result(Result.FAILURE,"数据传输失败!",null);
		}
	}
	@RequestMapping("/publicCourse")
	@ResponseBody
	public Result publicCourse(Course course){
		course.setIs_public("Y");
		courseService.updateIgnoreNull(course);
		return new Result(Result.SUCCESS,"公开成功!",null);
	}
	@RequestMapping("/updateCourse")
	@Transactional
	public @ResponseBody Result update(Course course,Chapter chapter,Long cid,Long chapter_id,String tradeSkillIds,String softwareIds,Integer type_id,Integer program_id,String program_content,String beginDate,String endDate,String onliveTime) throws Exception {
		if (course != null&&chapter!=null) {
			Course c=courseService.getCourseById(cid);
			if(course.getIs_apply().equals("Y")){
				chapter.setCheck_status("C");
				if(c!=null&&"Y".equals(c.getIs_public())&&"Y".equals(c.getIs_apply())){
					course.setIs_public("Y");
				}else{
					course.setIs_public("N");
				}
			}
			else if("Y".equals(course.getIs_free())){
				course.setIs_public("Y");
			}else{
				if("3".equals(course.getType())){
					
				}else{
					Integer n=chapterService.getYesCheckChapterByCourseId(cid);
					if("N".equals(c.getIs_free())&&n.equals(0)){
						course.setCheck_delflag("Y");
						course.setIs_public("N");
					}else{
						course.setCheck_delflag("N");
						course.setIs_public("Y");
					}
				}
			}
			course.setId(cid);
			course.setType_id(type_id);
			Program p=new Program();
			p.setContent(program_content);
			if(null==program_id&&p.getContent()!=null&&!p.getContent().isEmpty()){
				courseService.addProgram(p);
				program_id=p.getId();
			}
			course.setProgram_id(program_id);
			if(beginDate!=null&&!beginDate.isEmpty()){
				course.setBegin_date(dateSdf.parse(beginDate));
				}
			if(endDate!=null&&!endDate.isEmpty()){
				course.setEnd_date(dateSdf.parse(endDate));
			}
			if(onliveTime!=null&&!onliveTime.isEmpty()){
				course.setOnlive_time(new Time(timeSdf.parse(onliveTime).getTime()));
			}
			courseService.updateIgnoreNull(course);
			chapter.setCourse_id(cid);
			courseService.deleteCourseTradeSkill(cid);
			courseService.deleteCourseSoftware(cid);
			if(type_id==1){
				if(tradeSkillIds!=null&&!tradeSkillIds.trim().isEmpty()){
					String[] tradeskillIds=tradeSkillIds.split(",");
				for (String id : tradeskillIds) {
					Map<String,Object> map=new HashMap<String,Object>();
					map.put("course_id",chapter.getCourse_id());
					map.put("trade_skill_id",id);
					courseService.addCourseTradeSkill(map);
				}
				}
				 if(softwareIds!=null&&!softwareIds.trim().isEmpty()){
					 String[] softwareids=softwareIds.split(",");
						for (String id : softwareids) {
							Map<String,Object> map=new HashMap<String,Object>();
							map.put("course_id",chapter.getCourse_id());
							map.put("software_id",id);
							courseService.addCourseSoftware(map);
						}
				 }
			}
			chapter.setId(chapter_id);
			Chapter c1=chapterService.getChapterById(chapter_id);
			if("3".equals(course.getType())){
				chapter.setCheck_status("Y");
			}else{
				chapter.setCheck_status("C");
			}
			if(!c1.getVedio_url().equals(chapter.getVedio_url())){
				chapter.setVideo_status("N");
			}
			chapterService.updateIgnoreNull(chapter);
			return new Result(Result.SUCCESS,"保存成功!",chapter);
		} else {
			return new Result(Result.FAILURE,"数据传输失败!",null);
		}
	}
	/**
	 * 立即报名相关课程
	 * @param course_id
	 * @return
	 */
	@RequestMapping("/joinCourse")
	public @ResponseBody Result joinCourse(Long course_id,HttpSession session) {
		JSONObject user=JSONObject.parseObject(session.getAttribute("loginUser").toString());
		Long userId = user.getLong("id");
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("user_id", userId);
		map.put("course_id", course_id);
		map.put("type", "1");
		LearnerCourse learner_course=learnerCourseService.getLearnerCourseByUserMap(map);
		if(learner_course==null){
			learner_course=new LearnerCourse();
			learner_course.setCourse_id(course_id);
			learner_course.setType("1");
			learner_course.setCreate_date(new Date());
			learner_course.setDelflag("N");
			learner_course.setIs_free("Y");
			learner_course.setLearn_degree(0);
			learner_course.setUser_id(userId);
			learnerCourseService.save(learner_course);
			Course course=courseService.getCourseById(course_id);
			if(course!=null){
				Integer join_nums=course.getJoin_nums()+1;
				course.setJoin_nums(join_nums);
				courseService.updateIgnoreNull(course);
			}
		}else{
			if("Y".equals(learner_course.getDelflag())){
				learner_course.setDelflag("N");
				Course course=courseService.getCourseById(course_id);
				if(course!=null){
					Integer join_nums=course.getJoin_nums()+1;
					course.setJoin_nums(join_nums);
					courseService.updateIgnoreNull(course);
				}
			}else{
				learner_course.setDelflag("Y");
				Course course=courseService.getCourseById(course_id);
				if(course!=null){
					Integer join_nums=course.getJoin_nums()-1;
					course.setJoin_nums(join_nums);
					courseService.updateIgnoreNull(course);
				}
			}
			learnerCourseService.updateIgnoreNull(learner_course);
		}
		return new Result(Result.SUCCESS,"成功",null);
	}
	@RequestMapping("/getCollectCourseOfMyOrNot")
	@ResponseBody
	public  Result getCollectCourseOfMyOrNot(Long course_id,HttpSession session) {
		JSONObject user=JSONObject.parseObject(session.getAttribute("loginUser").toString());
		if(user!=null){
			Long userId = user.getLong("id");
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("user_id", userId);
			map.put("course_id", course_id);
			map.put("type", "3");
			LearnerCourse learner_course=learnerCourseService.getLearnerCourseByUserMap(map);
			if(learner_course==null||"Y".equals(learner_course.getDelflag())&&"3".equals(learner_course.getType())){
				return new Result(Result.SUCCESS,"成功",false);
			}else{
				return new Result(Result.SUCCESS,"成功",true);
			}
		}else{
			return new Result(Result.SUCCESS,"成功",false);
		}
		
		
	}
	/**
	 * 立即报名相关课程
	 * @param course_id
	 * @return
	 */
	@RequestMapping("/collectCourse")
	public @ResponseBody Result collectCourse(Long course_id,HttpSession session) {
		JSONObject user=JSONObject.parseObject(session.getAttribute("loginUser").toString());
		Long userId = user.getLong("id");
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("user_id", userId);
		map.put("course_id", course_id);
		map.put("type", "3");
		LearnerCourse learner_course=learnerCourseService.getLearnerCourseByUserMap(map);
		if(learner_course==null){
			learner_course=new LearnerCourse();
			learner_course.setCourse_id(course_id);
			learner_course.setType("3");
			learner_course.setCreate_date(new Date());
			learner_course.setDelflag("N");
			learner_course.setIs_free("Y");
			learner_course.setLearn_degree(0);
			learner_course.setUser_id(userId);
			learnerCourseService.save(learner_course);
			Course course=courseService.getCourseById(course_id);
			Integer join_nums=course.getJoin_nums()+1;
			course.setJoin_nums(join_nums);
			courseService.updateIgnoreNull(course);
		}else{
			if("Y".equals(learner_course.getDelflag())){
				learner_course.setDelflag("N");
				Course course=courseService.getCourseById(course_id);
				Integer join_nums=course.getJoin_nums()+1;
				course.setJoin_nums(join_nums);
				courseService.updateIgnoreNull(course);
			}else{
				learner_course.setDelflag("Y");
				Course course=courseService.getCourseById(course_id);
				Integer join_nums=course.getJoin_nums()-1;
				course.setJoin_nums(join_nums);
				courseService.updateIgnoreNull(course);
			}
			learnerCourseService.updateIgnoreNull(learner_course);
		}
		
		return new Result(Result.SUCCESS,"成功",null);
	}
	@RequestMapping("/delete")
	public @ResponseBody Result delete(Long course_id) {
		 Long chapterNums=chapterService.getHasChapterNumsOfCourse(course_id);
		 if(chapterNums>0){
			 return new Result(Result.FAILURE,"您的课程下有章节信息，不能删除该课程",null);
		 }
		 Course c=new Course();
		 c.setId(course_id);
		 c.setTeacher_delflag("Y");
		 courseService.updateIgnoreNull(c);
		return new Result(Result.SUCCESS,"删除成功!",null);
	}
	 
	@RequestMapping("/getTradeSkillsBySoftwareIds")
	@ResponseBody
	public Result getTradeSkillsBySoftwareIds(String softwareIds){
		Set<TradeSkill> ts=new HashSet<TradeSkill>();
		Set<Integer> ids=new HashSet<Integer>();
		List<TradeSkill> tradeSkills=new ArrayList<TradeSkill>();
		if(softwareIds!=null&&!softwareIds.isEmpty()){
			String[] ids1=softwareIds.split(",");
			for (String id : ids1) {
				ids.add(Integer.valueOf(id));
				
			}
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("ids", ids);
			tradeSkills=tradeSkillService.getTradeSkillsBySoftwareIds(map);
		}
		return new Result(Result.SUCCESS,"成功",tradeSkills);
	}
	@RequestMapping("/getOnlineSite")
	@ResponseBody
	public Result getOnlineSite(String course_id,String chapter_id){
		String teacher_key=CreateKey.getTeachKey(course_id+"_"+chapter_id, chapter_id);
		String student_key=CreateKey.getStudyKey(course_id+"_"+chapter_id, chapter_id);
		Chapter chapter=new Chapter();
		chapter.setId(Long.parseLong(chapter_id));
		chapter.setUnion_site(student_key);
		chapterService.updateIgnoreNull(chapter);
		Course c=courseService.getCourseById(Long.valueOf(course_id));
		if("Y".equals(c.getIs_save_video())){
			CreateVideoRecord.doExeUrl(course_id+"_"+chapter_id, chapter_id);
		}
		return new Result(Result.SUCCESS,"成功",teacher_key);
	}
}
