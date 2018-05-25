package view;

import java.util.List;

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