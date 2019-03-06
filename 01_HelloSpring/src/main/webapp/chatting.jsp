<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채팅</title>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
</head>
<body>
	<input type="text" id="msg"/>
	<input type="button" id="sendBtn" value="전송"/>
	<input type="file" id="upFile" name="upFile"/>
	<input type="button" id="fileBtn" value="전송"/>
	<div id="container">
	
	</div>
	<script>
		var nickname=prompt("대화명 입력하세요.");
		//접속할 웹소캣 주소
		var socket=new WebSocket("ws://192.168.20.20:9090/spring/chatting.jsp"); // spring까지는 root-context (괄호안에 주소값이 들어감) 외부에서 하려면 공용ip로 사용 X
		/* 소켓 안에는 onopen, onmessage, onclose, onerror 이벤트가 있음 */
		/* 데이터 전송 : send함수 */
		socket.onopen=function(e){ /* 괄호 안 데이터넣기 */
			console.log(e);
		}
		socket.onmessage=function(e){
			console.log("message : "+e);
			console.log(e);
			var data=JSON.parse(e.data);
			$('#container').prepend($('<p>').html(data["id"]+" : "+data["msg"]));
		}
		/* 위의 input값을 전송해보자 */
		$(function(){
			$('#sendBtn').click(function(){
				var msg={"id":nickname, "msg":$('#msg').val()}
				socket.send(JSON.stringify(msg));
			})
			$("#fileBtn").click(function(){
				var file=$("#upFile")[0].files[0]; //upFile중 첫번째의 files 중 첫번째
				console.log(file);
				socket.binaryType="arraybuffer";
				var reader=new FileReader();
				var rawData=new ArrayBuffer();
				reader.onload=function(e){ //e=이벤트
					rawData=e.target.result; //파일업로드 되었을 때 데이터 받아오기
					var msg={"id":nickname, "msg":file.name, "flag":"file"};
					socket.send(JSON.stringify(msg));
					socket.send(rawData);
				}
				reader.readAsArrayBuffer(file); //우리가 올린 파일을 어레이 버퍼로 읽어줘라.
			});
		});
	</script>
</body>
</html>
