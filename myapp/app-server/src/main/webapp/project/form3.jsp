<%@ page
    language="java"
    contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ page import="bitcamp.myapp.vo.Project" %>
<%@ page import="bitcamp.myapp.vo.User" %>
<%@ page import="java.util.List" %>

<jsp:include page="/header.jsp"/>

<h1>프로젝트 등록</h1>

<%
    Project project = (Project) session.getAttribute("project");
    if (project == null) {
%>
    <p>등록할 프로젝트 정보가 없습니다.</p>
<%
    } else {
%>

<form action='/project/add' method="post">
    프로젝트명: <%=project.getTitle()%><br>
    설명: <textarea><%=project.getDescription()%></textarea><br>
    기간: <%=project.getStartDate()%> ~ <%=project.getEndDate()%><br>
    팀원: <br>

    <ul>
<%
    for (User user : project.getMembers()) {
%>
        <li><%=user.getName()%>(<%=user.getEmail()%>)</li>
<%
    }
%>
    </ul>
    <button>등록</button>
</form>

<%
    }
%>

</body>
</html>
