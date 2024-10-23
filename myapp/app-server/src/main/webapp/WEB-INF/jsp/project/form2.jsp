<%@ page
    language="java" 
    contentType="text/html;charset=UTF-8" 
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../header.jsp"/>

<h1>프로젝트 등록 - 팀원</h1>
<form action='form3' method="post">
  팀원:<br>
    <ul>

<c:forEach items="${users}" var="user">
      <li><input name='memberNos' value='${user.no}' type='checkbox'> ${user.name}</li>
</c:forEach>

    </ul>
  <button>다음</button>
</form>

</body>
</html>