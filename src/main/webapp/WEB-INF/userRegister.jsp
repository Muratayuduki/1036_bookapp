<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規ユーザー登録</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
    <h1>新規ユーザー登録</h1>
    <p class="error">${error}</p>
    <form action="UserRegisterServlet" method="post">
        ユーザーID: <input type="text" name="userId" required><br>
        パスワード: <input type="password" name="password" required><br>
        <small>※8桁以上、英数字記号必須</small><br>
        名前: <input type="text" name="userName" required><br>
        <input type="submit" value="登録">
    </form>
    <p><a href="LoginServlet">ログイン画面へ戻る</a></p>
</div>
</body>
</html>
