package test;

import static org.junit.Assert.*;
import org.junit.Test;
import com.ferrari.finances.dk.rki.Rating;
import logic.CustomerDataModel;
import logic.LoanHandler;
import logic.SellerDataModel;

public class TestLoanHandler {

	// Rating A					+1
	// DownPayment under 50%	+1
	// Duration over 3 years	+1
	// Rate from bank is 8		+8
	@Test
	public void testRequestLoanAgreement()
	{
		LoanHandler loanHandler = new LoanHandler(new CustomerDataModel("", "", "", "", "", "", "", "1234567890"));
		loanHandler.setupLoanAgreement();
		loanHandler.getLoanAgreementDataModel().setAskingPrice("10000000");
		loanHandler.getLoanAgreementDataModel().setDownPayment( "2000000");
		loanHandler.getLoanAgreementDataModel().setDuration(6);
		loanHandler.setRating(Rating.A);
		loanHandler.setRate(8);

		loanHandler.requestLoanAgreement(new SellerDataModel("", "100", "", false));

		assertEquals("11.0",loanHandler.getLoanAgreementDataModel().getInterestRate());
	}
	
	
	// Rating A					+1
	// DownPayment under 50%	+1
	// Duration under 3 years	+0	<--
	// Rate from bank is 8		+8
	@Test
	public void testRequestLoanAgreement2()
	{
		LoanHandler loanHandler = new LoanHandler(new CustomerDataModel("", "", "", "", "", "", "", "1234567890"));
		loanHandler.setupLoanAgreement();
		loanHandler.getLoanAgreementDataModel().setAskingPrice("10000000");
		loanHandler.getLoanAgreementDataModel().setDownPayment( "2000000");
		loanHandler.getLoanAgreementDataModel().setDuration(1);
		loanHandler.setRating(Rating.A);
		loanHandler.setRate(8);

		loanHandler.requestLoanAgreement(new SellerDataModel("", "100", "", false));

		assertEquals("10.0",loanHandler.getLoanAgreementDataModel().getInterestRate());
	}
	
	// Rating B					+2	<--
	// DownPayment under 50%	+1
	// Duration under 3 years	+1
	// Rate from bank is 8		+8
	@Test
	public void testRequestLoanAgreement3()
	{
		LoanHandler loanHandler = new LoanHandler(new CustomerDataModel("", "", "", "", "", "", "", "1234567890"));
		loanHandler.setupLoanAgreement();
		loanHandler.getLoanAgreementDataModel().setAskingPrice("10000000");
		loanHandler.getLoanAgreementDataModel().setDownPayment( "2000000");
		loanHandler.getLoanAgreementDataModel().setDuration(6);
		loanHandler.setRating(Rating.B);
		loanHandler.setRate(8);

		loanHandler.requestLoanAgreement(new SellerDataModel("", "100", "", false));

		assertEquals("12.0",loanHandler.getLoanAgreementDataModel().getInterestRate());
	}
	
	// Rating A					+1
	// DownPaymen NOT under 50%	+0	<--
	// Duration over 3 years	+1
	// Rate from bank is 8		+8	
	@Test
	public void testRequestLoanAgreement4()
	{
		LoanHandler loanHandler = new LoanHandler(new CustomerDataModel("", "", "", "", "", "", "", "1234567890"));
		loanHandler.setupLoanAgreement();
		loanHandler.getLoanAgreementDataModel().setAskingPrice("10000000");
		loanHandler.getLoanAgreementDataModel().setDownPayment( "6000000");
		loanHandler.getLoanAgreementDataModel().setDuration(6);
		loanHandler.setRating(Rating.A);
		loanHandler.setRate(8);

		loanHandler.requestLoanAgreement(new SellerDataModel("", "100", "", false));

		assertEquals("10.0",loanHandler.getLoanAgreementDataModel().getInterestRate());
	}
	
	// Rating C					+3
	// DownPaymen NOT under 50%	+0	<--
	// Duration over 3 years	+1
	// Rate from bank is 8		+8	
	@Test
	public void testRequestLoanAgreement5()
	{
		LoanHandler loanHandler = new LoanHandler(new CustomerDataModel("", "", "", "", "", "", "", "1234567890"));
		loanHandler.setupLoanAgreement();
		loanHandler.getLoanAgreementDataModel().setAskingPrice("10000000");
		loanHandler.getLoanAgreementDataModel().setDownPayment( "6000000");
		loanHandler.getLoanAgreementDataModel().setDuration(6);
		loanHandler.setRating(Rating.C);
		loanHandler.setRate(8);

		loanHandler.requestLoanAgreement(new SellerDataModel("", "100", "", false));

		assertEquals("12.0",loanHandler.getLoanAgreementDataModel().getInterestRate());
	}
	
	@Test
	public void testCanReturnLoanAgreement() {
		LoanHandler loanHandler = new LoanHandler(new CustomerDataModel("", "", "", "", "", "", "", "1234567890"));
		loanHandler.setCanReturnLoanAgreement(true);
		assertEquals(true, loanHandler.getCanReturnLoanAgreement());
		assertEquals(true, loanHandler.canReturnLoanAgreementProperty().getValue());
	}
	@Test
	public void testCanReturnLoanAgreement2() {
		LoanHandler loanHandler = new LoanHandler(new CustomerDataModel("", "", "", "", "", "", "", "1234567890"));
		loanHandler.setCanReturnLoanAgreement(false);
		assertEquals(false, loanHandler.getCanReturnLoanAgreement());
		assertEquals(false, loanHandler.canReturnLoanAgreementProperty().getValue());
	}
	
	@Test
	public void testIsRatingApproved() {
		LoanHandler loanHandler = new LoanHandler(new CustomerDataModel("", "", "", "", "", "", "", "1234567890"));
		loanHandler.setRating(Rating.C);
		assertEquals(true, loanHandler.isRatingApproved());
	}	
	@Test
	public void testIsRatingApproved2() {
		LoanHandler loanHandler = new LoanHandler(new CustomerDataModel("", "", "", "", "", "", "", "1234567890"));
		loanHandler.setRating(Rating.D);
		assertEquals(false, loanHandler.isRatingApproved());
	}
}
