package logic;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import javafx.collections.FXCollections;

import org.nevec.rjm.*;

public class PaymentOverview {
	private LocalDate startDate;
	private BigDecimal rateYear;
	private BigDecimal rateMonth;
	private int noOfInstalments;
	private BigDecimal principal;
	private MathContext mc = new MathContext(50, RoundingMode.HALF_UP);
	private BigDecimal monthlyPayment;
	private DecimalFormat dTwoFormat=new DecimalFormat("#.##");
	private DecimalFormat dFourFormat=new DecimalFormat("#.####");

	public PaymentOverview(LoanAgreementDataModel LADM) {
		rateYear = (new BigDecimal(LADM.getInterestRate(), mc)).divide(new BigDecimal("100", mc));
		rateMonth = getMonthlyRate();
		noOfInstalments = LADM.getDuration() * 12;
		principal = (new BigDecimal(LADM.getAskingPrice(), mc)).subtract(new BigDecimal(LADM.getDownPayment()), mc);
		startDate = LADM.getStartDate();
		monthlyPayment = getMonthlyPayment();
	}
	
	/**
	 * Creates a complete list of payments
	 * 
	 * @return
	 * List<Payment>
	 */
	
	public List<Payment> getPayments() {
		List<Payment> payments = FXCollections.observableArrayList();
		BigDecimal newPrincipal = principal;
		for (int x = 0; x < noOfInstalments; x++) {
			Payment pay = new Payment();
			pay.setPaymentNo(x + 1);
			pay.setPayment(dFourFormat.format(monthlyPayment));
			pay.setDate(startDate.plusMonths(x));
			
			BigDecimal rateAmount = (newPrincipal.multiply(rateMonth, mc)).setScale(25, RoundingMode.HALF_UP);
			pay.setInterest(dTwoFormat.format(rateAmount));
			
			BigDecimal instalment = (monthlyPayment.subtract(rateAmount, mc)).setScale(25, RoundingMode.HALF_UP);
			pay.setInstalment(dTwoFormat.format(instalment));
			
			newPrincipal = (newPrincipal.subtract(instalment, mc)).setScale(25, RoundingMode.HALF_UP);
			pay.setPrincipal(dTwoFormat.format(newPrincipal));
			
			payments.add(pay);
		}
		
		payments.sort(Comparator.comparingInt(Payment::getPaymentNo));
		
		return payments;
	}
	
	public BigDecimal getMonthlyPayment() {
		if (monthlyPayment==null) {
			monthlyPayment = calculateMonthlyPayment();
		}
		return monthlyPayment;
	}
	
	/**
	 * Calculates the monthly payment on the Loan using the following formula:
	 * 
	 * principal * (rate_month / ((1 + rate_month)^(-1 * noOfInstallments) - 1))
	 * 
	 * @return
	 * BigDecimal
	 */
	private BigDecimal calculateMonthlyPayment() {
		BigDecimal first = new BigDecimal("1", mc);
		BigDecimal second = (first.add(rateMonth)).pow(noOfInstalments * -1, mc);
		first = first.subtract(second, mc);
		second = rateMonth.divide(first, mc);
		first = principal.multiply(second, mc);
		first = first.setScale(25, RoundingMode.HALF_UP);
		return first;
	}
	
	/**
	 * Calculates the monthly rate from the yearly rate using the following formula:
	 * 
	 * rate_month = (1 + rate_Year)^(1/12) - 1
	 * 
	 * @return
	 * BigDecimal
	 */
	private BigDecimal getMonthlyRate() {
		
		BigDecimal one = rateYear.add(new BigDecimal("1"));
		BigDecimal two = (new BigDecimal("1")).divide(new BigDecimal("12"), mc);
		one = BigDecimalMath.pow(one, two);
		one = one.subtract(new BigDecimal("1"));
		
		return one;
	}
}
