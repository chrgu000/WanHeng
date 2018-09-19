package com.chat.action;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;
import org.apache.catalina.websocket.WsOutbound;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.chat.entity.Member;
import com.chat.entity.Message;
import com.chat.service.IGroupChatWebSocketService;
import com.chat.util.GenerateFile;

@WebServlet("/group/chat")
public class GroupChatWebSocketServlet extends WebSocketServlet {
	private Map<String, Map<Integer, WsOutbound>> cache = new HashMap<String, Map<Integer, WsOutbound>>();
	private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	private static final long serialVersionUID = -1058445282919079067L;
    private final ApplicationContext app=new ClassPathXmlApplicationContext("spring.xml");
    private final IGroupChatWebSocketService groupChatWebSocketService=(IGroupChatWebSocketService) app.getBean("groupChatWebSocketService");

	@Override
	protected StreamInbound createWebSocketInbound(String arg0,
			HttpServletRequest request) {
		String group_id = request.getParameter("group_id");
		String my_nickname=request.getParameter("nickname");
		String user_id=request.getParameter("user_id");
		// StreamInbound：基于流的WebSocket实现类(带内流)，应用程序应当扩展这个类并实现其抽象方法onBinaryData和onTextData。
		return new ChatMessageInbound(group_id,user_id ,request.getSession(),my_nickname);
	}

	class ChatMessageInbound extends MessageInbound {
		HttpSession session = null;
		Map<Integer, WsOutbound> map = null;
        String nickname=null;
        String my_nickname=null;
        String group_id=null;
        String user_id=null;
		public ChatMessageInbound(String group_id,String user_id,HttpSession session,String my_nickname) {
			super();
			this.session = session;
			
			this.my_nickname=my_nickname;
			this.group_id=group_id;
			this.user_id=user_id;
			map = cache.get(group_id);
			if (map == null) {
				map = new HashMap<Integer, WsOutbound>();
				cache.put(group_id, map);
			}
		}

		// MessageInbound：基于消息的WebSocket实现类(带内消息)，应用程序应当扩展这个类并实现其抽象方法onBinaryMessage和onTextMessage。
		@Override
		protected void onOpen(WsOutbound outbound) {
			Integer hashcode = (Integer) session.getAttribute("hashcode");
			Integer code = outbound.hashCode();
			if (hashcode != code) {
				session.setAttribute("hashcode", code);
			}
			map.put(code, outbound);
			super.onOpen(outbound);
//			String msg = "<font>系统公告:请使用文明用语!" + sdf.format(new Date())
//					+ "</font><br/>";
//			CharBuffer buffer = CharBuffer.wrap(msg);
//			try {
//				outbound.writeTextMessage(buffer);
//				outbound.flush();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
		}

		@Override
		protected void onClose(int status) {
			map.remove(getWsOutbound().hashCode());
			super.onClose(status);
		}

		@Override
		protected void onBinaryMessage(ByteBuffer buffer) throws IOException {
		}

		@Override
		protected void onTextMessage(CharBuffer buffer) throws IOException {
			Map<String,Object> query=new HashMap<String,Object>();
			query.put("group_id", group_id);
			query.put("user_id", user_id);
			query=groupChatWebSocketService.getNameOfUserGroup(query);
			if(query.get("real_name")!=null&&!"".equals(query.get("real_name"))){
				this.nickname=(String) query.get("real_name");
			}else{
				this.nickname=(String) query.get("nickname");
			}
			String msg = buffer.toString();
			Message message = new Message();
			message.setNickname(nickname);
			message.setTime(new Date());
			message.setUser_id(Long.valueOf(user_id));
			message.setGroup_id(Long.valueOf(group_id));
			message.setMessage(msg);
			groupChatWebSocketService.sendGroupMessage(message);
			
			broadcast(buffer,message.getId());
		}

		private void broadcast(CharBuffer buffer,Long message_id) {
				Date date = new Date();
				Integer hashcode = (Integer) session.getAttribute("hashcode");
				Set<Integer> set = map.keySet();
				for (Integer integer : set) {
					String msg = buffer.toString();
					WsOutbound outbound = map.get(integer);
					if(msg.indexOf("</file>")==-1){
						if (hashcode.equals(integer)) {
							msg = " <div style='float:right;margin:2px 5px'><font  style='color:#000;float:right;font-size:14px;'> "
									+ my_nickname
									+ "&nbsp;&nbsp;"
									+ sdf.format(date)
									+ "</font><br/> <div style='word-wrap: break-word;word-break: normal;color:#fff;float:right;padding:5px;max-width:220px;background-color:#bf2934;border-radius:5px;display:inline-block;font-size:14px;line-height:20px;margin:5px 10px 5px 0px' >"
									+ msg
									+ "</div></div><div style='clear:both'></div>~~~"
											+ group_id+'&'+message_id;
						} else {
							msg = " <div style='float:left;margin:2px 5px;text-align:left'><font  style='color:#a7a7ad;font-size:14px';>"
									+ nickname
									+ "&nbsp;&nbsp;"
									+ sdf.format(date)
									+ "</font><br/>  <div style='word-wrap: break-word;word-break: normal;text-align:left;padding:5px;max-width:220px;display:inline-block;background-color:#fff;border-radius:5px;display:inline-block;font-size:14px;line-height:20px;margin:5px 0px 5px 10px' >"
									+ msg
									+ "</div></div><div style='clear:both'></div>~~~"
											+ group_id;
						}
						CharBuffer buffer1 = CharBuffer.wrap(msg);
						try {
							outbound.writeTextMessage(buffer1);
							outbound.flush();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else{
						if (!hashcode.equals(integer)) {
							Map<String,String> cache=GenerateFile.generate(msg);
							String filetype=(String) cache.get("filetype");
							String src="";
							if("suppress".equals(filetype)){
								src="../../../../../static/img/file2.jpg";
							}else{
								src="../../../../../static/img/file1.jpg";
							}
							String data="<file>filename=yangjun.zip&filetype=zip&filesize=112&filepath=cgwas/cloud/imagges</file>";
							String message="<div class='divdivleft'><div class='divdiv2'><p class='filenameClass'>"+cache.get("filename")+"</p><p><span>"+cache.get("filesize")+"</span><span class='divdownload'><a href='"+cache.get("filepath")+"' class='divbutton' >下载</a></span></p></div><div class='divdiv1'><img src='"+src+"' style='width: 100%;height: 100%;'></div></div>";
							System.out.println(message);
							msg = " <div style='float:left;margin:2px 5px;text-align:left'><font  style='color:#a7a7ad;font-size:14px;'>"
									+ nickname
									+ "&nbsp;&nbsp;"
									+ sdf.format(date)
									+ "</font><br/>  <div style='word-wrap: break-word;word-break: normal;text-align:left;padding:5px;max-width:220px;display:inline-block;background-color:#fff;border-radius:5px;display:inline-block;font-size:14px;margin:5px 0px 5px 10px' >"
									+ message+"</div></div><div style='clear:both'></div>~~~"
									+ group_id;
							CharBuffer buffer1 = CharBuffer.wrap(msg);
							try {
								outbound.writeTextMessage(buffer1);
								outbound.flush();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}else{
							String message="~~~"
									+ group_id+"&"+message_id+"&self";
							CharBuffer buffer1 = CharBuffer.wrap(message);
							try {
								outbound.writeTextMessage(buffer1);
								outbound.flush();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
					
				}
		}
	}
}