package view;

import java.util.List;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import logic.LoanAgreementDataModel;

public class ApproveLoanView  implements View
{
	private ApproveLoanController theController;
	private TableView<LoanAgreementDataModel> table;
	
	public ApproveLoanView(ApproveLoanController controller)
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
		
        TableColumn<LoanAgreementDataModel, String> sellerCol = new TableColumn<LoanAgreementDataModel, String>("Sælger");
        TableColumn<LoanAgreementDataModel, String> limitCol = new TableColumn<LoanAgreementDataModel, String>("Grænseværdi");
        TableColumn<LoanAgreementDataModel, String> loanAmounCol = new TableColumn<LoanAgreementDataModel, String>("Lånets størrelse");
        TableColumn<LoanAgreementDataModel, String> downPaymentCol = new TableColumn<LoanAgreementDataModel, String>("Udbetaling");
        TableColumn<LoanAgreementDataModel, String> durationCol = new TableColumn<LoanAgreementDataModel, String>("Løbetid");
        TableColumn<LoanAgreementDataModel, String> rateCol = new TableColumn<LoanAgreementDataModel, String>("Rente");
        
        sellerCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<LoanAgreementDataModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<LoanAgreementDataModel, String> p) 
            {
                return p.getValue().getSeller().usernameProperty();
            }
        });
        
        limitCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<LoanAgreementDataModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<LoanAgreementDataModel, String> p) 
            {
                return p.getValue().getSeller().loanLimitProperty();
            }
        });
        
        loanAmounCol.setCellValueFactory(new PropertyValueFactory<LoanAgreementDataModel, String>("askingPrice"));
        downPaymentCol.setCellValueFactory(new PropertyValueFactory<LoanAgreementDataModel, String>("downPayment"));
        durationCol.setCellValueFactory(new PropertyValueFactory<LoanAgreementDataModel, String>("duration"));
        rateCol.setCellValueFactory(new PropertyValueFactory<LoanAgreementDataModel, String>("interestRate"));
        
        table.getColumns().add(sellerCol);
        table.getColumns().add(limitCol);
        table.getColumns().add(loanAmounCol);
        table.getColumns().add(downPaymentCol);
        table.getColumns().add(durationCol);
        table.getColumns().add(rateCol);
		
        containerBox.getChildren().add(table);
        
        HBox buttonBox=new HBox();
        containerBox.getChildren().add(buttonBox);
        
        //create buttons
        Button approveLoanButton=new Button("Godkend L�n");
        approveLoanButton.setId("view_button");
        approveLoanButton.setOnAction(new EventHandler<ActionEvent>() 
		{
		    @Override
		    public void handle(ActionEvent e) 
		    {
		    	LoanAgreementDataModel loanAgreement=table.getSelectionModel().getSelectedItem();
		    	if(loanAgreement!=null)
		    	{
		    		theController.approveLoan(loanAgreement);
		    	}
		    }
		});
        
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
        
        buttonBox.getChildren().add(approveLoanButton);
        buttonBox.getChildren().add(closeButton);
        
		return root;
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
