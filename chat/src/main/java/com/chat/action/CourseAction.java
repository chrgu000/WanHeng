package com.chat.action;

import java.net.URLDecoder;
import java.util.Map;

import org.jdesktop.application.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.cgwas.common.json.entity.Result;
import com.cgwas.common.utils.OSSFilesUtil;
import com.chat.entity.Chapter;
import com.chat.service.IGroupChatWebSocketService;
import com.chat.util.ReadVideo;
import com.chat.util.SearchTransCodeInfo;

/**课程
 *  Author yangjun
 */
/**
 * 课程 Author yangjun
 */
@Controller
@Transactional
@RequestMapping("com/courseAction")
public class CourseAction {
	 
	@Autowired 
    private  IGroupChatWebSocketService groupChatWebSocketService;
	/**
	 * 视频转码成功后的回调处理
	 * @param map
	 * @return
	 * @throws Exception
	 */
    @RequestMapping("/transcodeFinished")
    @ResponseBody
    public Result transcodeFinished(@RequestBody Map<String,String> map) throws Exception{
    	String message=map.get("Message");
    	JSONObject obj=JSONObject.parseObject(message);
    	String jobId=obj.getString("jobId");
    	String state=obj.getString("state");
    	String result=SearchTransCodeInfo.doExeUrl(jobId);
    	String file_path=JSONObject.parseObject(JSONObject.parseObject(result).getJSONObject("JobList").getJSONArray("Job").get(0).toString()).getJSONObject("Input").get("Object").toString();
		String new_file_path=JSONObject.parseObject(JSONObject.parseObject(result).getJSONObject("JobList").getJSONArray("Job").get(0).toString()).getJSONObject("Output").getJSONObject("OutputFile").get("Object").toString();
		file_path=URLDecoder.decode(file_path, "utf-8");
		new_file_path=URLDecoder.decode(new_file_path, "utf-8");
		if(file_path.indexOf("cgwas/cloud")!=-1){
			OSSFilesUtil.deleteFile(file_path);
		}
		Chapter chapter = new Chapter();
    	if("Success".equals(state)){
			chapter.setVideo_status("Y");
			chapter.setVedio_url(new_file_path);
			groupChatWebSocketService.updateVideoStatus(chapter);
    	}else{
			chapter.setVideo_status("C");
			chapter.setCourse_length("未知");
			chapter.setVedio_url(new_file_path);
			groupChatWebSocketService.updateVideoStatus(chapter);
    	}
    	return new Result(Result.SUCCESS,"成功",null);
    }
	 
}
