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

@WebServlet("/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setEmail(email);
        UserDAO dao = new UserDAO();
        try {
            dao.updateUser(user);
            response.sendRedirect("ListUsersServlet");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}

