package database;

import logic.CustomerDataModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO 
{
	public boolean createNewCustomer(CustomerDataModel newCustomer)
	{
		PreparedStatement statement=null;
		Connection con=null;
		int result=0;
		try
		{
			//create prepared statement, find username and check password
			con=DbConnector.getConnection();
			statement =con.prepareStatement("INSERT INTO Customer_table ( firstName, lastName, address, city, postalCode, phoneNumber, email, cpr) VALUES(?,?,?,?,?,?,?,?)");
			statement.setString(1, newCustomer.getFirstName());
			statement.setString(2, newCustomer.getLastName());
			statement.setString(3, newCustomer.getAddress());
			statement.setString(4, newCustomer.getCity());
			statement.setString(5, newCustomer.getPostalCode());
			statement.setString(6, newCustomer.getPhone());
			statement.setString(7, newCustomer.getEmail());
			statement.setString(8, newCustomer.getCPR());
			
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
		return result!=0;
	}
	
	public List<CustomerDataModel> getCustomerList()
	{
		List<CustomerDataModel> resultList=new ArrayList<CustomerDataModel>();
		PreparedStatement statement=null;
		Connection con=null;
		ResultSet rs=null;
		
		try
		{      
			con=DbConnector.getConnection();
			statement =con.prepareStatement("SELECT * FROM Customer_table");
			
			rs = statement.executeQuery();
			
	        while(rs.next())
	        {
	        	int id=rs.getInt("customerID");
	        	String firstName=rs.getString("firstName");
	        	String lastName=rs.getString("lastName");
	        	String address=rs.getString("address");
	        	String city=rs.getString("city");
	        	String postalCode=rs.getString("postalCode");
	        	String phone=rs.getString("phoneNumber");
	        	String email=rs.getString("email");
	        	String cpr=rs.getString("cpr");
	        	
	        	CustomerDataModel cm = new CustomerDataModel(firstName, lastName, address, city, postalCode, phone, email, cpr);
	        	cm.setCustomerID(id);
	            
	            resultList.add(cm);                  
	        }
	        return resultList;
	    }
	    catch(Exception e)
		{
	          e.printStackTrace();
	          
	          return null;
	    }
		finally 
		{
			//make sure all connections are closed and resources released
			 DbConnector.closeConnection(rs);
			 DbConnector.closeConnection(statement);
			 DbConnector.closeConnection(con);
		}
		
	}
}