<%@ page
    language="java"
    contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%
    String email = "";
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("email")) {
                email = cookie.getValue();
                break;
            }
        }
    }
%>

<jsp:include page="/header.jsp"/>

<h1>로그인</h1>
<form action='/auth/login' method="post">
    이메일: <input name='email' type='email' value="<%=email%>"><br>
    암호: <input name='password' type='password'><br>
    <input type="checkbox" name="saveEmail"> 이메일 저장<br>
    <input type='submit' value='로그인'>
</form>

</body>
</html>
