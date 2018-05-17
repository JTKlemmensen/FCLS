package database;

import java.sql.ResultSet;
import java.sql.SQLException;

import logic.sellerDataModel;
import viewPackage.FCLSController;

public class SellerDAO 
{
	public static sellerDataModel checkLogin(String username, String password)
	{
		//get dbconnector and check if username exists
		String query="SELECT * FROM Salesman_table WHERE userName ='"+username+"'";
		ResultSet dbResult = DbConnector.INSTANCE.executeQuery(query);
		
		try
		{
			while (dbResult.next())
			{
				String uName= dbResult.getString("username");
				String uPassword = dbResult.getString("password");
				if(username.equals(uName)&&password.equals(uPassword))
				{
					String limit=dbResult.getString("loanLimit");
					String fullName=dbResult.getString("fullName");
					sellerDataModel salesPerson= new sellerDataModel(uName, limit, fullName);
					return salesPerson;
				}
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
