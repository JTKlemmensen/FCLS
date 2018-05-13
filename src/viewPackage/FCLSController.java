package viewPackage;


import javafx.scene.layout.StackPane;

public class FCLSController 
{
	public final static FCLSController INSTANCE = new FCLSController();
			
	private FCLS theView;
	
	public FCLS getView()
	{
		return theView;
	}
	
	public FCLSController()
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
		if(view.onClose())
			theView.setView(view.getSceneGUI());
	}
}
