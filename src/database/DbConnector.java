package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DbConnector
{
	public final static DbConnector INSTANCE = new DbConnector();
	private Connection connection;
	private DbConnector()
	{
		openConnection();
	}
	
	//TODO
	//Close connection to preserve lars's sanity
	private void openConnection()
	{
       String conUrl = "jdbc:sqlserver://188.181.204.238:1433; instance=SQLEXPRESS; databaseName=FCLS; user=hkkrestless; password=Bamsefar123;";

	   try 
	   {
	   connection = DriverManager.getConnection(conUrl);
	   } 
	   catch (Exception e) { e.printStackTrace(); }
	}
	
	public ResultSet executeQuery(String query)
	{
		ResultSet result=null;
		try
		{
			Statement statement = connection.createStatement();
			result= statement.executeQuery(query);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	public void executeUpdate(String query)
	{
		try
		{
			Statement statement = connection.createStatement();
			statement.executeUpdate(query);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}
