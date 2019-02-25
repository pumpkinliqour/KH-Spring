<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.kh.spring.demo.model.vo.*"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	Dev dev=(Dev)request.getAttribute("dev");
%>   
<c:set var="path" value="${pageContext.request.contextPath}"/>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="" name="pageTitle"/>
</jsp:include>
<style>
div#demo-container{
	width:40%;
	padding:15px;
	margin:0 auto;
	border:1px solid lightgray;
	border-radius: 10px;
}
</style>
<div id="demo-container">
	<form id="devFrm" method="post" action="${pageContext.request.contextPath}/demo/updateDevEnd.do">
	  <!-- 이름 -->
	  <div class="form-group row">
	    <label for="devName" class="col-sm-2">이름</label>
	    <input type="text" class="form-control col-sm-8" id="devName" name="devName" placeholder="이름" value="${dev.devName}" required/>
	  </div>
	  <div class="form-group row">
	    <label for="devAge" class="col-sm-2">나이</label>
	    <input type="number" class="form-control col-sm-8" id="devAge" name="devAge" value="${dev.devAge}" placeholder="나이" required/>
	  </div>
	  <div class="form-group row">
	    <label for="devEmail" class="col-sm-2">이메일</label>
	    <input type="email" class="form-control col-sm-8" id="devEmail" name="devEmail" value="${dev.devEmail}" placeholder="이메일" required/>
	  </div>
	  <div class="form-group row">
		<label class="col-sm-2">성별</label>
		<div class="form-check form-check-inline">
		  <input class="form-check-input" type="radio" name="devGender" id="gender-m" value="M" ${dev.devGender=='M'?"checked":"" }>
		  <label class="form-check-label" for="gender-m">남</label>
		</div>
		<div class="form-check form-check-inline">
		  <input class="form-check-input" type="radio" name="devGender" id="gender-f" value="F" ${dev.devGender=='F'?"checked":"" }>
		  <label class="form-check-label" for="gender-f">여</label>
		</div>
	  </div>
	  <div class="form-group row">
		<label class="col-sm-2">개발가능언어</label>
		<div class="form-check form-check-inline">
			<input class="form-check-input" type="checkbox"
			id="Java" name="devLang" value="Java"
			<%=Arrays.asList(dev.getDevLang())
			.contains("Java")?"checked":""%>/>
			<label for="Java">Java</label>
		</div>
		<div class="form-check form-check-inline">
			<input class="form-check-input" type="checkbox"
			id="c" name="devLang" value="C"
			<%=Arrays.asList(dev.getDevLang())
			.contains("C")?"checked":""%>/>
			<label for="c">C</label>
		</div>
		<div class="form-check form-check-inline">
			<input class="form-check-input" type="checkbox"
			id="JavaScript" name="devLang" value="JavaScript"
			<%=Arrays.asList(dev.getDevLang())
			.contains("JavaScript")?"checked":""%>/>
			<label for="JavaScript">JavaScript</label>
		</div>
	</div>
	<input type="hidden" name="devNo" value="${dev.devNo }"/>
	<button type="submit" onclick="return validate();" 
	class="list-group-item list-group-item-action">dev수정</button>
	</form>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>









