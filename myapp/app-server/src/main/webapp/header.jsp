<%@ page
    language="java"
    contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ page import="bitcamp.myapp.vo.User" %>
    
<!doctype html>
<html>
<head>
   <meta charset='UTF-8'>
   <title>Document</title>
   <link href='/css/common.css' rel='stylesheet'>
</head>
<body>

<header>
  <a href='/'><img src='/images/home.png'></a>
  <h1>프로젝트 관리 시스템</h1>

  <nav>
    <ul>
      <li class='btn btn-default'><a href='/app/user/list'>회원</a></li>
      <li class='btn btn-default'><a href='/app/project/list'>프로젝트</a></li>
      <li class='btn btn-default'><a href='/app/board/list'>게시글</a></li>
    </ul>
  </nav>

  <div class='login-state pos-right'>
<%
User loginUser = (User) ((HttpServletRequest) request).getSession().getAttribute("loginUser");
if (loginUser == null) {
%>
    <a class='btn btn-primary' href='/app/auth/login'>로그인</a>
<% } else { %>
    <a class='btn btn-light' href='/app/user/view?no=<%=loginUser.getNo()%>'><%=loginUser.getName()%></a>
    <a class='btn btn-secondary' href='/app/auth/logout'>로그아웃</a>
<% } %>

   </div>
</header>
