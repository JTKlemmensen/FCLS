package logic;

import com.ferrari.finances.dk.bank.InterestRate;
import com.ferrari.finances.dk.rki.CreditRator;
import com.ferrari.finances.dk.rki.Rating;


public class RKIandBank extends Thread{
	private String CPR;
	private LoanHandler loanHandler;
	
	public RKIandBank(String CPR, LoanHandler loanHandler) {
		this.CPR = CPR;
		this.loanHandler = loanHandler;
	}
	
	public void run(){		
		this.loanHandler.setRating(CreditRator.i().rate(this.CPR));
		this.loanHandler.setRate((InterestRate.i().todaysRate()));
	}
	
}
