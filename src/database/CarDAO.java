package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import logic.CarDataModel;
import logic.CustomerDataModel;

public class CarDAO 
{
	public static CarDataModel getRandomCarFromDb()
	{
		List<CarDataModel> carList=new ArrayList<CarDataModel>();
		PreparedStatement statement=null;
		Connection con=null;
		ResultSet rs=null;
		
		try
		{      
			con=DbConnector.getConnection();
			statement =con.prepareStatement("SELECT * FROM Car_table");
			
			rs = statement.executeQuery();
			
	        while(rs.next())
	        {
	        	String carVin=rs.getString("VIN");
	        	String carModel=rs.getString("modelName");
	        	
	        	CarDataModel car = new CarDataModel(carVin, carModel);
	        	carList.add(car);                  
	        }
	    }
	    catch(Exception e)
		{
	          e.printStackTrace();
	        
	    }
		finally 
		{
			//make sure all connections are closed and resources released
			 DbConnector.closeConnection(rs);
			 DbConnector.closeConnection(statement);
			 DbConnector.closeConnection(con);
		}
		
		if(carList.isEmpty())
		{
			return null;
		}
		else
		{
			CarDataModel randomCar=carList.get(new Random().nextInt(carList.size()));
			return randomCar;
		}
	}
}
