package logic;

import java.math.BigDecimal;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CarDataModel
{

    private StringProperty VIN = new SimpleStringProperty();
	public final String getVIN() {return VIN.get();}
	public final void setVIN(String value){VIN.set(value);}
	public StringProperty VINProperty(){return VIN;}
	
	private StringProperty carDescription = new SimpleStringProperty();
	public final String getCarDescription() {return carDescription.get();}
	public final void setCarDescription(String value){carDescription.set(value);}
	public StringProperty carDescriptionProperty(){return carDescription;}
    
    private BigDecimal startPrice;
   
    
    public CarDataModel(String stelNumber, BigDecimal startPrice, String description)
    {
	setVIN(stelNumber);
	this.startPrice = startPrice;
	setCarDescription(description);
    }
    
    public BigDecimal getStartPrice()
    {
	return startPrice;
    }
}
