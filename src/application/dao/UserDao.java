package application.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.model.Cookie;
import application.model.User;
import application.util.DBUtils;
import application.util.MD5;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserDao {
	Connection con;
	Statement sta;
	ResultSet rs;
	PreparedStatement pre;
	ObservableList<User> userDate = FXCollections.observableArrayList();
	MD5 md5 = new MD5();
	
	/**
	 * 检查密码
	 * @param account
	 * @param password
	 * @param status
	 * @return
	 */
	public boolean checkPassword(String account,String password,String status) {
		try {
	    	con = DBUtils.getConnection();
	    	sta = con.createStatement();
	    	String checkPasswordsql = "SELECT * FROM users WHERE account = '"+account+"' ";
	    	rs = sta.executeQuery(checkPasswordsql);
	    	while(rs.next()) {
	    		String passwordTest = rs.getString("password");
	    		String statusTest = rs.getString("status");
	    		if(passwordTest.equals(md5.md5Jdk(password))||passwordTest.equals(password)) {
	    			if(statusTest.equals(status)) {
	    				con.close();
	    				return true;
	    			}else {
	    				con.close();
	    				return false;
	    			}
	    		}
	    	}
	 
		}
	    catch(Exception e){
			e.printStackTrace();
	    }
		return false;
	}
	
	/**
	 * 获得所有用户信息
	 * @return
	 */
	public ObservableList<User> getAllReaderDate(){
		try {
	    	con = DBUtils.getConnection();
	    	sta = con.createStatement();
	    	userDate.clear();
			String sql = "SELECT * From users WHERE status = '读者'";
			rs = sta.executeQuery(sql);
			while(rs.next()) {
				String account = rs.getString(1);
				String userName = rs.getString(3);
				String status = rs.getString(4);
				int borrowNumber = rs.getInt(5);
				int borrowNumberSum = rs.getInt(6);
				int grade = rs.getInt(7);
				String academy = rs.getString(8);
				String clbum = rs.getString(9);
				String sex = rs.getString(11);
				String phoneNumber = rs.getString(12);
				String IcNumber = rs.getString(13);
				userDate.add(new User(account,userName,status,borrowNumber,borrowNumberSum,grade,academy,clbum,sex,IcNumber,phoneNumber));
			}
			con.close();
	}catch (SQLException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
		}
		return userDate;
	}
	
	
	/**
	 * 添加用户
	 * @param account
	 * @param userName
	 * @param status
	 * @param borrowNumber
	 * @param forfeit
	 * @param grade
	 * @param academy
	 * @param clbum
	 * @param sex
	 * @param IcNumber
	 * @param phoneNumber
	 */
	public void addUser(String account,String userName,String status,
			int borrowNumber,int forfeit,int grade,String academy,String clbum,
			String sex,String IcNumber,String phoneNumber) {
		try {
			con = DBUtils.getConnection();
	    	sta = con.createStatement();
		}
	    catch(Exception e){
			e.printStackTrace();
	    }
		String initialpassword = IcNumber.substring(12);
		String password = initialpassword;

		String sql = "INSERT INTO users VALUES('?','?','?','?',?,?,?,'?','?','?','?','?','?')";
		try {
			pre = con.prepareStatement(sql);
			pre.setString(1,account);
			pre.setString(2,password);
			pre.setString(3,userName);
			pre.setString(4,status);
			pre.setInt(5,borrowNumber);
			pre.setInt(6,forfeit);
			pre.setInt(7,grade);
			pre.setString(8,academy);
			pre.setString(9,clbum);
			pre.setString(10,sex);
			pre.setString(11,phoneNumber);
			pre.setString(12,IcNumber);
			pre.setString(13,initialpassword);
			con.close();
		}
	    catch(Exception e){
			e.printStackTrace();
	    }
	}
	
	/**
	 * 删除用户
	 * @param account
	 */
	public void deleteUser(String account) {
		try {
//	    	Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
//	    	con = DriverManager.getConnection("jdbc:derby:test;create = true");
			con = DBUtils.getConnection();
	    	sta = con.createStatement();
		}
	    
	    catch(Exception e){
			e.printStackTrace();
	    }
		String sql = "DELETE FROM users WHERE account = '"+account+"'";
		try {
			sta.executeUpdate(sql);
			con.close();
		}
	    catch(Exception e){
			e.printStackTrace();
	    }
	}
	
	
	public void addUser(String account,String userName,String status,
			String sex,String IcNumber,String phoneNumber) {
		try {
			con = DBUtils.getConnection();
	    	sta = con.createStatement();
		}
	    catch(Exception e){
			e.printStackTrace();
	    }
		String initialpassword = IcNumber.substring(12);
		String password = initialpassword;

		String sql = "INSERT INTO users(account, password, userName, status, borrowNumber,borrowNumberSum, sex,"
				+ " phoneNumber, IcNumber,initialpassword) VALUES(?,'123456',?,?,10,0,?,?,?,'046615')";
		try {
			pre = con.prepareStatement(sql);
			pre.setString(1,account);
			pre.setString(2,userName);
			pre.setString(3,status);
			pre.setString(4,sex);
			pre.setString(5,phoneNumber);
			pre.setString(6,IcNumber);
			pre.executeUpdate();
			con.close();
		}
	    catch(Exception e){
			e.printStackTrace();
	    }

	}
	
	/**
	 * 更新用户
	 * @param account
	 * @param userName
	 * @param status
	 * @param borrowNumber
	 * @param forfeit
	 * @param grade
	 * @param academy
	 * @param clbum
	 * @param sex
	 * @param IcNumber
	 * @param phoneNumber
	 */
	
	public void updateUser(String account,String userName,String status,
			int borrowNumber,int forfeit,int grade,String academy,String clbum,
			String sex,String IcNumber,String phoneNumber) {
		deleteUser(account);
		addUser(account,userName,status,borrowNumber,forfeit,grade,academy,clbum,sex,IcNumber,phoneNumber);
	}
	
	

	/**
	 * 更新用户
	 * @param account
	 * @param userName
	 * @param status
	 * @param borrowNumber
	 * @param forfeit
	 * @param grade
	 * @param academy
	 * @param clbum
	 * @param sex
	 * @param IcNumber
	 * @param phoneNumber
	 */
	public void upDateUser(String account,String userName,String status,
			String sex,String phoneNumber,String IcNumber) {
		try {
			con = DBUtils.getConnection();
	    	sta = con.createStatement();
		}
	    catch(Exception e){
			e.printStackTrace();
	    }


		String sql = "UPDATE users SET userName = ? , status = ? , sex = ? , phoneNumber = ? , IcNumber = ?	"
				+ "WHERE account = '"+account+"'";
		try {
			pre = con.prepareStatement(sql);

			pre.setString(1,userName);
			pre.setString(2,status);
			pre.setString(3,sex);
			pre.setString(4,phoneNumber);
			pre.setString(5,IcNumber);
			pre.executeUpdate();
			con.close();
		}
	    catch(Exception e){
			e.printStackTrace();
	    }

	}
	
	
	/**
	 * 更改密码
	 * @param IcNumber 账号
	 * @param password 密码
	 */
	public int changePassword(String account,String account1) {

		Connection con = DBUtils.getConnection();
		String sql = "UPDATE users SET account=? , password=? , userName =? , status=? , borrowNumber=? , "
				+ "borrowNumberSum=? , forfeit=? , grade=? , academy=? , clbum=? , sex=? , phoneNumber=? , initialpassword=? WHERE IcNumber=? and account=?";
		if(con != null) {
			if(getUserDate(account).getIcNumber() != null) {
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, getUserDate(account).getAccount());
			preparedStatement.setString(2, md5.md5Jdk(account1));
			preparedStatement.setString(3, getUserDate(account).getUserName());
			preparedStatement.setString(4, getUserDate(account).getStatus());
			preparedStatement.setLong(5, getUserDate(account).getBorrowNumber());
			preparedStatement.setLong(6, getUserDate(account).getBorrowNumberSum());
			preparedStatement.setLong(7, getUserDate(account).getForfeit());
			preparedStatement.setLong(8, getUserDate(account).getGrade());
			preparedStatement.setString(9, getUserDate(account).getAcademy());
			preparedStatement.setString(10, getUserDate(account).getClbum());
			preparedStatement.setString(11, getUserDate(account).getSex());
			preparedStatement.setString(12, getUserDate(account).getPhoneNumber());
			preparedStatement.setString(13, getUserDate(account).getInitialpassword());
			preparedStatement.setString(14, getUserDate(account).getIcNumber());
			preparedStatement.setString(15, account);
//			System.out.println("getUserDate(account).getPassword(): " + getUserDate(account).getPassword());
			return preparedStatement.executeUpdate();
		}
	    catch(Exception e){
			e.printStackTrace();
	    }finally{
	    	DBUtils.closeConnection(con);
	    }
	}else
		return 0;
	}
		return -1;
	}
	
	
	/**
	 * 根据账号获得对应用户
	 * 
	 */
	public User getUserDate(String sAccount){
		try {
			con = DBUtils.getConnection();
			sta = con.createStatement();
			String sql = "SELECT * From users WHERE account = '"+sAccount+"'";
			rs = sta.executeQuery(sql);
			while(rs.next()) {
				String account = rs.getString(1);
				String userName = rs.getString(3);
				String status = rs.getString(4);
				int borrowNumber = rs.getInt(5);
				int borrowNumberSum = rs.getInt(6);
				int grade = rs.getInt(7);
				String academy = rs.getString(8);
				String clbum = rs.getString(9);
				String sex = rs.getString(11);
				String phoneNumber = rs.getString(12);
				String IcNumber = rs.getString(13);
			
				User user = new User(account,userName,status,borrowNumber,borrowNumberSum,grade,academy,clbum,sex,IcNumber,phoneNumber);
				con.close();
				return user;
			}
			con.close();
		} catch(Exception e){
			e.printStackTrace();
	    }
		return null;
		
	}
	
	/**
	 * 根据账号获得对应用户
	 * 
	 */
	public ObservableList<User> getUserDataInList(String sAccount){
		try {
			userDate.clear();
			con = DBUtils.getConnection();
			sta = con.createStatement();
			String sql = "SELECT * From users WHERE account like '%"+sAccount+"%' or userName like  '%"+sAccount+"%'";
			rs = sta.executeQuery(sql);
			while(rs.next()) {
				String account = rs.getString(1);
				String userName = rs.getString(3);
				String status = rs.getString(4);
				int borrowNumber = rs.getInt(5);
				int borrowNumberSum = rs.getInt(6);
				int grade = rs.getInt(7);
				String academy = rs.getString(8);
				String clbum = rs.getString(9);
				String sex = rs.getString(11);
				String phoneNumber = rs.getString(12);
				String IcNumber = rs.getString(13);
				userDate.add(new User(account,userName,status,borrowNumber,borrowNumberSum,grade,academy,clbum,sex,IcNumber,phoneNumber));
			}
			con.close();
		} catch(Exception e){
			e.printStackTrace();
	    }
		return userDate;
		
	}
	
	/**
	 * 删除密码缓存表
	 * @param account
	 * @param password
	 * @param type
	 */
	public void deleteCookie() {
		try {
			con = DBUtils.getConnection();
			sta = con.createStatement();
			}
		 catch(Exception e){
			e.printStackTrace();
	    }
	
		try {
	    	String deleteCookie = "delete from cookies";
				pre = con.prepareStatement(deleteCookie);
				int i = pre.executeUpdate();
			} catch(Exception e){
				e.printStackTrace();
			}
    }
	
	/**
	 * 插入记住密码缓存表
	 * @param account
	 * @param password
	 * @param type
	 */
	public void setCookie(String account,String password,String type) {
		try {
			con = DBUtils.getConnection();
			sta = con.createStatement();
			}
		 catch(Exception e){
			e.printStackTrace();
	    }
	
		try {
	    	String deleteCookie = "delete from cookies";
				pre = con.prepareStatement(deleteCookie);
				int i = pre.executeUpdate();
			} catch(Exception e){
				e.printStackTrace();
			}
		
		try {
	    	String insertCookie = "INSERT INTO cookies VALUES (?,?,?)";
				pre = con.prepareStatement(insertCookie);
	    	pre.setString(1, account);
	    	pre.setString(2,MD5.md5Jdk(password));
	    	pre.setString(3,type);
			int m = pre.executeUpdate();
			} catch(Exception e){
				e.printStackTrace();
			}
    }
	
	/**
	 * 获得记住密码的账号、密码、身份
	 * @return
	 */
	public Cookie getCookie() {
		try {
			con = DBUtils.getConnection();
			sta = con.createStatement();
			String sql = "SELECT * From cookies";
			rs = sta.executeQuery(sql);
			while(rs.next()) {
				String account = rs.getString(1);
				String userName = rs.getString(2);
				String status = rs.getString(3);
				Cookie cookie = new Cookie(account,userName,status);
				con.close();
				return cookie;
			}
			con.close();
		} catch(Exception e){
			e.printStackTrace();
	    }
		return null;
	}
	
	public void setBorrowNumberIncrease(String stringAccount) {
		try {
			con = DBUtils.getConnection();
			sta = con.createStatement();
			}
		 catch(Exception e){
			e.printStackTrace();
	    }
	
		try {
			String sql = "UPDATE users SET account=? , password=? , userName =? , status=? , borrowNumber=? , "
					+ "borrowNumberSum=? , forfeit=? , grade=? , academy=? , clbum=? , sex=? , phoneNumber=? , initialpassword=? WHERE account = ?";

				pre = con.prepareStatement(sql);
				User user = getUserDate(stringAccount);
				
				pre.setString(1,user.getAccount());
				pre.setString(2,user.getPassword());
				pre.setString(3,user.getUserName());
				pre.setString(4,user.getStatus());
				pre.setInt(5,user.getBorrowNumber()-1);
				pre.setInt(6,user.getBorrowNumberSum());
				pre.setInt(7,user.getForfeit());
				pre.setInt(8,user.getGrade());
				pre.setString(9,user.getAcademy());
				pre.setString(10,user.getClbum());
				pre.setString(11,user.getSex());
				pre.setString(12,user.getPhoneNumber());
				pre.setString(13,user.getInitialpassword());
				pre.setString(14,stringAccount);
				pre.executeUpdate();
				con.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		
    }
	
	public void setBorrowNumberDecrease(String stringAccount) {
		try {
			con = DBUtils.getConnection();
			sta = con.createStatement();
			}
		 catch(Exception e){
			e.printStackTrace();
	    }
	
		try {
			String sql = "UPDATE users SET account=? , password=? , userName =? , status=? , borrowNumber=? , "
					+ "borrowNumberSum=? , forfeit=? , grade=? , academy=? , clbum=? , sex=? , phoneNumber=? , initialpassword=? WHERE account = ?";

				pre = con.prepareStatement(sql);
				User user = getUserDate(stringAccount);
				
				pre.setString(1,user.getAccount());
				pre.setString(2,user.getPassword());
				pre.setString(3,user.getUserName());
				pre.setString(4,user.getStatus());
				pre.setInt(5,user.getBorrowNumber()+1);
				pre.setInt(6,user.getBorrowNumberSum());
				pre.setInt(7,user.getForfeit());
				pre.setInt(8,user.getGrade());
				pre.setString(9,user.getAcademy());
				pre.setString(10,user.getClbum());
				pre.setString(11,user.getSex());
				pre.setString(12,user.getPhoneNumber());
				pre.setString(13,user.getInitialpassword());
				pre.setString(14,stringAccount);
				pre.executeUpdate();
				con.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		
    }
}
