package logic;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Observable;
import com.ferrari.finances.dk.rki.Rating;

public class LoanHandler extends Observable
{
    private LoanAgreementDataModel loanAgreement;
    private double dailyRate;
    private Rating rating;
    
    public LoanAgreementDataModel requestLoanAgreement(String carPrice, String downPayment, Date StartDate, String duration, CarDataModel car)
    {
	double rate = calculateRate(dailyRate, rating, carPrice, downPayment, duration);
	
	
	
	return null;
    }
    
    public void setupLoanAgreement(CustomerDataModel customer)
    {
	
    }
    
    private double calculateRate(double dailyRate, Rating rating, String carPrice, String downPayment, String duration)
    {
	switch(rating)
	{
	case A: dailyRate+=1; break;
	case B: dailyRate+=2; break;
	default: dailyRate+=3; break;
	}
	
	BigDecimal percentageOfCarPaid = new BigDecimal(downPayment).divide(new BigDecimal(carPrice));
	
	if(percentageOfCarPaid.compareTo(new BigDecimal(0.5))<0)
	    dailyRate+=1;
	    
	if(Integer.parseInt(duration) > 3)
	    dailyRate+=1;
	    
	return dailyRate;
    }

    public void setRating(Rating rating) 
    {
	this.rating = rating;
    }

    public void setRate(double rate) 
    {
	this.dailyRate = rate;
    }
}