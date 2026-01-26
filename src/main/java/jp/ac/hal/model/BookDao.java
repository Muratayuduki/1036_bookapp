package jp.ac.hal.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    private final String URL = "jdbc:mysql://localhost:3306/book_db";
    private final String USER = "root";
    private final String PASS = "Muratayuduki123";

    public BookDao() {
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
     * 全件取得または検索
     */
    public List<BookBean> search(String keyword) {
        List<BookBean> list = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE title LIKE ? OR author LIKE ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            String searchWord = (keyword == null) ? "%%" : "%" + keyword + "%";
            pstmt.setString(1, searchWord);
            pstmt.setString(2, searchWord);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                BookBean book = new BookBean();
                book.setBookId(rs.getInt("book_id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setDescription(rs.getString("description"));
                book.setRegisteredBy(rs.getString("registered_by"));
                list.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 本の新規登録
     */
    public boolean register(BookBean book) {
        String sql = "INSERT INTO books (title, author, description, registered_by) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getDescription());
            pstmt.setString(4, book.getRegisteredBy());
            
            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
