<%@ page
    language="java"
    contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ page import="bitcamp.myapp.vo.User" %>
<%@ page import="java.util.List"  %>

<jsp:include page="/header"/>

<h1>회원 목록</h1>
<p><a href='/user/form'>새 회원</a></p>
<table>
     <thead>
        <tr>
            <th>번호</th>
            <th>이름</th>
            <th>이메일</th>
        </tr>
    </thead>
    <tbody>

<%
    List<User> list = (List<User>) request.getAttribute("list");
    for (User user : list) {
%>
<tr>
    <td><%=user.getNo()%></td>
    <td><a href='/user/view?no=<%=user.getNo()%>'><%=user.getName()%></a></td>
    <td><%=user.getEmail()%></td>
</tr>
<%
}   
%>
    </tbody>
</table>

</body>
</html>
