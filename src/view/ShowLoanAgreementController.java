package view;

import java.io.IOException;
import java.util.List;

import csv.LoanToCSV;
import database.LoanDAO;
import logic.LoanAgreementDataModel;
import logic.LoanHandler;
import logic.Payment;

public class ShowLoanAgreementController 
{
	private ShowLoanAgreementView itsView;
	private LoanHandler loanHandler;
	
	public ShowLoanAgreementController(ShowLoanAgreementView view, LoanHandler loanHandler)
	{
		itsView=view;
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
			LoanToCSV csvWriter = new LoanToCSV("" + loanHandler.getLoanAgreementDataModel().getLoanIDNumber());
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
		CreateLoanAgreementView controller = new CreateLoanAgreementView(loanHandler);
		FCLSController.INSTANCE.changeView(controller);
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