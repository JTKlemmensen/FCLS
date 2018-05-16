package viewPackage;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.DbConnector;
import database.SellerDAO;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import logic.sellerDataModel;


public class LoginController 
{
	private LoginView itsView;
	private Stage itsStage;
	
	public LoginController(Stage stage)
	{
		itsView=new LoginView();
		itsStage=stage;
		stage.setOnCloseRequest((WindowEvent event) -> {
	        FCLSController.INSTANCE.changeUser(null);
	    });
	}
	
	public LoginView getView()
	{
		return itsView;
	}
	
	public boolean login(String username, String password)
	{
		sellerDataModel user=SellerDAO.checkLogin(username, password);
		
		if(user!=null)
		{
			FCLSController.INSTANCE.changeUser(user);
			itsStage.close();	
			
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void cancelLogin()
	{
		FCLSController.INSTANCE.changeUser(null);
		itsStage.close();
	}
}
