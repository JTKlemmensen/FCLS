package view;

import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import logic.CustomerDataModel;
import logic.LoanAgreementDataModel;

public class FindCustomerView implements View
{
	private FindCustomerController theController;
	private TableView<CustomerDataModel> table;
	//TODO CustomerID as search parameter
	public VBox getContent()
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
		Button calculateAgreementButton = new Button("Beregn låneaftale");
		calculateAgreementButton.setId("view_button");
		calculateAgreementButton.setOnAction(new EventHandler<ActionEvent>() 
		{
		    @Override
		    public void handle(ActionEvent e) 
		    {
		    	CustomerDataModel customer=table.getSelectionModel().getSelectedItem();
		    	if(customer!=null)
		    		theController.createLoanAgreementPressed(customer);
		    	else
		    	{
		    		Alert a = new FCLSAlert(AlertType.NONE,"Du skal vælge en kunde før du kan beregne en låneaftale.",ButtonType.OK);
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
		
		Button closePaneButton = new Button("Luk");
		closePaneButton.setId("view_button");
		closePaneButton.setOnAction(new EventHandler<ActionEvent>() 
		{
		    @Override
		    public void handle(ActionEvent e) 
		    {
		    	theController.closeView();
		    }
		});
	
		containerBox.add(calculateAgreementButton, 0, 0);
		containerBox.add(createCustomer,1,0);
		containerBox.add(closePaneButton,2,0);
		
		return root;
	}
	
	private VBox findCustomerArea()
	{
		table = new TableView<CustomerDataModel>();
		TableColumn<CustomerDataModel, String> customerIDCol = new TableColumn<CustomerDataModel, String>("Kunde ID");
        TableColumn<CustomerDataModel, String> firstNameCol = new TableColumn<CustomerDataModel, String>("First Name");
        TableColumn<CustomerDataModel, String> lastNameCol = new TableColumn<CustomerDataModel, String>("Last Name");
        TableColumn<CustomerDataModel, String> phoneCol = new TableColumn<CustomerDataModel, String>("Phone number");
        
        customerIDCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CustomerDataModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<CustomerDataModel, String> p) 
            {
                return new SimpleStringProperty(""+p.getValue().getCustomerID());
            }
        });
        firstNameCol.setCellValueFactory(new PropertyValueFactory<CustomerDataModel, String>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<CustomerDataModel, String>("lastName"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<CustomerDataModel, String>("phone"));
        
        table.getColumns().add(customerIDCol);
        table.getColumns().add(firstNameCol);
        table.getColumns().add(lastNameCol);
        table.getColumns().add(phoneCol);

        TextField customerIDTextField = new TextField();
        customerIDTextField.setMaxWidth(80);
        Label customerIDTextFieldLabel = new Label("Customer ID:");
        VBox customerIDTextFieldBox = new VBox();
        customerIDTextFieldBox.getChildren().addAll(customerIDTextFieldLabel,customerIDTextField);
        
        TextField firstNameTextField = new TextField();
        Label firstNameLabel = new Label("First Name:");
        VBox firstNameBox = new VBox();
        firstNameBox.setPadding(new Insets(0,0,0,20));
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
        searchArea.getChildren().addAll(customerIDTextFieldBox,firstNameBox,lastNameBox,phoneBox);

        ChangeListener<String> tableUpdate = getTableChangeListener(customerIDTextField, firstNameTextField, lastNameTextField, phoneTextField);
        customerIDTextField.textProperty().addListener(tableUpdate);
        firstNameTextField.textProperty().addListener(tableUpdate);
        lastNameTextField.textProperty().addListener(tableUpdate);
        phoneTextField.textProperty().addListener(tableUpdate);
        
        theController.updateTableView(table, customerIDTextField.getText(), firstNameTextField.getText(), lastNameTextField.getText(), phoneTextField.getText());
        
        VBox everything = new VBox();
		everything.getChildren().addAll(searchArea,table);
		return everything;
	}
	
	private ChangeListener<String> getTableChangeListener(TextField customerIDTextField, TextField firstName, TextField lastName, TextField phone)
	{
	    return new ChangeListener<String>() {
	            @Override
	            public void changed(ObservableValue<? extends String> observable,
	                    String oldValue, String newValue) 
	            {
	            	theController.updateTableView(table, customerIDTextField.getText(), firstName.getText(), lastName.getText(), phone.getText());
	            }
	        };
	}
	
	@Override
	public boolean onClose()
	{
		return true;
	}
}