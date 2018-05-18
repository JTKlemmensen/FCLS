package view;

import java.util.List;

import database.LoanDAO;
import logic.LoanAgreementDataModel;

public class ApproveLoanController 
{
	private ApproveLoanView itsView;
	
	public ApproveLoanController()
	{
		itsView=new ApproveLoanView(this);
	}
	
	public ApproveLoanView getView()
	{
		return itsView;
	}
	
	public void updateAgreementTable()
	{
		itsView.updateTable(LoanDAO.getNonApprovedLoanList());
	}
	
	public void approveLoan(LoanAgreementDataModel loanAgreement)
	{
		loanAgreement.setApproved(true);
		//update using dao
		LoanDAO.updateLoanAgreementDb(loanAgreement);
		updateAgreementTable();
	}
	
	public void close()
	{
		FCLSController.INSTANCE.changeView(null);
	}
}
