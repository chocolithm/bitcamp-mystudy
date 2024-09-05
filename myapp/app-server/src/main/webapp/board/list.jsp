<%@ page
    language="java"
    contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ page import="bitcamp.myapp.vo.Board" %>
<%@ page import="java.util.List"  %>

<jsp:include page="/header.jsp"/>

<h1>게시글 목록</h1>
<p><a href='/board/add'>새 게시글</a></p>
<table>
    <thead>
         <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
            <th>조회수</th>
        </tr>
    </thead>
    <tbody>

<%
    List<Board> list = (List<Board>) request.getAttribute("list");
    for (Board board : list) {
%>
<tr>
    <td><%=board.getNo()%></td>
    <td><a href='/board/view?no=<%=board.getNo()%>'><%=board.getTitle()%></a></td>
    <td><%=board.getWriter().getName()%></td>
    <td><%=String.format("%tY-%1$tm-%1$td", board.getCreatedDate())%></td>
    <td><%=board.getViewCount()%></td>
</tr>
<%
}   
%>
    </tbody>
</table>

</body>
</html>
