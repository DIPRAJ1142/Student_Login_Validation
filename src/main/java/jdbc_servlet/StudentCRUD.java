package jdbc_servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jdbc_servlet.StudentInfo;

public class StudentCRUD {

	public Connection getConnection() throws Exception
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/servletdb", "root", "root");
		return connection;
	}
	
	public int signUp(StudentInfo studentInfo) throws Exception
	{
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("insert into studentinfo values (?,?,?,?,?,?)");
		preparedStatement.setInt(1, studentInfo.getId());
		preparedStatement.setString(2, studentInfo.getName());
		preparedStatement.setInt(3, studentInfo.getAge());
		preparedStatement.setLong(4, studentInfo.getPhone());
		preparedStatement.setString(5, studentInfo.getEmail());
		preparedStatement.setString(6, studentInfo.getPassword());
		
		int result = preparedStatement.executeUpdate();
		connection.close();
		return result;
		
	}
	
	public String login(String email) throws Exception
	{
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("select password from studentinfo where email=?");
		preparedStatement.setString(1, email);
		
		String password = null;
		ResultSet resultSet = preparedStatement.executeQuery();
		if(resultSet.next())
		{
			password = resultSet.getString("password");
		}
		connection.close();
		return password;
	}
	
}
