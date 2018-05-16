package viewPackage;

import logic.CustomerDataModel;
import logic.LoanHandler;

public class FindCustomerController 
{
	public void createLoanAgreementPressed()
	{
		//TODO
		//simulate customer for now

		CustomerDataModel customer=new CustomerDataModel("Jens", "lyn", "langeløgallé 53", "Lem", "4343", "45232343", "Sutmin@hotmail.com", "0102033434");
		
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
}
