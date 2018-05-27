package logic;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CustomerDataModel
{
	//properties
		private StringProperty firstName = new SimpleStringProperty();
		public final String getFirstName() {return firstName.get();}
		public final void setFirstName(String value){firstName.set(value);}
		public StringProperty firstNameProperty(){return firstName;}
		
		private StringProperty lastName = new SimpleStringProperty();
		public final String getLastName() {return lastName.get();}
		public final void setLastName(String value){lastName.set(value);}
		public StringProperty lastNameProperty(){return lastName;}
		
		private StringProperty Address = new SimpleStringProperty();
		public final String getAddress() {return Address.get();}
		public final void setAddress(String value){Address.set(value);}
		public StringProperty addressProperty(){return Address;}
		
		private StringProperty city = new SimpleStringProperty();
		public final String getCity() {return city.get();}
		public final void setCity(String value){city.set(value);}
		public StringProperty cityProperty(){return city;}
		
		private StringProperty phone = new SimpleStringProperty();
		public final String getPhone() {return phone.get();}
		public final void setPhone(String value){phone.set(value);}
		public StringProperty phoneProperty(){return phone;}
		
		private StringProperty CPR = new SimpleStringProperty();
		public final String getCPR() {return CPR.get();}
		public final void setCPR(String value){CPR.set(value);}
		public StringProperty CPRProperty(){return CPR;}
		
		private StringProperty postalCode = new SimpleStringProperty();
		public final String getPostalCode() {return postalCode.get();}
		public final void setPostalCode(String value){postalCode.set(value);}
		public StringProperty postalCodeProperty(){return postalCode;}
	
		private StringProperty email = new SimpleStringProperty();
		public final String getEmail() {return email.get();}
		public final void setEmail(String value){email.set(value);}
		public StringProperty emailProperty(){return email;}
		
		private int customerID;
		public int getCustomerID() {return customerID;};
		public void setCustomerID(int value) {customerID=value;};
		
		
	public CustomerDataModel(String firstName, String lastName, String address, String city, String zipCode, String phoneNumber, String email, String CPR) 
	{
		setFirstName(firstName);
		setLastName(lastName);
		setAddress(address);
		setCity(city);
		setPostalCode(zipCode);
		setPhone(phoneNumber);
		setEmail(email);
		setCPR(CPR);
	}
	
	private String anonymousCPR() {
		StringBuilder sb = new StringBuilder();
    	char[] ch = getCPR().toCharArray();
    	for(int x = 0; x < ch.length; x++) {
    		if(x<6) {
    			sb.append(ch[x]);
    		} else if(x==6) {
    			sb.append("-x");
    		} else {
    			sb.append("x");
    		}
    	}
    	return sb.toString();
	}
	
	private String replaceCommaWithSemicolon(String word) {
		return word.replace(',', ';');
	}
	
    @Override
    public String toString() {
    	return getFirstName() + "," + getLastName()  + "," + replaceCommaWithSemicolon(getAddress()) + "," + getPostalCode() + "," + getCity() + ","
    		   + getPhone() + "," + getEmail() + "," + anonymousCPR() + "," + getCustomerID();
    }
}