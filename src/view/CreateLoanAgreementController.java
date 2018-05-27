package view;

import database.CarDAO;
import logic.CarDataModel;
import logic.LoanAgreementDataModel;
import logic.LoanHandler;

public class CreateLoanAgreementController 
{
	private CreateLoanAgreementView itsView;
	private LoanHandler loanHandler;
	private boolean isCloseable;
	
	public CreateLoanAgreementController(CreateLoanAgreementView view, LoanHandler loanHandler)
	{
		itsView=view;
		this.loanHandler = loanHandler;
	}
	
	public LoanHandler getLoanHandler()
	{
		return loanHandler;
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
		loanHandler.requestLoanAgreement(FCLSController.INSTANCE.getCurrentUser());
		ShowLoanAgreementView showLoan=new ShowLoanAgreementView(loanHandler);
		FCLSController.INSTANCE.changeView(showLoan);
	}
	
	public void cancelLoanAgreement()
	{
		//TODO Thread still running, bad?
		FCLSController.INSTANCE.changeView(null);
	}
	
	public void findCar()
	{
		CarDataModel car=CarDAO.getRandomCarFromDb();
		
		loanHandler.getLoanAgreementDataModel().getCar().setVIN(car.getVIN());
		loanHandler.getLoanAgreementDataModel().getCar().setCarDescription(car.getCarDescription());
	}
	
	private boolean checkInputViability()
	{
		LoanAgreementDataModel loanAgreement=loanHandler.getLoanAgreementDataModel();
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
		
		LoanAgreementDataModel loanAgreement=loanHandler.getLoanAgreementDataModel();

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

	public void setCanClose(boolean canClose)
	{
		isCloseable=canClose;
	}
}