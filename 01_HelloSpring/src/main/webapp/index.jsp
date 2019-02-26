<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="HelloSpring" name="pageTitle"/>
</jsp:include>

<img src="${pageContext.request.contextPath }/resources/images/logo-spring.png" 
id="center-image" alt="스프링로고"/>
<button onclick="ajaxTest1();" class="btn btn-outline-success">AjaxList</button>
<button onclick="ajaxTest2();" class="btn btn-outline-success">vo객체받기</button>
<input type="text" id="userId" placeholder="검색할 아이디입력"/>
<button onclick="ajaxTest3();" class="btn btn-outline-success">맵 받기</button>
<input type="number" id="no" placeholder="검색할 게시글번호"/>
<div id="result">

</div>
<script>
	function ajaxTest1(){
		$.ajax({
			url:"${pageContext.request.contextPath }/ajaxTest1.do",
			dataType:"json",
			success:function(data){
				console.log(data);
				var table=$('<table>');
				for(var i=0; i<data.length; i++){
					var tr=$('<tr>');
					for(var key in data[i]){
						var td=$("<td>");
						if(key=="BOARDDATE"){
							var date=new Date(data[i][key]);
							td.append(date.getFullYear()+"/"+date.getMonth()+"/"+date.getDate()+"일");
						}
						else{
							td.append(data[i][key]);
						}
						tr.append(td);
					}
					table.append(tr);
				}
				$('#result').html(table);
			}
		})
	}
	function ajaxTest2(){
		$.ajax({
			url:"${pageContext.request.contextPath }/ajaxTest2.do",
			dataType:"json",
			data:{"userId":$('#userId').val()},
			success:function(data){
				console.log(data);
			}
		})
	}
	function ajaxTest3(){
		$.ajax({
			url:"${pageContext.request.contextPath }/ajaxTest3.do",
			dataType:"json",
			data:{"no":$('#no').val()},
			success:function(data){
				console.log(data);
			}
		})
	}
</script>


<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
