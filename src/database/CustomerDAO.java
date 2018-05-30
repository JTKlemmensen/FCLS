package database;

import java.util.List;

import logic.CustomerDataModel;

public interface CustomerDAO 
{
	public boolean createNewCustomer(CustomerDataModel newCustomer);
	
	public List<CustomerDataModel> getCustomerList();
}
