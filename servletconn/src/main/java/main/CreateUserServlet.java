package main;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import dao.UserDAO;

@WebServlet("/CreateUserServlet")
public class CreateUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        UserDAO dao = new UserDAO();
        try {
            dao.addUser(user);
            response.sendRedirect("ListUsersServlet");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}


