package logic;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class sellerDataModel 
{
	//properties
	private StringProperty salesPersonUsername = new SimpleStringProperty();
	public final String getSalesPersonUsername() {return salesPersonUsername.get();}
	public final void setSalesPersonUsername(String value){salesPersonUsername.set(value);}
	public StringProperty salesPersonUsernameProperty(){return salesPersonUsername;}
	
	private StringProperty loanLimit = new SimpleStringProperty();
	public final String getLoanLimit() {return loanLimit.get();}
	public final void setLoanLimit(String value){loanLimit.set(value);}
	public StringProperty loanLimitProperty(){return loanLimit;}

	private StringProperty salesPersonFullName = new SimpleStringProperty();
	public final String getSalesPersonFullName() {return salesPersonFullName.get();}
	public final void setSalesPersonFullName(String value){salesPersonFullName.set(value);}
	public StringProperty salesPersonFullNameProperty(){return salesPersonFullName;}
	
	public sellerDataModel(String username, String limit, String fullName) 
	{
		setSalesPersonUsername(username);
		setLoanLimit(limit);
		setSalesPersonFullName(fullName);
	}
}
