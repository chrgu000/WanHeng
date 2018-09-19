package com.chat.action;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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

import com.chat.entity.Message;
import com.chat.service.ISingleChatMemberService;
import com.chat.service.ISingleChatMessageService;
import com.chat.util.GenerateFile;
/**
 * 单聊
 * @author 杨俊
 *
 */
@WebServlet("/single/chat")
public class SingleChatWebSocketServlet extends WebSocketServlet {
	public final Map<String, Map<Integer, WsOutbound>> cache = new HashMap<String, Map<Integer, WsOutbound>>();
	private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	private final long serialVersionUID = -1058445282919079067L;
	private final ApplicationContext app = new ClassPathXmlApplicationContext(
			"spring.xml");
	private final ISingleChatMessageService singleChatMessageService = (ISingleChatMessageService) app
			.getBean("singleChatMessageService");
	private final ISingleChatMemberService singleChatMemberService = (ISingleChatMemberService) app
			.getBean("singleChatMemberService");

	@Override
	protected StreamInbound createWebSocketInbound(String arg0,
			HttpServletRequest request) {
		String user_id = request.getParameter("user_id");
		String friend_id = request.getParameter("friend_id");
		String my_nickname = request.getParameter("nickname");
		// StreamInbound：基于流的WebSocket实现类(带内流)，应用程序应当扩展这个类并实现其抽象方法onBinaryData和onTextData。
		return new ChatMessageInbound(user_id, friend_id, request.getSession(),my_nickname);
	}

	class ChatMessageInbound extends MessageInbound {
		HttpSession session = null;
		Map<Integer, WsOutbound> map = null;
		String nickname = null;
		String my_nickname=null;
		String user_id = null;
		String friend_id = null;

		public ChatMessageInbound(String user_id, String friend_id,
				HttpSession session,String my_nickname) {
			super();
			this.session = session;
			this.my_nickname=my_nickname;
			this.user_id = user_id;
			this.friend_id = friend_id;
			String key1 = user_id + "_" + friend_id;
			String key2 = friend_id + "_" + user_id;
			if (cache.get(key1) == null && cache.get(key2) == null) {
				map = new HashMap<Integer, WsOutbound>();
				cache.put(key1, map);
			} else if (cache.get(key1) != null) {
				map = cache.get(key1);
			} else if (cache.get(key2) != null) {
				map = cache.get(key2);
			}
		}

		// MessageInbound：基于消息的WebSocket实现类(带内消息)，应用程序应当扩展这个类并实现其抽象方法onBinaryMessage和onTextMessage。
		@Override
		protected void onOpen(WsOutbound outbound) {
			super.onOpen(outbound);
			Integer hashcode = (Integer) session.getAttribute("hashcode");
			Integer code = outbound.hashCode();
			if (hashcode != code) {
				map.remove(hashcode);
				session.setAttribute("hashcode", code);
				map.put(code, outbound);
			}
			String msg = "<open>" + this.user_id + "</open>";
			CharBuffer buffer = CharBuffer.wrap(msg);
			if(!friend_id.equals("-1")){
				broadcast(buffer, true, "open");
			}
		}

		@Override
		protected void onClose(int status) {
			super.onClose(status);
			map.remove(getWsOutbound().hashCode());
			map.remove(session.getAttribute("hashcode"));
			String msg = "<close>" + this.user_id + "</close>";
			CharBuffer buffer = CharBuffer.wrap(msg);
			Map<String, Object> query = new HashMap<String, Object>();
			query.put("user_id", Math.abs(Long.valueOf(user_id)));
			query.put("state", "N");
			singleChatMemberService.updateOnlieState(query);
			broadcast(buffer, true, "close");
		}

		@Override
		protected void onBinaryMessage(ByteBuffer buffer) throws IOException {
		}

		@Override
		protected void onTextMessage(CharBuffer buffer) throws IOException {
			Map<String,Object> query=new HashMap<String,Object>();
			query.put("user_id", user_id);
			query.put("friend_id", friend_id);
			query=singleChatMemberService.getNameOfUser(query);
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
			message.setFriend_id(Long.valueOf(friend_id));
			if (msg.indexOf("</readstate>") != -1) {
				message.setReadstate("N");
				msg = msg.substring(0, msg.indexOf("</readstate>"));
			} else {
				message.setReadstate("Y");
			}
			message.setMessage(msg);
			singleChatMessageService.sendUMessage(message);
			broadcast(buffer, false, null);
		}

		private void broadcast(CharBuffer buffer, boolean onlineFlag,
				String state) {
			Date date = new Date();
			Integer hashcode = (Integer) session.getAttribute("hashcode");
			if (onlineFlag) {
				if ("open".equals(state)) {
					for (Integer key:map.keySet()) {
					}
					if (map.size() ==2) {
						try {
							for (Map.Entry<Integer, WsOutbound> entry : map
									.entrySet()) {
								if (!entry.getKey().equals(hashcode)) {
									WsOutbound outbound = entry.getValue();
									outbound.writeTextMessage(buffer);
									outbound.flush();
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				} else if ("close".equals(state)) {
                    if(map.size()==1){
                    	try {
    						for (Map.Entry<Integer, WsOutbound> entry : map
    								.entrySet()) {
    							if (!entry.getKey().equals(hashcode)) {
    								WsOutbound outbound = entry.getValue();
    								outbound.writeTextMessage(buffer);
    								outbound.flush();
    							}
    						}
    					} catch (Exception e) {
    						e.printStackTrace();
    					}
                    }
					if (map.size() == 0) {
						String key1 = user_id + "_" + friend_id;
						String key2 = friend_id + "_" + user_id;
						cache.remove(key1);
						cache.remove(key2);
					}
				}
			} else if (!onlineFlag) {
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
									+ user_id;
						} else {
							msg = " <div style='float:left;margin:2px 5px;text-align:left'><font  style='color:#a7a7ad;font-size:14px';>"
									+ nickname
									+ "&nbsp;&nbsp;"
									+ sdf.format(date)
									+ "</font><br/>  <div style='word-wrap: break-word;word-break: normal;text-align:left;padding:5px;max-width:220px;display:inline-block;background-color:#fff;border-radius:5px;display:inline-block;font-size:14px;line-height:20px;margin:5px 0px 5px 10px' >"
									+ msg
									+ "</div></div><div style='clear:both'></div>~~~"
									+ user_id;
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
							String message="<div class='divdivleft'><div class='divdiv2'><p class='filenameClass'>"+cache.get("filename")+"</p><p><span>"+cache.get("filesize")+"</span><span class='divdownload'><a href='"+cache.get("filepath")+"' class='divbutton' >下载</a></span></p></div><div class='divdiv1'><img src='"+src+"' style='width: 100%;height: 100%;'></div></div>";
							msg = " <div style='float:left;margin:2px 5px;text-align:left'><font  style='color:#a7a7ad;font-size:14px;'>"
									+ nickname
									+ "&nbsp;&nbsp;"
									+ sdf.format(date)
									+ "</font><br/>  <div style='word-wrap: break-word;word-break: normal;text-align:left;padding:5px;max-width:220px;display:inline-block;background-color:#fff;border-radius:5px;display:inline-block;font-size:14px;margin:5px 0px 5px 10px' >"
									+ message+"</div></div><div style='clear:both'></div>~~~"
									+ user_id;
							CharBuffer buffer1 = CharBuffer.wrap(msg);
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
}