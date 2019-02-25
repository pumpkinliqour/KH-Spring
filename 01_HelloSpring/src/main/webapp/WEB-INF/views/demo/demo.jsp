<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="paramTest" name="pageTitle"/>
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
		<form id="devFrm">
			<div class="form-group row">
				<label for="devName" 
				class="col-sm-2 col-form-label">
				이름</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" 
					id="devName" name="devName"/>
				</div>
			</div>
			<div class="form-group row">
				<label for="devAge" class="col-sm-2 col-form-label">나이</label>
				<div class="col-sm-10">
					<input id="devAge" name="devAge" type="number" 
					class="form-control"/>
				</div>
			</div>
			<div class="form-group row">
				<label for="devEmail" class="col-sm-2 col-form-label">이메일</label>
				<div class="col-sm-10">
					<input id="devEmail" name="devEmail" type="email" 
					class="form-control"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label">성별</label>
				<div class="col-sm-10">
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="radio" name="devGender" 
						id="devGender0" value="M"/>
						<label class="form-check-label" for="devGender0">남</label>
						<input class="form-check-input" type="radio" name="devGender" 
						id="devGender1" value="F"/>
						<label class="form-check-label" for="devGender1">여</label>
					</div>
				</div>
			</div>	
			<div class="form-group row">
				<label class="col-sm-2 col-form-label">개발언어</label>
				<div class="col-sm-10">
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="checkbox" name="devLang" 
						id="devLang0" value="Java"/>
						<label class="form-check-label" for="devLang0">자바</label>
						<input class="form-check-input" type="checkbox" name="devLang" 
						id="devLang1" value="C"/>
						<label class="form-check-label" for="devLang1">C</label>
						<input class="form-check-input" type="checkbox" name="devLang" 
						id="devLang2" value="JavaScript"/>
						<label class="form-check-label" for="devLang2">자바스크립트</label>
					</div>
				</div>
			</div>	
			<div class="list-group">
				<button type="button" onclick="demo1();" 
				class="list-group-item list-group-item-action">
				파라미터핸들링-HttpServletRequest</button>
				<button type="button" onclick="demo2();" 
				class="list-group-item list-group-item-action">
				파라미터핸들링-@RequestParam</button>
				<button type="button" onclick="demo3();" 
				class="list-group-item list-group-item-action">
				파라미터핸들링-vo객체 이용</button>
				<button type="button" onclick="demo4();" 
				class="list-group-item list-group-item-action">
				insert dev</button>
			</div>	
		</form>	
	</div>
	<script>
		function demo4(){
			$('#devFrm').attr("action","${pageContext.request.contextPath}/demo/insertDev.do");
			$('#devFrm').attr("method","post");
			$('#devFrm').submit();
		}
		function demo1()
		{
			$('#devFrm').attr("action","${pageContext.request.contextPath}/demo/demo1.do");
			$('#devFrm').submit();
		}
		function demo2()
		{
			$('#devFrm').attr("action","${pageContext.request.contextPath}/demo/demo2.do");
			$('#devFrm').submit();
		}
		function demo3()
		{
			$('#devFrm').attr("action","${pageContext.request.contextPath}/demo/demo3.do");
			$('#devFrm').submit();
		}
	</script>
	

<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>