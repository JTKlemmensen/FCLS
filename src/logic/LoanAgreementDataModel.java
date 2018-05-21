package logic;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoanAgreementDataModel
{
	private CustomerDataModel customer;
	private SellerDataModel seller;
	private CarDataModel car;
	
	private boolean approved;
	private int loanIDNumber;
	
	public CustomerDataModel getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDataModel customer) {
		this.customer = customer;
	}

	public SellerDataModel getSeller() {
		return seller;
	}

	public void setSeller(SellerDataModel seller) {
		this.seller = seller;
	}

	public CarDataModel getCar() {
		return car;
	}

	public void setCar(CarDataModel car) {
		this.car = car;
	}
	
	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	
	public void setLoanIDNumber(int value)
	{
		loanIDNumber=value;
	}
	
	public int getLoanIDNumber()
	{
		return loanIDNumber;
	}
	
	//properties
	
	private IntegerProperty duration = new SimpleIntegerProperty(6);
	public final int getDuration() {return duration.get();}
	public final void setDuration(int value){duration.set(value);}
	public IntegerProperty durationProperty(){return duration;}
	
	private StringProperty askingPrice = new SimpleStringProperty();
	public final String getAskingPrice() {return askingPrice.get();}
	public final void setAskingPrice(String value){askingPrice.set(value);}
	public StringProperty askingPriceProperty(){return askingPrice;}
	
	private StringProperty downPayment = new SimpleStringProperty();
	public final String getDownPayment() {return downPayment.get();}
	public final void setDownPayment(String value){downPayment.set(value);}
	public StringProperty downPaymentProperty(){return downPayment;}
	
	private StringProperty interestRate = new SimpleStringProperty();
	public final String getInterestRate() {return interestRate.get();}
	public final void setInterestRate(String value){interestRate.set(value);}
	public StringProperty interestRateProperty(){return interestRate;}
	
	private ObjectProperty<LocalDate> startDate = new SimpleObjectProperty<>();
	public final LocalDate getStartDate() {return startDate.get();}
	public final void setStartDate(LocalDate value) {startDate.set(value);}
	public ObjectProperty<LocalDate> startDateProperty() {return startDate;}
	
	
	public LoanAgreementDataModel(CustomerDataModel customer) 
	{
		this.customer = customer;
	}
	
}
