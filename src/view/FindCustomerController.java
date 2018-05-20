package view;

import javafx.scene.control.TableView;
import logic.CustomerDataModel;
import logic.CustomerHandler;
import logic.LoanHandler;

public class FindCustomerController 
{

	private CustomerHandler customerHandler;
	
	public FindCustomerController()
	{
		customerHandler=new CustomerHandler();
	}
	
	public void createLoanAgreementPressed(CustomerDataModel customer)
	{
		//setup loanagreementcalled
		LoanHandler loanHandler=new LoanHandler();
		loanHandler.setupLoanAgreement(customer);
		//create loanagreementview 
		CreateLoanAggrementController controller = new CreateLoanAggrementController(loanHandler);
		
		FCLSController.INSTANCE.changeView(controller.getView());
	}
	
	public void createCustomerPressed() {
		CustomerDataModel customer=new CustomerDataModel("", "", "", "", "", "", "", "");
		
		CustomerHandler customerHandler=new CustomerHandler();
		
		CreateCustomerController controller = new CreateCustomerController(customerHandler, customer);
		
		FCLSController.INSTANCE.changeView(controller.getView());
	}
	
	public void updateTableView(TableView<CustomerDataModel> table, String firstName, String lastName, String phoneNumber)
	{
		table.getItems().clear();
		table.getItems().addAll(customerHandler.getCustomers(firstName, lastName, phoneNumber));
//		customerHandler.getCustomers(firstName, lastName, phoneNumber);
	}
}