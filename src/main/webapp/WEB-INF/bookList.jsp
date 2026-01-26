<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>おすすめ本一覧</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
    <h1>おすすめ本一覧</h1>
    <div class="nav">
        こんにちは、${user.userName} さん | 
        <a href="BookRegisterServlet">新しい本を登録する</a> | 
        <a href="LoginServlet">ログアウト</a>
    </div>

    <form action="BookListServlet" method="post">
        検索キーワード: <input type="text" name="keyword" placeholder="タイトルまたは著者名" style="width: 60%;">
        <input type="submit" value="検索">
    </form>

    <p class="message">${message}</p>

    <table>
        <tr>
            <th>タイトル</th>
            <th>著者</th>
            <th>紹介文</th>
            <th>名前</th>
        </tr>
        <c:forEach var="book" items="${bookList}">
            <tr>
                <td>${book.title}</td>
                <td>${book.author}</td>
                <td>${book.description}</td>
                <td>${book.userName}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
