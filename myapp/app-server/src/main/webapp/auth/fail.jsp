<%@ page
    language="java"
    contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>

<jsp:include page="/header.jsp"/>

<h1>로그인</h1>
<p>이메일 또는 암호가 맞지 않습니다.</p>
<form action='login' method="post">
    이메일: <input name='email' type='email'><br>
    암호: <input name='password' type='password'><br>
    <input type='submit' value='로그인'>
</form>

</body>
</html>
