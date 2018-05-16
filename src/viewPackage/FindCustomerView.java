package viewPackage;

import java.time.LocalDate;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
	
	public VBox getSceneGUI()
	{
		theController=new FindCustomerController();
		
		VBox root = new VBox();
		root.setId("view_screen");
		
		VBox containerBox=new VBox();
		root.getChildren().add(findCustomerArea());
		root.getChildren().addAll(containerBox);
		
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
	
	private VBox findCustomerArea()
	{		
		TableView<CustomerDataModel> table = new TableView<CustomerDataModel>();
		
        TableColumn<CustomerDataModel, String> firstNameCol = new TableColumn<CustomerDataModel, String>("First Name");
        TableColumn<CustomerDataModel, String> lastNameCol = new TableColumn<CustomerDataModel, String>("Last Name");
        TableColumn<CustomerDataModel, String> phoneCol = new TableColumn<CustomerDataModel, String>("Phone number");
        
        firstNameCol.setCellValueFactory(new PropertyValueFactory<CustomerDataModel, String>("customerFirstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<CustomerDataModel, String>("customerLastName"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<CustomerDataModel, String>("customerPhone"));
        
        table.getColumns().add(firstNameCol);
        table.getColumns().add(lastNameCol);
        table.getColumns().add(phoneCol);
		        

        TextField firstNameTextField = new TextField();
        Label firstNameLabel = new Label();
        VBox firstNameBox = new VBox();
        firstNameBox.getChildren().addAll(firstNameLabel,firstNameTextField);
        
        TextField lastNameTextField = new TextField();
        Label lastNameLabel = new Label();
        VBox lastNameBox = new VBox();
        lastNameBox.getChildren().addAll(lastNameLabel,lastNameTextField);
        
        TextField phoneTextField = new TextField();
        Label fphoneLabel = new Label();
        VBox phoneBox = new VBox();
        phoneBox.getChildren().addAll(fphoneLabel,phoneTextField);
        
        HBox searchArea = new HBox();
        searchArea.getChildren().addAll(firstNameBox,lastNameBox,phoneBox);
        
        firstNameTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                theController.updateTableView(table,firstNameTextField.getText(), lastNameTextField.getText(), phoneTextField.getText());
            }
        });
        
        theController.updateTableView(table,firstNameTextField.getText(), lastNameTextField.getText(), phoneTextField.getText());
        
        VBox p = new VBox();
		p.getChildren().addAll(searchArea,table);
		return p;
	}
	
	@Override
	public boolean onClose()
	{
		// TODO Auto-generated method stub
		return true;
	}
}