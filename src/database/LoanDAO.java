package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import logic.LoanAgreementDataModel;

public class LoanDAO 
{
	public static void insertLoanAgreementDB(LoanAgreementDataModel loanAgreement)
	{
		/*PreparedStatement statement=null;
		Connection con=null;
		int result=0;
		try
		{
			//create prepared statement, find username and check password
			con=DbConnector.getConnection();
			statement =con.prepareStatement("INSERT INTO Customer_table ( firstName, lastName, address, city, postalCode, phoneNumber, email, cpr) VALUES(?,?,?,?,?,?,?,?)");
			statement.setString(1, newCustomer.getCustomerFirstName());
			statement.setString(2, newCustomer.getCustomerLastName());
			statement.setString(3, newCustomer.getCustomerAddress());
			statement.setString(4, newCustomer.getCustomerCity());
			statement.setString(5, newCustomer.getPostalCode());
			statement.setString(6, newCustomer.getCustomerCity());
			statement.setString(7, newCustomer.getCustomerEmail());
			statement.setString(8, newCustomer.getCustomerCPR());
			
			result = statement.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally 
		{
			//make sure all connections are closed and resources released
			 DbConnector.closeConnection(statement);
			 DbConnector.closeConnection(con);
		}
		
		if(result==0)
		{
			return false;
		}
		else
		{
			return true;
		}*/
	}
}
