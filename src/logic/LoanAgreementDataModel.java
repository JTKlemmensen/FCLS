package logic;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoanAgreementDataModel
{
	private CustomerDataModel customer;
	private sellerDataModel seller;
	private CarDataModel car;
	
	private LocalDate startDate;
	private boolean approved;
	
	public CustomerDataModel getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDataModel customer) {
		this.customer = customer;
	}

	public sellerDataModel getSeller() {
		return seller;
	}

	public void setSeller(sellerDataModel seller) {
		this.seller = seller;
	}

	public CarDataModel getCar() {
		return car;
	}

	public void setCar(CarDataModel car) {
		this.car = car;
	}
	
	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	
	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	
	//properties
	
	private StringProperty duration = new SimpleStringProperty();
	public final String getDuration() {return duration.get();}
	public final void setDuration(String value){duration.set(value);}
	public StringProperty durationProperty(){return duration;}
	
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
	
	
	
	
	public LoanAgreementDataModel(CustomerDataModel customer) {
		this.customer = customer;
	}
}
