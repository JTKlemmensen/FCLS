package logic;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CreatePaymentOverview {
	private LocalDate startDate;
	private BigDecimal rateYear;
	private BigDecimal rateMonth;
	private int noOfInstalments;
	private BigDecimal principal;
	private MathContext mc = new MathContext(15, RoundingMode.HALF_UP);

	public CreatePaymentOverview(LoanAgreementDataModel LADM) {
		rateYear = (new BigDecimal(LADM.getInterestRate(), mc)).divide(new BigDecimal("100", mc));
		rateMonth = getMonthlyRate();
		noOfInstalments = Integer.parseInt(LADM.getDuration()) * 12;
		principal = (new BigDecimal(LADM.getAskingPrice(), mc)).subtract(new BigDecimal(LADM.getDownPayment()), mc);
		startDate = LADM.getStartDate();
	}

	private ObservableList<Payment> getPaymentList() {
		ObservableList<Payment> payments = FXCollections.observableArrayList();
		BigDecimal payment = getPayment();
		BigDecimal newPrincipal = principal;
		for (int x = 0; x < noOfInstalments; x++) {
			Payment pay = new Payment();
			pay.setPaymentNo(Integer.toString(x + 1));
			pay.setPayment(payment.toString());
			pay.setDate(startDate.plusMonths(x));
			
			BigDecimal rateAmount = (newPrincipal.multiply(rateMonth, mc)).setScale(2, RoundingMode.HALF_UP);
			pay.setInterest(rateAmount.toString());
			
			BigDecimal instalment = (payment.subtract(rateAmount, mc)).setScale(2, RoundingMode.HALF_UP);
			pay.setInstalment(instalment.toString());
			
			newPrincipal = (newPrincipal.subtract(instalment, mc)).setScale(2, RoundingMode.HALF_UP);
			pay.setPrincipal(newPrincipal.toString());
			
			payments.add(pay);
		}
		
		return payments;
	}

	public Node getPaymentOverview() {
		TableView<Payment> table = new TableView<>();
		table.setItems(getPaymentList());
		
		//Payment number column
		TableColumn<Payment, String> paymentNoColumn = new TableColumn<>("Payment number");
		paymentNoColumn.setCellValueFactory(new PropertyValueFactory<>("paymentNo"));

		//Payment number column
		TableColumn<Payment, LocalDate> dateColumn = new TableColumn<>("Date");
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

		//Payment number column
		TableColumn<Payment, String> paymentColumn = new TableColumn<>("Payment");
		paymentColumn.setCellValueFactory(new PropertyValueFactory<>("payment"));

		//Payment number column
		TableColumn<Payment, String> interestColumn = new TableColumn<>("Interest");
		interestColumn.setCellValueFactory(new PropertyValueFactory<>("interest"));

		//Payment number column
		TableColumn<Payment, String> instalmentColumn = new TableColumn<>("Instalment");
		instalmentColumn.setCellValueFactory(new PropertyValueFactory<>("instalment"));

		//Payment number column
		TableColumn<Payment, String> principalColumn = new TableColumn<>("Principal");
		principalColumn.setCellValueFactory(new PropertyValueFactory<>("principal"));
		
		table.getColumns().addAll(paymentNoColumn, dateColumn, paymentColumn, interestColumn, instalmentColumn, principalColumn);
		
		return table;
	}

	private BigDecimal getPayment() {
		BigDecimal first = new BigDecimal("1", mc);
		BigDecimal second = (first.add(rateMonth)).pow(noOfInstalments * -1, mc);
		first = first.subtract(second, mc);
		second = rateMonth.divide(first, mc);
		first = principal.multiply(second, mc);
		first = first.setScale(2, RoundingMode.HALF_UP);
		return first;
	}
	
	private BigDecimal getMonthlyRate() {
		double dd = 1+rateYear.doubleValue();
		double one = 1;
		double twelve = 12;
		double dds = one/twelve;
		dd = Math.pow(dd, dds);
		dd = dd-1;
		
		return new BigDecimal(dd, mc);
	}

}
