package viewPackage;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import com.sun.org.apache.xml.internal.resolver.helpers.Debug;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import logic.CarDataModel;
import logic.CustomerDataModel;
import logic.LoanAgreementDataModel;
import logic.LoanHandler;

public class CreateLoanAggrementController 
{
	private CreateLoanAggrementView itsView;
	private LoanHandler itsHandler;
	private CustomerDataModel itsCustomer;
	
	//properties
	private StringProperty carPrice = new SimpleStringProperty();
	public final String getCarPrice() {return carPrice.get();}
	public final void setCarPrice(String value){carPrice.set(value);}
	public StringProperty carPriceProperty(){return carPrice;}
	
	private StringProperty downPayment = new SimpleStringProperty();
	public final String getDownPayment() {return downPayment.get();}
	public final void setDownPayment(String value){downPayment.set(value);}
	public StringProperty downPaymentProperty(){return downPayment;}
	
	private StringProperty carID = new SimpleStringProperty();
	public final String getCarID() {return carID.get();}
	public final void setCarID(String value){carID.set(value);}
	public StringProperty carIDProperty(){return carID;}
	
	public CreateLoanAggrementController(LoanHandler handler, CustomerDataModel customer)
	{
		itsHandler=handler;
		itsView=new CreateLoanAggrementView(this);
		itsCustomer=customer;
	}
	
	public CreateLoanAggrementView getView()
	{
		return itsView;
	}
	
	public void createLoanAggrement()
	{
		//get necessary info from view, for now just grap it, later bindings
		LoanAgreementDataModel loanAggrement=itsHandler.requestLoanAgreement(carPrice.get(), downPayment.get(), new Date(), "2", new CarDataModel("2323",new BigDecimal("20000")));
		
		if(loanAggrement==null)
		{
			System.out.println("fark");
		}
		else
		{
			ShowLoanAggrementController showLoan=new ShowLoanAggrementController(loanAggrement);
			MainScreenController.INSTANCE.changeScene(showLoan.getView().getSceneGUI());
		}
	}
	
	public CustomerDataModel getCustomer()
	{
		return itsCustomer;
	}
	
	public LoanHandler getHandler()
	{
		return itsHandler;
	}
}
