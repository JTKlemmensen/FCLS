package logic;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
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
	private MathContext mc = new MathContext(20, RoundingMode.HALF_UP);
	private BigDecimal monthlyPayment;

	public PaymentOverview(LoanAgreementDataModel LADM) {
		rateYear = (new BigDecimal(LADM.getInterestRate(), mc)).divide(new BigDecimal("100", mc));
		rateMonth = getMonthlyRate();
		noOfInstalments = LADM.getDuration() * 12;
		principal = (new BigDecimal(LADM.getAskingPrice(), mc)).subtract(new BigDecimal(LADM.getDownPayment()), mc);
		startDate = LADM.getStartDate();
		monthlyPayment = getMonthlyPayment();
	}
	
	//TODO convert to List and separate logic and View
	
	public List<Payment> getPayments() {
		List<Payment> payments = FXCollections.observableArrayList();
		BigDecimal newPrincipal = principal;
		for (int x = 0; x < noOfInstalments; x++) {
			Payment pay = new Payment();
			pay.setPaymentNo(x + 1);
			pay.setPayment(monthlyPayment.toString());
			pay.setDate(startDate.plusMonths(x));
			
			BigDecimal rateAmount = (newPrincipal.multiply(rateMonth, mc)).setScale(2, RoundingMode.HALF_UP);
			pay.setInterest(rateAmount.toString());
			
			BigDecimal instalment = (monthlyPayment.subtract(rateAmount, mc)).setScale(2, RoundingMode.HALF_UP);
			pay.setInstalment(instalment.toString());
			
			newPrincipal = (newPrincipal.subtract(instalment, mc)).setScale(2, RoundingMode.HALF_UP);
			pay.setPrincipal(newPrincipal.toString());
			
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
	
	private BigDecimal calculateMonthlyPayment() {
		BigDecimal first = new BigDecimal("1", mc);
		BigDecimal second = (first.add(rateMonth)).pow(noOfInstalments * -1, mc);
		first = first.subtract(second, mc);
		second = rateMonth.divide(first, mc);
		first = principal.multiply(second, mc);
		first = first.setScale(2, RoundingMode.HALF_UP);
		return first;
	}

	private BigDecimal getMonthlyRate() {
		
		BigDecimal one = rateYear.add(new BigDecimal("1"));
		BigDecimal two = (new BigDecimal("1")).divide(new BigDecimal("12"), mc);
		one = BigDecimalMath.pow(one, two);
		one = one.subtract(new BigDecimal("1"));
		
		return one;
	}
}
