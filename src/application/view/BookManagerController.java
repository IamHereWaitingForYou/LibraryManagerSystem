package application.view;

import application.MainApp;
import application.model.Book;
import application.model.Borrow;
import application.model.SBook;
import application.model.SBorrow;
import application.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class BookManagerController {
	
	
		@FXML
		public Label quit;
		
		/**
		 * 借书
		 */
		@FXML
		public Button insertBtn;
		
		@FXML
		public Button cleanBtn;
		
		@FXML
		public TextField bookId;
		
		@FXML
		public TextField bookName;
		
		@FXML
		public TextField bookAuthor;
		
		@FXML
		public TextField bookPublishHouse;
		
		@FXML
		public TextField borrowDate;
		
		@FXML
		public TextField userId;
		
		@FXML
		public TextField userName;
		
		@FXML
		public TextField userStatus;
		
		@FXML
		public TextField userBorrowNumber;
		
		@FXML
		public TextField dueDate;
		
		/**
		 * 还书
		 */
		@FXML
		public Button ensureBtn;
		
		@FXML
		public Button cleanBtn2;
		
		@FXML
		public TextField userId2;
		
		@FXML
		public TextField userName2;
		
		@FXML
		public TextField userStatus2;
		
		@FXML
		public TextField userBorrowNumber2;
		
		/**
		 * 个人借阅
		 */
		
		@FXML
		public TableView<SBorrow> BborrowTable2;
		
		@FXML
		TableColumn<SBorrow,String> BidColumn2;
		
		@FXML
		TableColumn<SBorrow,Integer> BrenewColumn2;
		
		@FXML
		TableColumn<SBorrow,String> BbookNameColumn2;
		
		@FXML
		TableColumn<SBorrow,String> BborrowDateColumn2;
		
		@FXML
		TableColumn<SBorrow,String> BdueDateColumn2;
		
		@FXML
		public TextField DId2;
		
		@FXML
		public TextField DName2;
		
		/**
		 * 图书查询表格
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
		public Button affirmBtn;
		
		@FXML
		public TextField textTf;
		
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
		public Button insertBtn3;
		
		@FXML
		public Button deleteBtn3;
		
		@FXML
		public Button updateBtn3;
		
		@FXML
		public Button refresh1;
		
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
		static String saveId,saveName;
		
		//Reference to the main application
		private MainApp mainApp;
		
		
		/**
		*The Constructor.
		*The Constructor is called before the initialize() method.
		*/
		public BookManagerController() {
			
		}
		
		
		/**
		 * Initializes the constructor class.
		 * This method is automatically called after the fxml file has been load.
		 */
		@FXML
		private void initialize() {
			 // Initialize the person table with the two columns.
			
			//个人借阅表格
			BidColumn2.setCellValueFactory(cellData -> cellData.getValue().getIdProperty());
			BbookNameColumn2.setCellValueFactory(cellData -> cellData.getValue().getBookNameProperty());
			BborrowDateColumn2.setCellValueFactory(cellData -> cellData.getValue().getBorrowDateProperty());
			BdueDateColumn2.setCellValueFactory(cellData -> cellData.getValue().getDueDateProperty());
			BrenewColumn2.setCellValueFactory(cellData -> cellData.getValue().getRenewProperty().asObject());
			

			
			//所有读者表格
	        accountColumn.setCellValueFactory(cellData -> cellData.getValue().getAccountProperty());
	        userNameColumn.setCellValueFactory(cellData -> cellData.getValue().getUserNameProperty());
	        statusColumn.setCellValueFactory(cellData -> cellData.getValue().getStatusProperty());
	        sexColumn.setCellValueFactory(cellData -> cellData.getValue().getSexProperty());
	        borrowNumberColumn.setCellValueFactory(cellData -> cellData.getValue().getBorrowNumberProperty().asObject());
	        forfeitColumn.setCellValueFactory(cellData -> cellData.getValue().getForfeitProperty().asObject());
	        borrowNumberSumColumn.setCellValueFactory(cellData -> cellData.getValue().getBorrowNumberSumProperty().asObject());
	        
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
			
			 
			//所有借阅表格
			BidColumn.setCellValueFactory(cellData -> cellData.getValue().getIdProperty());
			BbookNameColumn.setCellValueFactory(cellData -> cellData.getValue().getBookNameProperty());
			BuserNameColumn.setCellValueFactory(cellData -> cellData.getValue().getUserNameProperty());
			BborrowDateColumn.setCellValueFactory(cellData -> cellData.getValue().getBorrowDateProperty());
			BdueDateColumn.setCellValueFactory(cellData -> cellData.getValue().getDueDateProperty());
			BgiveColumn.setCellValueFactory(cellData -> cellData.getValue().getGiveBackProperty());
			BrenewColumn.setCellValueFactory(cellData -> cellData.getValue().getRenewProperty().asObject());
			BgiveBackDateColumn.setCellValueFactory(cellData -> cellData.getValue().getGiveBackDateProperty());
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
		
		
		
		public static void setId1(Book newValue) {
			saveId = newValue.getId();
			saveName = newValue.getName();
		}
		public static String getsaveName() {
			return saveName;
		}
		public static String getsaveId() {
			return saveId;
		}
		
		
//		public void getId(Book newValue) {
//			save = newValue.getId();
//		}
//		public static String getsave() {
//			return save;
//		}
		
		/**
		 * Is called by the main applicaiton to give a reference back to itself.
		 * 
		 * @param mainApp
		 */
		public void setMainApp(MainApp mainapp) {
			this.mainApp = mainapp;
			
			// Add observable list data to the table
	        userTable.setItems(mainapp.getUserData());
	        
	        //图书查询表格信息
			bookTable.setItems(mainapp.getAllSBookList());
			
			//所有图书表格信息
			bookTable2.setItems(mainapp.getAllBookList());
			
			//所有借阅表格信息
			BborrowTable.setItems(mainapp.getBorrowData());
		}
		
}
