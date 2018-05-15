package viewPackage;

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
	
	private StringProperty customerFirstName = new SimpleStringProperty();
	public final String getCustomerFirstName() {return customerFirstName.get();}
	public final void setCustomerFirstName(String value){customerFirstName.set(value);}
	public StringProperty customerFirstNameProperty(){return customerFirstName;}
	
	private StringProperty customerLastName = new SimpleStringProperty();
	public final String getCustomerLastName() {return customerLastName.get();}
	public final void setCustomerLastName(String value){customerLastName.set(value);}
	public StringProperty customerLastNameProperty(){return customerLastName;}
	
	
	private StringProperty customerAddress = new SimpleStringProperty();
	public final String getCustomerAddress() {return customerAddress.get();}
	public final void setCustomerAddress(String value){customerAddress.set(value);}
	public StringProperty customerAddressProperty(){return customerAddress;}
	
	private StringProperty customerCity = new SimpleStringProperty();
	public final String getCustomerCity() {return customerCity.get();}
	public final void setCustomerCity(String value){customerCity.set(value);}
	public StringProperty customerCityProperty(){return customerCity;}
	
	private StringProperty customerPhone = new SimpleStringProperty();
	public final String getCustomerPhone() {return customerPhone.get();}
	public final void setCustomerPhone(String value){customerPhone.set(value);}
	public StringProperty customerPhoneProperty(){return customerPhone;}
	
	private StringProperty customerCPR = new SimpleStringProperty();
	public final String getCustomerCPR() {return customerCPR.get();}
	public final void setCustomerCPR(String value){customerCPR.set(value);}
	public StringProperty customerCPRProperty(){return customerCPR;}
	
	private StringProperty postalCode = new SimpleStringProperty();
	public final String getPostalCode() {return postalCode.get();}
	public final void setPostalCode(String value){postalCode.set(value);}
	public StringProperty postalCodeProperty(){return postalCode;}

	private StringProperty customerEmail = new SimpleStringProperty();
	public final String getCustomerEmail() {return customerEmail.get();}
	public final void setCustomerEmail(String value){customerEmail.set(value);}
	public StringProperty customerEmailProperty(){return customerEmail;}
	
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
		itsHandler.createCustomer(customerFirstName.get(), customerLastName.get(), customerAddress.get(), customerCity.get(), customerPhone.get(), customerCPR.get(), postalCode.get(), customerEmail.get());
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
		if(getCustomerFirstName()==null||getCustomerFirstName().equals(""))
		{
			dataIsViable=false;
			itsView.addWarning("Indtast købspris");
		}
		if(getCustomerAddress()==null||getCustomerAddress().equals(""))
		{
			dataIsViable=false;
			itsView.addWarning("Indtast udbetaling");
		}
		if(getCustomerCity()==null||getCustomerCity().equals(""))
		{
			dataIsViable=false;
			itsView.addWarning("Vælg startdato");
		}
		if(getCustomerPhone()==null||getCustomerPhone()=="")
		{
			dataIsViable=false;
			itsView.addWarning("Vælg bil");
		}
		if(getCustomerCPR()==null||getCustomerCPR().equals(""))
		{
			dataIsViable=false;
			itsView.addWarning("Indtast købspris");
		}
		if(getPostalCode()==null||getPostalCode().equals(""))
		{
			dataIsViable=false;
			itsView.addWarning("Indtast udbetaling");
		}
		if(getCustomerEmail()==null||getCustomerEmail().equals(""))
		{
			dataIsViable=false;
			itsView.addWarning("Vælg startdato");
		}
		return dataIsViable;
	}
}
