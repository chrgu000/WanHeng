package com.fengyun.action;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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
 * 单聊
 * Author yangjun
 */
@Controller
@Transactional
@RequestMapping("com/singleChatAction")
public class SingleChatAction {
	private SimpleDateFormat timeSdf = new SimpleDateFormat("HH:mm:ss");
	private SimpleDateFormat sdf = new SimpleDateFormat("MM/dd HH:mm:ss");
	@Autowired
	private IMemberService memberService;
	/**
	 * 获取自己在单聊中的名称
	 * @param session
	 * @return
	 */
	@RequestMapping("getSingleChatOwnName")
	@ResponseBody
	public Map<String, Object> getSingleChatOwnName(HttpSession session) {
		JSONObject user = JSONObject.parseObject(session.getAttribute(
				"loginUser").toString());
		Long userId = user.getLong("id");
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("userId", userId);
		String result1 = HttpUtil.doPost(HTTPConfig.HTTP_PREFIX
				+ "/cgwas/cloud/getUserInfoById.action", query);
		JSONArray users = JSONArray.parseObject(result1).getJSONArray("data");
		JSONObject obj = users.getJSONObject(0);
		String name = obj.getString("name");
		if (name == null || name.isEmpty()) {
			name = user.getString("nickname");
		}
		query.clear();
		query.put("name", name);
		query.put("user_id", userId);
		query.put("head_pic_url", obj.getString("head_pic_path"));
		query.put("time", timeSdf.format(new Date()));
		return query;
	}
    /**
     * 更改自己在单聊中的聊天在线状态
     * @param session
     * @param state
     * @param user_id
     * @return
     */
	@RequestMapping("/changeOnlineState")
	@ResponseBody
	public Result changeOnlineState(HttpSession session, String state,
			Long user_id) {
		if (user_id == null) {
			JSONObject user = JSONObject.parseObject(session.getAttribute(
					"loginUser").toString());
			user_id = user.getLong("id");
		}
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("user_id", user_id);
		query.put("state", state);
		memberService.updateOnlieState(query);
		return new Result(Result.SUCCESS, "成功", null);
	}
	/**
	 * 更改自己在单聊中的真实姓名
	 * @param real_name
	 * @param friend_id
	 * @param session
	 * @return
	 */
	@RequestMapping("/changeRealName")
	@ResponseBody
	public Result changeRealName(String real_name, Long friend_id,
			HttpSession session) {
		JSONObject user = JSONObject.parseObject(session.getAttribute(
				"loginUser").toString());
		Long user_id = user.getLong("id");
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("real_name", real_name);
		query.put("user_id", user_id);
		query.put("friend_id", friend_id);
		memberService.changeRealName(query);
		return new Result(Result.SUCCESS, "成功", null);
	}
     /**
      * 是否同意别人邀你为好友关系
      * @param session
      * @param friend_id
      * @param agree
      * @param msg
      * @return
      */
	@RequestMapping("/agree")
	@ResponseBody
	public Result agree(HttpSession session, Long friend_id, String agree,
			String msg) {
		JSONObject user = JSONObject.parseObject(session.getAttribute(
				"loginUser").toString());
		Long user_id = user.getLong("id");
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("agree", agree);
		query.put("reply_msg", msg);
		query.put("user_id", user_id);
		query.put("friend_id", friend_id);
		query.put("reply_flag", "N");
		memberService.agree(query);
		query.put("user_id", friend_id);
		query.put("friend_id", user_id);
		query.put("reply_flag", "Y");
		query.put("progress_state", "C");
		memberService.agree(query);

		return new Result(Result.SUCCESS, "成功", null);
	}
    
	@RequestMapping("changeProgressState")
	@ResponseBody
	public Result changeProgressState(HttpSession session) {
		JSONObject user = JSONObject.parseObject(session.getAttribute(
				"loginUser").toString());
		Long user_id = user.getLong("id");
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("user_id", user_id);
		memberService.changeProgressState(query);
		return new Result(Result.SUCCESS, "成功", null);
	}
    /**
     * 删除我的好友
     * @param session
     * @param friend_id
     * @return
     */
	@RequestMapping("/deleteMyFriend")
	@ResponseBody
	public Result deleteMyFriend(HttpSession session, Long friend_id) {
		JSONObject user = JSONObject.parseObject(session.getAttribute(
				"loginUser").toString());
		Long user_id = user.getLong("id");
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("user_id", user_id);
		query.put("friend_id", friend_id);
		memberService.deleteMyFriend(query);
		return new Result(Result.SUCCESS, "成功", null);
	}

	
	/**
	 * 获取我的新好友
	 * @param session
	 * @return
	 */
	@RequestMapping("/getMyNewFriend")
	@ResponseBody
	public Result getMyNewFriend(HttpSession session) {
		JSONObject user = JSONObject.parseObject(session.getAttribute(
				"loginUser").toString());
		Long user_id = user.getLong("id");
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("user_id", user_id);
		List<Member> members = memberService.getMyNewFriend(query);
		int count = 0;
		for (Member member : members) {
			if (member.getIs_agree() == null || member.getIs_agree().isEmpty()
					|| !"Y".equals(member.getProgress_state())) {
				count++;
			}
		}
		return new Result(Result.SUCCESS, String.valueOf(count), members);
	}
	//添加员工到好友列表里
	@RequestMapping("/addEmployeeToChatMember")
	@ResponseBody
	public Result addEmployeeToChatMember(Long user_id,
			String user_nickname,String emp_tel,Long employee_id,String emp_nickname) {
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("userId", employee_id);
		Map<String, Object> cache = new HashMap<String, Object>();
		cache.put("user_id", employee_id);
		cache.put("friend_id", user_id);
		Member mr = memberService.checkIsMyFriend(cache);
		if (mr == null) {
			String result1 = HttpUtil.doPost(HTTPConfig.HTTP_PREFIX
					+ "/cgwas/cloud/getUserInfoById.action", query);
			JSONArray users = JSONArray.parseObject(result1).getJSONArray("data");
			JSONObject obj = users.getJSONObject(0);
			Member member = new Member();
			member.setHead_pic_url(obj.getString("head_pic_path"));
			member.setGender(obj.getString("sex"));
			member.setUser_id(user_id);
			member.setFriend_id(employee_id);
			member.setApply_msg("我们已经是好友拉，开始聊天吧...");
			member.setState("Y");
			member.setTel(emp_tel);
			member.setNickname(emp_nickname);
			member.setIs_agree("Y");
			member.setProgress_state("Y");
			Map<String, Object> select = new HashMap<String, Object>();
			select.put("search", user_nickname);
			List<Member> members = memberService.searchFriend(select);
			if (members.size() > 0) {
				memberService.save(member);
				Member m = members.get(0);
				Long u_id = member.getFriend_id();
				m.setUser_id(u_id);
				Map<String, Object> search = new HashMap<String, Object>();
				search.put("user_id", u_id);
				List<String> onlineStates = memberService
						.getOnlyOnlineState(search);
				if (onlineStates.contains("Y")) {
					m.setState("Y");
				} else {
					m.setState("N");
				}
				m.setProgress_state("C");
				m.setIs_agree("Y");
				m.setFriend_id(user_id);
				memberService.save(m);
			}
		}
		return new Result(Result.SUCCESS, "", null);
	}
	/**
	 * 申请添加好友
	 * @param session
	 * @param member
	 * @param msg
	 * @return
	 */
	@RequestMapping("/joinMemeber")
	@ResponseBody
	public Result joinMemeber(HttpSession session, Member member, String msg) {
		JSONObject user = JSONObject.parseObject(session.getAttribute(
				"loginUser").toString());
		Long user_id = user.getLong("id");
		if (user_id.equals(member.getFriend_id())) {
			return new Result(Result.FAILURE, "不能添加自己为好友!", null);
		}
		String nickname = user.getString("nickname");
		member.setUser_id(user_id);
		member.setApply_msg(msg);
		member.setState("Y");
		int num = memberService.isExistOrNot(member);
		if (num == 0) {
			memberService.save(member);
			Map<String, Object> query = new HashMap<String, Object>();
			query.put("search", nickname);
			List<Member> members = memberService.searchFriend(query);
			if (members.size() > 0) {
				Member m = members.get(0);
				Long u_id = member.getFriend_id();
				m.setUser_id(u_id);
				Map<String, Object> select = new HashMap<String, Object>();
				select.put("user_id", u_id);
				List<String> onlineStates = memberService
						.getOnlyOnlineState(select);
				if (onlineStates.contains("Y")) {
					m.setState("Y");
				} else {
					m.setState("N");
				}
				memberService.save(m);
			}
		} else {
			memberService.updateApplyMsg(member);
			member.setIs_agree(null);
			memberService.updateApplyState(member);
			Long friend_id = member.getFriend_id();
			member.setFriend_id(user_id);
			member.setUser_id(friend_id);
			member.setIs_agree("C");
			memberService.updateApplyState(member);
		}
		return new Result(Result.SUCCESS, "", null);
	}
	/**
	 * 检查指定人员是否为我的好友
	 * @param session
	 * @param friend_id
	 * @return
	 */
	@RequestMapping("/checkIsMyFriend")
	@ResponseBody
	public Result checkIsMyFriend(HttpSession session, Long friend_id) {
		JSONObject user = JSONObject.parseObject(session.getAttribute(
				"loginUser").toString());
		Long user_id = user.getLong("id");
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("user_id", friend_id);
		query.put("friend_id", user_id);
		Member m = memberService.checkIsMyFriend(query);
		if (m == null) {
			return new Result(Result.FAILURE, "不是好友关系", null);
		}
		return new Result(Result.SUCCESS, "是好友关系", null);
	}
     /**
      * 获取单聊的聊天记录
      * @param session
      * @param member
      * @param pageSize
      * @param pageNo
      * @return
      */
	@RequestMapping("/getChatMessage")
	@ResponseBody
	public Result getChatMessage(HttpSession session, Member member,
			Integer pageSize, Integer pageNo) {
		StringBuilder sb = new StringBuilder();
		JSONObject user = JSONObject.parseObject(session.getAttribute(
				"loginUser").toString());
		Long user_id = user.getLong("id");

		Map<String, Object> query = new HashMap<String, Object>();
		query.put("userId", user_id);
		String result1 = HttpUtil.doPost(HTTPConfig.HTTP_PREFIX
				+ "/cgwas/cloud/getUserInfoById.action", query);
		JSONArray users = JSONArray.parseObject(result1).getJSONArray("data");
		JSONObject obj = users.getJSONObject(0);
		String name = obj.getString("name");
		if (name == null || name.isEmpty()) {
			name = user.getString("nickname");
		}
		member.setUser_id(user_id);
		Map<String, String> params = new HashMap<String, String>();
		params.put("pageSize", String.valueOf(pageSize));
		params.put("pageNo", String.valueOf(pageNo));
		Page page = PageUtils.createPage(params);
		page = memberService.page(page, member);
		@SuppressWarnings("unchecked")
		List<Message> messages = (List<Message>) page.getDataList();
		for (int i = messages.size() - 1; i >= 0; i--) {
			Message msg = messages.get(i);
			// 聊天记录是自己的
			if (msg.getUser_id().equals(user_id)) {
				if (i == messages.size() - 1) {
					sb.append("<div single_msg_id='"
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
			} else if (msg.getFriend_id().equals(user_id)) {
				// 聊天记录是好友的。
				if (i == messages.size() - 1) {
					sb.append("<div single_msg_id='"
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
    /**
     * 获取对方的邀请好友消息
     * @param session
     * @return
     */
	@RequestMapping("/getDefaultMessage")
	@ResponseBody
	public Result getDefaultMessage(HttpSession session) {
		JSONObject user = JSONObject.parseObject(session.getAttribute(
				"loginUser").toString());
		Long user_id = user.getLong("id");
		String nickname = user.getString("nickname");
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("user_id", user_id);
		String message = memberService.getDefaultMessage(query);
		if (message == null) {
			message = "我是" + nickname + "我们开始聊天吧...";
		}
		return new Result(Result.SUCCESS, "成功", message);
	}
	/**
	 * 搜索好友
	 * @param search
	 * @return
	 */
	@RequestMapping("/searchFriend")
	@ResponseBody
	public Result searchFriend(String search) {
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("search", search);
		List<Member> members = memberService.searchFriend(query);
		return new Result(Result.SUCCESS, "成功", members);
	}
    
	@SuppressWarnings("unchecked")
	@RequestMapping("/isMyFrendOrNot")
	@ResponseBody
	public Result isMyFrendOrNot(String search, HttpSession session) {
		JSONObject user = JSONObject.parseObject(session.getAttribute(
				"loginUser").toString());
		Long user_id = user.getLong("id");
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("search", search);
		query.put("user_id", user_id);
		List<Member> members = memberService.isMyFrendOrNot(query);
		return new Result(Result.SUCCESS, "成功", members);
	}
	/**
	 * 搜索我的好友
	 * @param search
	 * @param session
	 * @return
	 */
	@RequestMapping("/searchMyFriend")
	@ResponseBody
	public Result searchMyFriend(String search, HttpSession session) {
		JSONObject user = JSONObject.parseObject(session.getAttribute(
				"loginUser").toString());
		Long user_id = user.getLong("id");
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("search", search);
		query.put("user_id", user_id);
		List<Member> members = memberService.searchMyFriend(query);
		sortMemebers(members);
		return new Result(Result.SUCCESS, "成功", members);
	}
	/**
	 * 更改我的消息阅读状态
	 * @param friend_id
	 * @param session
	 * @return
	 */
	@RequestMapping("/updateReadState")
	@ResponseBody
	public Result updateReadState(Long friend_id, HttpSession session) {
		JSONObject user = JSONObject.parseObject(session.getAttribute(
				"loginUser").toString());
		Long user_id = user.getLong("id");
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("friend_id", friend_id);
		query.put("user_id", user_id);
		memberService.updateReadState(query);
		return new Result(Result.SUCCESS, "成功", null);
	}
	/**
	 * 获取我的好友列表
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getMyFriends")
	@ResponseBody
	public Result getMyFriends(HttpSession session) throws Exception {
		JSONObject user = JSONObject.parseObject(session.getAttribute(
				"loginUser").toString());
		Long user_id = user.getLong("id");
		Map<String, Long> query = new HashMap<String, Long>();
		query.put("user_id", user_id);
		List<Member> members = memberService.getMyFriends(query);
		int onlineNum = 0;
		for (Member m : members) {
			Map<String, Object> select = new HashMap<String, Object>();
			select.put("user_id", user_id);
			select.put("friend_id", m.getFriend_id());
			Long num = memberService.getUnReadNum(select);
			String onlineState = memberService.getOnlineState(select);
			if ("Y".equals(onlineState)) {
				onlineNum++;
			}
			m.setState(onlineState);
			m.setUnReadNum(num);

		}
		sortMemebers(members);
		return new Result(Result.SUCCESS, onlineNum + "", members);
	}
	/**
	 * 对于好友列表按名称拼音排序
	 * @param members
	 */
	private void sortMemebers(List<Member> members) {
		Collections.sort(members, new Comparator<Member>() {
			@Override
			public int compare(Member o1, Member o2) {
				if (o1.getState().equals(o2.getState())) {
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
				if (o1.getState().equals("Y")) {
					return -1;
				}
				return 1;
			}
		});
	}
}