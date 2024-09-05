<%@ page
    language="java"
    contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ page import="bitcamp.myapp.vo.Board" %>

<jsp:include page="/header.jsp"/>

<h1>게시글 등록</h1>
      
<form action='/board/add' method="post">
    제목: <input name='title' type='text'><br>
    내용: <textarea name='content'></textarea><br>
    <input type='submit' value='등록'>
</form>

</body>
</html>
