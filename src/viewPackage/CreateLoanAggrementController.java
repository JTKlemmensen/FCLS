package viewPackage;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.sun.org.apache.xml.internal.resolver.helpers.Debug;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
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
	
	private StringProperty loanDuration = new SimpleStringProperty();
	public final String getLoanDuration() {return loanDuration.get();}
	public final void setLoanDuration(String value){loanDuration.set(value);}
	public StringProperty loanDurationProperty(){return loanDuration;}
	
	private ObjectProperty<LocalDate> startDate = new SimpleObjectProperty<>();
	public final LocalDate getStartDate() {return startDate.get();}
	public final void setStartDate(LocalDate value) {startDate.set(value);}
	public ObjectProperty<LocalDate> startDateProperty() {return startDate;}
	
	public boolean dataIsFilled=false;
	
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
		//TODO
		//check if data is enough for loanhandler
		boolean dataIsViable=true;
		if(getCarPrice()==null||getCarPrice()=="")
		{
			dataIsViable=false;
			itsView.addWarning("Indtast købspris");
		}
		if(getDownPayment()==null||getDownPayment()=="")
		{
			dataIsViable=false;
			itsView.addWarning("Indtast udbetaling");
		}
		if(!dataIsViable)
		{
			return;
		}
		//TODO
		//perhaps loanagreement is retrieved when creating createloanscreen
		LoanAgreementDataModel loanAggrement=itsHandler.requestLoanAgreement(carPrice.get(), downPayment.get(), getStartDate(), loanDuration.get(), new CarDataModel("2323",new BigDecimal("20000")));
		
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
	
	public void cancelLoanAgreement()
	{
		//TODO 
		//Thread still running, bad?
		MainScreenController.INSTANCE.changeScene(null);
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
