package logic;

import java.time.LocalDate;

public class Payment {
	private String paymentNo;
	public void setPaymentNo(String paymentNo) {this.paymentNo = paymentNo;}
	public String getPaymentNo() {return paymentNo;}
	
	private LocalDate date;
	public void setDate(LocalDate date) {this.date = date;}
	public LocalDate getDate() {return date;}
	
	private String payment;
	public void setPayment(String payment) {this.payment = payment;}
	public String getPayment() {return payment;}
	
	private String interest;
	public void setInterest(String interest) {this.interest = interest;}
	public String getInterest() {return interest;}
	
	private String instalment;
	public void setInstalment(String instalment) {this.instalment = instalment;}
	public String getInstalment() {return instalment;}
	
	private String principal;
	public void setPrincipal(String principal) {this.principal = principal;}
	public String getPrincipal() {return principal;}
}