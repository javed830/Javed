package in.bhusansirgur.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import in.bhusansirgur.entity.Employee;
import in.bhusansirgur.util.DBConnectionUtil;

public class EmployeeDAOImpl implements EmployeeDAO 
{
	Connection conn = null;
	Statement stmt = null;
	ResultSet res = null;
	PreparedStatement preparestatement = null;

	public List<Employee> get() 
	{
		try
		{
			List<Employee> list  = new ArrayList<Employee>();
			String sql = "SELECT * FROM tbl_employee";
			conn = DBConnectionUtil.openConnection();
			stmt = conn.createStatement();
			res = stmt.executeQuery(sql);
			while(res.next())
			{
				Employee employee = new Employee();
				employee.setId(res.getInt("id"));
				employee.setName(res.getString("name"));
				employee.setDepartment(res.getString("department"));
				employee.setDob(res.getString("dob"));
				list.add(employee);
			}
		}
		catch(Exception e )
		{
			e.printStackTrace();
		}
		return null;		
	}

	@Override
	public boolean save(Employee e) 
	{
		boolean flag =false;
		try
		{
			String sql = "INSERT INTO tbl_employee(name,dob,department)VALUES('"+e.getName()+"','"+e.getDob()+"','"+e.getDepartment()+"')";
			conn = DBConnectionUtil.openConnection();
			preparestatement = conn.prepareStatement(sql);
			preparestatement.executeUpdate();
			flag = true;
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return  flag;
	}

	@Override
	public Employee get(int id) 
	{
		Employee employee = null;
		try
		{
			employee = new Employee();
			String sql = "SELECT * FROM tbl_employee where id ="+id;
			conn = DBConnectionUtil.openConnection();
			stmt = conn.createStatement();
			res = stmt.executeQuery(sql);
			while(res.next())
			{
				employee.setId(res.getInt("id"));
				employee.setName(res.getString("name"));
				employee.setDob(res.getString("dob"));
				employee.setDepartment(res.getString("department"));
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		System.out.println("Employee name :"+employee.getName()+"Employee dob :"+employee.getDob()+
				"Employee department :"+employee.getDepartment());
		return employee;
	}

	@Override
	public boolean update(Employee e) 
	{
		boolean flag = false;
		try
		{
			String sql = "Update tbl_employeee SET name ='"+e.getName()+"',dob = '"+e.getDob()+"',"
					+ "department='"+e.getDepartment()+"' where id = '"+e.getId(); 
			conn = DBConnectionUtil.openConnection();
			preparestatement = conn.prepareStatement(sql);
			preparestatement.executeUpdate();
			flag = true;
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return flag;
		
	}

	@Override
	public boolean delete(int id)
	{
		boolean flag = false;
		try
		{
			String sql = "DELETE  FROM tbl_employee WHERE id ="+id;
			conn = DBConnectionUtil.openConnection();
			preparestatement = conn.prepareStatement(sql);
			preparestatement.executeUpdate();
			flag = true;
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return flag;
	}

}
