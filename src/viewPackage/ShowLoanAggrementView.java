package viewPackage;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ShowLoanAggrementView implements View
{
private ShowLoanAggrementController theController;
	
	public ShowLoanAggrementView(ShowLoanAggrementController controller)
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
		
		//create customerinfoGrid
		GridPane customerInformationGrid=new GridPane();
		//containerBox.getChildren().add(customerInformationGrid);
		
		Label customerInformationHeader=new Label("Kundeinformation :");
		customerInformationHeader.setId("part_header_label");
		
		Label customerNameHeader=new Label("Navn");
		customerNameHeader.setId("header_label");
		
		Label customerNameLabel=new Label();
		customerNameLabel.textProperty().bind(theController.getLoanAgreement().getCustomer().customerNameProperty());
		
		Label customerAdressHeader=new Label("Addresse");
		customerAdressHeader.setId("header_label");
		
		Label customerAdressLabel=new Label();
		customerAdressLabel.textProperty().bind(theController.getLoanAgreement().getCustomer().customerAddressProperty());
		
		Label customerTlfHeader=new Label("Tlf. nr.");
		customerTlfHeader.setId("header_label");
		
		Label customerTlfLabel=new Label();
		customerTlfLabel.textProperty().bind(theController.getLoanAgreement().getCustomer().customerPhoneProperty());
		
		customerInformationGrid.add(customerInformationHeader, 0, 0, 2,1);
		customerInformationGrid.add(customerNameHeader, 0, 1);
		customerInformationGrid.add(customerNameLabel, 0, 2);
		customerInformationGrid.add(customerAdressHeader, 1, 1);
		customerInformationGrid.add(customerAdressLabel, 1, 2);
		customerInformationGrid.add(customerTlfHeader, 0, 3);
		customerInformationGrid.add(customerTlfLabel, 0, 4);

		//create SellerInfoGRid
				GridPane sellerInfoGrid=new GridPane();
				//containerBox.getChildren().add(sellerInfoGrid);
				
				Label sellerInformationHeader=new Label("Sælgerinformation :");
				sellerInformationHeader.setId("part_header_label");
				
				Label sellerNameHeader=new Label("Sælgers Navn");
				sellerNameHeader.setId("header_label");
				
				Label sellerNameLabel=new Label();
				sellerNameLabel.textProperty().bind(theController.getLoanAgreement().getSeller().salesPersonNameProperty());
				
				sellerInfoGrid.add(sellerInformationHeader, 0, 0);
				sellerInfoGrid.add(sellerNameHeader, 0, 1);
				sellerInfoGrid.add(sellerNameLabel, 0, 2);

				//create BankInfoGRid
				GridPane bankInfoGrid=new GridPane();
				//containerBox.getChildren().add(bankInfoGrid);
				
				Label bankInformationHeader=new Label("Bankinformation :");
				bankInformationHeader.setId("part_header_label");
				
				Label bankNameHeader=new Label("Bankens Navn");
				bankNameHeader.setId("header_label");
				
				Label bankNameLabel=new Label("Realkredit Herning");
				
				bankInfoGrid.add(bankInformationHeader, 0, 0);
				bankInfoGrid.add(bankNameHeader, 0, 1);
				bankInfoGrid.add(bankNameLabel, 0, 2);
				
				//create carInfoGRid
				GridPane carInfoGrid=new GridPane();
				//containerBox.getChildren().add(carInfoGrid);
				
				Label carInformationHeader=new Label("Bilinformation :");
				carInformationHeader.setId("part_header_label");
				
				Label carNameHeader=new Label("Bilens Stel nr.");
				carNameHeader.setId("header_label");
				
				Label carNameLabel=new Label();
				carNameLabel.textProperty().bind(theController.getLoanAgreement().getCar().VINProperty());
				
				carInfoGrid.add(carInformationHeader, 0, 0);
				carInfoGrid.add(carNameHeader, 0, 1);
				carInfoGrid.add(carNameLabel, 0, 2);
		
				//create mainContainergrid
				GridPane containerGrid=new GridPane();
				containerBox.getChildren().add(containerGrid);
				
				containerGrid.add(customerInformationGrid, 0, 0);
				containerGrid.add(sellerInfoGrid, 1, 0);
				containerGrid.add(bankInfoGrid, 0, 1);
				containerGrid.add(carInfoGrid, 1, 1);
				containerGrid.setMargin(customerInformationGrid, new Insets(0, 40, 20, 0));
				
				
				//create loanInfoGRid
				GridPane loanInfoGrid=new GridPane();
				loanInfoGrid.setPadding(new Insets(20, 0, 0, 0));
				containerBox.getChildren().add(loanInfoGrid);
				
				Label loanInformationHeader=new Label("Låneinformation :");
				loanInformationHeader.setId("part_header_label");
				
				Label askingPriceHeader=new Label("Aftalt pris");
				askingPriceHeader.setId("header_label");
				
				Label askingPriceLabel=new Label();
				askingPriceLabel.textProperty().bind(theController.getLoanAgreement().askingPriceProperty());
				
				Label downpaymentHeader=new Label("Kundens udbetaling");
				downpaymentHeader.setId("header_label");
				
				Label downpaymentLabel=new Label();
				downpaymentLabel.textProperty().bind(theController.getLoanAgreement().downPaymentProperty());
				
				Label loanPeriodHeader=new Label("Lånets løbetid");
				loanPeriodHeader.setId("header_label");
				
				Label loanPeriodLabel=new Label();
				loanPeriodLabel.textProperty().bind(theController.getLoanAgreement().durationProperty());
				
				Label loanStartDateHeader=new Label("Lånets startdato");
				loanStartDateHeader.setId("header_label");
				
				Label loanStartDateLabel=new Label();
				loanStartDateLabel.textProperty().bind(theController.getLoanAgreement().startDateProperty().asString());
				
				Label loanExpirationDateHeader=new Label("Lånets slutdato");
				loanExpirationDateHeader.setId("header_label");
				
				Label loanExpirationDateLabel=new Label("2/2/3");
				
				Label interestRateHeader=new Label("Rentesats");
				interestRateHeader.setId("header_label");
				
				Label interestRateLabel=new Label();
				interestRateLabel.textProperty().bind(theController.getLoanAgreement().interestRateProperty());
				
				Label yearlyPaymentPercentageHeader=new Label("ÅOP");
				yearlyPaymentPercentageHeader.setId("header_label");
				
				Label yearlyPaymentPercentageLabel=new Label("20.2");
				
				Label montlyPaymentHeader=new Label("Månedlig Ydelse");
				montlyPaymentHeader.setId("header_label");
				
				Label montlyPaymentLabel=new Label("4000");
				
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
				
				loanInfoGrid.setMargin(askingPriceHeader, new Insets(0, 12, 0, 0));
				loanInfoGrid.setMargin(downpaymentHeader, new Insets(0, 12, 0, 0));
				loanInfoGrid.setMargin(loanPeriodHeader, new Insets(0, 12, 0, 0));
				loanInfoGrid.setMargin(loanStartDateHeader, new Insets(0, 12, 0, 0));
				
				//create buttons
				HBox buttonBox=new HBox();
				buttonBox.setPadding(new Insets(20));
				containerBox.getChildren().add(buttonBox);
				
				Button saveButton = new Button("Gem & Luk");
				saveButton.setId("view_button");
				saveButton.setOnAction(new EventHandler<ActionEvent>() 
				{
				    @Override
				    public void handle(ActionEvent e) 
				    {
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
				buttonBox.setMargin(exportButton, new Insets(0, 12, 0, 12));
		
		return root;
	}

	@Override
	public boolean onClose()
	{
		// TODO Auto-generated method stub
		return true;
	}
}