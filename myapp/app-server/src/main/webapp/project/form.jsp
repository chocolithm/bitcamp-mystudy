<%@ page
    language="java"
    contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ page import="bitcamp.myapp.vo.User" %>
<%@ page import="java.util.List" %>

<jsp:include page="/header.jsp"/>

<h1>프로젝트 등록</h1>

<form action='/project/add'>
     프로젝트명: <input name='title' type='text'><br>
     설명: <textarea name='description'></textarea><br>
     기간: <input name='startDate' type='date'> ~ <input name='endDate' type='date'><br>
     팀원: <br>

     <ul>
<%
    List<User> users = (List<User>) request.getAttribute("users");
    for (User user : users) {
%>
    <li><input name='member' value='<%=user.getNo()%>' type='checkbox'><%=user.getName()%></li>
<%
    }
%>
     </ul>

     <input type='submit' value='등록'>
</form>


</body>
</html>
