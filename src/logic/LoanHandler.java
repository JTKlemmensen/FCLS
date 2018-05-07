package logic;

import java.util.Date;
import java.util.Observable;


import com.ferrari.finances.dk.rki.Rating;

public class LoanHandler
{

    private LoanAgreementDataModel loanAgreement;
    private double dailyRate;
    private Rating rating;
    
    public LoanAgreementDataModel requestLoanAgreement(String carPrice, String downPayment, Date StartDate, String duration, CarDataModel car)
    {
	return null;
    }
    
    public void setupLoanAgreement(CustomerDataModel customer)
    {
	
    	RKIandBank rkiandBank = new RKIandBank(loanAgreement.getCustomer().getCPR(), this);
    }
    
    private double calculateRate(double dailyRate, Rating rating, String carPrice, String downPayment, String duration)
    {
	return 0.0;
    }

    

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public void setRate(double rate) {
		this.dailyRate = rate;
	}

}
