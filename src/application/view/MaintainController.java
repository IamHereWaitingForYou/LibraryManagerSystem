package application.view;

import application.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MaintainController {
	
	@FXML
	public TextField tsbhTf;
	
	@FXML
	public TextField tsmcTf;
	
	@FXML
	public TextField cbsjTf;
	
	@FXML
	public TextField tslxTf;
	
	@FXML
	public TextField zzTf;
	
	@FXML
	public TextField yzTf;
	
	@FXML
	public TextField cbsTf;
	
	@FXML
	public TextArea jjTf;
	
	@FXML
	public TextField clTf;
	
	@FXML
	public TextField ylTf;
	
	@FXML
	public TextField jgTf;
	
	@FXML
	public TextField grsjTf;
	
	@FXML
	public Button affirmBtn5;
	
	@FXML
	public Button cleanBtn5;;
	

	
	//Reference to the main application
	private MainApp mainApp; 
	public MaintainController() {
		
	}
	public void setMainApp(MainApp mainapp) {
		this.mainApp = mainapp;
	}
}
