package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.BookBean;
import model.BookDao;
import model.UserBean;
import java.io.IOException;

@WebServlet("/BookRegisterServlet")
public class BookRegisterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/bookRegister.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");

        if (user == null) {
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }

        String title = request.getParameter("title");
        String author = request.getParameter("author");

        BookBean book = new BookBean();
        book.setTitle(title);
        book.setAuthor(author);
        book.setRegisteredBy(user.getUserId());

        BookDao dao = new BookDao();
        if (dao.register(book)) {
            // 登録成功 -> 一覧へ戻る
            request.setAttribute("message", "本を登録しました。");
            request.getRequestDispatcher("/BookListServlet").forward(request, response);
        } else {
            request.setAttribute("error", "登録に失敗しました。");
            request.getRequestDispatcher("/WEB-INF/bookRegister.jsp").forward(request, response);
        }
    }
}
