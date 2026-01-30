<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // ログイン画面へフォワード
    request.getRequestDispatcher("/LoginServlet").forward(request, response);
%>
