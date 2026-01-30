package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.BookBean;
import model.BookDao;
import java.io.IOException;
import java.util.List;

@WebServlet("/BookListServlet")
public class BookListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        BookDao dao = new BookDao();
        List<BookBean> list = dao.search(keyword);

        request.setAttribute("bookList", list);
        request.getRequestDispatcher("/WEB-INF/bookList.jsp").forward(request, response);
    }
}
