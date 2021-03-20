package in.bhusansirgur.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import in.bhusansirgur.entity.Login;
import in.bhusansirgur.util.DBConnectionUtil;
public class LoginDAOImpl implements LoginDAO
{
	@Override
	public String authenticate(Login login) 
	{
		String sql = "SELECT * FROM tbl_login where email=? and password=?";
		try 
		{
			Connection conn = DBConnectionUtil.openConnection(); 
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,login.getEmail());
			ps.setString(2, login.getPassword());
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				return "true";
			}
			else
			{
				return "false";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "error";
	}

}
