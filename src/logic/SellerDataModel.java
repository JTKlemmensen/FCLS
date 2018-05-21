package logic;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SellerDataModel 
{
	//properties
	private StringProperty username = new SimpleStringProperty();
	public final String getUsername() {return username.get();}
	public final void setUsername(String value){username.set(value);}
	public StringProperty usernameProperty(){return username;}
	
	private StringProperty loanLimit = new SimpleStringProperty();
	public final String getLoanLimit() {return loanLimit.get();}
	public final void setLoanLimit(String value){loanLimit.set(value);}
	public StringProperty loanLimitProperty(){return loanLimit;}

	private StringProperty fullName = new SimpleStringProperty();
	public final String getFullName() {return fullName.get();}
	public final void setFullName(String value){fullName.set(value);}
	public StringProperty fullNameProperty(){return fullName;}
	
	private boolean isAdministrator;
	public boolean getIsAdministrator() 
	{
		return isAdministrator;
	}
	
	public SellerDataModel(String username, String limit, String fullName, boolean administrator) 
	{
		setUsername(username);
		setLoanLimit(limit);
		setFullName(fullName);
		isAdministrator=administrator;
	}
}
