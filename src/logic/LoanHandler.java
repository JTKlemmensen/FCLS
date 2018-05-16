package logic;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.util.Date;
import java.util.Observable;
import com.ferrari.finances.dk.rki.Rating;
import com.sun.org.apache.xpath.internal.operations.Bool;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoanHandler extends Observable {
	private LoanAgreementDataModel loanAgreement;
	private double dailyRate;
	private Rating rating;
	
	private BooleanProperty canReturnLoanAgreement = new SimpleBooleanProperty();
	public final Boolean getCanReturnLoanAgreement() {return canReturnLoanAgreement.get();}
	public final void setCanReturnLoanAgreement(Boolean value){canReturnLoanAgreement.set(value);}
	public BooleanProperty canReturnLoanAgreementProperty(){return canReturnLoanAgreement;}

	public void requestLoanAgreement(sellerDataModel salesPerson) 
	{	
		loanAgreement.setSeller(salesPerson);
		
		double rate = calculateRate(new BigDecimal(loanAgreement.getAskingPrice()), new BigDecimal(loanAgreement.getDownPayment()), Integer.parseInt(loanAgreement.getDuration()));
		
		loanAgreement.setInterestRate(Double.toString(rate));
	}

	public void setupLoanAgreement(CustomerDataModel customer) 
	{
		//TODO
		//keep or change to notifyobserver instead?
		setCanReturnLoanAgreement(false);
		loanAgreement = new LoanAgreementDataModel(customer);
		loanAgreement.setCar(new CarDataModel("", null, ""));
		RKIandBank rkiandBank = new RKIandBank(loanAgreement.getCustomer().getCustomerCPR(), this);
		rkiandBank.start();
	}

	private double calculateRate(BigDecimal carPrice, BigDecimal downPayment, int duration) 
	{
		double resultingRate=dailyRate;
		switch (rating) {
		case A:
			resultingRate += 1;
			break;
		case B:
			resultingRate += 2;
			break;
		default:
			resultingRate += 3;
			break;
		}
		//dividing with precision of 15 decimals
		BigDecimal percentageOfCarPaid = downPayment.divide(carPrice, new MathContext(15));

		if (percentageOfCarPaid.compareTo(new BigDecimal(0.5)) < 0)
			resultingRate += 1;

		if (duration > 3)
			resultingRate += 1;

		return resultingRate;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
		notifyObservers();
	}
	
	public boolean isRatingApproved() {
		return rating != Rating.D;
	}

	public void setRate(double dailyRate) {
		this.dailyRate = dailyRate;
	}
	
	public LoanAgreementDataModel getLoanAgreementDataModel()
	{
		return loanAgreement;
	}
}
