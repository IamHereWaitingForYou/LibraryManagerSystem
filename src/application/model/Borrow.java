package application.model;

import java.util.Date;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Borrow {

	private final StringProperty 	id;				//������ˮ��
	private final StringProperty userName;			//�����û�
	private final StringProperty bookName;			//ͼ������
	private final StringProperty borrowDate ;	//��������
	private final StringProperty dueDate;		//Ӧ������
	private final IntegerProperty renew;			//�������
	private final BooleanProperty giveBack;			//�Ƿ�黹
	private final StringProperty giveBackDate;//��������

	public Borrow(String id,String userName,String bookName,String borrowDate,String dueDate) {
		this.id 	   = new SimpleStringProperty(id);
		this.userName  = new SimpleStringProperty(userName);
		this.bookName  = new SimpleStringProperty(bookName);
		this.borrowDate= new SimpleStringProperty(borrowDate);
		this.dueDate   = new SimpleStringProperty(dueDate);
		this.renew 	   = new SimpleIntegerProperty(0);
		this.giveBack  = new SimpleBooleanProperty(false);
		this.giveBackDate = new SimpleStringProperty();
	}
	
	
	public Borrow(String id, String userName, String bookName, String borrowDate, String dueDate,
			String giveBackDate,Boolean give,int renew) {
		// TODO Auto-generated constructor stub
		this.id 	   = new SimpleStringProperty(id);
		this.userName  = new SimpleStringProperty(userName);
		this.bookName  = new SimpleStringProperty(bookName);
		this.borrowDate= new SimpleStringProperty(borrowDate);
		this.dueDate   = new SimpleStringProperty(dueDate);
		this.renew 	   = new SimpleIntegerProperty(renew);
		this.giveBack  = new SimpleBooleanProperty(give);
		this.giveBackDate = new SimpleStringProperty(giveBackDate);
	}


	/**
	 * �������н�����Ϣ
	 * @param id2
	 * @param bookName2
	 * @param userName2
	 * @param borrowDate2
	 * @param dueDate2
	 * @param renew2
	 * @param giveBack2
	 * @param giveBackDate2
	 */
	public Borrow(String id2, String bookName2, String userName2, String borrowDate2, String dueDate2,
			Boolean giveBack2, int renew2, String giveBackDate2) {
		// TODO �Զ����ɵĹ��캯�����
		this.id 	   = new SimpleStringProperty(id2);
		this.userName  = new SimpleStringProperty(userName2);
		this.bookName  = new SimpleStringProperty(bookName2);
		this.borrowDate= new SimpleStringProperty(borrowDate2);
		this.dueDate   = new SimpleStringProperty(dueDate2);
		this.renew 	   = new SimpleIntegerProperty(renew2);
		this.giveBack  = new SimpleBooleanProperty(giveBack2);
		this.giveBackDate = new SimpleStringProperty(giveBackDate2);
	}


	public String getId() {
		return id.get();
	}
	public StringProperty getIdProperty() {
        return id;
    }
    
    public void setId(String id) {
        this.id.set(id);
    }
    
    public String getUserName() {
    	return userName.get();
    }
    
    public StringProperty getUserNameProperty() {
    	return userName;
    }
    
    public void setUserName(String userName) {
    	this.userName.set(userName);
    }
    
    public String getBookName() {
    	return bookName.get();
    }
    
    public StringProperty getBookNameProperty() {
    	return bookName;
    }
    
    public void setBookName(String bookName) {
    	this.bookName.set(bookName);
    }
    
    public String getBorrowDate() {
    	return borrowDate.get();
    }
    
    public StringProperty getBorrowDateProperty() {
    	return borrowDate;
    }
    
    public void setBorrowDate(String borrowDate) {
    	this.borrowDate.set(borrowDate);
    }
    
    public String getDueDate() {
    	return dueDate.get();
    }
    
    public StringProperty getDueDateProperty() {
    	return dueDate;
    }
    
    public void setDueDate(String dueDate) {
    	this.dueDate.set(dueDate);
    }
    
    public int getRenew() {
		return renew.get();
	}
	public IntegerProperty getRenewProperty() {
        return renew;
    }
    
    public void setRenew(int renew) {
        this.renew.set(renew);
    }
    
    public Boolean getGiveBack() {
    	return giveBack.get();
    }
    
    public BooleanProperty getGiveBackProperty() {
    	return giveBack;
    }
    
    public void setGiveBack(Boolean giveBack) {
    	this.giveBack.set(giveBack);
    }
    
    public String getGiveBackDate() {
    	return giveBackDate.get();
    }
    
    public StringProperty getGiveBackDateProperty() {
    	return giveBackDate;
    }
    
    public void setGiveBackDate(String giveBack) {
    	this.giveBackDate.set(giveBack);
    }
    

}
