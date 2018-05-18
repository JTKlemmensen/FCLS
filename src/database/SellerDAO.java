package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import logic.sellerDataModel;
import view.FCLSController;

public class SellerDAO 
{
	
	public static sellerDataModel checkLogin(String username, String password)
	{
		sellerDataModel salesPerson=null;
		PreparedStatement statement=null;
		Connection con=null;
		ResultSet dbResult=null;
		
		try
		{
			//create prepared statement, find username and check password
			con=DbConnector.getConnection();
			statement =con.prepareStatement("SELECT * FROM Salesman_table WHERE userName =?");
			statement.setString(1, username);
			
			dbResult = statement.executeQuery();
			
			while (dbResult.next())
			{
				String uName= dbResult.getString("username");
				String uPassword = dbResult.getString("password");
				if(username.equals(uName)&&password.equals(uPassword))
				{
					String limit=dbResult.getString("loanLimit");
					String fullName=dbResult.getString("fullName");
					salesPerson= new sellerDataModel(uName, limit, fullName);
				}
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally 
		{
			//make sure all connections are closed and resources released
			 DbConnector.closeConnection(dbResult);
			 DbConnector.closeConnection(statement);
			 DbConnector.closeConnection(con);
		}
		return salesPerson;
	}
}
