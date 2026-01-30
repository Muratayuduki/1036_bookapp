package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private final String URL = "jdbc:mysql://localhost:3306/book_db";
    private final String USER = "root"; // 必要に応じて変更してください
    private final String PASS = "1036";     // 必要に応じて変更してください

    public UserDao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    /**
     * ログイン認証
     */
    public UserBean login(String userId, String password) {
        String sql = "SELECT * FROM users WHERE user_id = ? AND password = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, userId);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                UserBean user = new UserBean();
                user.setUserId(rs.getString("user_id"));
                user.setUserName(rs.getString("user_name"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ユーザーIDの存在チェック
     * @throws SQLException
     */
    public boolean userExists(String userId) throws SQLException {
        String sql = "SELECT user_id FROM users WHERE user_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        }
    }

    /**
     * 新規ユーザー登録
     * @return 更新された行数
     * @throws SQLException
     */
    public int register(UserBean user) throws SQLException {
        String sql = "INSERT INTO users (user_id, password, user_name) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getUserName());
            
            return pstmt.executeUpdate();
        }
    }

    /**
     * 全ユーザー取得
     */
    public List<UserBean> findAll() {
        List<UserBean> list = new ArrayList<>();
        String sql = "SELECT user_id, user_name FROM users ORDER BY user_name";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                UserBean user = new UserBean();
                user.setUserId(rs.getString("user_id"));
                user.setUserName(rs.getString("user_name"));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
