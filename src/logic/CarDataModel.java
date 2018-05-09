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
    
    private BigDecimal startPrice;
    
    public CarDataModel(String stelNumber, BigDecimal startPrice)
    {
	setVIN(stelNumber);
	this.startPrice = startPrice;
    }
    
    public BigDecimal getStartPrice()
    {
	return startPrice;
    }
}
