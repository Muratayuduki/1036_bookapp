package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

    /**
     * 全件取得または検索
     */
    public List<BookBean> search(String keyword) {
        List<BookBean> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_db", "root", "1036");

            String sql = "SELECT b.*, u.user_name FROM books b JOIN users u ON b.registered_by = u.user_id WHERE b.title LIKE ? OR b.author LIKE ?";
            pstmt = conn.prepareStatement(sql);
            String searchWord = (keyword == null) ? "%%" : "%" + keyword + "%";
            pstmt.setString(1, searchWord);
            pstmt.setString(2, searchWord);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                BookBean book = new BookBean();
                book.setBookId(rs.getInt("book_id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setRegisteredBy(rs.getString("registered_by"));
                book.setUserName(rs.getString("user_name"));
                list.add(book);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    /**
     * 本の新規登録
     */
    public boolean register(BookBean book) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_db", "root", "1036");
            
            String sql = "INSERT INTO books (title, author, registered_by) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getRegisteredBy());
            
            result = pstmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result > 0;
    }
}
