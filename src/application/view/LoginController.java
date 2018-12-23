package application.view;

import application.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class LoginController {
	
	@FXML
	public RadioButton readerRbtn;
	
	@FXML
	public RadioButton bookmanagerRbtn;
	
	@FXML
	public RadioButton systemmanagerRbtn;
	
	@FXML
	public Button login;
	
	@FXML
	public TextField accountField;
	
	@FXML
	public CheckBox savePassword;
	
	@FXML
	public Label forget;
	
	@FXML
	public PasswordField passwordField;
	//Reference to the main application
	private MainApp mainApp; 	
	
	//to judge the result whether click the button.
	
	/**
	*The Constructor.
	*The Constructor is called before the initialize() method.
	*/ 
	public LoginController() {
		
	}
	
	/**
	 * Initializes the constructor class.
	 * This method is automatically called after the fxml file has been load.
	 */
	@FXML
	private void initialize() {
		
	}
	
	/**
	 * Is called by the main applicaiton to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainapp) {
		this.mainApp = mainapp;
	}
	
	/**
	 * Called when the button reader is clicked.
	 */
	public void handleOpenReader() {

	}
	
	
	/**
	 * Called when the button Manager is clicked.
	 */
	public void handleOpenManager() {
		
	}
}
