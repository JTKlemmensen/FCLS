package test;

import static org.junit.Assert.*;

import org.junit.Test;

import logic.CustomerDataModel;

public class TestCustomerDataModel {
	CustomerDataModel customer = new CustomerDataModel(null, null, null, null, null, null, null, null);
	@Test
	public void testCustomerFirstName() {
		customer.setFirstName("bob");
		assertEquals("bob", customer.firstNameProperty().getValue());
		assertEquals("bob", customer.getFirstName());
	}
	@Test
	public void testCustomerFirstName2() {
		customer.setFirstName("IB");
		assertEquals("IB", customer.firstNameProperty().getValue());
		assertEquals("IB", customer.getFirstName());
	}
	@Test
	public void testCustomerLastName() {
		customer.setLastName("Nielsen");
		assertEquals("Nielsen", customer.lastNameProperty().getValue());
		assertEquals("Nielsen", customer.getLastName());
	}
	@Test
	public void testCustomerLastName2() {
		customer.setLastName("Petersen");
		assertEquals("Petersen", customer.lastNameProperty().getValue());
		assertEquals("Petersen", customer.getLastName());
	}
	@Test
	public void testCustomerAddress() {
		customer.setAddress("parkvej");
		assertEquals("parkvej", customer.addressProperty().getValue());
		assertEquals("parkvej", customer.getAddress());
	}
	@Test
	public void testCustomerAddress2() {
		customer.setAddress("Sjællandsgade");
		assertEquals("Sjællandsgade", customer.addressProperty().getValue());
		assertEquals("Sjællandsgade", customer.getAddress());
	}
	@Test
	public void testCustomerPhone() {
		customer.setPhone("12345678");
		assertEquals("12345678", customer.phoneProperty().getValue());
		assertEquals("12345678", customer.getPhone());
	}
	@Test
	public void testCustomerPhone2() {
		customer.setPhone("87654321");
		assertEquals("87654321", customer.phoneProperty().getValue());
		assertEquals("87654321", customer.getPhone());
	}
	@Test
	public void testCustomerCPR() {
		customer.setCPR("1234567890");
		assertEquals("1234567890", customer.CPRProperty().getValue());
		assertEquals("1234567890", customer.getCPR());
	}
	@Test
	public void testCustomerCPR2() {
		customer.setCity("09876654321");
		assertEquals("09876654321", customer.cityProperty().getValue());
		assertEquals("09876654321", customer.getCity());
	}
	@Test
	public void testCustomerPostalCode() {
		customer.setPostalCode("9000");
		assertEquals("9000", customer.postalCodeProperty().getValue());
		assertEquals("9000", customer.getPostalCode());
	}
	@Test
	public void testCustomerPostalCode2() {
		customer.setPostalCode("7400");
		assertEquals("7400", customer.postalCodeProperty().getValue());
		assertEquals("7400", customer.getPostalCode());
	}
	@Test
	public void testCustomerEmail() {
		customer.setEmail("god@gmail.com");
		assertEquals("god@gmail.com", customer.emailProperty().getValue());
		assertEquals("god@gmail.com", customer.getEmail());
	}
	@Test
	public void testCustomerEmail2() {
		customer.setEmail("Bibob@hotmail.com");
		assertEquals("Bibob@hotmail.com", customer.emailProperty().getValue());
		assertEquals("Bibob@hotmail.com", customer.getEmail());
	}
	@Test
	public void testCustomerID() {
		int ID = 10;
		customer.setCustomerID(ID);
		assertEquals(ID, customer.getCustomerID());
	}
	@Test
	public void testCustomerID2() {
		int ID = 20;
		customer.setCustomerID(ID);
		assertEquals(ID, customer.getCustomerID());
	}

}
