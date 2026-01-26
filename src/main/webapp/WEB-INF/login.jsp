<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン - 9999_BookApp</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
    <h1>ログイン</h1>
    <p class="error">${error}</p>
    <p class="message">${message}</p>
    <form action="LoginServlet" method="post">
        ユーザーID: <input type="text" name="userId" required><br>
        パスワード: <input type="password" name="password" required><br>
        <input type="submit" value="ログイン">
    </form>
    <p><a href="UserRegisterServlet">新規ユーザー登録はこちら</a></p>
</div>
</body>
</html>
