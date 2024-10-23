<%@ page
    language="java" 
    contentType="text/html;charset=UTF-8" 
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>

<jsp:include page="../header.jsp"/>

<h1>프로젝트 등록 - 기본 정보</h1>
<form action='form2' method="post">
      프로젝트명: <input name='title' type='text'><br>
      설명: <textarea name='description'></textarea><br>
      기간: <input name='startDate' type='date'> ~
      <input name='endDate' type='date'><br>
      <button>다음</button>
</form>

</body>
</html>