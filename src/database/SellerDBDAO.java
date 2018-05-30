package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import logic.SellerDataModel;

public class SellerDBDAO implements SellerDAO
{
	
	public SellerDataModel checkLogin(String username, String password)
	{
		SellerDataModel salesPerson=null;
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
					boolean administrator=dbResult.getBoolean("administrator");
					salesPerson= new SellerDataModel(uName, limit, fullName, administrator);
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
