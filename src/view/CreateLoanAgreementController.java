package view;

import database.CarDAO;
import logic.CarDataModel;
import logic.LoanAgreementDataModel;
import logic.LoanHandler;

public class CreateLoanAgreementController 
{
	private CreateLoanAgreementView itsView;
	private LoanHandler itsLoanHandler;
	private boolean isCloseable;
	
	public CreateLoanAgreementController(LoanHandler handler)
	{
		itsLoanHandler=handler;
		itsView=new CreateLoanAgreementView(this);
	}
	
	public CreateLoanAgreementView getView()
	{
		return itsView;
	}
	
	public void createLoanAgreement()
	{
		//check if data is enough for loanhandler
		if(checkInputViability()==false)
		{
			return;
		}
		
		isCloseable = true;
		itsLoanHandler.requestLoanAgreement(FCLSController.INSTANCE.getCurrentUser());
		ShowLoanAgreementController showLoan=new ShowLoanAgreementController(itsLoanHandler);
		FCLSController.INSTANCE.changeView(showLoan.getView());
	}
	
	public void cancelLoanAgreement()
	{
		//TODO 
		//Thread still running, bad?
		FCLSController.INSTANCE.changeView(null);
	}
	
	public void findCar()
	{
		CarDataModel car=CarDAO.getRandomCarFromDb();
		
		itsLoanHandler.getLoanAgreementDataModel().getCar().setVIN(car.getVIN());
		itsLoanHandler.getLoanAgreementDataModel().getCar().setCarDescription(car.getCarDescription());
	}
	
	public LoanHandler getHandler()
	{
		return itsLoanHandler;
	}
	
	private boolean checkInputViability()
	{
		LoanAgreementDataModel loanAgreement=itsLoanHandler.getLoanAgreementDataModel();
		boolean dataIsViable=true;
		
		if(loanAgreement.getAskingPrice()==null||loanAgreement.getAskingPrice().equals(""))
		{
			dataIsViable=false;
			itsView.addWarning("Indtast købspris");
		}
		if(loanAgreement.getDownPayment()==null||loanAgreement.getDownPayment().equals(""))
		{
			dataIsViable=false;
			itsView.addWarning("Indtast udbetaling");
		}
		if(loanAgreement.getStartDate()==null)
		{
			dataIsViable=false;
			itsView.addWarning("Vælg startdato");
		}
		if(loanAgreement.getCar().getVIN()==null||loanAgreement.getCar().getVIN().equals(""))
		{
			dataIsViable=false;
			itsView.addWarning("Vælg bil");
		}
		return dataIsViable;
	}

	public boolean canClose()
	{
		if (isCloseable) 
			return true;
		
		LoanAgreementDataModel loanAgreement=itsLoanHandler.getLoanAgreementDataModel();

		if(loanAgreement.getAskingPrice()!=null && loanAgreement.getAskingPrice().length()>0)
			return false;
		
		if(loanAgreement.getDownPayment()!=null && loanAgreement.getDownPayment().length()>0)
			return false;
		
		if(loanAgreement.getStartDate()!=null)
			return false;
		
		if(loanAgreement.getAskingPrice()!=null && loanAgreement.getAskingPrice().length()>0)
			return false;
		
		return true;
	}
}