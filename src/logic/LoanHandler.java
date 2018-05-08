package logic;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Observable;
import com.ferrari.finances.dk.rki.Rating;

public class LoanHandler extends Observable {
	private LoanAgreementDataModel loanAgreement;
	private double dailyRate;
	private Rating rating;

	public LoanAgreementDataModel requestLoanAgreement(String carPrice, String downPayment, Date startDate,
			String duration, CarDataModel car) {
		loanAgreement.setCarPrice(new BigDecimal(carPrice));
		loanAgreement.setDownPayment(new BigDecimal(downPayment));
		loanAgreement.setStartDate(startDate);
		loanAgreement.setDuration(Integer.parseInt(duration));
		loanAgreement.setCar(car);
		
		double rate = calculateRate(loanAgreement.getCarPrice(), loanAgreement.getDownPayment(), loanAgreement.getDuration());
		
		loanAgreement.setRate(rate);
		
		return null;
	}

	public void setupLoanAgreement(CustomerDataModel customer) {
		RKIandBank rkiandBank = new RKIandBank(loanAgreement.getCustomer().getCPR(), this);
	}

	private double calculateRate(BigDecimal carPrice, BigDecimal downPayment,
			int duration) {
		switch (rating) {
		case A:
			dailyRate += 1;
			break;
		case B:
			dailyRate += 2;
			break;
		default:
			dailyRate += 3;
			break;
		}
		BigDecimal percentageOfCarPaid = downPayment.divide(carPrice);

		if (percentageOfCarPaid.compareTo(new BigDecimal(0.5)) < 0)
			dailyRate += 1;

		if (duration > 3)
			dailyRate += 1;

		return dailyRate;

	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public void setRate(double rate) {
		this.dailyRate = rate;
	}
}
