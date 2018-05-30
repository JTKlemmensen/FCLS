package view;

import database.LoanDAO;
import database.LoanDBDAO;
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
		LoanDAO dao = new LoanDBDAO();
		itsView.updateTable(dao.getLoanList(true));
	}
	
	public void approveLoan(LoanAgreementDataModel loanAgreement)
	{
		loanAgreement.setApproved(true);
		//update using dao
		LoanDAO dao = new LoanDBDAO();
		dao.updateLoanAgreementDb(loanAgreement);
		updateAgreementTable();
	}
	
	public void close()
	{
		FCLSController.INSTANCE.changeView(null);
	}
}
