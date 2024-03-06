package jdbc_servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import jdbc_servlet.StudentCRUD;
@WebServlet("/student_register")
public class RegisterController extends GenericServlet{

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		int age = Integer.parseInt(req.getParameter("age"));
		long phone = Long.parseLong(req.getParameter("phone"));
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		StudentInfo studentInfo = new StudentInfo();
		studentInfo.setId(id);
		studentInfo.setName(name);
		studentInfo.setAge(age);
		studentInfo.setPhone(phone);
		studentInfo.setEmail(email);
		studentInfo.setPassword(password);
		
		StudentCRUD studentCrud = new StudentCRUD();
		try 
		{
			int result = studentCrud.signUp(studentInfo);
			PrintWriter out = res.getWriter();
			if (result != 0) 
			{
				out.print("Register Successfull");
			}
			else
			{
				out.print("Register Failed");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	
		
	
}
