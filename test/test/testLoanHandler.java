package test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import com.ferrari.finances.dk.rki.Rating;

import logic.CarDataModel;
import logic.CustomerDataModel;
import logic.LoanAgreementDataModel;
import logic.LoanHandler;
import logic.sellerDataModel;

public class testLoanHandler {

	@Test
	public void testRequestLoanAgreement()
	{
		LoanHandler loanHandler = new LoanHandler();
		loanHandler.setupLoanAgreement(new CustomerDataModel("", "", "", "", "", "", "", "1234567890"));
		loanHandler.getLoanAgreementDataModel().setAskingPrice("10000000");
		loanHandler.getLoanAgreementDataModel().setDownPayment( "2000000");
		loanHandler.getLoanAgreementDataModel().setDuration("6");
		loanHandler.setRating(Rating.A);

		loanHandler.requestLoanAgreement(new sellerDataModel("", "", ""));
		
		assertEquals("3.0",loanHandler.getLoanAgreementDataModel().getInterestRate());
	}
}
