package logic;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class sellerDataModel 
{
	//properties
	private StringProperty salesPersonName = new SimpleStringProperty();
	public final String getSalesPersonName() {return salesPersonName.get();}
	public final void setSalesPersonName(String value){salesPersonName.set(value);}
	public StringProperty salesPersonNameProperty(){return salesPersonName;}
	
	private StringProperty loanLimit = new SimpleStringProperty();
	public final String getLoanLimit() {return loanLimit.get();}
	public final void setLoanLimit(String value){loanLimit.set(value);}
	public StringProperty loanLimitProperty(){return loanLimit;}

	
	public sellerDataModel(String name, String limit) {
		salesPersonName.set(name);
		loanLimit.set(limit);
	}
}
