package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.UserBean;
import model.UserDao;

import java.io.IOException;
import java.util.List;

@WebServlet("/UserListServlet")
public class UserListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao dao = new UserDao();
        List<UserBean> userList = dao.findAll();

        request.setAttribute("userList", userList);
        request.getRequestDispatcher("/WEB-INF/userList.jsp").forward(request, response);
    }
}
