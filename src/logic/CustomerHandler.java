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
			if(firstName!=null && firstName.length()>0)
				if(!cdm.getCustomerFirstName().contains(firstName) )
					continue;
			
			if(lastName!=null && lastName.length()>0)
				if(!cdm.getCustomerLastName().contains(lastName) )
					continue;
			
			if(phoneNumber!=null && phoneNumber.length()>0)
				if(!cdm.getCustomerPhone().contains(phoneNumber) )
					continue;
			
			customers.add(cdm);
		}
		
		
		return customers;
	}

}