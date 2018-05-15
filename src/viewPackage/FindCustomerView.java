package viewPackage;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import logic.CustomerDataModel;

public class FindCustomerView implements View
{
	private FindCustomerController theController;
	
	private TableView<CustomerDataModel> table;
	
	public TableView<CustomerDataModel> getTable()
	{
		return table;
	}
	
	public StackPane getSceneGUI()
	{
		theController=new FindCustomerController();
		
		StackPane root = new StackPane();
		root.setId("view_screen");
		
		VBox containerBox=new VBox();
		root.getChildren().addAll(FindCustomerArea(),containerBox);
		
		Button calculateAggrementButton = new Button("Beregn låneaftale");
		calculateAggrementButton.setId("view_button");
		calculateAggrementButton.setOnAction(new EventHandler<ActionEvent>() 
		{
		    @Override
		    public void handle(ActionEvent e) 
		    {
		    	theController.createLoanAggrementPressed();
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
	
	private StackPane FindCustomerArea()
	{
		table = new TableView<CustomerDataModel>();
		
        TableColumn<CustomerDataModel, String> firstNameCol = new TableColumn<CustomerDataModel, String>("First Name");
        TableColumn<CustomerDataModel, String> lastNameCol = new TableColumn<CustomerDataModel, String>("Last Name");
        TableColumn<CustomerDataModel, String> phoneCol = new TableColumn<CustomerDataModel, String>("Phone number");
        
        firstNameCol.setCellValueFactory(new PropertyValueFactory<CustomerDataModel, String>("customerFirstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<CustomerDataModel, String>("customerLastName"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<CustomerDataModel, String>("customerPhone"));
        
        table.getColumns().add(firstNameCol);
        table.getColumns().add(lastNameCol);
        table.getColumns().add(phoneCol);
		        
        theController.updateTableView(table);
        
		StackPane p = new StackPane();
		p.getChildren().add(table);
		return p;
	}
	
	@Override
	public boolean onClose()
	{
		// TODO Auto-generated method stub
		return true;
	}
}