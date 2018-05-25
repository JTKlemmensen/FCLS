package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import logic.CustomerDataModel;


public class CreateCustomerView implements View{
	private CreateCustomerController theController;
	private VBox warningContainer;
	private Button createCustomer;
	
	public CreateCustomerView(CreateCustomerController controller)
	{
		theController=controller;
	}
	
	public GridPane getContent()
	{
		GridPane root = new GridPane();
		root.setId("view_screen");
		root.setAlignment(Pos.CENTER);
		
		root.add(createCustomerInfoGrid(),0,0);
		root.add(createButtonContainer(),0,1);
		
		return root;
	}
	
	private GridPane createCustomerInfoGrid()
	{
		CustomerDataModel customer=theController.getCustomer();
		
		GridPane customerInformationGrid=new GridPane();
		customerInformationGrid.setHgap(10);
		
		Label customerInformationHeader=new Label("Kundeinformation :");
		customerInformationHeader.setId("part_header_label");
		
		Label customerFirstNameHeader=new Label("Fornavn");
		customerFirstNameHeader.setId("create_customer_label");
		
		TextField customerFirstNameTextField=new TextField();		
		customerFirstNameTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches(".{0,32}")) {
                	customerFirstNameTextField.setText(oldValue);
                }
                customer.setFirstName(customerFirstNameTextField.getText());
            }
        });
		
		Label customerLastNameHeader=new Label("Efternavn");
		customerLastNameHeader.setId("create_customer_label");
		//customerInformationHeader.setStyle(value);
		
		TextField customerLastNameTextField=new TextField();
		customerLastNameTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches(".{0,32}")) {
                	customerLastNameTextField.setText(oldValue);
                }
                customer.setLastName(customerLastNameTextField.getText());
            }
        });
		
		Label customerAdressHeader=new Label("Addresse");
		customerAdressHeader.setId("create_customer_label");
		
		TextField customerAdressTextField=new TextField();
		customerAdressTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches(".{0,32}")) {
                	customerAdressTextField.setText(oldValue);
                }
                customer.setAddress(customerAdressTextField.getText());
            }
        });
		
		Label customerphoneHeader=new Label("Tlf. nr");
		customerphoneHeader.setId("create_customer_label");
		
		TextField customerPhoneTextField=new TextField();
		customerPhoneTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,20}")) {
                	customerPhoneTextField.setText(oldValue);
                }
                customer.setPhone(customerPhoneTextField.getText());
            }
        });
		
		Label customerCPRHeader=new Label("CPR nr.");
		customerCPRHeader.setId("create_customer_label");
		
		TextField customerCPRTextField=new TextField();
		customerCPRTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,10}")) {
                	customerCPRTextField.setText(oldValue);
                }
                customer.setCPR(customerCPRTextField.getText());
            }
        });
		
		Label customerPostalCodeHeader=new Label("Post nr.");
		customerPostalCodeHeader.setId("create_customer_label");
		
		TextField customerPostalCodeTextField=new TextField();
		customerPostalCodeTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,10}")) {
                	customerPostalCodeTextField.setText(oldValue);
                }
                customer.setPostalCode(customerPostalCodeTextField.getText());
            }
        });
		
		Label customerEmailCodeHeader=new Label("Email");
		customerEmailCodeHeader.setId("create_customer_label");
		
		TextField customerEmailTextField=new TextField();
		customerEmailTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches(".{0,50}")) {
                	customerEmailTextField.setText(oldValue);
                }
                customer.setEmail(customerEmailTextField.getText());
            }
        });
		
		Label customerCityCodeHeader=new Label("By");
		customerCityCodeHeader.setId("create_customer_label");
		
		TextField customerCityTextField=new TextField();
		customerCityTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches(".{0,32}")) {
                	customerCityTextField.setText(oldValue);
                }
                customer.setCity(customerCityTextField.getText());
            }
        });
		
		customerInformationGrid.add(customerInformationHeader, 0, 0);
		customerInformationGrid.add(customerFirstNameHeader, 0, 1);
		customerInformationGrid.add(customerFirstNameTextField, 0, 2);
		customerInformationGrid.add(customerLastNameHeader, 1, 1);
		customerInformationGrid.add(customerLastNameTextField, 1, 2);
		customerInformationGrid.add(customerAdressHeader, 0, 3);
		customerInformationGrid.add(customerAdressTextField, 0, 4);
		customerInformationGrid.add(customerCPRHeader, 1, 3);
		customerInformationGrid.add(customerCPRTextField, 1, 4);
		customerInformationGrid.add(customerPostalCodeHeader, 0, 5);
		customerInformationGrid.add(customerPostalCodeTextField, 0, 6);
		customerInformationGrid.add(customerEmailCodeHeader, 1, 5);
		customerInformationGrid.add(customerEmailTextField, 1, 6);
		customerInformationGrid.add(customerCityCodeHeader, 0, 7);
		customerInformationGrid.add(customerCityTextField, 0, 8);
		customerInformationGrid.add(customerphoneHeader, 1, 7);
		customerInformationGrid.add(customerPhoneTextField, 1, 8);
		
		return customerInformationGrid;
	}
	
	private VBox createButtonContainer()
	{
		VBox buttonContainer= new VBox();
		//buttonContainer.setPadding(new Insets(14));
		
		HBox buttonHolder=new HBox();
		buttonContainer.getChildren().add(buttonHolder);
		
		createCustomer = new Button("Opret Kunde");
		createCustomer.setId("view_button");
		
		createCustomer.setOnAction(new EventHandler<ActionEvent>() 
		{
		    @Override
		    public void handle(ActionEvent e) 
		    {
		    	warningContainer.getChildren().clear();
		    	theController.createCustomer();
		    }
		});
		
		Button cancelButton = new Button("Annuller");
		cancelButton.setId("view_button");
		cancelButton.setOnAction(new EventHandler<ActionEvent>() 
		{
		    @Override
		    public void handle(ActionEvent e) 
		    {
		    	theController.cancelCreateCustomer();
		    }
		});
		
		buttonHolder.getChildren().add(createCustomer);
		buttonHolder.getChildren().add(cancelButton);
		HBox.setMargin(createCustomer, new Insets(6, 6, 6, 0));
		HBox.setMargin(cancelButton, new Insets(6));
		
		warningContainer = new VBox();
		buttonContainer.getChildren().add(warningContainer);

		return buttonContainer;
	}
	
	public void addWarning(String warning)
	{
		Label warningLabel=new Label(warning);
		warningContainer.getChildren().add(warningLabel);
	}

	@Override
	public boolean onClose()
	{
		return true;
	}
}
