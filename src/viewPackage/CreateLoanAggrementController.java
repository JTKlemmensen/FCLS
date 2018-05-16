package viewPackage;

import java.math.BigDecimal;

import logic.CarDataModel;
import logic.LoanAgreementDataModel;
import logic.LoanHandler;

public class CreateLoanAggrementController 
{
	private CreateLoanAggrementView itsView;
	private LoanHandler itsLoanHandler;
	
	public CreateLoanAggrementController(LoanHandler handler)
	{
		itsLoanHandler=handler;
		itsView=new CreateLoanAggrementView(this);
	}
	
	public CreateLoanAggrementView getView()
	{
		return itsView;
	}
	
	public void createLoanAggrement()
	{
		//check if data is enough for loanhandler
		if(checkInputViability()==false)
		{
			return;
		}
		
		itsLoanHandler.requestLoanAgreement(FCLSController.INSTANCE.getCurrentUser());
		ShowLoanAggrementController showLoan=new ShowLoanAggrementController(itsLoanHandler);
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
		//TODO get car from db instead
		String carVin="1124232";
		String description="Model: Ferrari XT-fasterosa, Farve: Sort/Metal, Ekstra udstyr: Guldpakke";
		
		itsLoanHandler.getLoanAgreementDataModel().getCar().setVIN(carVin);
		itsLoanHandler.getLoanAgreementDataModel().getCar().setCarDescription(description);
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
}