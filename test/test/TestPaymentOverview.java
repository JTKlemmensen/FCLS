package test;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import logic.CustomerDataModel;
import logic.LoanHandler;
import logic.Payment;
import logic.PaymentOverview;

public class TestPaymentOverview {

	// first test
	// InterestRate 	10	
	// AskingPrice  	10000000
	// DownPayment		2000000
	// Duration			1
	// LocalDate		9-11-2020
	@Test
	public void testInstalment() {
		LoanHandler loanHandler = new LoanHandler(new CustomerDataModel("", "", "", "", "", "", "", "1234567890"));
		LocalDate localDate = LocalDate.of(2020, 11, 9);
		
		loanHandler.getLoanAgreementDataModel().setInterestRate("10");
		loanHandler.getLoanAgreementDataModel().setAskingPrice("10000000");
		loanHandler.getLoanAgreementDataModel().setDownPayment( "2000000");
		loanHandler.getLoanAgreementDataModel().setDuration(1);
		loanHandler.getLoanAgreementDataModel().setStartDate(localDate);
		PaymentOverview po = new PaymentOverview(loanHandler.getLoanAgreementDataModel());
		
		String[] instalments = {"630790.31", "637098.21", "643469.19", "649903.89", "656402.93", "662966.95", "669596.62", "676292.59", "683055.52", "689886.07", "696784.93", "703752.78"};
		
		for(int x = 0; x < instalments.length; x++) {			
		assertEquals(instalments[x], po.getPayments().get(x).getInstalment());
//		assertEquals(s,po.getPayments().get(index).getDate());
//		assertEquals(s,po.getPayments().get(index).getInterest());
//		assertEquals(s,po.getPayments().get(index).getPayment());
//		assertEquals(s,po.getPayments().get(index).getPaymentNo());
//		assertEquals(s,po.getPayments().get(index).getPrincipal());
		}
	}
	
	// second test
	// InterestRate 	8	
	// AskingPrice  	10000000
	// DownPayment		2000000
	// Duration			1
	// LocalDate		9-11-2020
	@Test
	public void testInstalmentChangeInterestRate() {
		LoanHandler loanHandler = new LoanHandler(new CustomerDataModel("", "", "", "", "", "", "", "1234567890"));
		LocalDate localDate = LocalDate.of(2020, 11, 9);
		
		loanHandler.getLoanAgreementDataModel().setInterestRate("8");
		loanHandler.getLoanAgreementDataModel().setAskingPrice("10000000");
		loanHandler.getLoanAgreementDataModel().setDownPayment( "2000000");
		loanHandler.getLoanAgreementDataModel().setDuration(1);
		loanHandler.getLoanAgreementDataModel().setStartDate(localDate);
		PaymentOverview po = new PaymentOverview(loanHandler.getLoanAgreementDataModel());
		
		String[] instalments = {"641387.88", "645877.59", "650398.73", "654951.53", "659536.19", "664152.94", "668802.01", "673483.62", "678198.01", "682945.4", "687726.01", "692540.1"};

		for(int x = 0; x < instalments.length; x++) {		
		assertEquals(instalments[x], po.getPayments().get(x).getInstalment());
//		assertEquals(s,po.getPayments().get(index).getDate());
//		assertEquals(s,po.getPayments().get(index).getInterest());
//		assertEquals(s,po.getPayments().get(index).getPayment());
//		assertEquals(s,po.getPayments().get(index).getPaymentNo());
//		assertEquals(s,po.getPayments().get(index).getPrincipal());
		}
	}
	
	// first test
	// InterestRate 	10	
	// AskingPrice  	20000000
	// DownPayment		2000000
	// Duration			1
	// LocalDate		9-11-2020
	@Test
	public void testChangeAskingPrice() {
		LoanHandler loanHandler = new LoanHandler(new CustomerDataModel("", "", "", "", "", "", "", "1234567890"));
		LocalDate localDate = LocalDate.of(2020, 11, 9);
		
		loanHandler.getLoanAgreementDataModel().setInterestRate("10");
		loanHandler.getLoanAgreementDataModel().setAskingPrice("20000000");
		loanHandler.getLoanAgreementDataModel().setDownPayment( "2000000");
		loanHandler.getLoanAgreementDataModel().setDuration(1);
		loanHandler.getLoanAgreementDataModel().setStartDate(localDate);
		PaymentOverview po = new PaymentOverview(loanHandler.getLoanAgreementDataModel());
		
		String[] payments = {"1419278.2", "1433470.98", "1447805.69", "1462283.74", "1476906.58", "1491675.65", "1506592.4", "1521658.33", "1536874.91", "1552243.66", "1567766.1", "1583443.76"};

		for(int x = 0; x < payments.length; x++) {
			
		assertEquals(payments[x], po.getPayments().get(x).getInstalment());
//		assertEquals(s,po.getPayments().get(index).getDate());
//		assertEquals(s,po.getPayments().get(index).getInterest());
//		assertEquals(s,po.getPayments().get(index).getPayment());
//		assertEquals(s,po.getPayments().get(index).getPaymentNo());
//		assertEquals(s,po.getPayments().get(index).getPrincipal());
		}
	}
	
	// first test
	// InterestRate 	10	
	// AskingPrice  	10000000
	// DownPayment		3000000
	// Duration			1
	// LocalDate		9-11-2020
	@Test
	public void testChangeDownPayment() {
		LoanHandler loanHandler = new LoanHandler(new CustomerDataModel("", "", "", "", "", "", "", "1234567890"));
		LocalDate localDate = LocalDate.of(2020, 11, 9);
		
		loanHandler.getLoanAgreementDataModel().setInterestRate("10");
		loanHandler.getLoanAgreementDataModel().setAskingPrice("10000000");
		loanHandler.getLoanAgreementDataModel().setDownPayment( "3000000");
		loanHandler.getLoanAgreementDataModel().setDuration(1);
		loanHandler.getLoanAgreementDataModel().setStartDate(localDate);
		PaymentOverview po = new PaymentOverview(loanHandler.getLoanAgreementDataModel());
		
		for(Payment o : po.getPayments()) {
			System.out.println(o.getInstalment());
		}
		
		String[] payments = {"551941.52", "557460.94", "563035.55", "568665.9", "574352.56", "580096.09", "585897.05", "591756.02", "597673.58", "603650.31", "609686.82", "615783.68"};

		for(int x = 0; x < payments.length; x++) {
			
		assertEquals(payments[x], po.getPayments().get(x).getInstalment());
//		assertEquals(s,po.getPayments().get(index).getDate());
//		assertEquals(s,po.getPayments().get(index).getInterest());
//		assertEquals(s,po.getPayments().get(index).getPayment());
//		assertEquals(s,po.getPayments().get(index).getPaymentNo());
//		assertEquals(s,po.getPayments().get(index).getPrincipal());
		}
	}
	
	// first test
	// InterestRate 	10	
	// AskingPrice  	10000000
	// DownPayment		3000000
	// Duration			2
	// LocalDate		9-11-2020
	@Test
	public void testChangeDuration() {
		LoanHandler loanHandler = new LoanHandler(new CustomerDataModel("", "", "", "", "", "", "", "1234567890"));
		LocalDate localDate = LocalDate.of(2020, 11, 9);
		
		loanHandler.getLoanAgreementDataModel().setInterestRate("10");
		loanHandler.getLoanAgreementDataModel().setAskingPrice("10000000");
		loanHandler.getLoanAgreementDataModel().setDownPayment( "2000000");
		loanHandler.getLoanAgreementDataModel().setDuration(2);
		loanHandler.getLoanAgreementDataModel().setStartDate(localDate);
		PaymentOverview po = new PaymentOverview(loanHandler.getLoanAgreementDataModel());
		
		for(Payment o : po.getPayments()) {
			System.out.println(o.getInstalment());
		}
			
		assertEquals(24, po.getPayments().size());
//		assertEquals(s,po.getPayments().get(index).getDate());
//		assertEquals(s,po.getPayments().get(index).getInterest());
//		assertEquals(s,po.getPayments().get(index).getPayment());
//		assertEquals(s,po.getPayments().get(index).getPaymentNo());
//		assertEquals(s,po.getPayments().get(index).getPrincipal());
	}
	
	// first test
	// InterestRate 	10	
	// AskingPrice  	10000000
	// DownPayment		3000000
	// Duration			1
	// LocalDate		3-8-2019
	@Test
	public void testChangeDate() {
		LoanHandler loanHandler = new LoanHandler(new CustomerDataModel("", "", "", "", "", "", "", "1234567890"));
		LocalDate localDate = LocalDate.of(2019, 8, 3);
		
		loanHandler.getLoanAgreementDataModel().setInterestRate("10");
		loanHandler.getLoanAgreementDataModel().setAskingPrice("10000000");
		loanHandler.getLoanAgreementDataModel().setDownPayment( "2000000");
		loanHandler.getLoanAgreementDataModel().setDuration(1);
		loanHandler.getLoanAgreementDataModel().setStartDate(localDate);
		PaymentOverview po = new PaymentOverview(loanHandler.getLoanAgreementDataModel());
		
		for(Payment o : po.getPayments()) {
			System.out.println(o.getInstalment());
		}
			
		assertEquals(12, po.getPayments().size());
//		assertEquals(s,po.getPayments().get(index).getDate());
//		assertEquals(s,po.getPayments().get(index).getInterest());
//		assertEquals(s,po.getPayments().get(index).getPayment());
//		assertEquals(s,po.getPayments().get(index).getPaymentNo());
//		assertEquals(s,po.getPayments().get(index).getPrincipal());
	}
}
