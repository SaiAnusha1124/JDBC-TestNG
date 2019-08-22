package com.atmecs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.testng.AssertJUnit;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners
public class Application
{
	Scanner sc = new Scanner(System.in);
	public Connection con;
	public ResultSet rs;
	String str;

	@BeforeSuite
	public Connection connection1() throws SQLException, ClassNotFoundException 
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String connectionUrl = "jdbc:sqlserver://ATMECSINLT-083\\SQLEXPRESS;database=Employee;integratedSecurity=true;";
		con = DriverManager.getConnection(connectionUrl);
		System.out.println("successfull connected");
		return con;
	}

	@Test
	public String select(int a) throws SQLException 
	{
		try 
		{
			Statement state = null;
			state = con.createStatement();
			String st = "Select * from List1";
			rs = state.executeQuery(st);

			while (rs.next()) 
			{
				str = rs.getInt(1) + "" + rs.getString(2) + "" + rs.getInt(3);
			}
			state.close();

		}
		catch (SQLException e) 
		{
			System.out.println();
		}
		return str;
	}

	@Test
	public void Insert( int b,String name, int d) throws SQLException 
	{
		Statement state = con.createStatement();
		String st = "INSERT INTO List1" + " VALUES("+ b+", " + "'" + name + "'" + "," + d + ")";
		state.execute(st);
		String st1 = "Select * from List1 ";
		rs = state.executeQuery(st1);
		while (rs.next())
		{
			System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t\t" + rs.getInt(3));
		}
		System.out.println("\n\n");

		state.close();
	}

	@Test
	public void Deleting(int a) throws SQLException
	{
		Statement state;
		String st = "DELETE FROM List1 WHERE productid=" + a + "";
		state = con.createStatement();
		state.execute(st);
		String st2 = "Select * from List1 ";
		rs = state.executeQuery(st2);
		while (rs.next()) 
		{
			System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t\t" + rs.getInt(3));
		}
		System.out.println("\n\n");
		state.close();
	}

	@Test(priority = 1)
	public void Update(int a, String name) throws SQLException 
	{
		Statement state;
		String st = "UPDATE [dbo].[List1] SET productname=" + "'" + name + "'" + " WHERE productid=" + a + "";
		state = con.createStatement();
		state.execute(st);
		String st3 = "Select * from List1 ";
		rs = state.executeQuery(st3);
		while (rs.next()) 
		{
			System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t\t" + rs.getInt(3));
		}
		System.out.println("\n\n");
		state.close();

	}

	@AfterTest
	public void close1() throws SQLException 
	{
		if (con != null) 
		{
			con.close();

			System.out.println("successfull closed");
		}

		Reporter.log("closed");
	}

	/**
	 * Rigourous Test :-)
	 */
	@Test
	public void testApp() 
	{
		AssertJUnit.assertTrue(true);
	}
}
