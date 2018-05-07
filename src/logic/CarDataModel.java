package logic;

import java.math.BigDecimal;

public class CarDataModel
{
    private String name;
    private BigDecimal limit;
    
    public CarDataModel(String name, BigDecimal limit)
    {
	this.name = name;
	this.limit = limit;
    }
    
    public String getName()
    {
	return name;
    }
    
    public BigDecimal limit()
    {
	return limit;
    }
}
