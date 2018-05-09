package viewPackage;

import logic.LoanAgreementDataModel;

public class ShowLoanAggrementController 
{
	private ShowLoanAggrementView itsView;
	private LoanAgreementDataModel itsLoanAgreement;
	
	public ShowLoanAggrementController(LoanAgreementDataModel loanAgreement)
	{
		itsView=new ShowLoanAggrementView(this);
		itsLoanAgreement=loanAgreement;
	}
	
	public ShowLoanAggrementView getView()
	{
		return itsView;
	}
	
	public LoanAgreementDataModel getLoanAgreement()
	{
		return itsLoanAgreement;
	}
}
