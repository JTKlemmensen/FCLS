package view;

import java.time.LocalDate;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import logic.CustomerDataModel;
import logic.LoanAgreementDataModel;
import logic.Payment;

public class ShowLoanAgreementView implements View
{
	private ShowLoanAgreementController theController;
	private boolean hasSaved = false;
	private boolean hasExported = false;
	
	public ShowLoanAgreementView(ShowLoanAgreementController controller)
	{
		theController=controller;
	}
	
	public StackPane getViewContent()
	{
		StackPane root = new StackPane();
		root.setId("view_screen");
		root.setPadding(new Insets(14));
		
		VBox containerBox=new VBox();
		root.getChildren().add(containerBox);
	
		//create mainContainergrid
		GridPane containerGrid=new GridPane();
		containerBox.getChildren().add(containerGrid);
		
		containerGrid.add(createCustomerInfoGrid(), 0, 0);
		containerGrid.add(createSellerInfoGrid(), 1, 0);
		containerGrid.add(createBankInfoGrid(), 0, 1);
		containerGrid.add(createCarInfoGrid(), 1, 1);
		
		containerBox.getChildren().add(createLoanInfoGrid());
		containerBox.getChildren().add(createLoanPaymentOverview());
		containerBox.getChildren().add(createButtons());
				
		Label needingApprovalLabel=new Label("Lånets størrelse kræver godkendelse af salgschef");
		needingApprovalLabel.setVisible(!theController.getLoanAgreement().isApproved());
		
		containerBox.getChildren().add(needingApprovalLabel);
		
		return root;
	}
	
	private GridPane createCustomerInfoGrid()
	{
		CustomerDataModel customer=theController.getLoanAgreement().getCustomer();
		GridPane customerInformationGrid=new GridPane();
		
		Label customerInformationHeader=new Label("Kundeinformation :");
		customerInformationHeader.setId("part_header_label");
		
		Label customerNameHeader=new Label("Navn");
		customerNameHeader.setId("header_label");
		
		Label customerNameLabel=new Label();
		customerNameLabel.textProperty().bind(Bindings.concat(customer.firstNameProperty(), " ", customer.lastNameProperty()));
		
		Label customerAdressHeader=new Label("Addresse");
		customerAdressHeader.setId("header_label");
		
		Label customerAdressLabel=new Label();
		customerAdressLabel.textProperty().bind(customer.addressProperty());
		
		Label customerTlfHeader=new Label("Tlf. nr.");
		customerTlfHeader.setId("header_label");
		
		Label customerTlfLabel=new Label();
		customerTlfLabel.textProperty().bind(customer.phoneProperty());
		
		customerInformationGrid.add(customerInformationHeader, 0, 0, 2,1);
		customerInformationGrid.add(customerNameHeader, 0, 1);
		customerInformationGrid.add(customerNameLabel, 0, 2);
		customerInformationGrid.add(customerAdressHeader, 1, 1);
		customerInformationGrid.add(customerAdressLabel, 1, 2);
		customerInformationGrid.add(customerTlfHeader, 0, 3);
		customerInformationGrid.add(customerTlfLabel, 0, 4);
		
		GridPane.setMargin(customerInformationGrid, new Insets(0, 40, 20, 0));
		
		return customerInformationGrid;
	}
	
	private GridPane createSellerInfoGrid()
	{
		GridPane sellerInfoGrid=new GridPane();
		
		Label sellerInformationHeader=new Label("Sælgerinformation :");
		sellerInformationHeader.setId("part_header_label");
		
		Label sellerNameHeader=new Label("Sælgers Navn");
		sellerNameHeader.setId("header_label");
		
		Label sellerNameLabel=new Label();
		sellerNameLabel.textProperty().bind(theController.getLoanAgreement().getSeller().fullNameProperty());
		
		sellerInfoGrid.add(sellerInformationHeader, 0, 0);
		sellerInfoGrid.add(sellerNameHeader, 0, 1);
		sellerInfoGrid.add(sellerNameLabel, 0, 2);
		
		return sellerInfoGrid;
	}
	
	private GridPane createBankInfoGrid()
	{
		GridPane bankInfoGrid=new GridPane();
		
		Label bankInformationHeader=new Label("Bankinformation :");
		bankInformationHeader.setId("part_header_label");
		
		Label bankNameHeader=new Label("Bankens Navn");
		bankNameHeader.setId("header_label");
		
		Label bankNameLabel=new Label("Realkredit Herning");
		
		bankInfoGrid.add(bankInformationHeader, 0, 0);
		bankInfoGrid.add(bankNameHeader, 0, 1);
		bankInfoGrid.add(bankNameLabel, 0, 2);
		
		return bankInfoGrid;
	}
	
	private GridPane createCarInfoGrid()
	{
		GridPane carInfoGrid=new GridPane();
		
		Label carInformationHeader=new Label("Bilinformation :");
		carInformationHeader.setId("part_header_label");
		
		Label carNameHeader=new Label("Bilens Stel nr.");
		carNameHeader.setId("header_label");
		
		Label carNameLabel=new Label();
		carNameLabel.textProperty().bind(theController.getLoanAgreement().getCar().VINProperty());
		
		carInfoGrid.add(carInformationHeader, 0, 0);
		carInfoGrid.add(carNameHeader, 0, 1);
		carInfoGrid.add(carNameLabel, 0, 2);
		
		return carInfoGrid;
	}
	
	private GridPane createLoanInfoGrid()
	{
		LoanAgreementDataModel loanAgreement=theController.getLoanAgreement();
		
		GridPane loanInfoGrid=new GridPane();
		loanInfoGrid.setPadding(new Insets(20, 0, 0, 0));
		
		Label loanInformationHeader=new Label("Låneinformation :");
		loanInformationHeader.setId("part_header_label");
		
		Label askingPriceHeader=new Label("Aftalt pris");
		askingPriceHeader.setId("header_label");
		
		Label askingPriceLabel=new Label();
		askingPriceLabel.textProperty().bind(loanAgreement.askingPriceProperty());
		
		Label downpaymentHeader=new Label("Kundens udbetaling");
		downpaymentHeader.setId("header_label");
		
		Label downpaymentLabel=new Label();
		downpaymentLabel.textProperty().bind(loanAgreement.downPaymentProperty());
		
		Label loanPeriodHeader=new Label("Lånets løbetid");
		loanPeriodHeader.setId("header_label");
		
		Label loanPeriodLabel=new Label();
		loanPeriodLabel.textProperty().bind(loanAgreement.durationProperty().asString());
		
		Label loanStartDateHeader=new Label("Lånets startdato");
		loanStartDateHeader.setId("header_label");
		
		Label loanStartDateLabel=new Label();
		loanStartDateLabel.textProperty().bind(loanAgreement.startDateProperty().asString());
		
		Label loanExpirationDateHeader=new Label("Lånets slutdato");
		loanExpirationDateHeader.setId("header_label");
		
		//TODO make better, faster, stronger
		Label loanExpirationDateLabel=new Label("2/2/3");
		LocalDate endDate =loanAgreement.getStartDate().plusMonths(loanAgreement.getDuration()*12-1);
		loanExpirationDateLabel.setText(endDate.toString());
		
		Label interestRateHeader=new Label("Rentesats");
		interestRateHeader.setId("header_label");
		
		Label interestRateLabel=new Label();
		interestRateLabel.textProperty().bind(Bindings.format("%.4f", Double.parseDouble(loanAgreement.getInterestRate())));
		
		Label yearlyPaymentPercentageHeader=new Label("ÅOP");
		yearlyPaymentPercentageHeader.setId("header_label");
		
		Label yearlyPaymentPercentageLabel=new Label("???");
		
		Label montlyPaymentHeader=new Label("Månedlig Ydelse");
		montlyPaymentHeader.setId("header_label");
		
		Label montlyPaymentLabel=new Label("???");
		
		loanInfoGrid.add(loanInformationHeader, 0, 0);
		loanInfoGrid.add(askingPriceHeader, 0, 1);
		loanInfoGrid.add(askingPriceLabel, 0, 2);
		loanInfoGrid.add(downpaymentHeader, 1, 1);
		loanInfoGrid.add(downpaymentLabel, 1, 2);
		loanInfoGrid.add(loanPeriodHeader, 2, 1);
		loanInfoGrid.add(loanPeriodLabel, 2, 2);
		loanInfoGrid.add(loanStartDateHeader, 3, 1);
		loanInfoGrid.add(loanStartDateLabel, 3, 2);
		loanInfoGrid.add(loanExpirationDateHeader, 0, 3);
		loanInfoGrid.add(loanExpirationDateLabel, 0, 4);
		loanInfoGrid.add(interestRateHeader, 1, 3);
		loanInfoGrid.add(interestRateLabel, 1, 4);
		loanInfoGrid.add(yearlyPaymentPercentageHeader, 2, 3);
		loanInfoGrid.add(yearlyPaymentPercentageLabel, 2, 4);
		loanInfoGrid.add(montlyPaymentHeader, 3, 3);
		loanInfoGrid.add(montlyPaymentLabel, 3, 4);
		
		GridPane.setMargin(askingPriceHeader, new Insets(0, 12, 0, 0));
		GridPane.setMargin(downpaymentHeader, new Insets(0, 12, 0, 0));
		GridPane.setMargin(loanPeriodHeader, new Insets(0, 12, 0, 0));
		GridPane.setMargin(loanStartDateHeader, new Insets(0, 12, 0, 0));
		
		return loanInfoGrid;
	}
	
	private HBox createButtons()
	{
		HBox buttonBox=new HBox();
		buttonBox.setPadding(new Insets(20));
		
		Button saveButton = new Button("Gem & Luk");
		saveButton.setId("view_button");
		saveButton.setOnAction(new EventHandler<ActionEvent>() 
		{
		    @Override
		    public void handle(ActionEvent e) 
		    {
		    	hasSaved=true;
		    	theController.closeAndSaveAgreement();
		    }
		});
		
		Button exportButton = new Button("Eksporter");
		exportButton.setId("view_button");
		exportButton.setOnAction(new EventHandler<ActionEvent>() 
		{
		    @Override
		    public void handle(ActionEvent e) 
		    {
		    	hasExported=true;
		    	theController.exportAgreementToCSVFile();
		    }
		});
		
		Button cancelButton = new Button("Tilbage");
		cancelButton.setId("view_button");
		cancelButton.setOnAction(new EventHandler<ActionEvent>() 
		{
		    @Override
		    public void handle(ActionEvent e) 
		    {
		    	theController.returnToCreateAgreementScene();
		    }
		});
		
		buttonBox.getChildren().add(saveButton);
		buttonBox.getChildren().add(exportButton);
		buttonBox.getChildren().add(cancelButton);
		HBox.setMargin(exportButton, new Insets(0, 12, 0, 12));
		
		return buttonBox;
	}
	
	private Node createLoanPaymentOverview() {
		TableView<Payment> table = new TableView<>();
				ObservableList<Payment> payments = (ObservableList<Payment>) theController.getPaymentOverview();
				table.setItems(payments);
		
				// Payment number column
				TableColumn<Payment, String> paymentNoColumn = new TableColumn<>("Løbenummer");
				paymentNoColumn.setCellValueFactory(new PropertyValueFactory<>("paymentNo"));
		
				// Date column
				TableColumn<Payment, LocalDate> dateColumn = new TableColumn<>("Dato");
				dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
		
				// Payment column
				TableColumn<Payment, String> paymentColumn = new TableColumn<>("Ydelse");
				paymentColumn.setCellValueFactory(new PropertyValueFactory<>("payment"));
		
				// Interest column
				TableColumn<Payment, String> interestColumn = new TableColumn<>("Renter");
				interestColumn.setCellValueFactory(new PropertyValueFactory<>("interest"));
		
				// Instalment column
				TableColumn<Payment, String> instalmentColumn = new TableColumn<>("Afdrag");
				instalmentColumn.setCellValueFactory(new PropertyValueFactory<>("instalment"));
		
				// Principal column
			TableColumn<Payment, String> principalColumn = new TableColumn<>("Ny restgæld");
				principalColumn.setCellValueFactory(new PropertyValueFactory<>("principal"));
		
				table.getColumns().addAll(paymentNoColumn, dateColumn, paymentColumn, interestColumn, instalmentColumn, principalColumn);
		
				return table;
	}

	@Override
	public boolean onClose()
	{
		if(hasSaved)
			return true;
		
		String question = "Vil du afslutte uden at have gemt";
		if(!hasExported)
				question+= " og exporteret til csv";
		question+="?";
		
		Alert alert = new FCLSAlert(AlertType.NONE, question , ButtonType.OK, new ButtonType("Annuller", ButtonData.CANCEL_CLOSE));
		alert.showAndWait();
		
		return alert.getResult() == ButtonType.OK;
	}
}