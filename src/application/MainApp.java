package application;
	
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import application.view.LoginController;
import application.view.Maintain2Controller;
import application.view.Maintain3Controller;
import application.view.Maintain4Controller;
import application.view.Maintain5Controlle;
import application.view.Maintain6Controlle;
import application.view.MaintainController;
import application.dao.BookDao;
import application.dao.BorrowDao;
import application.dao.UserDao;
import application.model.Book;
import application.model.Borrow;
import application.model.SBook;
import application.model.SBorrow;
import application.model.User;
import application.util.DBUtils;
import application.view.BookManagerController;
import application.view.ReaderController;
import application.view.SystemManagerController;
import application.view.register2Controller;
import application.view.registerController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class MainApp extends Application implements EventHandler<ActionEvent>{
	
	Stage primaryStage1 = new Stage();
	String stringid = null;
	//Dao实例化、全局变量
	private UserDao userDao = new UserDao();
	private BookDao bookDao = new BookDao();
	private BorrowDao borrowDao = new BorrowDao();
	
	//登录界面
	Stage primaryStage;											//登录舞台
	AnchorPane loginpane;										//登录pane
	Button login;												//登录界面登录按钮
	RadioButton readerRbtn,bookmanagerRbtn,systemmanagerRbtn;	//身份选择按钮
	ToggleGroup groupBtn;										//身份组
	TextField accountField;										//账号输入框
	PasswordField passwordField;								//密码输入框
	Label forget;												//登录界面的忘记密码
	CheckBox savePassword;										//记住密码CheckBox
	Boolean save = false;										//boolean 是否记住密码
	
	private String account;
	private String password;
	private String loginType;
	
	//修改密码
	Button sureBtn;												//确认	
	Button buttoncancel;										//取消
	
	//读者界面
	Label quit_reader;											//退出登录
	Button buttonsearch1;										//查询图书按钮
	TextField textsearch1;										//查询图书输入框
	
	//管理员共有元素
	Label quit_manager;											//退出按钮
	Button addPicture;											//添加图书		
	
	//图书管理员界面
	Button buttonsearch2;										//查询图书按钮
	TextField textsearch2;										//查询图书输入框
	TextField bookId,bookName,bookAuthor,bookPublishHouse,borrowDate;	//借书的书信息
	TextField userId,userName,userStatus,userBorrowNumber,dueDate;		//借书的用户信息
	TextField userId2,userName2,userStatus2,userBorrowNumber2,Did2,DName2;	//还书的用户信息
	
	//系统管理员界面
	Button addUser;												//添加用户按钮
	Button buttonsearch3;										//查询图书按钮
	TextField textsearch3;										//查询图书输入框
	
	//

	private ObservableList<User> userData = FXCollections.observableArrayList();//所有用户信息
	private ObservableList<Book> bookData = FXCollections.observableArrayList();//所有图书记录
	private ObservableList<SBook> pbookData = FXCollections.observableArrayList();//查阅图书记录
	private ObservableList<Borrow> borrowData = FXCollections.observableArrayList();//所有借阅记录
	private ObservableList<SBorrow> pborrowData = FXCollections.observableArrayList();//所有借阅记录
	
	public User Suser;
	
	//读者表
	TextField tsbhTf,cbsjTf,tsmcTf,clTf,tslxTf,ylTf,zzTf,yzTf,jgTf,cbsTf,grsjTf,userName3,userSex3,userType3,userId3,userAccount3,phoneNumber3;

	Label DName;
	TextArea jjTf;
	Button affirmBtn,renewBtn,ensureBtn,updateBtn,cancelBtn,deleteBtn;
	String name,borrowId,id;
	TableView<SBorrow> borrowTable,Table2;
	TableView<Book> bookTable;
	//流水号
	int borrowidnum = 210;
	TextField borrowIdTf,bookNameTf,userNameTf,renewTimeTf,returnTf,returnDateTf;
	
	//图书管理员
	Button insertBtn,cleanBtn,cleanBtn2,insertBtn3,deleteBtn3,cleanBtn3;
	
	/**
	 * start the stage.
	 *  
	 */
	@Override
	public void start(Stage primaryStage) {
		
		this.primaryStage = primaryStage;
		primaryStage.setTitle("图书管理系统");
		
		//加载页面
		Pane pane = new Pane();
		primaryStage.setScene(new Scene(pane,750,500));
		primaryStage.setResizable(false);
		primaryStage.show();
		
		//检查数据库
		loadDb();
		
		//如果cookies表中有数据，表示记住密码，直接登录
		if(userDao.getCookie() != null ) {
			String status = userDao.getCookie().getStatus();
			account = userDao.getCookie().getAccount();
			
//			Suser = userDao.getUserDate(userDao.getCookie().getAccount());
			if(status.equals("读者")) {
				showReader();
			}else if(status.equals("图书管理员")){
				showBookManager();
			}else if(status.equals("系统管理员")) {
				showSystemManager();
			}
			
			
			
		}else {
			showLogin();
		}
		
		
	}
	
	
	/**
	 * 加载数据库
	 
	 */
	private void loadDb() {
		// TODO Auto-generated method stub
		//导入数据库
		int result=DBUtils.createDB();
		Alert alert=new Alert(AlertType.NONE);
		if (result==1) {
			alert.setAlertType(AlertType.INFORMATION);
			alert.setHeaderText("软件在本地第一次运行，创建数据库完成");
			alert.show();
		}
		else if(result==-1){
			alert.setAlertType(AlertType.ERROR);
			alert.setHeaderText("数据创建失败！某些功能不能使用");
			alert.show();
		}
		
	}

	
	/**
	 * main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		//Add some sample data
		launch(args);
	}
	
	
	/**
	 * MainApp构造方法
	 */
	public MainApp() {

	}
	
	
	/**
	 * 点击事件
	 */
	@Override
	public void handle(ActionEvent e) {
		if(e.getSource() == login) {
			
			account = accountField.getText();
			password = passwordField.getText();
			
			//获得cookies表数据，如果存在数据，则直接登录
			save = savePassword.isSelected();
			
			//获取读者身份
	        RadioButton tempRadioButton = (RadioButton)groupBtn.getSelectedToggle();
	        loginType = tempRadioButton.getText();
	        
			if(userDao.checkPassword(account, password,loginType)) {
				if(loginType.equals("读者")) {
					showReader();
					getBorrow();
				}
				else if(loginType.equals("图书管理员")) {
					showBookManager();
				}
				else if(loginType.equals("系统管理员")) {
					showSystemManager();
				}
				if(save) {
					userDao.setCookie(account, password, loginType);
				}else {
					userDao.deleteCookie();
				}
			}else {
				Alert alert = new Alert(Alert.AlertType.ERROR,"账号或密码错误、请重新输入");
				alert.setHeaderText("登录错误");
				alert.showAndWait();
			}
		}
	}

	
	/**
	 * load the Login overview.
	 * 
	 */
	public void showLogin() {
		try {
            //Load Login overview.
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(MainApp.class.getResource("view/Login.fxml"));
           loginpane = (AnchorPane) loader.load(); 
           
           // Show the scene containing the login layout.
           Scene scene = new Scene(loginpane);
           primaryStage.setScene(scene);
           
           
           //Give the controller access to the main app.
           LoginController controller = loader.getController();
           controller.setMainApp(this);
           primaryStage.show();
           
           //Give the reference of button.
           accountField = controller.accountField;
           passwordField = controller.passwordField;
           savePassword = controller.savePassword;
           readerRbtn = controller.readerRbtn;
           bookmanagerRbtn = controller.bookmanagerRbtn;
           systemmanagerRbtn = controller.systemmanagerRbtn;
           
           groupBtn = new ToggleGroup();
           readerRbtn.setToggleGroup(groupBtn);
           bookmanagerRbtn.setToggleGroup(groupBtn);
           systemmanagerRbtn.setToggleGroup(groupBtn);
           
           login = controller.login;
           forget = controller.forget;
           
           //监听按钮login,使用handle处理事件
           login.setOnAction(this);
           
           //忘记密码
           forget.setOnMousePressed(e -> {
    			showRegister();
    		});
           
           
           
       } catch (IOException e) {
           e.printStackTrace();
       }
	}
	/**
	 * 修改密码1
	 * 
	 */
	public void showRegister() {
		try {
            //Load Reader overview.
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(MainApp.class.getResource("view/register.fxml"));
           AnchorPane RegisterPane = (AnchorPane) loader.load(); 
           
           // Show the scene containing the readerPane layout.
          
           Scene scene = new Scene(RegisterPane);
           primaryStage1.setScene(scene);
           primaryStage1.show();
           
           //Give the controller access to the main app.
           registerController controller = loader.getController();
           controller.setMainApp(this);
           sureBtn = controller.buttonsure;
           buttoncancel = controller.buttoncancel;
           TextField textXue = controller.textXue;
           TextField textidentity = controller.textidentity;
           //确认按钮
           sureBtn.setOnAction(e -> {
//        	   System.out.println(userDao.getUserDate(textXue.getText()).getAccount());
//        	   System.out.println(userDao.getUserDate(textXue.getText()).getIcNumber());
//        	   System.out.println("学号：" + textXue.getText());
//        	   System.out.println("身份证：" + textidentity.getText());
        	   if(userDao.getUserDate(textXue.getText()).getAccount().equals(textXue.getText()) &&
        			    userDao.getUserDate(textXue.getText()).getIcNumber().equals(textidentity.getText())) {
        		   account = textXue.getText();
        		   showRegister2();
        	   }else {
        		   Alert alert=new Alert(AlertType.NONE);
        		   alert.setAlertType(AlertType.WARNING);
   			alert.setHeaderText("身份证号或账号不对");
   			alert.show();
        	   }
           });
           
           //取消按钮
           buttoncancel.setOnAction(e -> {
        	   primaryStage1.close();
           });

		} catch (IOException e) {
           e.printStackTrace();
       }
	}
	
	
	/**
	 * 修改密码2
	 */
	public void showRegister2() {
		try {
            //Load Reader overview.
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(MainApp.class.getResource("view/register2.fxml"));
           AnchorPane RegisterPane = (AnchorPane) loader.load(); 
           
           // Show the scene containing the readerPane layout.
           Stage primaryStage2 = new Stage();
           Scene scene = new Scene(RegisterPane);
           primaryStage2.setScene(scene);
           primaryStage2.show();
           
           //Give the controller access to the main app.
           register2Controller controller = loader.getController();
           controller.setMainApp(this);
           
           TextField textNew = controller.textNew;
           TextField textNew1 = controller.textNew1;
           Button buttonsure2 = controller.buttonsure2;
           buttonsure2.setOnAction(e -> {
        	   if(textNew.getText().equals(textNew1.getText())) {
        		   userDao.changePassword(account,textNew1.getText());
        		   primaryStage2.close();
        		   
        	   primaryStage1.close();
           }else {
    		   Alert alert=new Alert(AlertType.NONE);
    		   alert.setAlertType(AlertType.WARNING);
			alert.setHeaderText("身份证号或账号不对");
			alert.show();
    	   }
        	   
           });
           
       } catch (IOException e) {
           e.printStackTrace();
       }
	}
	
	
	/**
	 * load the Reader's overview.
	 * pbookData用于图书查询
	 */
	public void showReader() {
		try {
            //Load Reader overview.
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(MainApp.class.getResource("view/Reader.fxml"));
           AnchorPane readerPane = (AnchorPane) loader.load(); 
           
           // Show the scene containing the readerPane layout.
           Scene scene = new Scene(readerPane);
           primaryStage.setScene(scene);
           primaryStage.show();
           
           //Give the controller access to the main app.
           ReaderController controller = loader.getController();
           getSuser(account);
           controller.setMainApp(this);
           
           //Give the reference of UI
           TableView<SBook> Table;
           quit_reader = controller.quit;
           
       	   //图书查询先获取所有图书
       	   Table = controller.bookTable;
       	   pbookData = bookDao.getAllSBook();
       	   Table.setItems(pbookData);
       	   
       	   //退出登录
           quit_reader.setOnMousePressed(e -> {
      			showLogin();
      			userDao.deleteCookie();
      		});
           
           //图书查询按钮
           buttonsearch1 = controller.findBtn;
           textsearch1 = controller.textTf;
       	   affirmBtn = controller.affirmBtn;
       	   
       	   renewBtn = controller.renewBtn;
       	   Label DId = controller.DId;
       	   Label DName = controller.DName;
       	   	DName.setText("");
       	   	DId.setText("");
       	   	
       	   Table.getSelectionModel().selectedItemProperty().addListener(
	            (observable, oldValue, newValue) -> {
	            	if(newValue != null) {
	        			DId.setText(newValue.getId());
	        			DName.setText(newValue.getName());
	        		}else {
	        			DName.setText("");
	        			DId.setText("");
	        		}
	            	name = DName.getText();
	            });
       	
      	   //查询图书
           buttonsearch1.setOnMouseClicked(e -> {
        	   	String text = textsearch1.getText();
      			pbookData = bookDao.searchBook(text);
      			Table.setItems(pbookData);
           });
           
       	   //确认图书
       	   affirmBtn.setOnMouseClicked(e -> {
     			showMaintain3();
     		});
       	   
       	 //续借功能
       	   borrowTable = controller.borrowTable;
       		borrowTable.getSelectionModel().selectedItemProperty().addListener(
		            (observable, oldValue, newValue) -> {
		            	if(newValue == null)
		            		stringid = oldValue.getId();
		            	else 
		            		stringid = newValue.getId();
		            });
       		
       	   renewBtn.setOnMouseClicked(e -> {
       		   borrowDao.renewBook(stringid);
        	   pborrowData = borrowDao.getAllSborrowData(Suser.getUserName());
        	   borrowTable.setItems(pborrowData);
       	   });
       	   
 
       } catch (IOException e) {
           e.printStackTrace();
       }
	}
	
	
	public void showMaintain3() {
		try {
            //Load Reader overview.
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(MainApp.class.getResource("view/Maintain3.fxml"));
           AnchorPane Maintain3Pane = (AnchorPane)loader.load();
           
           // Show the scene containing the readerPane layout.
           Scene scene = new Scene(Maintain3Pane);
           primaryStage1.setScene(scene);
           primaryStage1.show();
           	
           //Give the controller access to the main app.

           Maintain3Controller controller = loader.getController();
           tsbhTf = controller.tsbhTf;
           cbsjTf = controller.cbsjTf;
           tsmcTf = controller.tsmcTf;
           clTf = controller.clTf;
           tslxTf = controller.tslxTf;
           ylTf = controller.ylTf;
           zzTf = controller.zzTf;
           yzTf = controller.yzTf;
           jgTf = controller.jgTf;
           grsjTf = controller.grsjTf;
           cbsTf = controller.cbsTf;
           jjTf = controller.jjTf;
           showBookDetails(bookDao.searchBook(name).get(0));

           controller.setMainApp(this);

           //Give the reference of UI
       } catch (IOException e) {
           e.printStackTrace();
       }
	}
	
	
	private void showBookDetails(SBook sBook) {
		if(sBook!=null) {
			tsbhTf.setText(sBook.getId());
			tsmcTf.setText(sBook.getName());
			tslxTf.setText(sBook.getType());
			zzTf.setText(sBook.getAuthor());
			yzTf.setText(sBook.getTranslator());
			cbsTf.setText(sBook.getPublishHouse());
			cbsjTf.setText(sBook.getPublishTime());
			clTf.setText(sBook.getReserve()+"");
			ylTf.setText(sBook.getMargin()+"");
			jgTf.setText(sBook.getPrice()+"");
			jjTf.setText(sBook.getIntroduction());
//			jjTf.setWrapText(true);
			grsjTf.setText(sBook.getShopin());
		}
		else {
			tsbhTf.setText("");
			tsmcTf.setText("");
			tslxTf.setText("");
			zzTf.setText("");
			yzTf.setText("");
			cbsTf.setText("");
			cbsjTf.setText("");
			clTf.setText("");
			ylTf.setText("");
			jgTf.setText("");
			jjTf.setText("");
			grsjTf.setText("");
		}
		// TODO 自动生成的方法存根

	}

	/**
	 * load the BookManager's overview.
	 * pbookData用于图书查询、bookData用于所有图书显示
	 */
	public void showBookManager() {
		try {
            //Load Manager overview.
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(MainApp.class.getResource("view/BookManager.fxml"));
           AnchorPane managerPane = (AnchorPane) loader.load(); 
           
           // Show the scene containing the managerPane layout.
           Scene scene = new Scene(managerPane);
           primaryStage.setScene(scene);
           primaryStage.show();
           
           //Give the controller access to the main app.
           BookManagerController controller = loader.getController();
           controller.setMainApp(this);
           
           //Give the reference of UI
           
           //退出登录
           quit_manager = controller.quit;
           quit_manager.setOnMouseClicked(e -> {
     			showLogin();
     			userDao.deleteCookie();
     		});
           
           //借书
           Alert alert=new Alert(AlertType.NONE);
           bookId = controller.bookId;
           bookName = controller.bookName;
           bookAuthor = controller.bookAuthor;
           bookPublishHouse = controller.bookPublishHouse;
           borrowDate = controller.borrowDate;
           userId = controller.userId;
           userName = controller.userName;
           userStatus = controller.userStatus;
           userBorrowNumber = controller.userBorrowNumber;
           dueDate = controller.dueDate;
           
           insertBtn = controller.insertBtn;
           insertBtn.setOnMouseClicked(e -> {
        	  
        	   if(bookName.getText().equals("") || userName.getText().equals("")) {
        		   alert.setAlertType(AlertType.ERROR);
         		   alert.setHeaderText("借书失败，信息未填完整");
         		   alert.show();
        	   }else {
        		   if(!borrowDao.isBorrowRepeted(bookName.getText(),userName.getText())) {
        			   bookDao.bookNumDecrease(bookId.getText());
        			   borrowDao.insertborrowData(borrowidnum+"", userName.getText(), bookName.getText(), borrowDate.getText(), dueDate.getText());
        			   userDao.setBorrowNumberIncrease(userId.getText());
              		  borrowidnum++;
            		   
             		   alert.setAlertType(AlertType.INFORMATION);
             		   alert.setHeaderText("借书成功");
             		   alert.show();
             		   cleanInformation1();//在弹窗点击确认时再清理数据
             		  userBorrowNumber.setText(String.valueOf(userDao.getUserDate(userId.getText()).getBorrowNumber()));
        		   }else {
        			   alert.setAlertType(AlertType.INFORMATION);
             		   alert.setHeaderText("借书失败，同本书只能借一次");
             		   alert.show();
        		   }
         		 
        	   }
    		});
           cleanBtn = controller.cleanBtn;
           cleanBtn.setOnMouseClicked(e -> {
        	   cleanInformation();
           });
           
           //图书查询--确认图书
       	   affirmBtn = controller.affirmBtn;
       	   
       	   affirmBtn.setOnMouseClicked(e -> {
       		   name = controller.DName.getText();
     			showMaintain3();
     		});
           
           bookId.setOnKeyReleased(e -> {
        	   Book book = bookDao.searchBookById(bookId.getText());
        	   if(book!=null) {
        		   bookName.setText(book.getName());
            	   bookAuthor.setText(book.getAuthor());
            	   bookPublishHouse.setText(book.getPublishHouse());
            	   SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            	   borrowDate.setText(df.format(new Date()));
        	   }else {
        		   bookName.setText("");
            	   bookAuthor.setText("");
            	   bookPublishHouse.setText("");
            	   borrowDate.setText("");
        	   }
           });
           
           userId = controller.userId;
           userName = controller.userName;
           userStatus =controller.userStatus;
           userBorrowNumber = controller.userBorrowNumber;
           dueDate = controller.dueDate;
           userId.setOnKeyReleased(e -> {
        	   User user = userDao.getUserDate(userId.getText());
        	   if(user != null) {
            	   userName.setText(user.getUserName());
            	   userStatus.setText(user.getStatus());
            	   userBorrowNumber.setText(String.valueOf(user.getBorrowNumber()));
            	   SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            	   Date date = new Date();
            	   date.setMonth(date.getMonth()+1);
            	   dueDate.setText(df.format(date));
        	   }else {
            	   userName.setText("");
            	   userStatus.setText("");
            	   userBorrowNumber.setText("");
            	   dueDate.setText("");
        	   }
        	   
           });
           
           //还书
           userId2 = controller.userId2;
           userName2 = controller.userName2;
           userStatus2 = controller.userStatus2;
           userBorrowNumber2 = controller.userBorrowNumber2;
           

           Table2 = controller.BborrowTable2;

           userId2.setOnKeyReleased(e -> {
               User user2 = userDao.getUserDate(userId2.getText());
        	   if(user2 != null) {
        		   userName2.setText(user2.getUserName());
        		   userStatus2.setText(user2.getStatus());
        		   userBorrowNumber2.setText(String.valueOf(user2.getBorrowNumber()));
        		   Table2.setItems(borrowDao.getPersonalBorrowDateNoBack(userName2.getText()));
        	   }else{
        		   showBorrowDetails(null);
        		   Table2.setItems(null);
        		   userName2.setText("");
        		   userStatus2.setText("");
        		   userBorrowNumber2.setText("");
        	   }
           });
           Did2 = controller.DId2;
           DName2 = controller.DName2;
           
           ensureBtn = controller.ensureBtn;
           ensureBtn.setOnMouseClicked(e ->{
        	   if(Did2.getText().equals("") || DName2.getText().equals("")) {
        		   alert.setAlertType(AlertType.WARNING);
         		   alert.setHeaderText("还书失败");
         		   alert.show();
        	   }else {
        		   borrowDao.returnBook(Did2.getText());
        		   bookDao.bookNumIncrease(DName2.getText());
        		   alert.setAlertType(AlertType.INFORMATION);
         		   alert.setHeaderText(DName2.getText()+" 还书成功");
         		   alert.show();        			   
         		  userDao.setBorrowNumberDecrease(userId2.getText());
         		  userBorrowNumber2.setText(String.valueOf(userDao.getUserDate(userId2.getText()).getBorrowNumber()));
         		  Table2.setItems(borrowDao.getPersonalBorrowDateNoBack(userName2.getText()));
         		 Did2.setText("");
         		 DName2.setText("");
        	   }
        	  
           });
           cleanBtn2 = controller.cleanBtn2;
           cleanBtn2.setOnMouseClicked(e->{
        	   cleanInformation2();
           });
	
			//图书查询列表点击
			Table2.getSelectionModel().selectedItemProperty().addListener(
			            (observable, oldValue, newValue) -> showBorrowDetails(newValue));
           
           
           //图书查询先获取所有图书
           TableView<SBook> Table = controller.bookTable;
       	   pbookData = bookDao.getAllSBook();
       	   Table.setItems(pbookData);
       	   
       	   //查询图书
       	   buttonsearch2 = controller.findBtn;
       	   textsearch2 = controller.textTf;
           buttonsearch2.setOnMouseClicked(e -> {
       	   	String text = textsearch2.getText();
     		pbookData = bookDao.searchBook(text);
     		Table.setItems(pbookData);
           });
           
           //所有图书	--刷新
           Button refresh1 = controller.refresh1;
           TableView<Book> bookTable2 = controller.bookTable2;
           refresh1.setOnAction(e ->{
        	   bookTable2.setItems(getAllBookList());
           });
           
           //所有读者	--刷新 
           Button refresh2 = controller.refresh2;
           TableView<User> userTable = controller.userTable;
           refresh2.setOnAction(e ->{
        	   userTable.setItems(getUserData());
           });
           
           //所有借阅信息	--刷新 
           Button refresh3 = controller.refresh3;
           TableView<Borrow> BborrowTable = controller.BborrowTable;
           refresh3.setOnAction(e ->{
        	   BborrowTable.setItems(getBorrowData());
           });
           
           //所有图书	--添加图书
           addPicture = controller.insertBtn3;
           addPicture.setOnMouseClicked(e -> {
     			showMaintain();
     		});
           
           //所有图书列表监听事件
           bookTable2.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,newValue)->{
        	   if(newValue==null)
        		   BookManagerController.setId1(oldValue);
       		else
       		 BookManagerController.setId1(newValue); 
        	  
           });

           //删除图书
           bookTable = controller.bookTable2;
           deleteBtn = controller.deleteBtn3;
    	   
           deleteBtn.setOnMouseClicked(e->{
        	   	bookDao.deleteBook(BookManagerController.getsaveId());
        	    bookTable2.setItems(getAllBookList());
           });
           
           //修改书籍
	     
           updateBtn = controller.updateBtn3;

           updateBtn.setOnMouseClicked(e->{
               id = BookManagerController.getsaveId();
        	  showMaintain4(id);

           });
           

  		 
           
       } catch (IOException e) {
           e.printStackTrace();
       }
	}
	public void cleanInformation2() {
		userId2.setText("");
		userName2.setText("");
		userStatus2.setText("");
		userBorrowNumber2.setText("");
		Did2.setText("");
		DName2.setText("");
		Table2.setItems(null);
	}
	public void cleanInformation() {
		bookId.setText("");
		userId.setText("");
		bookName.setText("");
		userName.setText("");
		bookAuthor.setText("");
		userStatus.setText("");
		bookPublishHouse.setText("");
		userBorrowNumber.setText("");
		borrowDate.setText("");
		dueDate.setText("");
		
	}
	
	public void cleanInformation1() {
		bookId.setText("");
		bookName.setText("");
		bookAuthor.setText("");
		bookPublishHouse.setText("");
		borrowDate.setText("");
	}
	/**
	 * load the SystemManager's overview.
	 */
	public void showSystemManager() {
		try {
            //Load Manager overview.
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(MainApp.class.getResource("view/SystemManager.fxml"));
           AnchorPane managerPane = (AnchorPane) loader.load(); 
           
           // Show the scene containing the managerPane layout.
           Scene scene = new Scene(managerPane);
           primaryStage.setScene(scene);
           primaryStage.show();
           
           //Give the controller access to the main app.
           SystemManagerController controller = loader.getController();
           controller.setMainApp(this);
           
           //Give the reference of UI
           TableView<SBook> Table = controller.bookTable;
           TableView<Book> AllBookTable = controller.bookTable2;
           TableView<User> AllUserTable = controller.userTable;
           TableView<Borrow> AllBorrowTable = controller.BborrowTable;
           quit_manager = controller.quit;
           buttonsearch3 = controller.findBtn;
           textsearch3 = controller.textTf;
           Button allBookSearch = controller.findBtn2;
           Button allUserSearch = controller.findBtn3;
           Button allBorrowSearch = controller.findBtn4;
       	   

       	   
       	   //退出登录
           quit_manager.setOnMouseClicked(e -> {
     			showLogin();
     			userDao.deleteCookie();
     		});
           
           //查询图书
           buttonsearch3.setOnMouseClicked(e -> {
          	   	String text = textsearch3.getText();
        		pbookData = bookDao.searchBook(text);
        		Table.setItems(pbookData);
             });
           
           //所有查询图书
           allBookSearch.setOnMouseClicked(e -> {
        	   TextField allBookText = controller.Tf2;
        		bookData = bookDao.searchAllBook(allBookText.getText());
        		AllBookTable.setItems(bookData);
             });
           
           //查询读者
           allUserSearch.setOnMouseClicked(e -> {
           	   TextField allUserText = controller.Tf3;
        		userData = userDao.getUserDataInList(allUserText.getText());
        		AllUserTable.setItems(userData);
             });
           
           //查询借阅记录
           allBorrowSearch.setOnMouseClicked(e -> {
           	   TextField allBorrowText = controller.Tf4;
        		borrowData = borrowDao.getAllBorrow(allBorrowText.getText());
        		AllBorrowTable.setItems(borrowData);
             });
           
           //添加图书
           addPicture = controller.insertBtn2;
           addPicture.setOnMouseClicked(e -> {
     			showMaintain();
     		});
           
           //添加读者
           addUser = controller.insertBtn3;
           addUser.setOnAction(e -> {
        	   showMaintain2();
           });
           
    	   //所有图书	--刷新
           Button refresh1 = controller.refresh1;
           TableView<Book> bookTable2 = controller.bookTable2;
           refresh1.setOnAction(e ->{
        	   bookTable2.setItems(getAllBookList());
           });
           
           
           //所有读者	--刷新 
           Button refresh2 = controller.refresh2;
           TableView<User> userTable = controller.userTable;
           refresh2.setOnAction(e ->{
        	   userTable.setItems(getUserData());
           });
           
           //所有借阅信息	--刷新 
           Button refresh3 = controller.refresh3;
           TableView<Borrow> BborrowTable = controller.BborrowTable;
           refresh3.setOnAction(e ->{
        	   BborrowTable.setItems(getBorrowData());
           });
           
           
         //查书界面
           DName = controller.DName;
       	   affirmBtn = controller.affirmBtn;
       	   affirmBtn.setOnMouseClicked(e -> {
       		   name = controller.DName.getText();
     			showMaintain3();
     		});
       	   
       	   //所有图书
       	   insertBtn = controller.insertBtn2;
       	   deleteBtn = controller.deleteBtn2;
       	   updateBtn = controller.updateBtn2;
       	   bookTable = controller.bookTable2;
       	   insertBtn.setOnMouseClicked(e->{
     			showMaintain();
       	   });
       	   deleteBtn.setOnMouseClicked(e->{
       	   	bookDao.deleteBook(SystemManagerController.getsaveId());
       	   	bookTable2.setItems(getAllBookList());
       	   });
       	   updateBtn.setOnMouseClicked(e->{
       		  id = SystemManagerController.getsaveId();
         	  showMaintain4(id);
       	   });
       	   
       	   //所有读者
       	   insertBtn = controller.insertBtn3;
       	   deleteBtn = controller.deleteBtn3;
       	   updateBtn = controller.updateBtn3;

       	   deleteBtn.setOnMouseClicked(e->{
       		   userDao.deleteUser(SystemManagerController.getsaveAccount());
       		   userTable.setItems(getUserData());
       	   });
       	   updateBtn.setOnMouseClicked(e->{
       		   showMaintain5(SystemManagerController.getsaveAccount());
       	   });
       	   //所有借阅记录
       	   deleteBtn = controller.deleteBtn4;
       	   updateBtn = controller.updateBtn4;
       	   deleteBtn.setOnMouseClicked(e->{
       		   borrowDao.deleteborrowData(SystemManagerController.getSaveBorrowId());
       		   BborrowTable.setItems(getBorrowData());
       	   });
       	   updateBtn.setOnMouseClicked(e->{
//       		   System.out.println(SystemManagerController.getSaveBorrowId());
       		   showMaintain6(SystemManagerController.getSaveBorrowId());
       		   BborrowTable.setItems(getBorrowData());
       	   });
       	   
       	   
           
       } catch (IOException e) {
           e.printStackTrace();
       }
	}
	

	/**
	 * 显示图书信息维护
	 * 
	 */
	public void showMaintain() {
		try {
            //Load Reader overview.
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(MainApp.class.getResource("view/Maintain.fxml"));
           AnchorPane MaintainPane = (AnchorPane) loader.load(); 
           
           // Show the scene containing the readerPane layout.
           Stage primaryStage1 = new Stage();
           Scene scene = new Scene(MaintainPane);
           primaryStage1.setResizable(false);
           primaryStage1.setScene(scene);
           primaryStage1.show();
           
           //Give the controller access to the main app.
           MaintainController controller = loader.getController();
           controller.setMainApp(this);


           tsbhTf = controller.tsbhTf;
           cbsjTf = controller.cbsjTf;
           tsmcTf = controller.tsmcTf;
           clTf = controller.clTf;
           tslxTf = controller.tslxTf;
           ylTf = controller.ylTf;
           zzTf = controller.zzTf;
           yzTf = controller.yzTf;
           jgTf = controller.jgTf;
           grsjTf = controller.grsjTf;
           cbsTf = controller.cbsTf;
           jjTf = controller.jjTf;
           affirmBtn = controller.affirmBtn5;
           affirmBtn.setOnMouseClicked(e->{
        	   bookDao.insertBook(tsbhTf.getText(),tsmcTf.getText(),tslxTf.getText(),
        			   zzTf.getText(),yzTf.getText(),cbsTf.getText(), cbsjTf.getText(),
        			   Integer.valueOf(clTf.getText()),Integer.valueOf(ylTf.getText()),
        			   Double.valueOf(jgTf.getText()), jjTf.getText(),grsjTf.getText());
        	   primaryStage1.close();
           });
           cleanBtn = controller.cleanBtn5;
           cleanBtn.setOnMouseClicked(e->{
        	   cleanInformation3();
           });
           //Give the reference of UI
//           addPicture = controller.addPicture;
//           addPicture.setOnMousePressed(e -> {
//     			showMaintain();
//     		});
//           
       } catch (IOException e) {
           e.printStackTrace();
       }
	}
	public void showMaintain4(String id) {
		try {
            //Load Reader overview.
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(MainApp.class.getResource("view/Maintain4.fxml"));
           AnchorPane Maintain4Pane = (AnchorPane) loader.load(); 
           
           // Show the scene containing the readerPane layout.
           Stage primaryStage1 = new Stage();
           Scene scene = new Scene(Maintain4Pane);
           primaryStage1.setResizable(false);
           primaryStage1.setScene(scene);
           primaryStage1.show();
           
           //Give the controller access to the main app.
           Maintain4Controller controller = loader.getController();
           controller.setMainApp(this);


           tsbhTf = controller.tsbhTf;
           cbsjTf = controller.cbsjTf;
           tsmcTf = controller.tsmcTf;
           clTf = controller.clTf;
           tslxTf = controller.tslxTf;
           ylTf = controller.ylTf;
           zzTf = controller.zzTf;
           yzTf = controller.yzTf;
           jgTf = controller.jgTf;
           grsjTf = controller.grsjTf;
           cbsTf = controller.cbsTf;
           jjTf = controller.jjTf;
           affirmBtn = controller.affirmBtn6;
           
           showBookDetails(bookDao.searchBook(id).get(0));
           
           affirmBtn.setOnMouseClicked(e->{
        	   try {
        		bookDao.deleteBook(id);
				bookDao.insertBook(tsbhTf.getText(),tsmcTf.getText(),tslxTf.getText(),
						   zzTf.getText(),yzTf.getText(),cbsTf.getText(), cbsjTf.getText(),
						   Integer.valueOf(clTf.getText()),Integer.valueOf(ylTf.getText()),
						   Double.valueOf(jgTf.getText()), jjTf.getText(),grsjTf.getText());
	        	   primaryStage1.close();
			} catch (NumberFormatException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
           });
           cancelBtn = controller.cancelBtn6;
           cancelBtn.setOnMouseClicked(e->{
        	   primaryStage1.close();
           });
           //Give the reference of UI
//           addPicture = controller.addPicture;
//           addPicture.setOnMousePressed(e -> {
//     			showMaintain();
//     		});
//           
       } catch (IOException e) {
           e.printStackTrace();
       }
	}
	
	public void cleanInformation3() {
		tsbhTf.setText("");
		cbsjTf.setText("");
		tsmcTf.setText("");
		clTf.setText("");
		tslxTf.setText("");
		ylTf.setText("");
		zzTf.setText("");
		yzTf.setText("");
		jgTf.setText("");
		grsjTf.setText("");
	}

	/**
	 * 用户添加窗口
	 */
	private void showMaintain2() {
		// TODO Auto-generated method stub
		try {
            //Load Reader overview.
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(MainApp.class.getResource("view/Maintain2.fxml"));
           AnchorPane MaintainPane = (AnchorPane) loader.load(); 
           
           // Show the scene containing the readerPane layout.
           Stage primaryStage1 = new Stage();
           Scene scene = new Scene(MaintainPane);
           primaryStage1.setResizable(false);
           primaryStage1.setScene(scene);
           primaryStage1.show();
           
           //Give the controller access to the main app.
           Maintain2Controller controller = loader.getController();
           controller.setMainApp(this);
           
           //Give the reference of UI
           affirmBtn = controller.affirmBtn;
           buttoncancel = controller.cancelBtn;
           
           userAccount3 = controller.userAccount;
           userSex3 = controller.userSex;
           userId3 = controller.userId;
           userName3 = controller.userName;
           userType3 = controller.userType;
           phoneNumber3 = controller.phoneNumber;
           
           cancelBtn = controller.cancelBtn;
           
           affirmBtn.setOnMousePressed(e -> {
        	   userDao.addUser(userAccount3.getText(), userName3.getText(),
        			   userType3.getText(), userSex3.getText(),
        			   userId3.getText(),phoneNumber3.getText());
        	   primaryStage1.close();
     		});
           cancelBtn.setOnAction(e -> {
        	   primaryStage1.close();
           });
           
       } catch (IOException e) {
           e.printStackTrace();
       }
	}
	
	private void showMaintain5(String account) {
		// TODO Auto-generated method stub
		try {
            //Load Reader overview.
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(MainApp.class.getResource("view/Maintain5.fxml"));
           AnchorPane MaintainPane = (AnchorPane) loader.load(); 
           
           // Show the scene containing the readerPane layout.
           Stage primaryStage1 = new Stage();
           Scene scene = new Scene(MaintainPane);
           
           primaryStage1.setResizable(false);
           primaryStage1.setScene(scene);
           primaryStage1.show();
           
           //Give the controller access to the main app.
           Maintain5Controlle controller = loader.getController();
           controller.setMainApp(this);
           
           //Give the reference of UI
           affirmBtn = controller.affirmBtn;
           buttoncancel = controller.cancelBtn;
           userAccount3 = controller.userAccount;
           userSex3 = controller.userSex;
           userId3 = controller.userId;
           userName3 = controller.userName;
           userType3 = controller.userType;
           cancelBtn = controller.cancelBtn;
           phoneNumber3 = controller.phoneNumber;
           showUserDate(userDao.getUserDate(account));
           affirmBtn.setOnAction(e -> {
        	   userDao.upDateUser(userAccount3.getText(), userName3.getText(),
        			   userType3.getText(), userSex3.getText(),
        			   phoneNumber3.getText(),userId3.getText());
        	   primaryStage1.close();
     		});
           cancelBtn.setOnAction(e -> {
        	 
//        	   primaryStage1.close();
           });
           
          
           
       } catch (IOException e) {
           e.printStackTrace();
       }
	}
	
	public void showUserDate(User value) {
		if(value!=null) {
		userAccount3.setText(value.getAccount());
		userSex3.setText(value.getSex());
		userId3.setText(value.getIcNumber());
		userName3.setText(value.getUserName());
		phoneNumber3.setText(value.getPhoneNumber());
		userType3.setText(value.getStatus());
		}
		else {
			userAccount3.setText("");
			userSex3.setText("");
			userId3.setText("");
			userName3.setText("");
			phoneNumber3.setText("");
			userType3.setText("");
		}
	}
	
	private void showMaintain6(String borrowId) {

		// TODO Auto-generated method stub
		try {
            //Load Reader overview.
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(MainApp.class.getResource("view/Maintain6.fxml"));
           AnchorPane MaintainPane = (AnchorPane) loader.load(); 
           
           // Show the scene containing the readerPane layout.
           Stage primaryStage1 = new Stage();
           Scene scene = new Scene(MaintainPane);
           primaryStage1.setResizable(false);
           primaryStage1.setScene(scene);
           primaryStage1.show();
           
           //Give the controller access to the main app.
           Maintain6Controlle controller = loader.getController();
           controller.setMainApp(this);
           borrowIdTf = controller.borrowIdTf;
           bookNameTf = controller.bookNameTf;
           userNameTf = controller.userNameTf;
           renewTimeTf = controller.renewTimeTf;
           returnTf = controller.returnTf;
           returnDateTf = controller.returnDateTf;
           affirmBtn = controller.affirmBtn;
           cancelBtn = controller.cancelBtn;
           showBorrowDate(borrowDao.getBorrowDate(borrowId).get(0));
           cancelBtn.setOnMouseClicked(e->{
        	   primaryStage1.close();
           });
           affirmBtn.setOnMouseClicked(e->{
        	   borrowDao.updateBorrowData(borrowIdTf.getText(),bookNameTf.getText(),userNameTf.getText(),
        			   Integer.valueOf(renewTimeTf.getText()), Boolean.getBoolean(returnTf.getText()), returnDateTf.getText());
       		   primaryStage1.close();
           });
		}catch (IOException e) {
	           e.printStackTrace();
	       }

}
	
	public void showBorrowDate(SBorrow newValue) {
		if(newValue!=null) {
			borrowIdTf.setText(newValue.getId());
			bookNameTf.setText(newValue.getBookName());
			userNameTf.setText(newValue.getUserName());
			renewTimeTf.setText(newValue.getRenew()+"");
			returnTf.setText(newValue.getGiveBack()+"");
			returnDateTf.setText(newValue.getGiveBackDate());
		}
		else {
			borrowIdTf.setText("");
			bookNameTf.setText("");
			userNameTf.setText("");
			renewTimeTf.setText("");
			returnTf.setText("");
			returnDateTf.setText("");
		}
	}
	/**
	 * 获得当前用户的集合类
	 * @param Saccount
	 * @return
	 */
	public User getSuser(String Saccount){
		Suser = userDao.getUserDate(Saccount);
		return Suser;
	}
	
	
	/**
	 * 获得当前用户名
	 */
	public String getName() {
		return Suser.getUserName();
	}
	
	
	/**
	 * 获得当前用户性别
	 * @return
	 * 
	 */
	public String getSex() {
		return Suser.getSex();
	}
	
	
	/**
	 * 获得当前用户罚金
	 * @return
	 */
	public String getForfeit() {
		return String.valueOf(Suser.getForfeit());
	}
	
	
	/**
	 * 可借数量
	 * @return
	 */
	public int getBorrows() {
		return Suser.getBorrowNumber() - Suser.getBorrowNumberSum();
	}
	
	
	/**
	 * 获得当前账号
	 * 
	 */
	public String getAccount() {
		return this.account;
	}
	
	
	/**
	 * 获得当前账号的身份
	 * 
	 */
	public String getStatus() {
		return Suser.getStatus();
	}
	
	
	/**
	 * 更改当前的账号
	 */
	public void setAccount(String account) {
		this.account = account;
	}
	
	
	/**
	 * 获得所有用户信息
	 * Returns the data as an observable list of Users.
	 */
	public ObservableList<User> getUserData(){
		userData = userDao.getAllReaderDate();
		return userData;
	}

	
	/**
	 * 获得所有图书
	 * @return ObservableList<Book> 
	 */
	public ObservableList<Book> getAllBookList(){
		bookData = bookDao.getAllBook();
		return bookData;
	}
	
	
	/**
	 * 获得所有图书
	 * @return ObservableList<SBook> 
	 */
	public ObservableList<SBook> getAllSBookList(){
		return pbookData;
	}

	
	/**
	 * 获得图书查询记录
	 * @return
	 */
	public ObservableList<SBook> getSearchOne(){
		return pbookData;
	}
	
	
	/**
	 * 获得某用户的借阅记录
	 * @return
	 */
	public ObservableList<SBorrow> getBorrow(){
		pborrowData = borrowDao.getPersonalBorrowDate(this.getName());
		return pborrowData;
	}
	
	
	/**
	 * 获得所有借阅记录
	 * @return	ObservableList<Borrow>
	 */
	public ObservableList<Borrow> getBorrowData(){
		borrowData = borrowDao.getAllBorrowData();
		return borrowData;
	}

	
	/**
	 * 借阅记录点击查看
	 * @param newValue
	 */
	private void showBorrowDetails(SBorrow newValue) {
		if(newValue != null) {
			Did2.setText(newValue.getId());
			DName2.setText(newValue.getBookName());
		}else {
			DName2.setText("");
			Did2.setText("");
		}
	}
	
}

