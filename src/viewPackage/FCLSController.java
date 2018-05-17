package viewPackage;

import javafx.application.Platform;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logic.sellerDataModel;

public class FCLSController 
{
	public final static FCLSController INSTANCE = new FCLSController();
	
	private FCLS theView;
	private sellerDataModel currentSalesPerson;
	
	public FCLS getView()
	{
		return theView;
	}
	
	private FCLSController()
	{
		theView=new FCLS(this);
	}
	
	public void openSearchCustomer()
	{
		FindCustomerView view=new FindCustomerView();
		changeView(view);
	}
	
	public void changeView(View view)
	{	
		if(view==null)
			theView.setView(null);
		else if(view.onClose())
			theView.setView(view.getSceneGUI());
	}
	
	public void changeUser(sellerDataModel newSalesPerson)
	{
		if(newSalesPerson==null)
		{
			if(currentSalesPerson==null)
			{
				Platform.exit();
			}
		}
		else
		{
			currentSalesPerson=newSalesPerson;
			theView.setCurrentUserField(currentSalesPerson);
		}
	}
	
	public void startLoginScreen()
	{
		Stage loginStage=new Stage();
		LoginController theController=new LoginController(loginStage);
		loginStage.setTitle("Login");
		loginStage.initModality(Modality.APPLICATION_MODAL);
		loginStage.setScene(theController.getView().getSceneGUI(theController));
		loginStage.show();
	}
	
	public sellerDataModel getCurrentUser()
	{
		return currentSalesPerson;
	}
}
