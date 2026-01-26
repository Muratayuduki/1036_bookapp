package jp.ac.hal.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.ac.hal.model.BookBean;
import jp.ac.hal.model.BookDao;
import jp.ac.hal.model.UserBean;
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
        String description = request.getParameter("description");

        BookBean book = new BookBean();
        book.setTitle(title);
        book.setAuthor(author);
        book.setDescription(description);
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
