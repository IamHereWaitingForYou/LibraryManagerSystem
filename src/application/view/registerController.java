package application.view;

import application.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class registerController {
	@FXML
	public Button buttonsure;
	@FXML
	public Button buttoncancel;
	
	private MainApp mainApp; 
	
	@FXML
	public TextField textXue;
	
	@FXML
	public TextField textidentity;
	
	public registerController() {
		
	}
	
	@FXML
	private void initialize() {
		
	}
	
	public void setMainApp(MainApp mainapp) {
		this.mainApp = mainapp;
	}
	
}
