package com.kh.spring.chatting;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//문자기반 통신할 때 받을 수 있는 핸들러(간단한 채팅 이미지x)
//public class ChattingHandler extends TextWebSocketHandler {
public class ChattingHandler extends BinaryWebSocketHandler {

	
	private List<WebSocketSession> client=new ArrayList<WebSocketSession>();
	private String fileName;
	
	
	// 소캣으로 연결되면 실행되는 메소드
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("Open : "+session);
		System.out.println(session.getRemoteAddress());
		client.add(session); //세션을 넣어줌. 하지만 세션이 계쏙 살아있기 때문에 closed 메소드에서 없애야함.
	}

	// 접속된 클라이언트로부터 메세지가 도착하면 실행되는 메소드
//	@Override
//	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) {
		// WebSocketSession : 사용자에 대한 아이디 값. (웹소캣에 접속되었을 때 별도로 지정되는 클라이언트의 아이디 값)
		// TextMessage : 클라이언트가 데이터를 보낼 때 메세지를 담아주는 객체 (JSON방식으로 넘어옴. JSON 맵퍼로해서 다시 파싱을 해서 이용해야함)
		
		System.out.println("Text : "+session+" : "+message);
		//파싱처리
		Map<String,String> map=null;

		ObjectMapper mapper=new ObjectMapper();//잭슨에서 제공
		try {
			map=mapper.readValue(message.getPayload(), HashMap.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(map);
		/*메세지 파싱*/
		TextMessage tm=null;
		try {
			tm = new TextMessage(mapper.writeValueAsString(map));
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(map.get("flag")!=null&&map.get("flag").equals("file")) {
			fileName=map.get("msg");
		}
		else {
			//클라이언트에게 오는 메세지 보관
			for(WebSocketSession s : client) {
//			if(s==session) { 
//				//나한테 메세지 안보내고싶을 때.(채팅창에 내 정보와 메세지가 안보이게 하고싶을때)
//				continue;
//			}
				try {
					s.sendMessage(tm);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		System.out.println(tm);
		
		//Text : hwang3324 : 안녕하세요?
	}
	
	@Override
	protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
		System.out.println("binary"+session+" : "+message);
		String dir="c:\\";
		FileOutputStream fos=new FileOutputStream(new File(dir+session.getId()+"_"+fileName)); //_ 파일명 안겹치기 위해서 구분자
		ByteBuffer bb=message.getPayload();
		fos.write(bb.array());
		fos.close();
		ObjectMapper mapper=new ObjectMapper();
		Map<String,String> msg=new HashMap();
		msg.put("msg",  "파일업로드 완료");
		session.sendMessage(new TextMessage(mapper.writeValueAsString(msg)));
	}

	// 접속된 클라이언트와 접속이 끊어졌을 때 실행되는 메소드
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("Close : "+session);
		client.remove(session); //접속이 끊길 때 세션 없애주기.
	}
}
