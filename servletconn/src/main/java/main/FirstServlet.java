package main;

import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FirstServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			String n=request.getParameter("username");
			out.print("Welcome"+ n);
			HttpSession session=request.getSession();
            session.setAttribute("uname", n);
			out.print("<a href='servlet2'>Visit</a>");
			out.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

}
