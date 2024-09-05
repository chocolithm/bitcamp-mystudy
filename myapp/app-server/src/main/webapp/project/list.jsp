<%@ page
    language="java"
    contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ page import="bitcamp.myapp.vo.Project" %>
<%@ page import="java.util.List"  %>

<jsp:include page="/header.jsp"/>

<h1>프로젝트 목록</h1>
<p><a href='/project/add'>새 프로젝트</a></p>
<table>
     <thead>
        <tr>
            <th>번호</th>
            <th>프로젝트</th>
            <th>기간</th>
        </tr>
    </thead>
    <tbody>

<%
    List<Project> list = (List<Project>) request.getAttribute("list");
    for (Project project : list) {
%>
<tr>
    <td><%=project.getNo()%></td>
    <td><a href='/project/view?no=<%=project.getNo()%>'><%=project.getTitle()%></a></td>
    <td><%=project.getStartDate() + " ~ " + project.getEndDate()%></td>
</tr>
<%
}   
%>
    </tbody>
</table>

</body>
</html>
