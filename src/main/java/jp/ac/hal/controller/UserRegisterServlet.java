package jp.ac.hal.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.ac.hal.model.UserBean;
import jp.ac.hal.model.UserDao;
import java.io.IOException;

@WebServlet("/UserRegisterServlet")
public class UserRegisterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/userRegister.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String pass = request.getParameter("password");
        String userName = request.getParameter("userName");

        // パスワードバリデーション (8桁以上、英・数・記号すべて含む)
        if (isValidPassword(pass)) {
            UserBean user = new UserBean();
            user.setUserId(userId);
            user.setPassword(pass);
            user.setUserName(userName);

            UserDao dao = new UserDao();
            if (dao.register(user)) {
                request.setAttribute("登録が完了");
                request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            } else {
                request.setAttribute("IDが既に使われています。");
            }
        } else {
            request.setAttribute("error", "パスワードは8桁以上で、アルファベット、数字、記号をすべて含めてください。");
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
