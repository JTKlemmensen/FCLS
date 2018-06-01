package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public final class DbConnector
{
	private static final String CONNECTIONURL = "jdbc:sqlserver://188.181.204.238:1433; instance=SQLEXPRESS; databaseName=FCLS; user=eksaminator; password=DMU17HE;";
//	private static final String CONNECTIONURL = "jdbc:sqlserver://localhost:1433;" + "instanceName=SQLEXPRESS;" + "databaseName=FCLS" + ";" + "integratedSecurity=true;";
	
	private DbConnector()
	{
		//-prevent instantiation 
	}
	
	public static Connection getConnection()
	{
	   Connection con;

	   try 
	   {
		   con = DriverManager.getConnection(CONNECTIONURL);
		   return con;
	   } 
	   catch (Exception e) 
	   { 
		   e.printStackTrace(); 
		   return null;
	   }
	}
	
	public static void closeConnection(ResultSet rs)
	{
		try 
		{ 
			rs.close(); 
		} 
		catch (Exception e) 
		{ 
			//silent
		}
	}
	
	public static void closeConnection(PreparedStatement ps)
	{
		try 
		{ 
			ps.close(); 
		} 
		catch (Exception e) 
		{ 
			//silent
		}
	}
	
	public static void closeConnection(Connection con)
	{
		try 
		{ 
			con.close(); 
		} 
		catch (Exception e) 
		{ 
			//silent
		}
	}
}
