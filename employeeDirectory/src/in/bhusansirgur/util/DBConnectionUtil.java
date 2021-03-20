package in.bhusansirgur.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectionUtil 
{
	private static final String jdbcURL = "jdbc:mysql://localhost:3306/employeedirectory";
	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String username = "root";
	private static final String password = "Javed@123";
	private static Connection conn = null;
	public static Connection  openConnection()
	{
		if(conn != null)
		{
			return conn;
		}
		else
		{
			try
			{
				Class.forName(driver);
				conn = DriverManager.getConnection(jdbcURL,username,password);
			}
			catch(Exception e) 
			{
				e.printStackTrace();
			}
		}
		return conn;
	}
}
