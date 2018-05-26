package view;

import java.io.IOException;
import java.util.List;

import csv.CSVWriter;
import database.LoanDAO;
import logic.LoanAgreementDataModel;
import logic.LoanHandler;
import logic.Payment;

public class ShowLoanAgreementController 
{
	private ShowLoanAgreementView itsView;
	private LoanHandler loanHandler;
	
	public ShowLoanAgreementController(LoanHandler loanHandler)
	{
		itsView=new ShowLoanAgreementView(this);
		this.loanHandler=loanHandler;
	}
	
	public ShowLoanAgreementView getView()
	{
		return itsView;
	}
	
	public LoanAgreementDataModel getLoanAgreement()
	{
		return loanHandler.getLoanAgreementDataModel();
	}
	
	public void closeAndSaveAgreement()
	{
		LoanDAO.insertLoanAgreementDB(loanHandler.getLoanAgreementDataModel());
		FCLSController.INSTANCE.changeView(null);
	}
	
	public void exportAgreementToCSVFile()
	{
		try {
			CSVWriter csvWriter = new CSVWriter("" + loanHandler.getLoanAgreementDataModel().getLoanIDNumber(), ",");
			csvWriter.write(loanHandler);
			csvWriter.flush();
			csvWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void returnToCreateAgreementScene()
	{
		CreateLoanAgreementController controller = new CreateLoanAgreementController(loanHandler);
		FCLSController.INSTANCE.changeView(controller.getView());
	}
	
	public List<Payment> getPayments() {
		return loanHandler.getPayments();
	}

	public String getAPR() {
		return loanHandler.getAPR();
	}
	
	public String getMonthlyPayment() {
		return loanHandler.getMonthlyPayment();
	}
}