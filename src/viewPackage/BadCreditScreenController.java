package viewPackage;

import javafx.stage.Modality;
import javafx.stage.Stage;

public class BadCreditScreenController 
{
	private Stage dialogScreenStage;
	private BadCreditScreenView itsView;
	
	public BadCreditScreenController()
	{
		itsView=new BadCreditScreenView();
	}
	
	public void showBadCreditScreen()
	{
		dialogScreenStage=new Stage();
		dialogScreenStage.setTitle("RKI Afvisning");
		dialogScreenStage.initModality(Modality.APPLICATION_MODAL);
		dialogScreenStage.setScene(itsView.createLoginScene(this));
		dialogScreenStage.show();
	}
	
	public void warningAccepted()
	{
		dialogScreenStage.close();
		MainScreenController.INSTANCE.changeScene(null);
	}
}
