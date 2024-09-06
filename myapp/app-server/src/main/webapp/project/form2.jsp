<%@ page
    language="java"
    contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ page import="bitcamp.myapp.vo.User" %>
<%@ page import="java.util.List" %>

<jsp:include page="/header.jsp"/>

<h1>프로젝트 등록 - 팀원</h1>

<form action='/project/form3' method="post">
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

    <button>다음</button>
</form>


</body>
</html>
