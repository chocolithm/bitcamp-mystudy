<%@ page
    language="java" 
    contentType="text/html;charset=UTF-8" 
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../header.jsp"/>

<h1>프로젝트 조회</h1>

<c:if test="${empty project}">
    <p>없는 프로젝트입니다.</p>
</c:if>
<c:if test="${not empty project}">
<form action='update' method="post">
    번호: <input readonly name='no' type='text' value='${project.no}'><br>
    프로젝트명: <input name='title' type='text' value='${project.title}'><br>
    설명: <textarea name='description'>${project.description}</textarea><br>
    기간:
        <input name='startDate' type='date' value='${project.startDate}'> ~
        <input name='endDate' type='date' value='${project.endDate}'><br>
    팀원:<br>
        <ul>
        <c:forEach items="${users}" var="user">
          <li><input ${project.members.contains(user) ? "checked" : ""}
                name='memberNos'
                value='${user.no}'
                type='checkbox'> ${user.name}</li>
        </c:forEach>
        </ul>
    <button>변경</button>
    <button type='button'
            onclick='location.href="delete?no=${project.no}"'>삭제</button>
</form>
</c:if>

</body>
</html>