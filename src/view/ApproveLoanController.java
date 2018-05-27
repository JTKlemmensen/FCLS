package view;

import database.LoanDAO;
import logic.LoanAgreementDataModel;

public class ApproveLoanController 
{
	private ApproveLoanView itsView;
	
	public ApproveLoanController( ApproveLoanView view )
	{
		itsView=view;
	}
	
	public ApproveLoanView getView()
	{
		return itsView;
	}
	
	public void updateAgreementTable()
	{
		itsView.updateTable(LoanDAO.getLoanList(true));
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
