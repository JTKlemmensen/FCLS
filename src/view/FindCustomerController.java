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
	
	public void createLoanAgreementPressed(CustomerDataModel selectedCustomer)
	{
		//setup loanagreementcalled
		LoanHandler loanHandler=new LoanHandler();
		loanHandler.setupLoanAgreement(selectedCustomer);
		//create loanagreementview 
		CreateLoanAgreementController controller = new CreateLoanAgreementController(loanHandler);
		
		FCLSController.INSTANCE.changeView(controller.getView());
		
	}
	
	public void createCustomerPressed() 
	{
		CreateCustomerController controller = new CreateCustomerController(customerHandler);
		
		FCLSController.INSTANCE.changeView(controller.getView());
	}
	
	public void updateTableView(TableView<CustomerDataModel> table, String customerID, String firstName, String lastName, String phoneNumber)
	{
		table.getItems().clear();
		table.getItems().addAll(customerHandler.getCustomers(customerID, firstName, lastName, phoneNumber));
	}
}