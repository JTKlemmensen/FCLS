package view;

import javafx.scene.Node;
import database.LoanDAO;
import logic.LoanAgreementDataModel;
import logic.LoanHandler;

public class ShowLoanAgreementController 
{
	private ShowLoanAgreementView itsView;
	private LoanHandler itsLoanHandler;
	
	public ShowLoanAgreementController(LoanHandler handler)
	{
		itsView=new ShowLoanAgreementView(this);
		itsLoanHandler=handler;
	}
	
	public ShowLoanAgreementView getView()
	{
		return itsView;
	}
	
	public LoanAgreementDataModel getLoanAgreement()
	{
		return itsLoanHandler.getLoanAgreementDataModel();
	}
	
	public void closeAndSaveAgreement()
	{
		LoanDAO.insertLoanAgreementDB(itsLoanHandler.getLoanAgreementDataModel());
		FCLSController.INSTANCE.changeView(null);
	}
	
	public void exportAgreementToCSVFile()
	{
		
	}
	
	public void returnToCreateAgreementScene()
	{
		CreateLoanAgreementController controller = new CreateLoanAgreementController(itsLoanHandler);
		FCLSController.INSTANCE.changeView(controller.getView());
	}
	
	public Node getPaymentOverview() {
		return itsLoanHandler.getPaymentOverview();
	}
}