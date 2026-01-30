package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.UserBean;
import model.UserDao;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String pass = request.getParameter("password");

        UserDao dao = new UserDao();
        UserBean user = dao.login(userId, pass);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            // ログイン成功 -> メニュー（本一覧）へフォワード
            request.getRequestDispatcher("/BookListServlet").forward(request, response);
        } else {
            request.setAttribute("error", "ユーザーIDまたはパスワードが正しくありません。");
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
    }
}
