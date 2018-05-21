package logic;

import com.ferrari.finances.dk.bank.InterestRate;
import com.ferrari.finances.dk.rki.CreditRator;
import com.ferrari.finances.dk.rki.Rating;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import view.BadCreditScreenController;
import view.FCLSAlert;
import view.FCLSController;


public class RKIandBank extends Thread
{
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
				public void run() 
				{
				//TODO
				//is this proper procedure?
				loanHandler.setCanReturnLoanAgreement(true);
			}
			});
		}
		else
		{
			Platform.runLater(new Runnable() {
				public void run() 
				{
				Alert alert = new FCLSAlert(AlertType.NONE,"Kunde er registreret som dårlig betaler, lånetilbud er afvist",new ButtonType("Accepter"));
				alert.setTitle("RKI Afvisning");
				alert.showAndWait();
				FCLSController.INSTANCE.changeView(null);
				}
			});
		}
	}
	
}
