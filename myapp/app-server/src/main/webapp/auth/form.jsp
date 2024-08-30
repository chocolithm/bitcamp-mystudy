<%@ page
    language="java"
    contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>

<jsp:include page="/header"/>

<h1>로그인</h1>
<form action='/auth/login'>
    이메일: <input name='email' type='email'><br>
    암호: <input name='password' type='password'><br>
    <input type='submit' value='로그인'>
</form>

</body>
</html>
