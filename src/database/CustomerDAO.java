package database;


import logic.CustomerDataModel;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import logic.CustomerDataModel;

public class CustomerDAO 
{
	public Boolean createNewCustomer(CustomerDataModel newCustomer)
	{
		//TODO
		//check if customer already exists (problematic due to time between check and insert, implement if there's time and a strong need for it)
		
		//insert customer
		//Perhaps change this to prepared statements or something similar to clean up code, if time
		String customerValues=" '"+newCustomer.getCustomerFirstName()+"', '"+newCustomer.getCustomerLastName()+"', '"+newCustomer.getCustomerAddress()+"', '"+newCustomer.getCustomerCity()+"', '"+newCustomer.getPostalCode()+"', '"+newCustomer.getCustomerPhone()+"', '"+newCustomer.getCustomerEmail()+"', '"+newCustomer.getCustomerCPR()+"'";
		String query="INSERT INTO Customer_table ( firstName, lastName, address, city, postalCode, phoneNumber, email, cpr) VALUES ("+customerValues+" )";
		try
		{
			DbConnector.INSTANCE.executeUpdate(query);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public List<CustomerDataModel> getCustomerList()
	{
		List<CustomerDataModel> resultList=new ArrayList<CustomerDataModel>();
		//get customer resultset from db
		try{      
	        String SQL = "SELECT * FROM Customer_table";            
	        ResultSet rs = DbConnector.INSTANCE.executeQuery(SQL);
	        while(rs.next())
	        {
	        	String firstName=rs.getString("firstName");
	        	String lastName=rs.getString("lastName");
	        	String address=rs.getString("address");
	        	String city=rs.getString("city");
	        	String postalCode=rs.getString("postalCode");
	        	String phone=rs.getString("phoneNumber");
	        	String email=rs.getString("email");
	        	String cpr=rs.getString("cpr");
	        	
	        	CustomerDataModel cm = new CustomerDataModel(firstName, lastName, address, city, postalCode, phone, email, cpr);
	            
	            resultList.add(cm);                  
	        }
	        return resultList;
	    }
	    catch(Exception e){
	          e.printStackTrace();
	          System.out.println("Error on Building Data");
	          return null;
	    }
	}

}
