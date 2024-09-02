<%@ page
    language="java"
    contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ page import="bitcamp.myapp.vo.Project" %>
<%@ page import="bitcamp.myapp.vo.User" %>
<%@ page import="java.util.List" %>

<jsp:include page="/header.jsp"/>

<%!
private boolean isMember(List<User> members, User user) {
    for (User member : members) {
      if (member.getNo() == user.getNo()) {
        return true;
      }
    }
    return false;
  }
%>

<h1>프로젝트 조회</h1>

<%
    Project project = (Project) request.getAttribute("project");
    if (project == null) {
%>
        <p>없는 프로젝트입니다.</p>
<%
    } else {
%>

<form action='/project/update'>
    번호: <input name='no' readonly type='text' value='<%=project.getNo()%>'><br>
    프로젝트명: <input name='title' type='text' value='<%=project.getTitle()%>'><br>
    설명: <textarea name='description'><%=project.getDescription()%></textarea><br>
    기간: <input name='startDate' type='date' value='<%=project.getStartDate()%>'> ~ <input name='endDate' type='date' value='<%=project.getEndDate()%>'><br>
    팀원: <br>

    <ul>

<%
    List<User> users = (List<User>) request.getAttribute("users");
    for (User user : users) {
%>
        <li><input <%=isMember(project.getMembers(), user) ? "checked" : ""%>
            name='member'
            value='<%=user.getNo()%>'
            type='checkbox'><%=user.getName()%></li>
<%
    }
%>
    </ul>
    <button>변경</button>
    <button type='button' onclick='location.href="/project/delete?no=<%=project.getNo()%>"'>삭제</button>
</form>

<%
    }
%>

</body>
</html>
