package viewPackage;

import logic.LoanAgreementDataModel;
import logic.LoanHandler;

public class ShowLoanAggrementController 
{
	private ShowLoanAggrementView itsView;
	private LoanHandler itsLoanHandler;
	
	public ShowLoanAggrementController(LoanHandler handler)
	{
		itsView=new ShowLoanAggrementView(this);
		itsLoanHandler=handler;
	}
	
	public ShowLoanAggrementView getView()
	{
		return itsView;
	}
	
	public LoanAgreementDataModel getLoanAgreement()
	{
		return itsLoanHandler.getLoanAgreementDataModel();
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
		CreateLoanAggrementController controller = new CreateLoanAggrementController(itsLoanHandler);
		FCLSController.INSTANCE.changeView(controller.getView());
	}
}