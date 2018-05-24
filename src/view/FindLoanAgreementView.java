package view;

import java.util.List;

import com.sun.org.apache.xml.internal.resolver.helpers.Debug;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import logic.CustomerDataModel;
import logic.LoanAgreementDataModel;

public class FindLoanAgreementView implements View 
{
	private FindLoanAgreementController theController;
	private TableView<LoanAgreementDataModel> table;
	
	public FindLoanAgreementView(FindLoanAgreementController controller)
	{
		theController=controller;
	}
	
	@Override
	public Pane getViewContent() {
		StackPane root = new StackPane();
		root.setId("view_screen");
		root.setPadding(new Insets(14));
		
		VBox containerBox=new VBox();
		root.getChildren().add(containerBox);
		
		
		
		//show tableview
		table = new TableView<LoanAgreementDataModel>();
		
		TableColumn<LoanAgreementDataModel, String> loanIDCol = new TableColumn<LoanAgreementDataModel, String>("Låne ID");
		TableColumn<LoanAgreementDataModel, String> customerIDCol = new TableColumn<LoanAgreementDataModel, String>("Kunde ID");//
		TableColumn<LoanAgreementDataModel, String> customerNameCol = new TableColumn<LoanAgreementDataModel, String>("Kunde Navn");//
        TableColumn<LoanAgreementDataModel, String> sellerCol = new TableColumn<LoanAgreementDataModel, String>("Sælger");
        TableColumn<LoanAgreementDataModel, String> approvedCol = new TableColumn<LoanAgreementDataModel, String>("Godkendt");//
        TableColumn<LoanAgreementDataModel, String> carIDCol = new TableColumn<LoanAgreementDataModel, String>("Bil ID");
        TableColumn<LoanAgreementDataModel, String> loanAmountCol = new TableColumn<LoanAgreementDataModel, String>("Lånets størrelse");
        
        loanIDCol.setCellValueFactory(new PropertyValueFactory<LoanAgreementDataModel, String>("loanIDNumber"));
        
        customerIDCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<LoanAgreementDataModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<LoanAgreementDataModel, String> p) 
            {
                return new SimpleStringProperty(""+p.getValue().getCustomer().getCustomerID());
            }
        });
        
        customerNameCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<LoanAgreementDataModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<LoanAgreementDataModel, String> p) 
            {
                return new SimpleStringProperty(p.getValue().getCustomer().getFirstName().concat(" ").concat(p.getValue().getCustomer().getLastName()));
            }
        });
        
        sellerCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<LoanAgreementDataModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<LoanAgreementDataModel, String> p) 
            {
                return p.getValue().getSeller().usernameProperty();
            }
        });
        
        carIDCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<LoanAgreementDataModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<LoanAgreementDataModel, String> p) 
            {
                return p.getValue().getCar().VINProperty();
            }
        });
        
        approvedCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<LoanAgreementDataModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<LoanAgreementDataModel, String> p) 
            {
            	if(p.getValue().isApproved())
            	{
            		return new SimpleStringProperty("JA");
            	}
            	else
            	{
            		return new SimpleStringProperty("NEJ");
            	}
            }
        });
        
        loanAmountCol.setCellValueFactory(new PropertyValueFactory<LoanAgreementDataModel, String>("askingPrice"));
        
        table.getColumns().add(loanIDCol);
        table.getColumns().add(customerIDCol);
        table.getColumns().add(customerNameCol);
        table.getColumns().add(sellerCol);
        table.getColumns().add(approvedCol);
        table.getColumns().add(carIDCol);
        table.getColumns().add(loanAmountCol);
		
        containerBox.getChildren().add(createSearchFields());
        containerBox.getChildren().add(table);
        
        HBox buttonBox=new HBox();
        containerBox.getChildren().add(buttonBox);
        
        //create buttons
        Button closeButton=new Button("Luk");
        closeButton.setId("view_button");
        closeButton.setOnAction(new EventHandler<ActionEvent>() 
		{
		    @Override
		    public void handle(ActionEvent e) 
		    {
		    	theController.close();
		    }
		});
        buttonBox.getChildren().add(closeButton);
        
		return root;
	}
	
	private HBox createSearchFields()
	{
        TextField loanIDTextField = new TextField();
        Label loanIDLabel = new Label("Låne ID:");
        VBox loanIDBox = new VBox();
        loanIDBox.getChildren().addAll(loanIDLabel,loanIDTextField);
        
        TextField customerInfoTextField = new TextField();
        Label customerInfoLabel = new Label("Kunde ID:");
        VBox customerInfoBox = new VBox();
        customerInfoBox.setPadding(new Insets(0,0,0,20));
        customerInfoBox.getChildren().addAll(customerInfoLabel,customerInfoTextField);
        
        TextField sellerNameTextField = new TextField();
        Label sellerNameLabel = new Label("Sælger:");
        VBox sellerNameBox = new VBox();
        sellerNameBox.setPadding(new Insets(0,0,0,20));
        sellerNameBox.getChildren().addAll(sellerNameLabel,sellerNameTextField);
        
        TextField carIDTextField = new TextField();
        Label carIDLabel = new Label("Bil ID:");
        VBox carIDBox = new VBox();
        carIDBox.setPadding(new Insets(0,0,0,20));
        carIDBox.getChildren().addAll(carIDLabel,carIDTextField);
        
        HBox searchArea = new HBox();
        searchArea.setAlignment(Pos.CENTER);
        searchArea.setPadding(new Insets(10,0,20,0));
        searchArea.getChildren().addAll(loanIDBox,customerInfoBox,sellerNameBox, carIDBox);

        ChangeListener<String> tableUpdate = getTableChangeListener(table, loanIDTextField, customerInfoTextField, sellerNameTextField, carIDTextField);
        loanIDTextField.textProperty().addListener(tableUpdate);
        customerInfoTextField.textProperty().addListener(tableUpdate);
        sellerNameTextField.textProperty().addListener(tableUpdate);
        carIDTextField.textProperty().addListener(tableUpdate);
        
        table.setItems(FXCollections.observableArrayList(theController.filterCustomers("", "", "", "")));
        
		return searchArea;
	}
	
	public ChangeListener<String> getTableChangeListener(TableView<LoanAgreementDataModel> table, TextField loanID, TextField customerInfo, TextField sellerName, TextField carID)
	{
	    return new ChangeListener<String>() {
	            @Override
	            public void changed(ObservableValue<? extends String> observable,
	                    String oldValue, String newValue) 
	            {
	            	table.setItems(FXCollections.observableArrayList(theController.filterCustomers(loanID.getText(), customerInfo.getText(), sellerName.getText(), carID.getText())));
	            }
	        };
	}

	@Override
	public boolean onClose() 
	{
		return true;
	}
	
	public void updateTable(List<LoanAgreementDataModel> loanList)
	{
		table.setItems(FXCollections.observableArrayList(loanList));
	}
}
