package view;

import javafx.application.Platform;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logic.SellerDataModel;

public class FCLSController 
{
	public final static FCLSController INSTANCE = new FCLSController();
	
	private FCLS fuckthis;
	private SellerDataModel currentSalesPerson;
	private View view;
	
	public FCLS getView()
	{
		return fuckthis;
	}
	
	private FCLSController()
	{
		fuckthis=new FCLS(this);
	}
	
	public void openSearchCustomer()
	{
		FindCustomerView view=new FindCustomerView();
		changeView(view);
	}
	
	public void showApproveDeals()
	{
		ApproveLoanController controller = new ApproveLoanController();
		changeView(controller.getView());
		//update table, do this elsewhere later
		controller.updateAgreementTable();
	}
	
	public void changeView(View view)
	{
		//TODO change variable names
		if(this.view == null || this.view.onClose())
			if(view==null)
			{
				fuckthis.setView(null);
				this.view = null;
			}
			else
			{
				this.view = view;
				fuckthis.setView(view.getViewContent());
			}
	}
	
	public void changeUser(SellerDataModel newSalesPerson)
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
			fuckthis.setCurrentUserField(currentSalesPerson);
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
	
	public SellerDataModel getCurrentUser()
	{
		return currentSalesPerson;
	}
}
