package application.view;

import application.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Maintain6Controlle {

	@FXML
	public TextField borrowIdTf;
	
	@FXML
	public TextField bookNameTf;
	
	@FXML
	public TextField userNameTf;
	
	@FXML
	public TextField renewTimeTf;
	
	@FXML
	public TextField returnTf;
	
	@FXML
	public TextField returnDateTf;
	
	
	@FXML
	public Button affirmBtn;
	
	@FXML
	public Button cancelBtn;
	
	//Reference to the main application
	private MainApp mainApp; 
	public Maintain6Controlle() {
		
	}
	public void setMainApp(MainApp mainapp) {
		this.mainApp = mainapp;
	}
}
