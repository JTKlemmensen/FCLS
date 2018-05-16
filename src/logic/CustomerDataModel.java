package logic;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CustomerDataModel
{
	//properties
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
		
	public CustomerDataModel(String firstName, String lastName, String address, String city, String zipCode, String phoneNumber, String email, String CPR) 

	{
		setCustomerFirstName(firstName);
		setCustomerLastName(lastName);
		setCustomerAddress(address);
		setCustomerCity(city);
		setPostalCode(zipCode);
		setCustomerPhone(phoneNumber);
		setCustomerEmail(email);
		setCustomerCPR(CPR);
	}
}
