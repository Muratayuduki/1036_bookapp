-- データベース作成 (存在しない場合)
CREATE DATABASE IF NOT EXISTS book_db CHARACTER SET utf8mb4;
USE book_db;

-- ユーザーテーブル
CREATE TABLE IF NOT EXISTS users (
    user_id VARCHAR(50) PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    user_name VARCHAR(100) NOT NULL
);

-- 本テーブル
CREATE TABLE IF NOT EXISTS books (
    book_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(100),
    description TEXT,
    registered_by VARCHAR(50),
    FOREIGN KEY (registered_by) REFERENCES users(user_id)
);

-- テスト用ユーザー (パスワード: Password123!)
-- ※本来はハッシュ化すべきですが、今回は学習用として平文または簡単な管理とします。
INSERT IGNORE INTO users (user_id, password, user_name) VALUES ('admin', 'Password123!', '管理者');
