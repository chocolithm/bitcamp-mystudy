<%@ page
    language="java" 
    contentType="text/html;charset=UTF-8" 
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>

<jsp:include page="../header.jsp"/>

<h1>회원 등록</h1>
<form action="." method="post" enctype="multipart/form-data">
    이름: <input name='name' type='text'><br>
    이메일: <input name='email' type='email'><br>
    암호: <input name='password' type='password'><br>
    연락처: <input name='tel' type='tel'><br>
    사진: <input name='file' type='file'><br>
    <input type='submit' value='등록'>
</form>

</body>
</html>