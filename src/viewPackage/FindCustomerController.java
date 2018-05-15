package viewPackage;

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
	
	public void createLoanAggrementPressed()
	{
		//TODO
		//simulate customer for now
		CustomerDataModel customer=new CustomerDataModel("Jens", "Lyn", "langeløgallé 53", "Lem", "4343", "45232343", "Sutmin@hotmail.com", "0102033434");
		
		//setup loanaggrementcalled
		LoanHandler loanHandler=new LoanHandler();
		loanHandler.setupLoanAgreement(customer);
		//create loanaggrementview 
		CreateLoanAggrementController controller = new CreateLoanAggrementController(loanHandler, customer);
		
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