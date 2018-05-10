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

	public LoanAgreementDataModel requestLoanAgreement(String carPrice, String downPayment, LocalDate startDate,
			String duration, CarDataModel car) {
		loanAgreement.setAskingPrice(carPrice);
		loanAgreement.setDownPayment(downPayment);
		loanAgreement.setStartDate(startDate);
		loanAgreement.setDuration(duration);
		loanAgreement.setCar(car);
		
		//TODO
		//get seller elsewhere
		loanAgreement.setSeller(new sellerDataModel("Carsten Bjørn", "2000000"));
		
		double rate = calculateRate(new BigDecimal(loanAgreement.getAskingPrice()), new BigDecimal(loanAgreement.getDownPayment()), Integer.parseInt(loanAgreement.getDuration()));
		
		loanAgreement.setInterestRate(Double.toString(rate));
		
		return loanAgreement;
	}

	public void setupLoanAgreement(CustomerDataModel customer) {
		setCanReturnLoanAgreement(false);
		loanAgreement = new LoanAgreementDataModel(customer);
		RKIandBank rkiandBank = new RKIandBank(loanAgreement.getCustomer().getCustomerCPR(), this);
		Thread t = new Thread(rkiandBank);
		t.start();
	}

	private double calculateRate(BigDecimal carPrice, BigDecimal downPayment,
			int duration) {
		switch (rating) {
		case A:
			dailyRate += 1;
			break;
		case B:
			dailyRate += 2;
			break;
		default:
			dailyRate += 3;
			break;
		}
		//dividing with precision of 15 decimals
		BigDecimal percentageOfCarPaid = downPayment.divide(carPrice, new MathContext(15));

		if (percentageOfCarPaid.compareTo(new BigDecimal(0.5)) < 0)
			dailyRate += 1;

		if (duration > 3)
			dailyRate += 1;

		return dailyRate;
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
}
