package logic;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import java.util.Observable;

import com.ferrari.finances.dk.rki.Rating;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class LoanHandler extends Observable {
	private LoanAgreementDataModel loanAgreement;
	private double dailyRate;
	private Rating rating;
	private PaymentOverview overview;
	
	private BooleanProperty canReturnLoanAgreement = new SimpleBooleanProperty();
	public final Boolean getCanReturnLoanAgreement() {return canReturnLoanAgreement.get();}
	public final void setCanReturnLoanAgreement(Boolean value){canReturnLoanAgreement.set(value);}
	public BooleanProperty canReturnLoanAgreementProperty(){return canReturnLoanAgreement;}

	public LoanHandler(CustomerDataModel customer)
	{
		loanAgreement = new LoanAgreementDataModel(customer);
		loanAgreement.setCar(new CarDataModel("", ""));

	}
	
	public void requestLoanAgreement(SellerDataModel salesPerson) 
	{	
		loanAgreement.setSeller(salesPerson);
		
		double rate = calculateRate(new BigDecimal(loanAgreement.getAskingPrice()), new BigDecimal(loanAgreement.getDownPayment()), loanAgreement.getDuration());
		
		loanAgreement.setInterestRate(Double.toString(rate));
		
		//set approval based on salesperson
		double loanAmount=Double.parseDouble(loanAgreement.getAskingPrice())-Double.parseDouble(loanAgreement.getDownPayment());
		if(loanAmount<=Double.parseDouble(salesPerson.getLoanLimit()))
		{
			loanAgreement.setApproved(true);
		}
		else
		{
			loanAgreement.setApproved(false);
		}
	}

	public void setupLoanAgreement() 
	{
		//TODO keep or change to notifyobserver instead?
		setCanReturnLoanAgreement(false);
		RKIandBank rkiandBank = new RKIandBank(loanAgreement.getCustomer().getCPR(), this);
		rkiandBank.run();
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
	
	private PaymentOverview getPaymentList() {
		if (overview == null)
			overview = new PaymentOverview(loanAgreement);
		return overview;
	}
	
	public List<Payment> getPayments() {
		return getPaymentList().getPayments();
	}
	
	/**
	 * Calculates the compounded yearly rate or actual percentage yield using the following formula: 
	 * 
	 * (((r/100)/12+1)^12-1)*100
	 * 
	 * @return
	 * String
	 */
	public String getAPR() {
		MathContext mc = new MathContext(20, RoundingMode.HALF_UP);
		BigDecimal one = (new BigDecimal(loanAgreement.getInterestRate(), mc)).divide(new BigDecimal("100", mc));
		one = one.divide(new BigDecimal("12"), mc);
		one = new BigDecimal("1").add(one);
		one = one.pow(12, mc);
		one = one.subtract(new BigDecimal("1"));
		one = one.multiply(new BigDecimal("100"));
        
		return (one.setScale(4, RoundingMode.HALF_UP)).toString();
	}
	
	/**
	 * Create String representation of the monthly payment rounded 2 decimals.
	 *  
	 * @return
	 * BigDecimal
	 */
	public String getMonthlyPayment() {
		return (getPaymentList().getMonthlyPayment().setScale(2, RoundingMode.HALF_UP)).toString();
	}
}
