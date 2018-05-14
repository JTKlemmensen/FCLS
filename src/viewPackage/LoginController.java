package viewPackage;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.DbConnector;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import logic.sellerDataModel;


public class LoginController 
{
	private LoginView itsView;
	private Stage itsStage;
	
	public LoginController(Stage stage)
	{
		itsView=new LoginView();
		itsStage=stage;
	}
	
	public LoginView getView()
	{
		return itsView;
	}
	
	public boolean login(String username, String password)
	{
		//get dbconnector and checkif username exists
		String query="SELECT * FROM Salesman_table WHERE userName ='"+username+"'";
		ResultSet dbResult = DbConnector.INSTANCE.executeQuery(query);
		
		try
		{
			while (dbResult.next())
			{
				String uName= dbResult.getString("username");
				String uPassword = dbResult.getString("password");
				if(username.equals(uName)&&password.equals(uPassword))
				{
					String limit=dbResult.getString("loanLimit");
					String fullName=dbResult.getString("fullName");
					//run login on FCLS and close this stage
					sellerDataModel salesPerson= new sellerDataModel(uName, limit, fullName);
					FCLSController.INSTANCE.changeUser(salesPerson);
					itsStage.close();
					return true;
				}
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public void cancelLogin()
	{
		Platform.exit();
	}
}
