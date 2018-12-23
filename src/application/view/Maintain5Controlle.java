package application.view;

import application.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Maintain5Controlle {

	@FXML
	public TextField userName;
	
	@FXML
	public TextField userType;
	
	@FXML
	public TextField userAccount;
	
	@FXML
	public TextField userSex;
	
	@FXML
	public TextField userId;
	
	@FXML
	public TextField phoneNumber;
	
	
	@FXML
	public Button affirmBtn;
	
	@FXML
	public Button cancelBtn;
	
	//Reference to the main application
	private MainApp mainApp; 
	public Maintain5Controlle() {
		
	}
	public void setMainApp(MainApp mainapp) {
		this.mainApp = mainapp;
	}
}
