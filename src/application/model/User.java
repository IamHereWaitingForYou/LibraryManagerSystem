package application.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {
	
	private final StringProperty account;			//账号
	private final StringProperty password;			//密码
	private final StringProperty userName;			//用户名
	private final StringProperty status;			//身份:(读者);(图书管理员);(系统管理员)
	private final IntegerProperty borrowNumber;		//可借数目
	private final IntegerProperty borrowNumberSum;	//已借数目`新9.10`
	private final IntegerProperty forfeit;			//罚金
	private final IntegerProperty grade;			//年级
	private final StringProperty academy;			//学院
	private final StringProperty clbum;				//班级
	private final StringProperty sex;				//性别
	private final StringProperty phoneNumber;		//电话号码
	private final StringProperty IcNumber;			//身份证号码
	private final StringProperty initialpassword;	//初始密码
	
	
	public User(String account,String userName,String status,String IcNumber,String initialpassword,int grade,String academy,String clbum,String sex,String phoneNumber) {
		
		this.account  = new SimpleStringProperty  (account);
		this.userName = new SimpleStringProperty  (userName);
		this.status   = new SimpleStringProperty   (status);
		this.IcNumber = new SimpleStringProperty (IcNumber);
		this.grade    = new SimpleIntegerProperty    (grade);
		this.academy  = new SimpleStringProperty   (academy);
		this.clbum    = new SimpleStringProperty     (clbum);
		this.sex      = new SimpleStringProperty       (sex);
		this.phoneNumber     = new SimpleStringProperty      (phoneNumber);
		this.initialpassword = new SimpleStringProperty  (initialpassword);
		
		
		this.password     = new SimpleStringProperty(initialpassword+"");
		this.borrowNumber = new SimpleIntegerProperty(10);
		this.forfeit = new SimpleIntegerProperty(0);
		this.borrowNumberSum = new SimpleIntegerProperty(0);
	}
	
	public User(String account2, String userName2, String status2,int borrowNumber2,int borrowNumberSum2, int grade2, String academy2, String clbum2,
			String sex2, String icNumber2, String phoneNumber2) {
		// TODO Auto-generated constructor stub
		this.account  = new SimpleStringProperty  (account2);
		this.userName = new SimpleStringProperty  (userName2);
		this.status   = new SimpleStringProperty   (status2);
		this.borrowNumber = new SimpleIntegerProperty(borrowNumber2);
		this.borrowNumberSum = new SimpleIntegerProperty(borrowNumberSum2);
		this.IcNumber = new SimpleStringProperty (icNumber2);
		this.grade    = new SimpleIntegerProperty    (grade2);
		this.academy  = new SimpleStringProperty   (academy2);
		this.clbum    = new SimpleStringProperty     (clbum2);
		this.sex      = new SimpleStringProperty       (sex2);
		this.phoneNumber     = new SimpleStringProperty      (phoneNumber2);
		
		String initialpassword = "123456";
		this.initialpassword = new SimpleStringProperty  (initialpassword);
		
		
		this.password     = new SimpleStringProperty(initialpassword+"");
		this.forfeit = new SimpleIntegerProperty(0);
		
	}

	public String getAccount() {
		return account.get();
	}
	
    public StringProperty getAccountProperty() {
        return account;
    }
    
    public void setAccount(String account) {
        this.account.set(account);
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
    
    public String getStatus() {
    	return status.get();
    }
    
    public StringProperty getStatusProperty() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status.set(status);
    }
    
    public String getIcNumber() {
    	return IcNumber.get();
    }
    
    public StringProperty getIcNumberProperty() {
        return IcNumber;
    }
    
    public void setIcNumber(String IcNumber) {
        this.IcNumber.set(IcNumber);
    }
    
    public int getGrade() {
    	return grade.get();
    }
    
    
    public IntegerProperty getGradeProperty() {
        return grade;
    }
    
    public void setGrade(int grade) {
        this.grade.set(grade);
    }
    
    public String getAcademy() {
    	return this.academy.get();
    }
    
    public StringProperty getAcademyProperty() {
        return academy;
    }
    
    public void setAcademy(String academy) {
        this.academy.set(academy);
    }
    
    public String getClbum() {
    	return clbum.get();
    }
    
    public StringProperty getClbumProperty() {
        return clbum;
    }
    
    public void setClbum(String clbum) {
        this.clbum.set(clbum);
    }
    
    public String getSex() {
    	return sex.get();
    }
    
    public StringProperty getSexProperty() {
        return sex;
    }
    
    public void setSex(String sex) {
        this.sex.set(sex);
    }

    public String getPhoneNumber() {
    	return phoneNumber.get();
    }
    
    public StringProperty getPhoneNumberProperty() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }
    
    public String getInitialpassword() {
    	return initialpassword.get();
    }

    public StringProperty getInitialpasswordProperty() {
        return initialpassword;
    }

    public void setInitialpassword(String initialpassword) {
        this.initialpassword.set(initialpassword);
    }
    
    public String getPassword() {
    	return password.get();
    }
    
    public StringProperty getPasswordProperty() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password.set(password);
    }
    
    public int getBorrowNumber() {
    	return borrowNumber.get();
    }
    
    public IntegerProperty getBorrowNumberProperty() {
        return borrowNumber;
    }
    
    public void setBorrowNumber(int borrowNumber) {
        this.borrowNumber.set(borrowNumber);
    }
    
    public int getBorrowNumberSum() {
    	return borrowNumberSum.get();
    }
    
    public IntegerProperty getBorrowNumberSumProperty() {
        return borrowNumberSum;
    }
    
    public void setBorrowNumberSum(int borrowNumberSum) {
        this.borrowNumberSum.set(borrowNumberSum);
    }
    
    public int getForfeit() {
    	return forfeit.get();
    }
    
    
    public IntegerProperty getForfeitProperty() {
        return forfeit;
    }
    
    public void setForfeit(int forfeit) {
        this.forfeit.set(forfeit);
    }
    

}
