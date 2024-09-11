<%@ page
    language="java"
    contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/header.jsp"/>

<h1>회원 조회</h1>

<c:if test="${empty user}">
    <p>없는 회원입니다.</p>
</c:if>

<c:if test="${not empty user}">
    <form action='/user/update' method="post">
        번호: <input name='no' readonly type='text' value='${user.no}'><br>
        이름: <input name='name' type='text' value='${user.name}'><br>
        이메일: <input name='email' type='email' value='${user.email}'><br>
        암호: <input name='password' type='password'><br>
        연락처: <input name='tel' type='tel' value='${user.tel}'><br>
        <button>변경</button>
        <button type='button' onclick='location.href="/user/delete?no=${user.no}"'>삭제</button>
    </form>
</c:if>

</body>
</html>
