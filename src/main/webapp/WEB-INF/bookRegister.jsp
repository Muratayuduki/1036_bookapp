<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>本の登録</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
    <h1>新しいおすすめ本を登録</h1>
    <p class="error">${error}</p>
    <form action="BookRegisterServlet" method="post">
        タイトル: <input type="text" name="title" required><br>
        著者: <input type="text" name="author"><br>
        紹介文: <textarea name="description" rows="5"></textarea><br>
        <input type="submit" value="登録">
    </form>
    <p><a href="BookListServlet">一覧へ戻る</a></p>
</div>
</body>
</html>
