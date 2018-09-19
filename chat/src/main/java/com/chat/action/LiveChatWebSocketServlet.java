package com.chat.action;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.sql.Time;
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
import com.chat.service.ILiveChatWebSocketService;
/**
 * 直播
 * @author 杨俊
 *
 */
@WebServlet("/chat")
public class LiveChatWebSocketServlet extends WebSocketServlet {
	private Map<String, Map<Integer, WsOutbound>> cache = new HashMap<String, Map<Integer, WsOutbound>>();
	private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	private static final long serialVersionUID = -1058445282919079067L;
    private final ApplicationContext app=new ClassPathXmlApplicationContext("spring.xml");
    private final ILiveChatWebSocketService liveChatWebSocketService=(ILiveChatWebSocketService) app.getBean("liveChatWebSocketService");

	@Override
	protected StreamInbound createWebSocketInbound(String arg0,
			HttpServletRequest request) {
		String chapter_id = request.getParameter("chapter_id");
		String course_id = request.getParameter("course_id");
		String nickname=request.getParameter("nickname");
		// StreamInbound：基于流的WebSocket实现类(带内流)，应用程序应当扩展这个类并实现其抽象方法onBinaryData和onTextData。
		return new ChatMessageInbound(chapter_id,course_id, request.getSession(),nickname);
	}

	class ChatMessageInbound extends MessageInbound {
		HttpSession session = null;
		Map<Integer, WsOutbound> map = null;
        String nickname=null;
        String chapter_id=null;
        String course_id=null;
		public ChatMessageInbound(String chapter_id, String course_id,HttpSession session,String nickname) {
			super();
			this.session = session;
			this.nickname=nickname;
			this.chapter_id=chapter_id;
			this.course_id=course_id;
			map = cache.get(chapter_id);
			if (map == null) {
				map = new HashMap<Integer, WsOutbound>();
				cache.put(chapter_id, map);
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
			String msg = "<font>系统公告:请使用文明用语!" + sdf.format(new Date())
					+ "</font><br/>";
			CharBuffer buffer = CharBuffer.wrap(msg);
			try {
				outbound.writeTextMessage(buffer);
				outbound.flush();
				broadcast(CharBuffer.wrap("<i>"+map.size() + "</i>"),true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		protected void onClose(int status) {
			map.remove(getWsOutbound().hashCode());
			super.onClose(status);
			broadcast(CharBuffer.wrap("<i>"+map.size() + "</i>"),true);
		}

		@Override
		protected void onBinaryMessage(ByteBuffer buffer) throws IOException {
		}

		@Override
		protected void onTextMessage(CharBuffer buffer) throws IOException {
			Message message=new Message();
			message.setNickname(nickname);
			message.setTime(new Time(System.currentTimeMillis()));
			message.setMessage(buffer.toString());
			message.setChapter_id(Long.valueOf(chapter_id));
			message.setCourse_id(Long.valueOf(course_id));
			liveChatWebSocketService.sendMessage(message);
			broadcast(buffer,false);
		}

		private void broadcast(CharBuffer buffer,boolean flag) {
			if(flag){
				Set<Integer> set = map.keySet();
				String msg = buffer.toString();
				for (Integer integer : set) {
					WsOutbound outbound = map.get(integer);
					CharBuffer buffer1 = CharBuffer.wrap(msg);
					try {
						outbound.writeTextMessage(buffer1);
						outbound.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}else{
				Date date = new Date();
				Integer hashcode = (Integer) session.getAttribute("hashcode");
				Set<Integer> set = map.keySet();
				for (Integer integer : set) {
					String msg = buffer.toString();
					WsOutbound outbound = map.get(integer);
					if (hashcode == integer) {
						msg = " <div style='float:right;margin:-10px 10px'><font  style='color:red;float:right;font-size:13px;'> " + nickname +"&nbsp;&nbsp;"+sdf.format(date)
								+ "</font><br/> <div style='word-wrap: break-word;word-break: normal;float:right;padding:5px;max-width:220px;background-color:#b2e281;border-radius:5px;display:inline-block;font-size:12px;line-height:16px' >"+msg+"</div></div><div style='clear:both'></div>";
					} else {
						msg = " <div style='float:left;margin:-10px 0px'><font  style='color:blue;font-size:13px';>" + nickname +"&nbsp;&nbsp;" +sdf.format(date)
								+ "</font><br/>  <div style='word-wrap: break-word;word-break: normal;text-align:left;padding:5px;max-width:220px;display:inline-block;background-color:#dbdbdb;border-radius:5px;display:inline-block;font-size:12px;line-height:16px' >"+msg+"</div></div><div style='clear:both'></div>";
					}
					CharBuffer buffer1 = CharBuffer.wrap(msg);
					try {
						outbound.writeTextMessage(buffer1);
						outbound.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		
		}
	}
}