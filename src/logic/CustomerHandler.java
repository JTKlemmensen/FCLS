package logic;

import java.util.ArrayList;
import java.util.List;

public class CustomerHandler {

	private List<CustomerDataModel> customers;
	
	public void createCustomer(String firstName, String lastName, String address, String city, String phone, String CPR,
			String postalCode, String customerEmail) {
		// TODO Auto-generated method stub
		
	}
	
	public List<CustomerDataModel> getCustomers(String firstName, String lastName, String phoneNumber)
	{
//		if(customers==null)
//			customers = CustomerDAO.getAll();
		
		List<CustomerDataModel> customers = new ArrayList<CustomerDataModel>();
		
		for(CustomerDataModel cdm : this.customers)
		{
			if( !checkCustomerProperty(cdm.getCustomerFirstName(),firstName) ||
				!checkCustomerProperty(cdm.getCustomerLastName(),lastName) ||
				!checkCustomerProperty(cdm.getCustomerPhone(),phoneNumber) )
				continue;
			
			customers.add(cdm);
		}
		
		
		return customers;
	}
	
	private boolean checkCustomerProperty(String customerProperty, String otherProperty)
	{
		if(otherProperty!=null && otherProperty.length()>0)
			if(customerProperty!= null && !customerProperty.contains(otherProperty) )
				return false;
		return true;
	}

}