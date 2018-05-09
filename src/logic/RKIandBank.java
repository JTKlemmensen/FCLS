package logic;

import com.ferrari.finances.dk.bank.InterestRate;
import com.ferrari.finances.dk.rki.CreditRator;
import com.ferrari.finances.dk.rki.Rating;

import javafx.application.Platform;
import viewPackage.BadCreditScreenController;


public class RKIandBank extends Thread{
	private String CPR;
	private LoanHandler loanHandler;
	
	public RKIandBank(String CPR, LoanHandler loanHandler) {
		this.CPR = CPR;
		this.loanHandler = loanHandler;
	}
	
	public void run(){	
		Rating rating = CreditRator.i().rate(this.CPR);
		this.loanHandler.setRating(rating);
		
		if(Rating.D != rating)
		{
			this.loanHandler.setRate((InterestRate.i().todaysRate()));
			
			Platform.runLater(new Runnable() {
				public void run() {
				loanHandler.setCanReturnLoanAgreement(true);
			}
			});
		}
		else
		{
			Platform.runLater(new Runnable() {
				public void run() {
				BadCreditScreenController controller = new BadCreditScreenController();
				controller.showBadCreditScreen();
			}
			});
		}
	}
	
}
