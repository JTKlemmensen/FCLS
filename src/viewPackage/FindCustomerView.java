package viewPackage;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class FindCustomerView implements View
{
	private FindCustomerController theController;
	public StackPane getSceneGUI()
	{
		theController=new FindCustomerController();
		
		StackPane root = new StackPane();
		root.setId("view_screen");
		
		VBox containerBox=new VBox();
		root.getChildren().add(containerBox);
		
		Button calculateAggrementButton = new Button("Beregn låneaftale");
		calculateAggrementButton.setId("view_button");
		calculateAggrementButton.setOnAction(new EventHandler<ActionEvent>() 
		{
		    @Override
		    public void handle(ActionEvent e) 
		    {
		    	theController.createLoanAgreementPressed();
		    }
		});
		
		Button createCustomer = new Button("Opret kunde");
		createCustomer.setId("view_button");
		createCustomer.setOnAction(new EventHandler<ActionEvent>() 
		{
		    @Override
		    public void handle(ActionEvent e) 
		    {
		    	theController.createCustomerPressed();
		    }
		});
	
		containerBox.getChildren().add(calculateAggrementButton);
		containerBox.getChildren().add(createCustomer);
		
		return root;
	}
	@Override
	public boolean onClose()
	{
		// TODO Auto-generated method stub
		return true;
	}
}
