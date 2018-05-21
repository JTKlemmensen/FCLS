package view;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class FCLSAlert extends Alert
{
	public FCLSAlert(AlertType alertType)
	{
		super(alertType);
		setIcon();
	}
	
	public FCLSAlert(AlertType alertType, String contentText, ButtonType...buttons)
	{
		super(alertType, contentText, buttons);
		setIcon();
	}
	
	private void setIcon()
	{
		Stage stage = (Stage)this.getDialogPane().getScene().getWindow();
		Image image= new Image(getClass().getResourceAsStream("ferrariLogo.png"));
		stage.getIcons().add(image);
	}

}
