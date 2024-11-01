package main;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import dao.UserDAO;


@WebServlet("/ListUsersServlet")
public class ListUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDAO dao = new UserDAO();
        try {
			List<User> users= dao.getAllUsers();
			response.setContentType("text/html");
			response.getWriter().println("<html><body>");
			response.getWriter().println("<h1> List of users </h1>");
			response.getWriter().println("<table border='1'><tr><th>ID</th><th>Name</th><th>Email</th><th>Actions</th></tr>");
			for (User user : users) {
			response.getWriter().println("<tr>");
			response.getWriter().println("<td>" + user.getId()+ "</td>");
			response.getWriter().println("<td>"+user.getName()+ "</td>");
			response.getWriter().println("<td>"+user.getEmail()+"</td>");
			response.getWriter().println("<td>");
			response.getWriter().println("<a href='UpdateUserServlet?id="+user.getId()+"'>Edit</a>");
			response.getWriter().println("<a href='DeleteUserServlet?id="+user.getId()+"'>Delete</a>");
			response.getWriter().println("</td>");
			response.getWriter().println("</tr>");
			}
			response.getWriter().println("</table>");
			response.getWriter().println("<a href='createUser.html'>Add New User</a>");
		    response.getWriter().println("</body></html>");
		} catch (Exception e) {
			// TODO: handle exception
			throw new ServletException(e);
		}
    }	

}
