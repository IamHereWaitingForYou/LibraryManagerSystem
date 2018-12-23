package application.view;

import application.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class register2Controller {

	@FXML
	public Button buttonsure2;
	
	@FXML
	public TextField textNew; 
	
	@FXML
	public TextField textNew1;
	
	private MainApp mainApp; 
	
	public register2Controller() {
		
	}
	
	@FXML
	private void initialize() {
		
	}
	
	public void setMainApp(MainApp mainapp) {
		this.mainApp = mainapp;
	}
}
