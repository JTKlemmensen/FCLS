package test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import logic.CarDataModel;

public class TestCarDataModel {

	CarDataModel car = new CarDataModel(null, null);
	@Test
	public void testVIN() {
		car.setVIN("bob");
		assertEquals("bob", car.VINProperty().getValue());
		assertEquals("bob", car.getVIN());
	}
	@Test
	public void testVIN2() {
		car.setVIN("IB");
		assertEquals("IB", car.VINProperty().getValue());
		assertEquals("IB", car.getVIN());
	}
	@Test
	public void testDescription() {
		car.setCarDescription("Black");
		assertEquals("Black", car.carDescriptionProperty().getValue());
		assertEquals("Black", car.getCarDescription());
	}
	@Test
	public void testDescription2() {
		car.setCarDescription("Red");
		assertEquals("Red", car.carDescriptionProperty().getValue());
		assertEquals("Red", car.getCarDescription());
	}
}
