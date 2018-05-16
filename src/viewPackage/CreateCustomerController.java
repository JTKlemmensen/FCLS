package viewPackage;

import java.math.BigDecimal;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import logic.CarDataModel;
import logic.CustomerDataModel;
import logic.LoanAgreementDataModel;
import logic.LoanHandler;

public class CreateCustomerController {
	private CreateCustomerView itsView;
	private CustomerHandler itsHandler;
	private CustomerDataModel itsCustomer;
	
	public CreateCustomerController(CustomerHandler handler, CustomerDataModel customer)
	{
		itsHandler=handler;
		itsView=new CreateCustomerView(this);
		itsCustomer=customer;
	}
	
	public CreateCustomerView getView()
	{
		return itsView;
	}
	
	public void createCustomer()
	{
		//TODO
		//check if data is enough for loanhandler
		if(checkInputViability()==false)
		{
			return;
		}
		//TODO
		//perhaps loanagreement is retrieved when creating createloanscreen
		boolean isInsertSucessful = itsHandler.insertToDB(itsCustomer);
	}
	
	public void cancelCreateCustomer()
	{

		FCLSController.INSTANCE.changeView(null);
	}
	
	public CustomerDataModel getCustomer()
	{
		return itsCustomer;
	}
	
	public CustomerHandler getHandler()
	{
		return itsHandler;
	}
	
	private boolean checkInputViability()
	{
		boolean dataIsViable=true;
		if(itsCustomer.getCustomerFirstName()==null||itsCustomer.getCustomerFirstName().equals(""))
		{
			dataIsViable=false;
			itsView.addWarning("Indtast købspris");
		}
		if(itsCustomer.getCustomerLastName()==null||itsCustomer.getCustomerLastName().equals(""))
		{
			dataIsViable=false;
			itsView.addWarning("Indtast udbetaling");
		}
		if(itsCustomer.getCustomerAddress()==null||itsCustomer.getCustomerAddress().equals(""))
		{
			dataIsViable=false;
			itsView.addWarning("Vælg startdato");
		}
		if(itsCustomer.getCustomerCity()==null||itsCustomer.getCustomerCity()=="")
		{
			dataIsViable=false;
			itsView.addWarning("Vælg bil");
		}
		if(itsCustomer.getCustomerPhone()==null||itsCustomer.getCustomerPhone().equals(""))
		{
			dataIsViable=false;
			itsView.addWarning("Indtast købspris");
		}
		if(itsCustomer.getCustomerCPR()==null||itsCustomer.getCustomerCPR().equals(""))
		{
			dataIsViable=false;
			itsView.addWarning("Indtast udbetaling");
		}
		if(itsCustomer.getPostalCode()==null||itsCustomer.getPostalCode().equals(""))
		{
			dataIsViable=false;
			itsView.addWarning("Vælg startdato");
		}
		if(itsCustomer.getCustomerEmail()==null||itsCustomer.getCustomerEmail().equals(""))
		{
			dataIsViable=false;
			itsView.addWarning("Vælg startdato");
		}
		return dataIsViable;
	}
}
