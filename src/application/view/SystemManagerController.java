package application.view;

import application.MainApp;
import application.model.Book;
import application.model.Borrow;
import application.model.SBook;
import application.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SystemManagerController {
		@FXML
		public Label quit;
		private static String saveId;

		private static String saveName;
		
		private static String saveBorrowId;
		
		/**
		 * 查询图书表格
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
		Label DId;
		
		@FXML
		public Label DName;

		@FXML
		public Button findBtn;
		
		@FXML
		public TextField textTf;
		
		@FXML
		public Button affirmBtn;
		
		/**
		 * 所有图书表格
		 */
		@FXML
		public TableView<Book> bookTable2;
		
		@FXML
		TableColumn<Book, String> idColumn2;
		
		@FXML
		TableColumn<Book, String> nameColumn2;
		
		@FXML
		TableColumn<Book, String> typeColumn2;
		
		@FXML
		TableColumn<Book, String> authorColumn2;
		
		@FXML
		TableColumn<Book, String> publishHouseColumn2;
		
		@FXML
		TableColumn<Book, String> publishTimeColumn2;
		
		@FXML
		TableColumn<Book, Integer> reserveColumn2;
		
		@FXML
		TableColumn<Book, Integer> marginColumn2;
		
		@FXML
		TableColumn<Book, Double> priceColumn2;
		
		@FXML
		public Button refresh1;
		
		@FXML
		public Button findBtn2;
		
		@FXML
		public Button insertBtn2;
		
		@FXML
		public Button deleteBtn2;
		
		@FXML
		public Button updateBtn2;
		
		@FXML
		public TextField Tf2;
		
		/**
		 * 所有读者表格
		 */
		@FXML
		public TableView<User> userTable;
		
		@FXML
	    private TableColumn<User, String> accountColumn;
		
		@FXML
	    private TableColumn<User, String> userNameColumn;
		
		@FXML
	    private TableColumn<User, String> statusColumn;
		
		@FXML
	    private TableColumn<User, String> sexColumn;
		
		@FXML
	    private TableColumn<User, Integer> borrowNumberColumn;
		
		@FXML
	    private TableColumn<User, Integer> forfeitColumn;
		
		@FXML
	    private TableColumn<User, Integer> borrowNumberSumColumn;
		
		@FXML
		public Button refresh2;
		
		@FXML
		public Button findBtn3;
		
		@FXML
		public Button insertBtn3;
		
		@FXML
		public Button deleteBtn3;
		
		@FXML
		public Button updateBtn3;
		
		@FXML
		public TextField Tf3;
		
		
		/**
		 * 所有借阅
		 */
		@FXML
		public TableView<Borrow> BborrowTable;
		
		@FXML
		TableColumn<Borrow,String> BidColumn;
		
		@FXML
		TableColumn<Borrow,String> BbookNameColumn;
		
		@FXML
		TableColumn<Borrow,String> BuserNameColumn;
		
		@FXML
		TableColumn<Borrow,String> BborrowDateColumn;
		
		@FXML
		TableColumn<Borrow,String> BdueDateColumn;
		
		@FXML
		TableColumn<Borrow,Boolean> BgiveColumn;
		
		@FXML
		TableColumn<Borrow,Integer> BrenewColumn;
		
		@FXML
		TableColumn<Borrow,String> BgiveBackDateColumn;
		
		@FXML
		public Button refresh3;
		
		@FXML
		public Button findBtn4;
		
		@FXML
		public Button deleteBtn4;
		
		@FXML
		public Button updateBtn4;
		
		@FXML
		public TextField Tf4;
		
		
		
		//Reference to the main application
		private MainApp mainApp;
		
		
		/**
		*The Constructor.
		*The Constructor is called before the initialize() method.
		*/
		public SystemManagerController() {
			
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
				
				showSBookDetails(null);
				//图书查询列表点击
				bookTable.getSelectionModel().selectedItemProperty().addListener(
				            (observable, oldValue, newValue) -> showSBookDetails(newValue));
				bookTable2.getSelectionModel().selectedItemProperty().addListener(
			            (observable, oldValue, newValue) -> {
			            	if(newValue == null)
			            		setId(oldValue);
			            	else {
			            		setId(newValue);
			            	}
			            });
				
				//所有图书表格
				idColumn2.setCellValueFactory(cellData -> cellData.getValue().getIdProperty());
				nameColumn2.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
				typeColumn2.setCellValueFactory(cellData -> cellData.getValue().getTypeProperty());
				authorColumn2.setCellValueFactory(cellData -> cellData.getValue().getAuthorProperty());
				publishHouseColumn2.setCellValueFactory(cellData -> cellData.getValue().getPublishHouseProperty());
				publishTimeColumn2.setCellValueFactory(cellData -> cellData.getValue().getPublishTimeProperty());
				reserveColumn2.setCellValueFactory(cellData -> cellData.getValue().getReserveProperty().asObject());
				marginColumn2.setCellValueFactory(cellData -> cellData.getValue().getMarginProperty().asObject());
				priceColumn2.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty().asObject());
				
				//所有读者表格
		        accountColumn.setCellValueFactory(cellData -> cellData.getValue().getAccountProperty());
		        userNameColumn.setCellValueFactory(cellData -> cellData.getValue().getUserNameProperty());
		        statusColumn.setCellValueFactory(cellData -> cellData.getValue().getStatusProperty());
		        sexColumn.setCellValueFactory(cellData -> cellData.getValue().getSexProperty());
		        borrowNumberColumn.setCellValueFactory(cellData -> cellData.getValue().getBorrowNumberProperty().asObject());
		        forfeitColumn.setCellValueFactory(cellData -> cellData.getValue().getForfeitProperty().asObject());
		        borrowNumberSumColumn.setCellValueFactory(cellData -> cellData.getValue().getBorrowNumberSumProperty().asObject());
		        
		     	userTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)->{
		     		if(newValue == null) {
		     			setAccount(oldValue);
		     		}else
		     			setAccount(newValue);
		     	});
		     	
		        //所有借阅表格
				BidColumn.setCellValueFactory(cellData -> cellData.getValue().getIdProperty());
				BbookNameColumn.setCellValueFactory(cellData -> cellData.getValue().getBookNameProperty());
				BuserNameColumn.setCellValueFactory(cellData -> cellData.getValue().getUserNameProperty());
				BborrowDateColumn.setCellValueFactory(cellData -> cellData.getValue().getBorrowDateProperty());
				BdueDateColumn.setCellValueFactory(cellData -> cellData.getValue().getDueDateProperty());
				BgiveColumn.setCellValueFactory(cellData -> cellData.getValue().getGiveBackProperty());
				BrenewColumn.setCellValueFactory(cellData -> cellData.getValue().getRenewProperty().asObject());
				BgiveBackDateColumn.setCellValueFactory(cellData -> cellData.getValue().getGiveBackDateProperty());
				BborrowTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)->{
					if(newValue == null) {
						setBorrowId(oldValue);
					}else {
						setBorrowId(newValue);
					}
				});
		}
		
		//图书查询列表点击事件
		private void showSBookDetails(SBook newValue) {
			if(newValue != null) {
				DId.setText(newValue.getId());
				DName.setText(newValue.getName());
			}else {
				DName.setText("");
				DId.setText("");
			}
		}
		public static void setId(Book newValue) {
			saveId = newValue.getId();
			saveName = newValue.getName();
		}
		public static String getsaveName() {
			return saveName;
		}
		public static String getsaveId() {
			return saveId;
		}
		public static void setAccount(User newValue) {
			saveId = newValue.getAccount();
		}
		public static String getsaveAccount() {
			return saveId;
		}
		public static void setBorrowId(Borrow newValue) {
			saveBorrowId = newValue.getId();
		}
		public static String getSaveBorrowId() {
			return saveBorrowId;
		}
				
		
		
		
		/**
		 * Is called by the main applicaiton to give a reference back to itself.
		 * 
		 * @param mainApp
		 */
		public void setMainApp(MainApp mainapp) {
			this.mainApp = mainapp;
			
			//所有读者表格信息
	        userTable.setItems(mainapp.getUserData());
	        
			 //图书查询表格信息
			bookTable.setItems(mainapp.getAllSBookList());
			
			//所有图书表格信息
			bookTable2.setItems(mainapp.getAllBookList());
			
			//所有借阅表格信息
			BborrowTable.setItems(mainapp.getBorrowData());
		}
		
}
