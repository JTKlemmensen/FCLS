package test;

import static org.junit.Assert.*;

import org.junit.Test;

import logic.CustomerDataModel;

public class TestCustomerDataModel {
	CustomerDataModel customer = new CustomerDataModel(null, null, null, null, null, null, null, null);
	@Test
	public void testCustomerFirstName() {
		customer.setCustomerFirstName("bob");
		assertEquals("bob", customer.customerFirstNameProperty().getValue());
		assertEquals("bob", customer.getCustomerFirstName());
	}
	@Test
	public void testCustomerFirstName2() {
		customer.setCustomerFirstName("IB");
		assertEquals("IB", customer.customerFirstNameProperty().getValue());
		assertEquals("IB", customer.getCustomerFirstName());
	}
	@Test
	public void testCustomerLastName() {
		customer.setCustomerLastName("Nielsen");
		assertEquals("Nielsen", customer.customerLastNameProperty().getValue());
		assertEquals("Nielsen", customer.getCustomerLastName());
	}
	@Test
	public void testCustomerLastName2() {
		customer.setCustomerLastName("Petersen");
		assertEquals("Petersen", customer.customerLastNameProperty().getValue());
		assertEquals("Petersen", customer.getCustomerLastName());
	}
	@Test
	public void testCustomerAddress() {
		customer.setCustomerAddress("parkvej");
		assertEquals("parkvej", customer.customerAddressProperty().getValue());
		assertEquals("parkvej", customer.getCustomerAddress());
	}
	@Test
	public void testCustomerAddress2() {
		customer.setCustomerAddress("Sjællandsgade");
		assertEquals("Sjællandsgade", customer.customerAddressProperty().getValue());
		assertEquals("Sjællandsgade", customer.getCustomerAddress());
	}
	@Test
	public void testCustomerPhone() {
		customer.setCustomerPhone("12345678");
		assertEquals("12345678", customer.customerPhoneProperty().getValue());
		assertEquals("12345678", customer.getCustomerPhone());
	}
	@Test
	public void testCustomerPhone2() {
		customer.setCustomerPhone("87654321");
		assertEquals("87654321", customer.customerPhoneProperty().getValue());
		assertEquals("87654321", customer.getCustomerPhone());
	}
	@Test
	public void testCustomerCPR() {
		customer.setCustomerCPR("1234567890");
		assertEquals("1234567890", customer.customerCPRProperty().getValue());
		assertEquals("1234567890", customer.getCustomerCPR());
	}
	@Test
	public void testCustomerCPR2() {
		customer.setCustomerCity("09876654321");
		assertEquals("09876654321", customer.customerCityProperty().getValue());
		assertEquals("09876654321", customer.getCustomerCity());
	}
	@Test
	public void testCustomerPostalCode() {
		customer.setCustomerPostalCode("9000");
		assertEquals("9000", customer.customerPostalCodeProperty().getValue());
		assertEquals("9000", customer.getCustomerPostalCode());
	}
	@Test
	public void testCustomerPostalCode2() {
		customer.setCustomerPostalCode("7400");
		assertEquals("7400", customer.customerPostalCodeProperty().getValue());
		assertEquals("7400", customer.getCustomerPostalCode());
	}
	@Test
	public void testCustomerEmail() {
		customer.setCustomerEmail("god@gmail.com");
		assertEquals("god@gmail.com", customer.customerEmailProperty().getValue());
		assertEquals("god@gmail.com", customer.getCustomerEmail());
	}
	@Test
	public void testCustomerEmail2() {
		customer.setCustomerEmail("Bibob@hotmail.com");
		assertEquals("Bibob@hotmail.com", customer.customerEmailProperty().getValue());
		assertEquals("Bibob@hotmail.com", customer.getCustomerEmail());
	}

}
