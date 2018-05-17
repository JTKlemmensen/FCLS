package logic;

import java.util.ArrayList;
import java.util.List;

import database.CustomerDAO;

public class CustomerHandler {

	private List<CustomerDataModel> customers;
	
	public boolean insertToDB(CustomerDataModel customer) {
		CustomerDAO CD = new CustomerDAO();
		return CD.createNewCustomer(customer);	
	}
	
	public List<CustomerDataModel> getCustomers(String firstName, String lastName, String phoneNumber)
	{
//		if(customers==null)
//			customers = CustomerDAO.getAll();
		
		customers = new ArrayList<CustomerDataModel>();
		customers.add(new CustomerDataModel("Peter", "Jensen", "Villavej 25", "Herning", "7400", "12645678", "ja", "yes"));
		customers.add(new CustomerDataModel("Ole", "Jensen", "Villavej 24", "Herning", "7400", "12342378", "ja", "yes"));
		customers.add(new CustomerDataModel("Niels", "Jensen", "Villavej 23", "Herning", "7400", "76531247", "ja", "yes"));
		customers.add(new CustomerDataModel("Henrik", "Jensen", "Villavej 21", "Herning", "7400", "90843231", "ja", "yes"));
		customers.add(new CustomerDataModel("Per", "Jensen", "Villavej 22", "Herning", "7400","97423231", "PerJensen@hot.com", "1234567790"));
		
		List<CustomerDataModel> customers = new ArrayList<CustomerDataModel>();
		
		for(CustomerDataModel cdm : this.customers)
		{
			if( !considerProperty(cdm.getCustomerFirstName(),firstName) ||
				!considerProperty(cdm.getCustomerLastName(),lastName) ||
				!considerProperty(cdm.getCustomerPhone(),phoneNumber) )
				continue;
			
			customers.add(cdm);
		}
		
		return customers;
	}
	
	private boolean considerProperty(String customerProperty, String otherProperty)
	{
		if(otherProperty!=null && otherProperty.length()>0)
			if(customerProperty!= null && !customerProperty.toLowerCase().contains(otherProperty.toLowerCase()) )
				return false;
		return true;
	}

}