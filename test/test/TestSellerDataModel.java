package test;

import static org.junit.Assert.*;

import org.junit.Test;

import logic.LoanHandler;
import logic.SellerDataModel;

public class TestSellerDataModel {
	SellerDataModel seller = new SellerDataModel(null, null, null, false);
	
	@Test
	public void testLoanLimit() {
		String loanLimit = "300000";
		seller.setLoanLimit(loanLimit);
		assertEquals(loanLimit, seller.getLoanLimit());
		assertEquals(loanLimit, seller.loanLimitProperty().getValue());
	}
	@Test
	public void testLoanLimit2() {
		String loanLimit = "200000";
		seller.setLoanLimit(loanLimit);
		assertEquals(loanLimit, seller.getLoanLimit());
		assertEquals(loanLimit, seller.loanLimitProperty().getValue());
	}
	@Test
	public void testUsername() {
		String username = "Findus Pedersen";
		seller.setUsername(username);
		assertEquals(username, seller.getUsername());
		assertEquals(username, seller.usernameProperty().getValue());
	}
	@Test
	public void testUsername2() {
		String username = "Line Pedersen";
		seller.setUsername(username);
		assertEquals(username, seller.getUsername());
		assertEquals(username, seller.usernameProperty().getValue());
	}
	@Test
	public void testFullName() {
		String fullName = "Nicholas Nielsen";
		seller.setFullName(fullName);
		assertEquals(fullName, seller.getFullName());
		assertEquals(fullName, seller.fullNameProperty().getValue());
	}
	@Test
	public void testFullName2() {
		String fullName = "Line Pedersen";
		seller.setFullName(fullName);
		assertEquals(fullName, seller.getFullName());
		assertEquals(fullName, seller.fullNameProperty().getValue());
	}
	@Test
	public void testIsAdministrator() {
		SellerDataModel seller = new SellerDataModel(null, null, null, false);
		assertEquals(false, seller.getIsAdministrator());
	}
	@Test
	public void testIsAdministrator2() {
		SellerDataModel seller = new SellerDataModel(null, null, null, true);
		assertEquals(true, seller.getIsAdministrator());
	}

}
