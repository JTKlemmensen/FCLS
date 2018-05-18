package view;

import java.time.LocalDate;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
	private CustomerDataModel selectedCustomer;
	
	public VBox getSceneGUI()
	{
		theController=new FindCustomerController();
		
		VBox root = new VBox();
		root.setId("view_screen");
		
		GridPane containerBox=new GridPane();
		root.getChildren().add(findCustomerArea());
		root.getChildren().addAll(containerBox);
		containerBox.setAlignment(Pos.CENTER);
		containerBox.setPadding(new Insets(10,0,10,0));
		containerBox.setHgap(30);
		Button calculateAggrementButton = new Button("Beregn lÃ¥neaftale");
		calculateAggrementButton.setId("view_button");
		calculateAggrementButton.setOnAction(new EventHandler<ActionEvent>() 
		{
		    @Override
		    public void handle(ActionEvent e) 
		    {
		    	if(selectedCustomer!=null)
		    		theController.createLoanAgreementPressed(selectedCustomer);
		    	else
		    	{
		    		Alert a = new Alert(AlertType.NONE,"Du skal først vælge en kunde før du kan udarbejde en låneaftale.",ButtonType.OK);
		    		a.setHeaderText("");
		    		a.setTitle("Fejl");
		    		a.showAndWait();
		    	}
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
	
		containerBox.add(calculateAggrementButton, 0, 0);
		containerBox.add(createCustomer,1,0);
		
		return root;
	}
	
	private VBox findCustomerArea()
	{			
		TableView<CustomerDataModel> table = new TableView<CustomerDataModel>();
		
		table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CustomerDataModel>() {

			@Override
			public void changed(ObservableValue<? extends CustomerDataModel> obs, CustomerDataModel oldCustomer,
					CustomerDataModel newCustomer)
			{
				selectedCustomer = newCustomer;			
			}
			
		});
		
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
        Label firstNameLabel = new Label("First Name:");
        VBox firstNameBox = new VBox();
        firstNameBox.getChildren().addAll(firstNameLabel,firstNameTextField);
        
        TextField lastNameTextField = new TextField();
        Label lastNameLabel = new Label("Last Name:");
        VBox lastNameBox = new VBox();
        lastNameBox.setPadding(new Insets(0,0,0,20));
        lastNameBox.getChildren().addAll(lastNameLabel,lastNameTextField);
        
        TextField phoneTextField = new TextField();
        Label fphoneLabel = new Label("Phone Number:");
        VBox phoneBox = new VBox();
        phoneBox.setPadding(new Insets(0,0,0,20));
        phoneBox.getChildren().addAll(fphoneLabel,phoneTextField);
        
        HBox searchArea = new HBox();
        searchArea.setAlignment(Pos.CENTER);
        searchArea.setPadding(new Insets(10,0,20,0));
        searchArea.getChildren().addAll(firstNameBox,lastNameBox,phoneBox);

        ChangeListener<String> tableUpdate = getTableChangeListener(table, firstNameTextField, lastNameTextField, phoneTextField);
        firstNameTextField.textProperty().addListener(tableUpdate);
        lastNameTextField.textProperty().addListener(tableUpdate);
        phoneTextField.textProperty().addListener(tableUpdate);
        
        theController.updateTableView(table,firstNameTextField.getText(), lastNameTextField.getText(), phoneTextField.getText());
        
        VBox p = new VBox();
		p.getChildren().addAll(searchArea,table);
		return p;
	}
	
	public ChangeListener<String> getTableChangeListener(TableView<CustomerDataModel> table, TextField firstName, TextField lastName, TextField phone)
	{
	    return new ChangeListener<String>() {
	            @Override
	            public void changed(ObservableValue<? extends String> observable,
	                    String oldValue, String newValue) 
	            {
	                theController.updateTableView(table,firstName.getText(), lastName.getText(), phone.getText());
	            }
	        };
	}
	
	@Override
	public boolean onClose()
	{
		// TODO Auto-generated method stub
		return true;
	}
}