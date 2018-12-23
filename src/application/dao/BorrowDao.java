package application.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import application.model.Borrow;
import application.model.SBorrow;
import application.util.DBUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class BorrowDao {
	Connection con;
	Statement sta;
	ResultSet rs;
	PreparedStatement pre;
	ObservableList<Borrow> borrowData = FXCollections.observableArrayList();
	ObservableList<SBorrow> pborrowData = FXCollections.observableArrayList();
	
	/**
	 *查询获得所有借阅记录
	 * @return ObservableList<Borrow>
	 */
	public ObservableList<Borrow> getAllBorrowData() {
		try {
			con = DBUtils.getConnection();
	    	sta = con.createStatement();
		borrowData.clear();
		String sql = "SELECT * FROM borrows";
		rs = sta.executeQuery(sql);
		while(rs.next()) {
			String id = rs.getString(1);
			String userName = rs.getString(2);
			String bookName = rs.getString(3);
			String borrowDate = rs.getString(4);
			String dueDate = rs.getString(5);
			int renew = rs.getInt(6);
			Boolean giveBack = rs.getBoolean(7);
			String giveBackDate = rs.getString(8);
			borrowData.add(new Borrow(id,bookName,userName,borrowDate,dueDate,giveBack,renew,giveBackDate));
			} 
		con.close();
		}catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return borrowData;
	}
	
	
	public ObservableList<SBorrow> getAllSborrowData(String stringUserName) {
		try {
			con = DBUtils.getConnection();
	    	sta = con.createStatement();
		pborrowData.clear();
		String sql = "SELECT * FROM borrows WHERE userName='"+stringUserName+"'";
		rs = sta.executeQuery(sql);
		while(rs.next()) {
			String id = rs.getString(1);
			String userName = rs.getString(2);
			String bookName = rs.getString(3);
			String borrowDate = rs.getString(4);
			String dueDate = rs.getString(5);
			int renew = rs.getInt(6);
			Boolean giveBack = rs.getBoolean(7);
			String giveBackDate = rs.getString(8);
			pborrowData.add(new SBorrow(id,bookName,userName,borrowDate,dueDate,giveBack,renew,giveBackDate));
			} 
		con.close();
		}catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return pborrowData;
	}
	
	
	/**
	 * 根据id号查找借阅记录
	 * @param id
	 * @return
	 */
	public ObservableList<SBorrow> getPersonalBorrowDate(String SName){
		try {
			con = DBUtils.getConnection();
	    	sta = con.createStatement();
	    	pborrowData.clear();
			String sql = "SELECT * FROM borrows WHERE userName = '"+SName+"'";
			rs = sta.executeQuery(sql);
			while(rs.next()) {
				String id = rs.getString(1);
				String userName = rs.getString(2);
				String bookName = rs.getString(3);
				String borrowDate =rs.getString(4);
				String dueDate =rs.getString(5);
				Boolean give = rs.getBoolean(7);
				int renew = rs.getInt(6);
				String giveBackDate =rs.getString(8);
				pborrowData.add(new SBorrow(id,userName,bookName,borrowDate,dueDate,giveBackDate,give,renew));
			}
			con.close();
		}
	    
	    catch(Exception e){
			e.printStackTrace();
	    }
		
		return pborrowData;
	}
	
	/**
	 * 根据id号查找借阅记录
	 * @param id
	 * @return
	 */
	public ObservableList<SBorrow> getPersonalBorrowDateNoBack(String SName){
		try {
			con = DBUtils.getConnection();
	    	sta = con.createStatement();
	    	pborrowData.clear();
			String sql = "SELECT * FROM borrows WHERE userName = '"+SName+"' and giveBack = 'false'";
			rs = sta.executeQuery(sql);
			while(rs.next()) {
				String id = rs.getString(1);
				String userName = rs.getString(2);
				String bookName = rs.getString(3);
				String borrowDate =rs.getString(4);
				String dueDate =rs.getString(5);
				Boolean give = rs.getBoolean(7);
				int renew = rs.getInt(6);
				String giveBackDate =rs.getString(8);
				pborrowData.add(new SBorrow(id,userName,bookName,borrowDate,dueDate,giveBackDate,give,renew));
			}
			con.close();
		}
	    
	    catch(Exception e){
			e.printStackTrace();
	    }
		
		return pborrowData;
	}
	
	
	/**
	 * 根据id号查找借阅记录
	 * @param id
	 * @return
	 */
	public ObservableList<Borrow> getAllBorrow(String str){
		try {
			con = DBUtils.getConnection();
	    	sta = con.createStatement();
	    	borrowData.clear();
			String sql = "SELECT * FROM borrows WHERE  borrowId like '%"+str+"%' or bookName like '%"+str+"%' or userName like '%"+str+"%'";
			rs = sta.executeQuery(sql);
			while(rs.next()) {
				String id = rs.getString(1);
				String userName = rs.getString(2);
				String bookName = rs.getString(3);
				String borrowDate =rs.getString(4);
				String dueDate =rs.getString(5);
				Boolean give = rs.getBoolean(7);
				int renew = rs.getInt(6);
				String giveBackDate =rs.getString(8);
				borrowData.add(new Borrow(id,userName,bookName,borrowDate,dueDate,giveBackDate,give,renew));
			}
			con.close();
		}
	    
	    catch(Exception e){
			e.printStackTrace();
	    }
		
		return borrowData;
	}
	
	
public void insertborrowData(String id,String userName,String bookName,String borrowData,String dueDate) {
	try {
		con = DBUtils.getConnection();
    	sta = con.createStatement();
	}	    
    catch(Exception e){
		e.printStackTrace();
    }

	try {
	String insertCondition = "INSERT INTO borrows(borrowId,userName,bookName,borrowDate,dueDate,renew,giveBack,giveBackDate) "+"VALUES (?,?,?,?,?,0,false,'无')";
		pre = con.prepareStatement(insertCondition);
	pre.setString(1, id);
	pre.setString(2,userName);
	pre.setString(3,bookName);
	pre.setString(4,borrowData);
	pre.setString(5,dueDate);

	int m = pre.executeUpdate();
	con.close();
	} catch (SQLException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}
	
}


public void returnBook(String borrowId) {
	Calendar cal=Calendar.getInstance();   
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	String str = sf.format(cal.getTime());
	try {
		con = DBUtils.getConnection();
    	sta = con.createStatement();
	}	    
    catch(Exception e){
		e.printStackTrace();
    }
	String sql1 = "UPDATE borrows SET giveBack = true  WHERE borrowId = '"+borrowId+"'";
	String sql2 = "UPDATE borrows SET giveBackDate = '"+str+"' WHERE borrowId = '"+borrowId+"'";
	try {
		sta.executeUpdate(sql1);
		sta.executeUpdate(sql2);
		con.close();
	}	 catch (SQLException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}
}
 

public void deleteborrowData(String id) {
	try {
		con = DBUtils.getConnection();
    	sta = con.createStatement();
	}	    
    catch(Exception e){
		e.printStackTrace();
    }
	String sql = "DELETE FROM borrows WHERE borrowId = '"+id+"'";
	try {
		sta.executeUpdate(sql);
		con.close();
	}	 catch (SQLException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}
}


public void updateborrowData(String id,String userName,String bookName,String borrowData,String dueDate,String giveBackDate) {
	deleteborrowData(id);
	//insertborrowData(id,userName,bookName,borrowData,dueDate,giveBackDate);
}

public int renewBook(String id) {
	
	Connection con = DBUtils.getConnection();
	
	String sql = "UPDATE borrows SET userName=? , bookName=? , borrowDate=? , dueDate=? , renew=? , giveBack=? , "
			+ "giveBackDate=? WHERE borrowId = '"+id+"'";
	
	if(con != null) {
		if(getBorrowData(id).getId() != null) {
			SimpleDateFormat sdFormat=new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar = Calendar.getInstance();
			try {
				Date date = sdFormat.parse(getBorrowData(id).getDueDate());
				calendar.setTime(date);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			if(getBorrowData(id).getRenew() < 2) {
				calendar.add(Calendar.DATE,30);
			}else {
					Alert alert=new Alert(AlertType.NONE);
	      		 	alert.setAlertType(AlertType.WARNING);
	      		 	alert.setHeaderText("续借次数已达到上限");
	      		 	alert.show();
	      		 	return 1;
			}
			String str = sdFormat.format(calendar.getTime());
			
			try {
				PreparedStatement preparedStatement = con.prepareStatement(sql);
				preparedStatement.setString(1, getBorrowData(id).getUserName());
				preparedStatement.setString(2, getBorrowData(id).getBookName());
				preparedStatement.setString(3, getBorrowData(id).getBorrowDate());
				preparedStatement.setString(4, str);
				preparedStatement.setLong(5, getBorrowData(id).getRenew() + 1);
				preparedStatement.setBoolean(6, getBorrowData(id).getGiveBack());
				preparedStatement.setString(7, getBorrowData(id).getGiveBackDate());
				//preparedStatement.setString(8, getBorrowData(id).getId());	
				return preparedStatement.executeUpdate();
			}	 catch (SQLException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
		}finally {
			DBUtils.closeConnection(con);
		}
	}else
		return 0;
}
	return -1;
}

public boolean isBorrowRepeted(String StringbookName,String stringUserName) {
	try {
		con = DBUtils.getConnection();
    	sta = con.createStatement();
	String sql = "SELECT * FROM borrows WHERE bookName='"+StringbookName+"' and userName = '"+stringUserName+"' and giveBack = 'false'";
	rs = sta.executeQuery(sql);
	while(rs.next()) {
//		String id = rs.getString(1);
//		String userName = rs.getString(2);
//		String bookName = rs.getString(3);
//		String borrowDate = rs.getString(4);
//		String dueDate = rs.getString(5);
//		int renew = rs.getInt(6);
//		Boolean giveBack = rs.getBoolean(7);
//		String giveBackDate = rs.getString(8);
		
		con.close();
		
		return true;
	} 
	con.close();
	}catch (SQLException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}
	return false;
}

public Borrow getBorrowData(String stringid) {
	try {
		con = DBUtils.getConnection();
    	sta = con.createStatement();
	String sql = "SELECT * FROM borrows WHERE borrowId='"+stringid+"'";
	rs = sta.executeQuery(sql);
	while(rs.next()) {
		String id = rs.getString(1);
		String userName = rs.getString(2);
		String bookName = rs.getString(3);
		String borrowDate = rs.getString(4);
		String dueDate = rs.getString(5);
		int renew = rs.getInt(6);
		Boolean giveBack = rs.getBoolean(7);
		String giveBackDate = rs.getString(8);
		Borrow borrow = new Borrow(id,bookName,userName,borrowDate,dueDate,giveBack,renew,giveBackDate);
		con.close();
		return borrow;	
	} 
	con.close();
	}catch (SQLException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}
	return null;
}

public ObservableList<SBorrow> getBorrowDate(String borrowId){
	String BorrowId,userName,bookName,borrowDate,dueDate,giveBackDate;
	boolean give;
	int renew;
	try {
		con = DBUtils.getConnection();
    	sta = con.createStatement();
    	pborrowData.clear();
	}
    catch(Exception e){
		e.printStackTrace();
    }
	try {
		String sql = "SELECT * FROM borrows WHERE borrowId =  '"+borrowId+"'";
		rs = sta.executeQuery(sql);
		while(rs.next()){
			 BorrowId = rs.getString(1);
			 System.out.println(BorrowId);
			 userName = rs.getString(2);
			 bookName = rs.getString(3);
			 borrowDate =rs.getString(4);
			 dueDate =rs.getString(5);
			 give = rs.getBoolean(7);
			 renew = rs.getInt(6);
			 giveBackDate =rs.getString(8);
			 pborrowData.add(new SBorrow(BorrowId,userName,bookName,borrowDate,dueDate,giveBackDate,give,renew));
		}
		con.close();

	}
    
    catch(Exception e){
		e.printStackTrace();
    }
	
	return pborrowData;
}


public void updateBorrowData(String borrowId,String bookName,String userName,
		int renewTime,boolean Return,String returnTime) {
	try {
		con = DBUtils.getConnection();
    	sta = con.createStatement();
	}
    catch(Exception e){
		e.printStackTrace();
    }


	String sql = "UPDATE borrows SET bookName = ? , userName = ? , renew = ? , giveBack = ? , giveBackDate = ?	"
			+ "WHERE borrowId = '"+borrowId+"'";
	try {
		pre = con.prepareStatement(sql);

		pre.setString(1,bookName);
		pre.setString(2,userName);
		pre.setInt(3,renewTime);
		pre.setBoolean(4,Return);
		pre.setString(5,returnTime);
		pre.executeUpdate();
		con.close();
	}
    catch(Exception e){
		e.printStackTrace();
    }

}
}

