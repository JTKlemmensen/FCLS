package view;

import logic.CustomerDataModel;
import logic.CustomerHandler;

public class CreateCustomerController {
	private CreateCustomerView itsView;
	private CustomerHandler itsHandler;
	private CustomerDataModel itsCustomer;
	
	public CreateCustomerController(CustomerHandler handler)
	{
		itsHandler = handler;
		itsView = new CreateCustomerView(this);
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
			//TODO change to Alert
			itsView.addWarning("Kunde ikke gemt");
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
		if(itsCustomer.getCustomerLastName()==null||itsCustomer.getCustomerLastName().equals(""))
		{
			dataIsViable=false;
			itsView.addWarning("Indtast efternavn");
		}
		if(itsCustomer.getCustomerAddress()==null||itsCustomer.getCustomerAddress().equals(""))
		{
			dataIsViable=false;
			itsView.addWarning("Indtast adresse");
		}
		if(itsCustomer.getCustomerCPR()==null||itsCustomer.getCustomerCPR().equals(""))
		{
			dataIsViable=false;
			itsView.addWarning("Indtast cpr");
		} else if(itsCustomer.getCustomerCPR().length() != 10)
		{
			dataIsViable=false;
			itsView.addWarning("CPR er forkert længde");
		}
		if(itsCustomer.getPostalCode()==null||itsCustomer.getPostalCode().equals(""))
		{
			dataIsViable=false;
			itsView.addWarning("Indtast postnummer");
		}
		//TODO remove check
		if(!itsCustomer.getCustomerEmail().matches("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?"))
		{
			dataIsViable=false;
			itsView.addWarning("Indtast Email");
		}
		if(itsCustomer.getCustomerCity()==null||itsCustomer.getCustomerCity()=="")
		{
			dataIsViable=false;
			itsView.addWarning("Indtast by");
		}
		if(itsCustomer.getCustomerPhone()==null||itsCustomer.getCustomerPhone().equals(""))
		{
			dataIsViable=false;
			itsView.addWarning("Indtast tlf nr.");
		}

		return dataIsViable;
	}
}
