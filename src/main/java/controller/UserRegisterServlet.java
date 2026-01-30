package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.UserBean;
import model.UserDao;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/UserRegisterServlet")
public class UserRegisterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/userRegister.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String pass = request.getParameter("password");
        String userName = request.getParameter("userName");

        // パスワードバリデーション
        if (!isValidPassword(pass)) {
            request.setAttribute("error", "パスワードは8桁以上で、アルファベット、数字、記号をすべて含めてください。");
            request.getRequestDispatcher("/WEB-INF/userRegister.jsp").forward(request, response);
            return;
        }

        UserBean user = new UserBean();
        user.setUserId(userId);
        user.setPassword(pass);
        user.setUserName(userName);

        UserDao dao = new UserDao();
        try {
            if (dao.userExists(userId)) {
                // ユーザーIDが既に存在する場合
                request.setAttribute("error", "このIDは既に使用されています。");
                request.getRequestDispatcher("/WEB-INF/userRegister.jsp").forward(request, response);
            } else {
                // ユーザー登録処理
                int result = dao.register(user);
                if (result > 0) {
                    // 登録成功
                    request.setAttribute("message", "ユーザー登録が完了しました。");
                    request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                } else {
                    // 登録失敗 (通常は発生しにくい)
                    request.setAttribute("error", "ユーザー登録に失敗しました。");
                    request.getRequestDispatcher("/WEB-INF/userRegister.jsp").forward(request, response);
                }
            }
        } catch (SQLException e) {
            // データベース関連のエラー
            e.printStackTrace(); // サーバーのログにエラー詳細を出力
            request.setAttribute("error", "データベースエラーが発生しました。しばらくしてから再度お試しください。");
            request.getRequestDispatcher("/WEB-INF/userRegister.jsp").forward(request, response);
        }
    }

    private boolean isValidPassword(String password) {
        if (password == null || password.length() < 8) return false;
        // 英字が含まれているか
        boolean hasLetter = password.matches(".*[a-zA-Z].*");
        // 数字が含まれているか
        boolean hasDigit = password.matches(".*[0-9].*");
        // 記号（英数字以外）が含まれているか
        // [^a-zA-Z0-9] は英数字以外の文字を指します
        boolean hasSymbol = password.matches(".*[^a-zA-Z0-9].*");
        
        return hasLetter && hasDigit && hasSymbol;
    }
}
