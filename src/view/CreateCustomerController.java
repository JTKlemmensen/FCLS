package view;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import logic.CustomerDataModel;
import logic.CustomerHandler;

public class CreateCustomerController {
	private CreateCustomerView itsView;
	private CustomerHandler itsHandler;
	private CustomerDataModel itsCustomer;
	
	public CreateCustomerController(CreateCustomerView view)
	{
		itsHandler = new CustomerHandler();
		itsView = view;
		itsCustomer = new CustomerDataModel("", "", "", "", "", "", "", "");
	}
	
	public CreateCustomerView getView()
	{
		return itsView;
	}
	
	public void createCustomer()
	{
		if(checkInputViability()==false)
		{
			return;
		}

		boolean isInsertSuccessful = itsHandler.insertCustomer(itsCustomer);
		
		if(!isInsertSuccessful) 
		{
			Alert alert = new FCLSAlert(AlertType.NONE,"Kunde blev ikke oprettet",ButtonType.OK);
			alert.showAndWait();
			alert.getResult();
		} else {
			FindCustomerView view=new FindCustomerView();
			FCLSController.INSTANCE.changeView(view);
		}
	}
	
	public void cancelCreateCustomer()
	{
		FCLSController.INSTANCE.changeView(null);
	}
	
	public CustomerDataModel getCustomer()
	{
		return itsCustomer;
	}
	
	public CustomerHandler getHandler()
	{
		return itsHandler;
	}
	
	private boolean checkInputViability()
	{
		boolean dataIsViable=true;
		if(itsCustomer.getFirstName()==null||itsCustomer.getFirstName().equals(""))
		{
			dataIsViable=false;
			itsView.addWarning("Indtast fornavn");
		}
		if(itsCustomer.getLastName()==null||itsCustomer.getLastName().equals(""))
		{
			dataIsViable=false;
			itsView.addWarning("Indtast efternavn");
		}
		if(itsCustomer.getAddress()==null||itsCustomer.getAddress().equals(""))
		{
			dataIsViable=false;
			itsView.addWarning("Indtast adresse");
		}
		if(itsCustomer.getCPR()==null||itsCustomer.getCPR().equals(""))
		{
			dataIsViable=false;
			itsView.addWarning("Indtast cpr");
		} else if(itsCustomer.getCPR().length() != 10)
		{
			dataIsViable=false;
			itsView.addWarning("CPR er forkert længde");
		}
		if(itsCustomer.getPostalCode()==null||itsCustomer.getPostalCode().equals(""))
		{
			dataIsViable=false;
			itsView.addWarning("Indtast postnummer");
		}
		if(itsCustomer.getEmail() == null || itsCustomer.getEmail().equals(""))
		{
			dataIsViable=false;
			itsView.addWarning("Indtast Email");
		}
		if(itsCustomer.getCity()==null||itsCustomer.getCity()=="")
		{
			dataIsViable=false;
			itsView.addWarning("Indtast by");
		}
		if(itsCustomer.getPhone()==null||itsCustomer.getPhone().equals(""))
		{
			dataIsViable=false;
			itsView.addWarning("Indtast tlf nr.");
		}

		return dataIsViable;
	}
}
