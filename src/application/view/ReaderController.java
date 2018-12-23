package application.view;

import application.MainApp;
import application.dao.UserDao;
import application.dao.BookDao;
import application.model.Book;
import application.model.Borrow;
import application.model.SBook;
import application.model.SBorrow;
import application.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ReaderController {
	@FXML
	public Label quit;
	
	@FXML
	TextField account;
	
	@FXML
	TextField name;
	
	@FXML
	TextField status;
	
	@FXML
	TextField sex;
	
	@FXML
	TextField number;
	
	@FXML
	TextField day;
	
	@FXML
	TextField forfeit;
	
	@FXML
	public Label DId;
	
	@FXML
	public Label DName;
	
	@FXML
	public Button affirmBtn;
	
	@FXML
	public Button renewBtn;
	/**
	 * 图书查询
	 */
	@FXML
	public TableView<SBook> bookTable;
	
	@FXML
	TableColumn<SBook, String> idColumn;
	
	@FXML
	TableColumn<SBook, String> nameColumn;
	
	@FXML
	TableColumn<SBook, String> typeColumn;
	
	@FXML
	TableColumn<SBook, String> authorColumn;
	
	@FXML
	TableColumn<SBook, String> publishHouseColumn;
	
	@FXML
	TableColumn<SBook, String> publishTimeColumn;
	
	@FXML
	TableColumn<SBook, Integer> reserveColumn;
	
	@FXML
	TableColumn<SBook, Integer> marginColumn;
	
	@FXML
	public Button findBtn;
	
	@FXML
	public TextField textTf;
	
	/**
	 * 借阅查询
	 */
	@FXML
	public
	TableView<SBorrow> borrowTable;
	
	@FXML
	TableColumn<SBorrow,String> BidColumn;
	
	@FXML
	TableColumn<SBorrow,String> BbookNameColumn;
	
	@FXML
	TableColumn<SBorrow,String> BdueDateColumn;
	
	@FXML
	TableColumn<SBorrow,Boolean> BgiveColumn;
	
	@FXML
	TableColumn<SBorrow,Integer> BrenewColumn;
	
	//Reference to the main application
	private MainApp mainApp;
	private User user;
	private ObservableList<User> userDate;
	private BookDao bookDao;
	
	private ObservableList<Book> pbookData = FXCollections.observableArrayList();//查阅图书记录
	
	String sAccount,sName,sStatus,sSex,sForfeit;

	String sNumber;

	/**
	*The Constructor.
	*The Constructor is called before the initialize() method.
	*/
	public ReaderController() {
		
	}

	
	/**
	 * Initializes the constructor class.
	 * This method is automatically called after the fxml file has been load.
	 */
	@FXML
	private void initialize() {
		//图书查询表格
		idColumn.setCellValueFactory(cellData -> cellData.getValue().getIdProperty());
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
		typeColumn.setCellValueFactory(cellData -> cellData.getValue().getTypeProperty());
		authorColumn.setCellValueFactory(cellData -> cellData.getValue().getAuthorProperty());
		publishHouseColumn.setCellValueFactory(cellData -> cellData.getValue().getPublishHouseProperty());
		publishTimeColumn.setCellValueFactory(cellData -> cellData.getValue().getPublishTimeProperty());
		reserveColumn.setCellValueFactory(cellData -> cellData.getValue().getReserveProperty().asObject());
		marginColumn.setCellValueFactory(cellData -> cellData.getValue().getMarginProperty().asObject());
		
		 //借阅表格
		 BidColumn.setCellValueFactory(cellData -> cellData.getValue().getIdProperty());
		 BbookNameColumn.setCellValueFactory(cellData -> cellData.getValue().getBookNameProperty());
		 BdueDateColumn.setCellValueFactory(cellData -> cellData.getValue().getDueDateProperty());
		 BgiveColumn.setCellValueFactory(cellData -> cellData.getValue().getGiveBackProperty().asObject());
		 BrenewColumn.setCellValueFactory(cellData -> cellData.getValue().getRenewProperty().asObject());
	}
	

	
	/**
	 * Is called by the main applicaiton to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainapp) {
		this.mainApp = mainapp;
		
		//获取信息
		sAccount = mainapp.getAccount();
		sStatus = mainapp.getStatus();
		
		sName = mainapp.getName();
		sSex = mainapp.getSex();
		sNumber = String.valueOf(mainapp.getBorrows());
		sForfeit = String.valueOf(mainapp.getForfeit());
		
		//输入信息
		account.setText(sAccount);
		name.setText(sName);
		status.setText(sStatus);
		sex.setText(sSex);
		number.setText(sNumber);
		day.setText("30");
		forfeit.setText(sForfeit);	
		
		bookTable.setItems(mainapp.getAllSBookList());
		borrowTable.setItems(mainapp.getBorrow());
		
	}


	

}
