package logic;

import java.math.BigDecimal;

public class CarDataModel
{
    private String stelNumber;
    private BigDecimal startPrice;
    
    public CarDataModel(String stelNumber, BigDecimal startPrice)
    {
	this.stelNumber = stelNumber;
	this.startPrice = startPrice;
    }
    
    public String getStelNumber()
    {
	return stelNumber;
    }
    
    public BigDecimal getStartPrice()
    {
	return startPrice;
    }
}
