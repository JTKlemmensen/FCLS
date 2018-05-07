package logic;

<<<<<<< HEAD
import java.util.Date;
import java.util.Observable;

=======
>>>>>>> 27dee8ac088cdf39406771266d8470f786d2b9eb
import com.ferrari.finances.dk.rki.Rating;

public class LoanHandler
{
<<<<<<< HEAD
    private LoanAgreementDataModel loanAgreement;
    private double dailyRate;
    private Rating rating;
    
    public LoanAgreementDataModel requestLoanAgreement(String carPrice, String downPayment, Date StartDate, String duration, CarDataModel car)
    {
	return null;
    }
    
    public void setupLoanAgreement(CustomerDataModel customer)
    {
	
    }
    
    private double calculateRate(double dailyRate, Rating rating, String carPrice, String downPayment, String duration)
    {
	return 0.0;
    }
=======
	private Rating rating;
	private double rate;

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

>>>>>>> 27dee8ac088cdf39406771266d8470f786d2b9eb
}
