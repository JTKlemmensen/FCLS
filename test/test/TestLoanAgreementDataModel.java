package test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Test;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import logic.CarDataModel;
import logic.CustomerDataModel;
import logic.LoanAgreementDataModel;
import logic.SellerDataModel;

public class TestLoanAgreementDataModel {
	CustomerDataModel customer = new CustomerDataModel("Jens", "lyn", "langeløgallé 53", "Lem", "4343", "45232343", "Submit@hotmail.com", "0102033434");
	@Test
	public void testCustomer() {
		LoanAgreementDataModel lGDM = new LoanAgreementDataModel(customer);
		assertEquals(customer, lGDM.getCustomer());
	}	
	@Test
	public void testCustomer2() {
		CustomerDataModel customer2 = new CustomerDataModel("Ben", "Nielsen", "langeløgallé 53", "Lem", "7680", "45252343", "Submi@hotmail.com", "0102036434");
		LoanAgreementDataModel lGDM = new LoanAgreementDataModel(customer2);
		assertEquals(customer2, lGDM.getCustomer());
	}
	@Test
	public void testSeller() {
		LoanAgreementDataModel lGDM = new LoanAgreementDataModel(customer);
		SellerDataModel seller = new SellerDataModel("heiter", "500000", "Jacob Ingilesen", false);
		lGDM.setSeller(seller);
		assertEquals(seller, lGDM.getSeller());
	}
	@Test
	public void testSeller2() {
		LoanAgreementDataModel lGDM = new LoanAgreementDataModel(customer);
		SellerDataModel seller = new SellerDataModel("Bennie", "600000", "Ben Nielsen", false);
		lGDM.setSeller(seller);
		assertEquals(seller, lGDM.getSeller());
	}
	@Test
	public void testCar() {
		LoanAgreementDataModel lGDM = new LoanAgreementDataModel(customer);
		CarDataModel car = new CarDataModel("fffffdsr", "4 døre");
		lGDM.setCar(car);
		assertEquals(car, lGDM.getCar());
	}
	@Test
	public void testCar2() {
		LoanAgreementDataModel lGDM = new LoanAgreementDataModel(customer);
		CarDataModel car = new CarDataModel("f14", "2 døre");
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
	public void testIsApproved2() {
		LoanAgreementDataModel lGDM = new LoanAgreementDataModel(customer);
		lGDM.setApproved(false);
		assertEquals(false, lGDM.isApproved());
	}
	@Test
	public void testDuration() {
		LoanAgreementDataModel lGDM = new LoanAgreementDataModel(customer);
		int duration = 20;
		lGDM.setDuration(duration);
		assertEquals(duration, lGDM.getDuration());
	}
	@Test
	public void testDuration2() {
		LoanAgreementDataModel lGDM = new LoanAgreementDataModel(customer);
		int duration = 30;
		lGDM.setDuration(duration);
		assertEquals(duration, lGDM.getDuration());
	}
	@Test
	public void testDurationProperty() {
		LoanAgreementDataModel lGDM = new LoanAgreementDataModel(customer);
		int duration = 30;
		lGDM.setDuration(duration);
		IntegerProperty pattern = new SimpleIntegerProperty();
		pattern.set(duration);
		assertEquals(pattern.getValue(), lGDM.durationProperty().getValue());
	}
	@Test
	public void testAskingPrice() {
		LoanAgreementDataModel lGDM = new LoanAgreementDataModel(customer);
		String askingPrice = "2000000";
		lGDM.setAskingPrice(askingPrice);
		assertEquals(askingPrice, lGDM.getAskingPrice());
	}
	@Test
	public void testAskingPrice2() {
		LoanAgreementDataModel lGDM = new LoanAgreementDataModel(customer);
		String askingPrice = "3000000";
		lGDM.setAskingPrice(askingPrice);
		assertEquals(askingPrice, lGDM.getAskingPrice());
	}
	@Test
	public void testAskingPriceProperty() {
		LoanAgreementDataModel lGDM = new LoanAgreementDataModel(customer);
		String askingPrice = "3000000";
		lGDM.setAskingPrice(askingPrice);
		StringProperty pattern = new SimpleStringProperty();
		pattern.set(askingPrice);
		assertEquals(pattern.getValue(), lGDM.askingPriceProperty().getValue());
	}
	@Test
	public void testDownPayment() {
		LoanAgreementDataModel lGDM = new LoanAgreementDataModel(customer);
		String downPayment = "2000000";
		lGDM.setDownPayment(downPayment);
		assertEquals(downPayment, lGDM.getDownPayment());
	}
	@Test
	public void testDownPayment2() {
		LoanAgreementDataModel lGDM = new LoanAgreementDataModel(customer);
		String downPayment = "3000000";
		lGDM.setDownPayment(downPayment);
		assertEquals(downPayment, lGDM.getDownPayment());
	}
	@Test
	public void testDownPaymentProperty() {
		LoanAgreementDataModel lGDM = new LoanAgreementDataModel(customer);
		String downPayment = "3000000";
		lGDM.setDownPayment(downPayment);
		StringProperty pattern = new SimpleStringProperty();
		pattern.set(downPayment);
		assertEquals(pattern.getValue(), lGDM.downPaymentProperty().getValue());
	}
	@Test
	public void testInterestRate() {
		LoanAgreementDataModel lGDM = new LoanAgreementDataModel(customer);
		String interestRate = "3";
		lGDM.setInterestRate(interestRate);
		assertEquals(interestRate, lGDM.getInterestRate());
	}
	@Test
	public void testInterestRate2() {
		LoanAgreementDataModel lGDM = new LoanAgreementDataModel(customer);
		String interestRate = "4";
		lGDM.setInterestRate(interestRate);
		assertEquals(interestRate, lGDM.getInterestRate());
	}
	@Test
	public void testInterestRateProperty() {
		LoanAgreementDataModel lGDM = new LoanAgreementDataModel(customer);
		String interestRate = "4";
		lGDM.setInterestRate(interestRate);
		StringProperty pattern = new SimpleStringProperty();
		pattern.set(interestRate);
		assertEquals(pattern.getValue(), lGDM.interestRateProperty().getValue());
	}
	@Test
	public void testLocalDate() {
		LoanAgreementDataModel lGDM = new LoanAgreementDataModel(customer);
		LocalDate localDate = LocalDate.of(2019, 10, 12);
		lGDM.setStartDate(localDate);
		assertEquals(localDate, lGDM.getStartDate());
	}
	@Test
	public void testLocalDate2() {
		LoanAgreementDataModel lGDM = new LoanAgreementDataModel(customer);
		LocalDate localDate = LocalDate.of(2020, 11, 9);
		lGDM.setStartDate(localDate);
		assertEquals(localDate, lGDM.getStartDate());
	}
	@Test
	public void testLocalDateProperty() {
		LoanAgreementDataModel lGDM = new LoanAgreementDataModel(customer);
		LocalDate localDate = LocalDate.of(2020, 11, 9);
		lGDM.setStartDate(localDate);
		ObjectProperty<LocalDate> startDate = new SimpleObjectProperty<>();
		startDate.set(localDate);
		assertEquals(startDate.getValue(), lGDM.startDateProperty().getValue());
	}
	@Test
	public void testLoanID() {
		LoanAgreementDataModel lGDM = new LoanAgreementDataModel(customer);
		int ID = 10;
		lGDM.setLoanIDNumber(ID);
		assertEquals(ID, lGDM.getLoanIDNumber());
	}
	@Test
	public void testLoanID2() {
		LoanAgreementDataModel lGDM = new LoanAgreementDataModel(customer);
		int ID = 20;
		lGDM.setLoanIDNumber(ID);
		assertEquals(ID, lGDM.getLoanIDNumber());
	}
}
