package view;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
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
		LoanHandler loanHandler=new LoanHandler(selectedCustomer);
		CreateLoanAgreementController controller = new CreateLoanAgreementController(loanHandler);
		
		FCLSController.INSTANCE.changeView(controller.getView());
		
		Thread t = new Thread(new Runnable() {
		    @Override
		    public void run()
		    {
			loanHandler.setupLoanAgreement();
			
			Platform.runLater(new Runnable() {
			    @Override
			    public void run()
			    {
				if(loanHandler.isRatingApproved())
				    loanHandler.setCanReturnLoanAgreement(true);
				else
				{
				    Alert alert = new FCLSAlert(AlertType.NONE,"Kunden er registreret hos RKI. Lånetilbud er afvist",new ButtonType("Accepter"));
				    alert.setTitle("RKI Afvisning");
				    alert.showAndWait();
				    controller.setCanClose(true);
				    FCLSController.INSTANCE.changeView(null);
				}
				
			    }
			    
			});
			
		    }
		    
		});
		t.start();
		//create loanagreementview 
	}
	
	public void createCustomerPressed() 
	{
		CreateCustomerController controller = new CreateCustomerController(customerHandler);
		
		FCLSController.INSTANCE.changeView(controller.getView());
	}
	
	public void updateTableView(TableView<CustomerDataModel> table, String firstName, String lastName, String phoneNumber)
	{
		table.getItems().clear();
		table.getItems().addAll(customerHandler.getCustomers(firstName, lastName, phoneNumber));
	}
	
	public void closePane()
	{
		FCLSController.INSTANCE.changeView(null);
	}
}