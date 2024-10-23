<%@ page
    language="java" 
    contentType="text/html;charset=UTF-8" 
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="../header.jsp"/>

<h1>게시글 목록</h1>
<p><a href='form'>새 글</a></p>
<table>
  <thead>
      <tr><th>번호</th><th>제목</th><th>작성자</th><th>작성일</th><th>조회수</th></tr>
  </thead>
  <tbody>
<c:forEach items="${list}" var="board">
    <tr>
      <td>${board.no}</td>
      <td><a href='view?no=${board.no}'>${board.title}</a></td>
      <td>${board.writer.name}</td>
      <td><fmt:formatDate value="${board.createdDate}" pattern="yyyy-MM-dd"/></td>
      <td>${board.viewCount}</td>
    </tr>
</c:forEach>
  </tbody>
</table>

[
<c:if test="${pageNo > 1}">
    <a href="list?pageNo=${pageNo - 1}&pageSize=${pageSize}">이전<a>
</c:if>
<c:if test="${pageNo <= 1}">
    이전
</c:if>
]

${pageNo}

[
<c:if test="${pageNo < pageCount}">
    <a href="list?pageNo=${pageNo + 1}&pageSize=${pageSize}">다음<a>
</c:if>
<c:if test="${pageNo >= pageCount}">
    다음
</c:if>
]
</body>
</html>