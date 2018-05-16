package viewPackage;

import database.CustomerDAO;
import logic.CustomerDataModel;

public class CustomerHandler {

	public boolean insertToDB(CustomerDataModel customer) {
		CustomerDAO CD = new CustomerDAO();
		return CD.createNewCustomer(customer);	
	}

}
