<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="paramTest" name="pageTitle"/>
</jsp:include>

<style>
div#memo-container{width:60%; margin:0 auto;}
</style>
<div id="memo-container">
    <form action="${pageContext.request.contextPath}/memo/insertMemo.do" class="form-inline">
        <input type="text" class="form-control col-sm-6" name="memo" placeholder="메모" required/>&nbsp;
        <input type="password" class="form-control col-sm-2" name="password" maxlength="4" placeholder="비밀번호" required/>&nbsp;
        <button class="btn btn-outline-success" type="submit" >저장</button>
    </form>
</div>
<br />
        <!-- 메모목록 -->
        <table class="table">
            <tr>
                <th scope="col">번호</th>
                <th scope="col">메모</th>
                <th scope="col">날짜</th>
                <th scope="col">삭제</th>
            </tr>
            <c:forEach items="${memoList}" var="memo" varStatus="vs">
                <tr>
                    <!-- VO 사용시 -->
                    <%-- 
                    <td>${memo.memoNo}</td>
                    <td>${memo.memo}</td>
                    <td>${memo.memoDate}</td>
                     --%>
                    <!-- Map 사용시 -->
                    <td>${vs.count}</td>
                    <td>${memo.MEMO}</td>
                    <td>${memo.MEMODATE}</td>
                    <td><button type="button" class="btn btn-outline-default" onclick="deleteMemo(${memo.MEMONO});">삭제</button></td>
                </tr>
            </c:forEach>
        </table>
        <script>
        	function deleteMemo(memoNo){
        		location.href="${path}/memo/deleteMemo.do?memoNo="+memoNo;
        	}
        </script>

<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>