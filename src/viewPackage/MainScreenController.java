package viewPackage;


import javafx.scene.layout.StackPane;

public class MainScreenController 
{
	public final static MainScreenController INSTANCE = new MainScreenController();
			
	private MainScreenView theView;
	
	public MainScreenView getView()
	{
		return theView;
	}
	
	public MainScreenController()
	{
		theView=new MainScreenView(this);
	}
	
	public void openSearchCustomer()
	{
		FindCustomerView view=new FindCustomerView();
		changeScene(view.getSceneGUI());
	}
	
	public void changeScene(StackPane newView)
	{
		theView.setScene(newView);
	}
}
