package viewPackage;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public class CreateCustomerView implements View{
	private CreateCustomerController theController;
	private VBox warningContainer;
	
	public CreateCustomerView(CreateCustomerController controller)
	{
		theController=controller;
	}
	
	public StackPane getSceneGUI()
	{
		StackPane root = new StackPane();
		root.setId("view_screen");
		root.setPadding(new Insets(14));
		
		VBox containerBox=new VBox();
		root.getChildren().add(containerBox);
		containerBox.getChildren().add(createCustomerInfoGrid());
		containerBox.getChildren().add(createButtonContainer());
		
		return root;
	}
	
	private GridPane createCustomerInfoGrid()
	{
		GridPane customerInformationGrid=new GridPane();
		customerInformationGrid.setId("create_customer_info_grid");
		
		Label customerInformationHeader=new Label("Kundeinformation :");
		customerInformationHeader.setId("part_create_customer_label");
		
		Label customerFirstNameHeader=new Label("Navn");
		customerFirstNameHeader.setId("create_customer_label");
		
		TextField customerFirstNameTextField=new TextField();		
		customerFirstNameTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\w{0,10}")) {
                	customerFirstNameTextField.setText(oldValue);
                }
                theController.setCustomerFirstName(customerFirstNameTextField.getText());
            }
        });
		
		Label customerLastNameHeader=new Label("Navn");
		customerLastNameHeader.setId("create_customer_label");
		//customerInformationHeader.setStyle(value);
		
		TextField customerLastNameTextField=new TextField();
		customerLastNameTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\w{0,10}")) {
                	customerLastNameTextField.setText(oldValue);
                }
                theController.setCustomerLastName(customerLastNameTextField.getText());
            }
        });
		
		Label customerAdressHeader=new Label("Addresse");
		customerAdressHeader.setId("create_customer_label");
		
		TextField customerAdressTextField=new TextField();
		customerAdressTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\w{0,10}")) {
                	customerAdressTextField.setText(oldValue);
                }
                theController.setCustomerAddress(customerAdressTextField.getText());
            }
        });
		
		Label customerTlfHeader=new Label("Tlf. nr");
		customerTlfHeader.setId("create_customer_label");
		
		TextField customerPhoneTextField=new TextField();
		customerPhoneTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,13}")) {
                	customerPhoneTextField.setText(oldValue);
                }
                theController.setCustomerPhone(customerPhoneTextField.getText());
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
                theController.setCustomerCPR(customerCPRTextField.getText());
            }
        });
		
		Label customerPostalCodeHeader=new Label("Post nr.");
		customerPostalCodeHeader.setId("create_customer_label");
		
		TextField customerPostalCodeTextField=new TextField();
		customerPostalCodeTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,13}")) {
                	customerPostalCodeTextField.setText(oldValue);
                }
                theController.setPostalCode(customerPostalCodeTextField.getText());
            }
        });
		
		Label customerEmailCodeHeader=new Label("Email");
		customerEmailCodeHeader.setId("create_customer_label");
		
		TextField customerEmailTextField=new TextField();
		customerEmailTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$")) {
                	customerEmailTextField.setText(oldValue);
                }
                theController.setCustomerEmail(customerEmailTextField.getText());
            }
        });
		
		customerInformationGrid.add(customerInformationHeader, 0, 0);
		customerInformationGrid.add(customerFirstNameHeader, 0, 1);
		customerInformationGrid.add(customerFirstNameTextField, 0, 2);
		customerInformationGrid.add(customerAdressHeader, 1, 1);
		customerInformationGrid.add(customerAdressTextField, 1, 2);
		customerInformationGrid.add(customerTlfHeader, 0, 3);
		customerInformationGrid.add(customerPhoneTextField, 0, 4);
		customerInformationGrid.add(customerCPRHeader, 1, 3);
		customerInformationGrid.add(customerCPRTextField, 1, 4);
		customerInformationGrid.add(customerPostalCodeHeader, 0, 5);
		customerInformationGrid.add(customerPostalCodeTextField, 0, 6);
		customerInformationGrid.add(customerEmailCodeHeader, 1, 5);
		customerInformationGrid.add(customerEmailTextField, 1, 6);
		
		return customerInformationGrid;
	}
	
	private VBox createButtonContainer()
	{
		VBox buttonContainer= new VBox();
		buttonContainer.setPadding(new Insets(14));
		
		HBox buttonHolder=new HBox();
		buttonContainer.getChildren().add(buttonHolder);
		
		Button createCustomer = new Button("Opret Kunde");
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
		buttonHolder.setMargin(createCustomer, new Insets(6));
		buttonHolder.setMargin(cancelButton, new Insets(6));
		
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
		// TODO Auto-generated method stub
		return true;
	}
}
