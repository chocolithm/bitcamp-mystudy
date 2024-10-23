<%@ page
    language="java" 
    contentType="text/html;charset=UTF-8" 
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="../header.jsp"/>

<h1>게시글 조회</h1>

<c:if test="${empty board}">
  <p>없는 게시글입니다.</p>
</c:if>

<c:if test="${not empty board}">
  <form action='update' method="post" enctype="multipart/form-data">
      번호: <input readonly name='no' type='text' value='${board.no}'><br>
      제목: <input name='title' type='text' value='${board.title}'><br>
      내용: <textarea name='content'>${board.content}</textarea><br>
      작성일: <input readonly type='text'
                     value='<fmt:formatDate value="${board.createdDate}" pattern="yyyy-MM-dd"/>'><br>
      조회수: <input readonly type='text' value='${board.viewCount}'><br>
      작성자: <input readonly type='text' value='${board.writer.name}'><br>
      첨부파일: <br>
      <c:if test="${board.attachedFiles.size() > 0}">
        <ul>
        <c:forEach items="${board.attachedFiles}" var="attachedFile">
          <li>
            <a href="../download?path=board&fileNo=${attachedFile.fileNo}">${attachedFile.originFilename}</a>
            <a href="file/delete?boardNo=${board.no}&fileNo=${attachedFile.fileNo}">[삭제]</a>
          </li>
        </c:forEach>
        </ul>
      </c:if>
      <input name="files" type="file" multiple><br>
      <button>변경</button>
      <button type='button' onclick='location.href="delete?no=${board.no}"'>삭제</button>
  </form>
</c:if>

</body>
</html>