<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>   
<c:set var="path" value="${pageContext.request.contextPath}"/>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="" name="pageTitle"/>
</jsp:include>
`<section id='board-contianer' class='container'>

   <p> 총 ${boardCount }건의 게시물이 있습니다.</p>
   <input type="button" value="글쓰기" id='btn-add' class='btn btn-outline-success' onclick='location.href="${path}/board/boardForm"'/>

   <table id='tbl-board' class='table table-striped table-hover'>
      <tr>
         <th>번호</th>
         <th>제목</th>
         <th>작성자</th>
         <th>작성일</th>
         <th>첨부파일</th>
         <th>조회수</th>         
      </tr>
      <c:forEach var="b" items="${list }">
            <tr>
               <td>${b.BOARDNO }</td>
               <td><a href="${path}/board/boardView.do?boardNo="${b.BOARDNO}>${b.BOARDTITLE }</a></td>
               <td>${b.BOARDWRITER }</td>
               <td>${b.BOARDDATE }</td>
               <td align="center">
               	<c:if test="${b.ATTACHCOUNT>0 }">
               		<img src="${path }/resources/images/file.png" width="16px"/>
               	</c:if>
               </td>
               <td>${b.BOARDREADCOUNT }</td>
            </tr>   
	</c:forEach>
   </table>
   ${pageBar }
</section>

<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>