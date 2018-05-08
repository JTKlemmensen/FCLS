package logic;

import java.math.BigDecimal;
import java.util.Date;

public class LoanAgreementDataModel
{
	private CustomerDataModel customer;
	private sellerDataModel seller;
	private CarDataModel car;
	private Date startDate;
	private int duration;
	private BigDecimal carPrice;
	private BigDecimal downPayment;
	private double rate;
	private boolean approved;
	
	public LoanAgreementDataModel(CustomerDataModel customer) {
		this.customer = customer;
	}

	public CustomerDataModel getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDataModel customer) {
		this.customer = customer;
	}

	public sellerDataModel getSeller() {
		return seller;
	}

	public void setSeller(sellerDataModel seller) {
		this.seller = seller;
	}

	public CarDataModel getCar() {
		return car;
	}

	public void setCar(CarDataModel car) {
		this.car = car;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public BigDecimal getCarPrice() {
		return carPrice;
	}

	public void setCarPrice(BigDecimal offer) {
		this.carPrice = offer;
	}

	public BigDecimal getDownPayment() {
		return downPayment;
	}

	public void setDownPayment(BigDecimal downPayment) {
		this.downPayment = downPayment;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}
}
