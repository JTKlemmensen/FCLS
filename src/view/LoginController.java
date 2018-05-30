package view;

import database.SellerDAO;
import database.SellerDBDAO;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import logic.SellerDataModel;


public class LoginController 
{
	private Login itsView;
	private Stage itsStage;
	
	public LoginController(Stage stage)
	{
		itsView=new Login();
		itsStage=stage;
		itsStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			
			@Override
			public void handle(WindowEvent event) {
				FCLSController.INSTANCE.changeUser(null);
			}
		});
	}
	
	public Login getView()
	{
		return itsView;
	}
	
	public boolean login(String username, String password)
	{
		SellerDAO dao = new SellerDBDAO();
		SellerDataModel user=dao.checkLogin(username, password);
		
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