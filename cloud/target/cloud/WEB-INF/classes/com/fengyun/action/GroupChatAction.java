package com.fengyun.action;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageUtils;
import com.cgwas.common.json.entity.Result;
import com.fengyun.entity.Member;
import com.fengyun.entity.Message;
import com.fengyun.service.IMemberService;
import com.fengyun.util.GenerateFile;
import com.fengyun.util.HTTPConfig;
import com.fengyun.util.HttpUtil;
import com.fengyun.util.Pinyin4jUtil;

/**
 * Author yangjun
 */
@Controller
@Transactional
@RequestMapping("com/groupChatAction")
public class GroupChatAction {
	private SimpleDateFormat timeSdf = new SimpleDateFormat("HH:mm:ss");
	private SimpleDateFormat sdf = new SimpleDateFormat("MM/dd HH:mm:ss");
	@Autowired
	private IMemberService memberService;
	@RequestMapping("/addMemberMessage")
	@ResponseBody
	public Result addMemberMessage(Long group_id,Long message_id){
		Map<String,Object> query=new HashMap<String,Object>();
		query.put("group_id", group_id);
		List<Member> members=memberService.getGroupMembers(query);
		for (Member m : members) {
			Map<String,Object> mess=new HashMap<String,Object>();
			Map<String,Object> select=new HashMap<String,Object>();
			select.put("user_id", m.getUser_id());
			List<String> onlineStates=memberService.getOnlyOnlineState(select);
			if(onlineStates.contains("Y")){
				mess.put("readstate","Y");
			}else{
				mess.put("readstate","N");
			}
			
			mess.put("group_id",group_id);
			mess.put("user_id",m.getUser_id());
			mess.put("message_id",message_id);
			memberService.addMemberMessage(mess);
		}
		return new Result(Result.SUCCESS, "成功", null);
	}
	
	@RequestMapping("/searchGroup")
	@ResponseBody
	public Result searchGroup(String search,HttpSession session) {
		JSONObject user = JSONObject.parseObject(session.getAttribute(
				"loginUser").toString());
		Long user_id = user.getLong("id");
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("search", search);
		query.put("user_id", user_id);
		List<Map<String,Object>> groups = memberService.searchGroup(query);
		for (Map<String, Object> group : groups) {
			if(user_id.equals(Long.valueOf(""+group.get("group_master_id")))){
				group.put("is_master","Y");
			}else{
				group.put("is_master", "N");
			}
		}
		return new Result(Result.SUCCESS, "成功", groups);
	}
	@RequestMapping("/searchGroupMembers")
	@ResponseBody
	public Result searchGroupMembers(String search,Long group_id) {
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("search", search);
		query.put("group_id", group_id);
		List<Member> members = memberService.searchGroupMembers(query);
		sortMemebers(members);
		return new Result(Result.SUCCESS, "成功", members);
	}
	
	@RequestMapping("/checkIsMasterOfGroupOrNot")
	@ResponseBody
	public Result checkIsMasterOfGroupOrNot(HttpSession session, Long group_id)
			throws Exception {
		JSONObject user = JSONObject.parseObject(session.getAttribute(
				"loginUser").toString());
		Long user_id = user.getLong("id");
		Map<String, Long> query = new HashMap<String, Long>();
		query.put("group_id", group_id);
		Long master_group_id = memberService.getMasterGroupId(query);
		if (master_group_id.equals(user_id)) {
			return new Result(Result.SUCCESS, "成功", null);
		}
		return new Result(Result.FAILURE, "成功", null);

	}

	@RequestMapping("/createNewGroupChat")
	@ResponseBody
	public Result createNewGroupChat(HttpSession session, String ids,
			String name, String head_pic_url) {
		JSONObject user = JSONObject.parseObject(session.getAttribute(
				"loginUser").toString());
		Long group_master_id = user.getLong("id");
		// Long group_master_id = 6L;
		String[] userIds = ids.split(",");
		Map<String, Object> group = new HashMap<String, Object>();
		group.put("name", name);
		group.put("head_pic_url", head_pic_url);
		group.put("group_master_id", group_master_id);
		group.put("member_num", userIds.length+1);
		memberService.createNewGroupChat(group);
		for (String user_id : userIds) {
			createGroup(group, user_id);
		}
		createGroup(group, group_master_id+"");
		return new Result(Result.SUCCESS, "成功", null);
	}

	public void createGroup(Map<String, Object> group, String user_id) {
		Map<String, Object> member = new HashMap<String, Object>();
		member.put("user_id", user_id);
		Member m = memberService.getMemberById(member);
		member.put("nickname", m.getNickname());
		member.put("head_pic_url", m.getHead_pic_url());
		member.put("gender", m.getGender());
		member.put("tel", m.getTel());
		memberService.addGroupMemberInfo(member);
		member.put("member_id", member.get("id"));
		member.put("group_id", group.get("id"));
		memberService.addGroupMember(member);
	}

	@RequestMapping("/addFriendsToGroup")
	@ResponseBody
	public Result addFriendsToGroup(String ids, Long group_id) {
		String[] userIds = ids.split(",");
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("group_id", group_id);
		Integer memberNum = memberService.getGroupMemberNum(query);
		memberNum += userIds.length;
		for (String user_id : userIds) {
			Map<String, Object> member = new HashMap<String, Object>();
			member.put("user_id", user_id);
			Member m = memberService.getMemberById(member);
			member.put("nickname", m.getNickname());
			member.put("head_pic_url", m.getHead_pic_url());
			member.put("gender", m.getGender());
			member.put("tel", m.getTel());
			memberService.addGroupMemberInfo(member);
			member.put("member_id", member.get("id"));
			member.put("group_id", group_id);
			memberService.addGroupMember(member);
		}
		query.put("memberNum", memberNum);
		memberService.updateGroupMemberNum(query);
		return new Result(Result.SUCCESS, "成功", null);
	}

	@RequestMapping("/exitGroupChat")
	@ResponseBody
	public Result exitGroupChat(Long group_id, HttpSession session) {
		JSONObject user = JSONObject.parseObject(session.getAttribute(
				"loginUser").toString());
		Long user_id = user.getLong("id");
		// Long user_id = 6L;
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("user_id", user_id);
		query.put("group_id", group_id);
		Long member_id = memberService.getMemberIdByQuery(query);
		query.put("member_id", member_id);
		memberService.deleteGroupMember(query);
		memberService.deleteMemberById(query);
		Integer memberNum = memberService.getGroupMemberNum(query);
		memberNum--;
		query.put("memberNum", memberNum);
		memberService.updateGroupMemberNum(query);
		return new Result(Result.SUCCESS, "成功", null);
	}

	@RequestMapping("/deleteGroupMembers")
	@ResponseBody
	public Result deleteGroupMembers(Long group_id, String ids) {
		String[] userIds = ids.split(",");
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("group_id", group_id);
		Integer memberNum = memberService.getGroupMemberNum(query);
		memberNum -= userIds.length;
		for (String user_id : userIds) {
			Map<String, Object> cache = new HashMap<String, Object>();
			cache.put("group_id", group_id);
			cache.put("user_id", user_id);
			Long member_id = memberService.getMemberIdByQuery(cache);
			query.put("member_id", member_id);
			memberService.deleteGroupMember(query);
			memberService.deleteMemberById(query);
		}
		if (memberNum > 0) {
			query.put("memberNum", memberNum);
			memberService.updateGroupMemberNum(query);
		} else {
			memberService.deleteGroupById(query);
		}
		return new Result(Result.SUCCESS, "成功", null);
	}

	@RequestMapping("/getMyChatGroups")
	@ResponseBody
	public Result getMyChatGroups(HttpSession session) {
		JSONObject user = JSONObject.parseObject(session.getAttribute(
		"loginUser").toString()); 
		Long user_id = user.getLong("id");
//		Long user_id = 6L;
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("user_id", user_id);
		List<Map<String, Object>> groups = memberService.getMyChatGroups(query);
		for (Map<String, Object> group : groups) {
			Map<String, Object> select = new HashMap<String, Object>();
			select.put("user_id", user_id);
			select.put("group_id", group.get("id"));
			System.out.println("===================");
			System.out.println(select);
			Long num = memberService.getGroupUnReadNum(select);
			if(user_id.equals(Long.valueOf(""+group.get("group_master_id")))){
				group.put("is_master","Y");
			}else{
				group.put("is_master", "N");
			}
			group.put("unReadNum", num);
		}
		return new Result(Result.SUCCESS, "成功", groups);
	}
	@RequestMapping("/getGroupChatMessage")
	@ResponseBody
	public Result getChatMessage(HttpSession session, Member member,
			Integer pageSize, Integer pageNo) {
		JSONObject user = JSONObject.parseObject(session.getAttribute(
				"loginUser").toString());
		Long user_id = user.getLong("id");
		Map<String,Object>  query=new HashMap<String,Object>();
		query.put("userId", user_id);
		String result1=HttpUtil.doPost(HTTPConfig.HTTP_PREFIX+"/cgwas/cloud/getUserInfoById.action", query);
		JSONArray users=JSONArray.parseObject(result1).getJSONArray("data");
		JSONObject obj=users.getJSONObject(0);
		String name=obj.getString("name");
		if(name==null||name.isEmpty()){
			name=user.getString("nickname");
		}
		StringBuilder sb = new StringBuilder();
		Map<String, String> params = new HashMap<String, String>();
		params.put("pageSize", String.valueOf(pageSize));
		params.put("pageNo", String.valueOf(pageNo));
		Page page = PageUtils.createPage(params);
		page = memberService.page1(page, member);
		System.out.println(page.getDataList());
		@SuppressWarnings("unchecked")
		List<Message> messages = (List<Message>) page.getDataList();
		for (int i = messages.size() - 1; i >= 0; i--) {
			Message msg = messages.get(i);
			// 聊天记录是自己的
			if (msg.getUser_id().equals(user_id)) {
				if (i == messages.size() - 1) {
					sb.append("<div group_msg_id='"
							+ messages.get(messages.size() - 1).getId() + "'>");
				}
				String message = "";
				if (msg.getMessage().indexOf("</file>") == -1) {
					message = msg.getMessage();
				} else {
					Map<String, String> cache = GenerateFile.generate(msg
							.getMessage());
					String filetype = (String) cache.get("filetype");
					String src = "";
					if ("suppress".equals(filetype)) {
						src = "../../../../../static/img/file2.jpg";
					} else {
						src = "../../../../../static/img/file1.jpg";
					}
					message = "<div class='divdivright'><div class='divdiv2'><p class='filenameClass'>"
							+ cache.get("filename")
							+ "</p><p><span>"
							+ cache.get("filesize")
							+ "</span><span class='divdownload'><a href='"
							+ cache.get("filepath")
							+ "' class='divbutton' >下载</a></span></p></div><div class='divdiv1'><img src='"
							+ src
							+ "' style='width: 100%;height: 100%;'></div></div>";

				}
				sb.append(" <div style='float:right;margin:2px 5px'>"
						+ "<font  style='color:#000;float:right;font-size:14px;'> "
						+ name
						+ "&nbsp;&nbsp;"
						+ sdf.format(msg.getTime())
						+ "</font><br/> <div style='word-wrap: break-word;word-break: "
						+ "normal;float:right;padding:5px;max-width:220px;"
						+ "background-color:#bf2934;color:#fff;border-radius:5px;display:"
						+ "inline-block;font-size:14px;margin:5px 10px 5px 0px'' >"
						+ message
						+ "</div></div><div style='clear:both'></div>");
				if (i == 0) {
					sb.append("</div><i style='display:none'>"
							+ messages.get(messages.size() - 1).getId()
							+ "</i>");
				}
			} else {
				// 聊天记录是好友的。
				if (i == messages.size() - 1) {
					sb.append("<div group_msg_id='"
							+ messages.get(messages.size() - 1).getId() + "'>");
				}
				String message = "";
				if (msg.getMessage().indexOf("</file>") == -1) {
					message = msg.getMessage();
				} else {
					Map<String, String> cache = GenerateFile.generate(msg
							.getMessage());
					String filetype = (String) cache.get("filetype");
					String src = "";
					if ("suppress".equals(filetype)) {
						src = "../../../../../static/img/file2.jpg";
					} else {
						src = "../../../../../static/img/file1.jpg";
					}
					message = "<div class='divdivleft'><div class='divdiv2'><p  class='filenameClass'>"
							+ cache.get("filename")
							+ "</p><p><span>"
							+ cache.get("filesize")
							+ "</span><span class='divdownload'><a href='"
							+ cache.get("filepath")
							+ "' class='divbutton' >下载</a></span></p></div><div class='divdiv1'><img src='"
							+ src
							+ "' style='width: 100%;height: 100%;'></div></div>";

				}
				sb.append(" <div style='float:left;margin:2px 5px;text-align:left'><font"
						+ "  style='color:#a7a7ad;font-size:14px;'>"
						+ msg.getNickname()
						+ "&nbsp;&nbsp;"
						+ sdf.format(msg.getTime())
						+ "</font><br/>  <div style='word-wrap: break-word;word-break: normal;"
						+ "text-align:left;padding:5px;max-width:220px;display:inline-block;"
						+ "background-color:#fff;border-radius:5px;display:inline-block;"
						+ "font-size:14px;margin:5px 0px 5px 10px'' >"
						+ message
						+ "</div>"
						+ "</div><div style='clear:both'></div>");
				if (i == 0) {
					sb.append("</div><i style='display:none'>"
							+ messages.get(messages.size() - 1).getId()
							+ "</i>");
				}
			}
		}
		params.put("message", sb.toString());
		return new Result(Result.SUCCESS, "成功", params);
	}
	private void sortMemebers(List<Member> members) {
		Collections.sort(members, new Comparator<Member>() {
			@Override
			public int compare(Member o1, Member o2) {
					if ((o1.getReal_name() == null || o1.getReal_name().trim()
							.isEmpty())
							&& (o2.getReal_name() == null || o2.getReal_name()
									.trim().isEmpty())) {
						return Pinyin4jUtil
								.getPinYin(o1.getNickname())
								.compareTo(
										Pinyin4jUtil.getPinYin(o2.getNickname()));
					} else if ((o1.getReal_name() != null && !o1.getReal_name()
							.trim().isEmpty())
							&& (o2.getReal_name() == null || o2.getReal_name()
									.trim().isEmpty())) {
						return Pinyin4jUtil
								.getPinYin(o1.getReal_name())
								.compareTo(
										Pinyin4jUtil.getPinYin(o2.getNickname()));
					} else if ((o1.getReal_name() == null || o1.getReal_name()
							.trim().isEmpty())
							&& (o2.getReal_name() != null && !o2.getReal_name()
									.trim().isEmpty())) {
						return Pinyin4jUtil.getPinYin(o1.getNickname())
								.compareTo(
										Pinyin4jUtil.getPinYin(o2
												.getReal_name()));
					} else {
						return Pinyin4jUtil.getPinYin(o1.getReal_name())
								.compareTo(
										Pinyin4jUtil.getPinYin(o2
												.getReal_name()));
					}
			}
		});
	}

	@RequestMapping("/getGroupInfoById")
	@ResponseBody
	public Result getGroupFriendsById(Long group_id,HttpSession session) throws Exception {
		JSONObject user = JSONObject.parseObject(session.getAttribute(
				"loginUser").toString());
		Long user_id = user.getLong("id");
		Map<String, Long> query = new HashMap<String, Long>();
		query.put("group_id", group_id);
		Map<String,Object> group=memberService.getGroupInfoById(query);
		System.out.println(group);
		System.out.println(user_id);
		if(user_id.equals(Long.valueOf(""+group.get("group_master_id")))){
			group.put("is_master","Y");
		}else{
			group.put("is_master", "N");
		}
		List<Member> members = memberService.getGroupFriendsById(query);
		
		for (Member member : members) {
			if(member.getUser_id().equals(user_id)){
				if(member.getReal_name()!=null){
					group.put("nickname", member.getReal_name());
				}else{
					group.put("nickname", member.getNickname());
				}
			}
		}
		sortMemebers(members);
		group.put("members", members);
		return new Result(Result.SUCCESS, "成功", group);
	}

	// TODO群聊状态如何实现
	@RequestMapping("/updateGroupReadState")
	@ResponseBody
	public Result updateReadState(Long group_id, HttpSession session) {
		JSONObject user = JSONObject.parseObject(session.getAttribute(
				"loginUser").toString());
		Long user_id = user.getLong("id");
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("group_id", group_id);
		query.put("user_id", user_id);
		memberService.updateGroupReadState(query);
		return new Result(Result.SUCCESS, "成功", null);
	}
    @RequestMapping("/changeGroupName")
    @ResponseBody
    public Result changeGroupName(String name,Long group_id){
    	Map<String, Object> query = new HashMap<String, Object>();
		query.put("group_id", group_id);
		query.put("name", name);
		memberService.changeGroupName(query);
		return new Result(Result.SUCCESS, "成功", null);
    }
    @RequestMapping("/changeGroupMemberRealName")
    @ResponseBody
    public Result changeGroupMemberRealName(String real_name, Long group_id,HttpSession session){
    	JSONObject user = JSONObject.parseObject(session.getAttribute(
				"loginUser").toString());
		Long user_id = user.getLong("id");
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("real_name", real_name);
		query.put("user_id", user_id);
		query.put("group_id", group_id);
		memberService.changeGroupMemberRealName(query);
		return new Result(Result.SUCCESS, "成功", null);
    }
	@RequestMapping("/update")
	public @ResponseBody
	Result update(Member member) {
		if (member != null) {
			memberService.updateIgnoreNull(member);
			return new Result("保存成功!");
		} else {
			return new Result("数据传输失败!");
		}
	}

}