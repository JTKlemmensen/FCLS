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

public class ShowLoanAggrementView 
{
private ShowLoanAggrementController theController;
	
	public ShowLoanAggrementView(ShowLoanAggrementController controller)
	{
		theController=controller;
	}
	
	public StackPane getSceneGUI()
	{
		StackPane root = new StackPane();
		root.setStyle("-fx-background-color: #bdc7cc; -fx-border-color: #828889; -fx-border-width: 2;");
		
		VBox containerBox=new VBox();
		root.getChildren().add(containerBox);
		
		//create customerinfoGrid
		GridPane customerInformationGrid=new GridPane();
		containerBox.getChildren().add(customerInformationGrid);
		
		Label customerInformationHeader=new Label("Kundeinformation :");
		
		Label customerNameHeader=new Label("Navn :");
		
		Label customerNameLabel=new Label("Jesper");
		customerNameLabel.textProperty().bind(theController.getLoanAgreement().getCustomer().customerNameProperty());
		
		Label customerAdressHeader=new Label("Addresse :");
		
		Label customerAdressLabel=new Label("lagervej 32");
		customerAdressLabel.textProperty().bind(theController.getLoanAgreement().getCustomer().customerAddressProperty());
		
		Label customerTlfHeader=new Label("Tlf. nr :");
		
		Label customerTlfLabel=new Label("50339539");
		customerTlfLabel.textProperty().bind(theController.getLoanAgreement().getCustomer().customerPhoneProperty());
		
		Label customerCPRHeader=new Label("CPR. nr :");
		
		Label customerCPRLabel=new Label("010203-2211");
		customerTlfLabel.textProperty().bind(theController.getLoanAgreement().getCustomer().customerCPRProperty());
		
		customerInformationGrid.add(customerInformationHeader, 0, 0);
		customerInformationGrid.add(customerNameHeader, 0, 1);
		customerInformationGrid.add(customerNameLabel, 0, 2);
		customerInformationGrid.add(customerAdressHeader, 1, 1);
		customerInformationGrid.add(customerAdressLabel, 1, 2);

		//create SellerInfoGRid
				GridPane sellerInfoGrid=new GridPane();
				containerBox.getChildren().add(sellerInfoGrid);
				
				Label sellerInformationHeader=new Label("Sælgerinformation :");
				
				Label sellerNameHeader=new Label("Sælgers Navn :");
				
				Label sellerNameLabel=new Label("Torben");
				sellerNameLabel.textProperty().bind(theController.getLoanAgreement().getSeller().salesPersonNameProperty());
				
				sellerInfoGrid.add(sellerInformationHeader, 0, 0);
				sellerInfoGrid.add(sellerNameHeader, 0, 1);
				sellerInfoGrid.add(sellerNameLabel, 0, 2);

				//create BankInfoGRid
				GridPane bankInfoGrid=new GridPane();
				containerBox.getChildren().add(bankInfoGrid);
				
				Label bankInformationHeader=new Label("Bankinformation :");
				
				Label bankNameHeader=new Label("Bankens Navn :");
				
				Label bankNameLabel=new Label("Realkredit Herning");
				
				bankInfoGrid.add(bankInformationHeader, 0, 0);
				bankInfoGrid.add(bankNameHeader, 0, 1);
				bankInfoGrid.add(bankNameLabel, 0, 2);
				
				//create carInfoGRid
				GridPane carInfoGrid=new GridPane();
				containerBox.getChildren().add(carInfoGrid);
				
				Label carInformationHeader=new Label("Bilinformation :");
				
				Label carNameHeader=new Label("Bilens Stel.nr :");
				
				Label carNameLabel=new Label("2323423");
				carNameLabel.textProperty().bind(theController.getLoanAgreement().getCar().VINProperty());
				
				carInfoGrid.add(carInformationHeader, 0, 0);
				carInfoGrid.add(carNameHeader, 0, 1);
				carInfoGrid.add(carNameLabel, 0, 2);
		
				//create loanInfoGRid
				GridPane loanInfoGrid=new GridPane();
				containerBox.getChildren().add(loanInfoGrid);
				
				Label loanInformationHeader=new Label("Låneinformation :");
				
				Label askingPriceHeader=new Label("Aftalt pris :");
				
				Label askingPriceLabel=new Label("435345");
				askingPriceLabel.textProperty().bind(theController.getLoanAgreement().askingPriceProperty());
				
				Label downpaymentHeader=new Label("Kundens udbetaling :");
				
				Label downpaymentLabel=new Label("20000");
				downpaymentLabel.textProperty().bind(theController.getLoanAgreement().downPaymentProperty());
				
				Label loanPeriodHeader=new Label("Lånets løbetid :");
				
				Label loanPeriodLabel=new Label("2");
				loanPeriodLabel.textProperty().bind(theController.getLoanAgreement().durationProperty());
				
				Label loanStartDateHeader=new Label("Lånets startdato :");
				
				Label loanStartDateLabel=new Label("2/2/2");
				
				Label loanExpirationDateHeader=new Label("Lånets slutdato :");
				
				Label loanExpirationDateLabel=new Label("2/2/3");
				
				Label interestRateHeader=new Label("Rentesats :");
				
				Label interestRateLabel=new Label("4.3");
				interestRateLabel.textProperty().bind(theController.getLoanAgreement().interestRateProperty());
				
				Label yearlyPaymentPercentageHeader=new Label("ÅOP :");
				
				Label yearlyPaymentPercentageLabel=new Label("20.2");
				
				Label montlyPaymentHeader=new Label("Månedlig Ydelse :");
				
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
				loanInfoGrid.add(loanExpirationDateHeader, 4, 1);
				loanInfoGrid.add(loanExpirationDateLabel, 4, 2);
				loanInfoGrid.add(interestRateHeader, 5, 1);
				loanInfoGrid.add(interestRateLabel, 5, 2);
				loanInfoGrid.add(yearlyPaymentPercentageHeader, 6, 1);
				loanInfoGrid.add(yearlyPaymentPercentageLabel, 6, 2);
				loanInfoGrid.add(montlyPaymentHeader, 7, 1);
				loanInfoGrid.add(montlyPaymentLabel, 7, 2);
				
				//create buttons
				HBox buttonBox=new HBox();
				containerBox.getChildren().add(buttonBox);
				
				Button saveButton = new Button("Gem & Luk");
				
				Button exportButton = new Button("Eksporter");
				
				Button cancelButton = new Button("Tilbage");
				
				buttonBox.getChildren().add(saveButton);
				buttonBox.getChildren().add(exportButton);
				buttonBox.getChildren().add(cancelButton);
		
		return root;
	}
}
