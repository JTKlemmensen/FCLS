package viewPackage;

import logic.LoanAgreementDataModel;
import logic.LoanHandler;

public class ShowLoanAggrementController 
{
	private ShowLoanAggrementView itsView;
	private LoanAgreementDataModel itsLoanAgreement;
	private LoanHandler itsHandler;
	
	public ShowLoanAggrementController(LoanAgreementDataModel loanAgreement, LoanHandler handler)
	{
		itsView=new ShowLoanAggrementView(this);
		itsLoanAgreement=loanAgreement;
		itsHandler=handler;
	}
	
	public ShowLoanAggrementView getView()
	{
		return itsView;
	}
	
	public LoanAgreementDataModel getLoanAgreement()
	{
		return itsLoanAgreement;
	}
	
	public void closeAndSaveAgreement()
	{
		FCLSController.INSTANCE.changeView(null);
	}
	
	public void exportAgreementToCSVFile()
	{
		
	}
	
	public void returnToCreateAgreementScene()
	{
		CreateLoanAggrementController controller = new CreateLoanAggrementController(itsHandler, itsLoanAgreement.getCustomer());
		FCLSController.INSTANCE.changeView(controller.getView());
	}
}