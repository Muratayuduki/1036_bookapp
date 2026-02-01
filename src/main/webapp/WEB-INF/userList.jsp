<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー一覧</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body class="user-list-page">
<div class="container">
    <h1>ユーザー一覧</h1>
    <div class="nav">
        こんにちは、${user.userName} さん | 
        <a href="BookListServlet">本の一覧へ戻る</a> | 
        <a href="LoginServlet">ログアウト</a>
    </div>

    <table>
        <tr>
            <th>ユーザーID</th>
            <th>ユーザー名</th>
        </tr>
        <c:forEach var="u" items="${userList}">
            <tr>
                <td>${u.userId}</td>
                <td>${u.userName}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
