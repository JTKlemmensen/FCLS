package test;

import static org.junit.Assert.*;

import org.junit.Test;

import logic.LoanHandler;
import logic.sellerDataModel;

public class TestSellerDataModel {
	sellerDataModel seller = new sellerDataModel(null, null, null);
	
	@Test
	public void testSalesPersonLoanLimit() {
		String loanLimit = "300000";
		seller.setLoanLimit(loanLimit);
		assertEquals(loanLimit, seller.getLoanLimit());
		assertEquals(loanLimit, seller.loanLimitProperty().getValue());
	}
	@Test
	public void testSalesPersonLoanLimit2() {
		String loanLimit = "200000";
		seller.setLoanLimit(loanLimit);
		assertEquals(loanLimit, seller.getLoanLimit());
		assertEquals(loanLimit, seller.loanLimitProperty().getValue());
	}
	@Test
	public void testSalesPersonUsername() {
		String username = "Findus Pedersen";
		seller.setSalesPersonUsername(username);
		assertEquals(username, seller.getSalesPersonUsername());
		assertEquals(username, seller.salesPersonUsernameProperty().getValue());
	}
	@Test
	public void testSalesPersonUsername2() {
		String username = "Line Pedersen";
		seller.setSalesPersonUsername(username);
		assertEquals(username, seller.getSalesPersonUsername());
		assertEquals(username, seller.salesPersonUsernameProperty().getValue());
	}
	@Test
	public void testSalesPersonFullName() {
		String fullName = "Nicholas Nielsen";
		seller.setSalesPersonFullName(fullName);
		assertEquals(fullName, seller.getSalesPersonFullName());
		assertEquals(fullName, seller.salesPersonFullNameProperty().getValue());
	}
	@Test
	public void testSalesPersonFullName2() {
		String fullName = "Line Pedersen";
		seller.setSalesPersonFullName(fullName);
		assertEquals(fullName, seller.getSalesPersonFullName());
		assertEquals(fullName, seller.salesPersonFullNameProperty().getValue());
	}

}
