package view;

import java.util.ArrayList;
import java.util.List;

import database.LoanDAO;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import logic.CustomerDataModel;
import logic.CustomerHandler;
import logic.LoanAgreementDataModel;
import logic.LoanHandler;

public class FindLoanAgreementController 
{
private FindLoanAgreementView itsView;
private List<LoanAgreementDataModel> loanAgreements;
	
	public FindLoanAgreementController(FindLoanAgreementView view)
	{
		itsView=view;
	}
	
	public FindLoanAgreementView getView()
	{
		return itsView;
	}
	
	public void close()
	{
		FCLSController.INSTANCE.changeView(null);
	}
	
	public List<LoanAgreementDataModel> filterCustomers(String loanID, String customerID, String sellerID, String carID)
	{
		if(loanAgreements==null)
			loanAgreements = LoanDAO.getLoanList(false);
		
		List<LoanAgreementDataModel> resultList = new ArrayList<LoanAgreementDataModel>();
		
		for(LoanAgreementDataModel ldm : loanAgreements)
		{
			if( !considerProperty(""+ldm.getLoanIDNumber(),loanID) ||
				!considerProperty(""+ldm.getCustomer().getCustomerID(),customerID) ||
				!considerProperty(ldm.getSeller().getUsername(),sellerID) ||
				!considerProperty(ldm.getCar().getVIN(),carID) )
				continue;
			
			resultList.add(ldm);
		}
		
		return resultList;
	}
	
	private boolean considerProperty(String customerProperty, String otherProperty)
	{
		if(otherProperty!=null && otherProperty.length()>0)
			if(customerProperty!= null && !customerProperty.toLowerCase().contains(otherProperty.toLowerCase()) )
				return false;
		return true;
	}
}
