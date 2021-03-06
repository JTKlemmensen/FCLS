package logic;

import com.ferrari.finances.dk.bank.InterestRate;
import com.ferrari.finances.dk.rki.CreditRator;
import com.ferrari.finances.dk.rki.Rating;

//TODO remove loanhandler and move to view
public class RKIandBank
{
	private String CPR;
	private LoanHandler loanHandler;
	
	public RKIandBank(String CPR, LoanHandler loanHandler) {
		this.CPR = CPR;
		this.loanHandler = loanHandler;
	}
	
	public void run()
	{	
		Rating rating = CreditRator.i().rate(this.CPR);

		this.loanHandler.setRating(rating);
		this.loanHandler.setRate((InterestRate.i().todaysRate()));
	}
}