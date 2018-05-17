package view;

import java.math.BigDecimal;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import logic.CarDataModel;
import logic.CustomerDataModel;
import logic.CustomerHandler;
import logic.LoanAgreementDataModel;
import logic.LoanHandler;

public class CreateCustomerController {
	private CreateCustomerView itsView;
	private CustomerHandler itsHandler;
	private CustomerDataModel itsCustomer;
	private boolean isInsertSuccessful;
	
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

		if(checkInputViability()==false)
		{
			return;
		}

		isInsertSuccessful = itsHandler.insertToDB(itsCustomer);
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
			itsView.addWarning("Indtast fornavn");
		}
		if(itsCustomer.getCustomerLastName()==null||itsCustomer.getCustomerLastName().equals(""))
		{
			dataIsViable=false;
			itsView.addWarning("Indtast efternavn");
		}
		if(itsCustomer.getCustomerAddress()==null||itsCustomer.getCustomerAddress().equals(""))
		{
			dataIsViable=false;
			itsView.addWarning("Indtast adresse");
		}
		if(itsCustomer.getCustomerCity()==null||itsCustomer.getCustomerCity()=="")
		{
			dataIsViable=false;
			itsView.addWarning("Indtast by");
		}
		if(itsCustomer.getCustomerPhone()==null||itsCustomer.getCustomerPhone().equals(""))
		{
			dataIsViable=false;
			itsView.addWarning("Indtast tlf nr.");
		}
		if(itsCustomer.getCustomerCPR()==null||itsCustomer.getCustomerCPR().equals(""))
		{
			dataIsViable=false;
			itsView.addWarning("Indtast cpr");
		}else if(itsCustomer.getCustomerCPR().length() != 10)
		{
			dataIsViable=false;
			itsView.addWarning("CPR er forkert længde");
		}
		if(itsCustomer.getPostalCode()==null||itsCustomer.getPostalCode().equals(""))
		{
			dataIsViable=false;
			itsView.addWarning("Indtast postnummer");
		}
		if(!itsCustomer.getCustomerEmail().matches("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?"))
		{
			dataIsViable=false;
			itsView.addWarning("Indtast Email");
		}
		return dataIsViable;

	}

	public boolean getInsertSuccessful() {
		return isInsertSuccessful;	
	}
}
