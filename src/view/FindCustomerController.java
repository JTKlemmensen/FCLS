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
		CreateLoanAgreementView view = new CreateLoanAgreementView(loanHandler);
		
		FCLSController.INSTANCE.changeView(view);
		
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
			    		view.setCanClose(true);
			    		FCLSController.INSTANCE.changeView(null);
			    	}
			    } 
			});
		    }  
		});
		t.start();
	}
	
	public void createCustomerPressed() 
	{
		CreateCustomerView controller = new CreateCustomerView();
		
		FCLSController.INSTANCE.changeView(controller);
	}
	
	public void updateTableView(TableView<CustomerDataModel> table, String customerID, String firstName, String lastName, String phoneNumber)
	{
		table.getItems().clear();
		table.getItems().addAll(customerHandler.getCustomers(customerID, firstName, lastName, phoneNumber));
	}
	
	public void closeView()
	{
		FCLSController.INSTANCE.changeView(null);
	}
}