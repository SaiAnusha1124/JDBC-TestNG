package com.atmecs;

import java.sql.Connection;
import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Listeners
public class Testing 
{
	Connection con;
	Application app = new Application();
	@BeforeTest
	void testingconnection() throws ClassNotFoundException, SQLException
	{
		con = app.connection1();
		Assert.assertNotNull(con, "not connected");
		System.out.println("Connection Estabilished");
	}
	@Test(priority = 0)
	void insert() throws SQLException 
	{
		app.Insert(4, "samsung", 13500);
		String rs = app.select(4);
		Assert.assertEquals(rs, "4samsung13500");
		System.out.println("successfully inserted");
	}
	void update() throws SQLException 
	{
		app.Update(5, "galaxy");
		String rs = app.select(5);
		Assert.assertEquals(rs, "5galaxy5700");
		System.out.println("successfully updated");
	}
	@Test
	void delete() throws SQLException 
	{
		app.Deleting(4);
		System.out.println("successfully deleted");

	}
	@AfterTest
	void close2() throws SQLException {
		app.close1();
	}
}
