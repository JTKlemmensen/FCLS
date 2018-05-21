package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import logic.CarDataModel;
import logic.CustomerDataModel;
import logic.LoanAgreementDataModel;
import logic.sellerDataModel;

public class LoanDAO 
{
	public static Boolean insertLoanAgreementDB(LoanAgreementDataModel loanAgreement)
	{
		PreparedStatement statement=null;
		Connection con=null;
		int result=0;
		try
		{
			//create prepared statement, find username and check password
			con=DbConnector.getConnection();
			statement =con.prepareStatement("INSERT INTO LoanAgreement_table ( customer, salesPerson, car, loanStartDate, loanDuration, askingPrice, rate, approved, downPayment) VALUES(?,?,?,?,?,?,?,?,?)");
			//TODO atm, fake customerID, later get real customerID, also conversions could be prettier
			statement.setInt(1, loanAgreement.getCustomer().getCustomerID());
			statement.setString(2, loanAgreement.getSeller().getSalesPersonUsername());
			statement.setString(3, loanAgreement.getCar().getVIN());
			
			Date date=Date.valueOf(loanAgreement.getStartDate());
			statement.setDate(4, date);
			
			statement.setInt(5, Integer.parseInt(loanAgreement.getDuration()));
			statement.setString(6, loanAgreement.getAskingPrice());
			statement.setString(7, loanAgreement.getInterestRate());
			statement.setBoolean(8, loanAgreement.isApproved());
			statement.setString(9, loanAgreement.getDownPayment());
			
			result = statement.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally 
		{
			//make sure all connections are closed and resources released
			 DbConnector.closeConnection(statement);
			 DbConnector.closeConnection(con);
		}
		
		if(result==0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public static List<LoanAgreementDataModel> getNonApprovedLoanList()
	{
		List<LoanAgreementDataModel> resultList=new ArrayList<LoanAgreementDataModel>();
		PreparedStatement statement=null;
		Connection con=null;
		ResultSet rs=null;
		
		try
		{      
			con=DbConnector.getConnection();
			statement =con.prepareStatement("SELECT * FROM LoanAgreement_table loan INNER JOIN Customer_table ctm ON loan.customer=ctm.customerID INNER JOIN Car_table car ON loan.car=car.VIN INNER JOIN Salesman_table sale ON loan.salesPerson=sale.userName WHERE loan.approved=0");
			rs = statement.executeQuery();
			
	        while(rs.next())
	        {
	        	//assign customer
	        	int id=rs.getInt("customerID");
	        	String firstName=rs.getString("firstName");
	        	String lastName=rs.getString("lastName");
	        	String address=rs.getString("address");
	        	String city=rs.getString("city");
	        	String postalCode=rs.getString("postalCode");
	        	String phone=rs.getString("phoneNumber");
	        	String email=rs.getString("email");
	        	String cpr=rs.getString("cpr");
	        	
	        	CustomerDataModel customer=new CustomerDataModel(firstName, lastName, address, city, postalCode, phone, email, cpr);
	        	customer.setCustomerID(id);
	        	
	        	//assign car
	        	String carID=rs.getString("VIN");
	        	String carModel=rs.getString("modelName");
	        	
	        	CarDataModel car=new CarDataModel(carID, carModel);
	        	
	        	//assign seller
	        	String userName=rs.getString("userName");
	        	String password=rs.getString("password");
	        	String fullname=rs.getString("fullName");
	        	String limit=rs.getString("loanLimit");
	        	boolean administrator=rs.getBoolean("administrator");
	        	
	        	sellerDataModel salesPerson=new sellerDataModel(userName, limit, fullname, administrator);
	        	
	        	//assign loanInfo
	        	int loanID=rs.getInt("loanID");
	        	LocalDate date=rs.getDate("loanStartDate").toLocalDate();
	        	String duration=""+rs.getInt("loanDuration");
	        	String price=rs.getString("askingPrice");
	        	String rate=rs.getString("rate");
	        	boolean approved=rs.getBoolean("approved");
	        	String downPayment=rs.getString("downPayment");
	        	
	        	LoanAgreementDataModel cm = new LoanAgreementDataModel(customer);
	        	cm.setLoanIDNumber(loanID);
	        	cm.setCar(car);
	        	cm.setSeller(salesPerson);
	        	cm.setAskingPrice(price);
	        	cm.setDuration(duration);
	        	cm.setInterestRate(rate);
	        	cm.setStartDate(date);
	        	cm.setApproved(approved);
	        	cm.setDownPayment(downPayment);
	        	
	        	
	            resultList.add(cm);                  
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
		return resultList;
	}
	
	public static boolean updateLoanAgreementDb(LoanAgreementDataModel loanAgreement)
	{
		PreparedStatement statement=null;
		Connection con=null;
		int result=0;
		try
		{
			//create prepared statement, find username and check password
			con=DbConnector.getConnection();
			statement =con.prepareStatement("UPDATE LoanAgreement_table SET customer = ?, salesPerson = ?, car = ?, loanStartDate = ?, loanDuration = ?, askingPrice = ?, rate = ?, approved = ?, downPayment = ? WHERE loanID = ?");
			
			statement.setInt(1, loanAgreement.getCustomer().getCustomerID());
			statement.setString(2, loanAgreement.getSeller().getSalesPersonUsername());
			statement.setString(3, loanAgreement.getCar().getVIN());
			
			Date date=Date.valueOf(loanAgreement.getStartDate());
			statement.setDate(4, date);
			
			statement.setInt(5, Integer.parseInt(loanAgreement.getDuration()));
			statement.setString(6, loanAgreement.getAskingPrice());
			statement.setString(7, loanAgreement.getInterestRate());
			statement.setBoolean(8, loanAgreement.isApproved());
			statement.setString(9, loanAgreement.getDownPayment());
			statement.setInt(10, loanAgreement.getLoanIDNumber());
			
			result = statement.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally 
		{
			//make sure all connections are closed and resources released
			 DbConnector.closeConnection(statement);
			 DbConnector.closeConnection(con);
		}
		
		if(result==0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
}
