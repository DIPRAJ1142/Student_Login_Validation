package jdbc_servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc_servlet.StudentCRUD;
@WebServlet("/student_login")
public class LoginController extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		StudentCRUD studentCrud = new StudentCRUD();
		
		try 
		{
			String dbPassword = studentCrud.login(email);
//			PrintWriter out = res.getWriter();
			if (dbPassword != null) 
			{
				if(password.equals(dbPassword))
				{
//					out.print("Login Successfull");
					RequestDispatcher requestDispatcher = req.getRequestDispatcher("success.html");
					requestDispatcher.forward(req, res);
				}
				else
				{
//					out.print("Invalid Password");
					RequestDispatcher requestDispatcher = req.getRequestDispatcher("student_login.html");
					requestDispatcher.forward(req, res);
				}
			}
			else
			{
//				out.print("User not register with "+email);
				RequestDispatcher requestDispatcher = req.getRequestDispatcher("student_signup.html");
				requestDispatcher.forward(req, res);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}

}
