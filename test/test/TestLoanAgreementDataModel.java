package test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Test;

import logic.CarDataModel;
import logic.CustomerDataModel;
import logic.LoanAgreementDataModel;
import logic.sellerDataModel;

public class TestLoanAgreementDataModel {
	CustomerDataModel customer = new CustomerDataModel("Jens", "lyn", "langeløgallé 53", "Lem", "4343", "45232343", "Submit@hotmail.com", "0102033434");
	@Test
	public void testCustomer() {
		LoanAgreementDataModel lGDM = new LoanAgreementDataModel(customer);
		assertEquals(customer, lGDM.getCustomer());
	}
	@Test
	public void testSeller() {
		LoanAgreementDataModel lGDM = new LoanAgreementDataModel(customer);
		sellerDataModel seller = new sellerDataModel("heiter", "500000", "Jacob Ingilesen");
		lGDM.setSeller(seller);
		assertEquals(seller, lGDM.getSeller());
	}
	@Test
	public void testCar() {
		LoanAgreementDataModel lGDM = new LoanAgreementDataModel(customer);
		CarDataModel car = new CarDataModel("fffffdsr", new BigDecimal(2000000), "4 døre");
		lGDM.setCar(car);
		assertEquals(car, lGDM.getCar());
	}
	@Test
	public void testIsApproved() {
		LoanAgreementDataModel lGDM = new LoanAgreementDataModel(customer);
		lGDM.setApproved(true);
		assertEquals(true, lGDM.isApproved());
	}
	@Test
	public void testDuration() {
		LoanAgreementDataModel lGDM = new LoanAgreementDataModel(customer);
		lGDM.setDuration("20");
		assertEquals("20", lGDM.getDuration());
	}
	@Test
	public void testAskingPrice() {
		LoanAgreementDataModel lGDM = new LoanAgreementDataModel(customer);
		String askingPrice = "2000000";
		lGDM.setAskingPrice(askingPrice);
		assertEquals(askingPrice, lGDM.getAskingPrice());
	}
	@Test
	public void testDownPayment() {
		LoanAgreementDataModel lGDM = new LoanAgreementDataModel(customer);
		String downPayment = "2000000";
		lGDM.setDownPayment(downPayment);
		assertEquals(downPayment, lGDM.getDownPayment());
	}
	@Test
	public void testInterestRate() {
		LoanAgreementDataModel lGDM = new LoanAgreementDataModel(customer);
		String interestRate = "3";
		lGDM.setInterestRate(interestRate);
		assertEquals(interestRate, lGDM.getInterestRate());
	}
	@Test
	public void testLocalDate() {
		LoanAgreementDataModel lGDM = new LoanAgreementDataModel(customer);
		LocalDate localDate = LocalDate.of(2019, 10, 12);
		lGDM.setStartDate(localDate);
		assertEquals(localDate, lGDM.getStartDate());
	}

}
