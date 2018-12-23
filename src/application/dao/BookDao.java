package application.dao;
import application.model.Book;
import application.model.SBook;
import application.util.DBUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BookDao{
	Connection con;
	Statement sta;
	ResultSet rs;
	PreparedStatement pre;
	ObservableList<Book> bookData = FXCollections.observableArrayList();	//保存查询的所有书目信息
	ObservableList<SBook> pbookData = FXCollections.observableArrayList();	//查询图书使用的集合类
	
//	private static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");	没有使用data数据类型 
	
	/**
	 * 获得所有书目信息
	 * @return ObservableList<Book>
	 */
	public ObservableList<Book> getAllBook() {
		try {
			bookData.clear();
			con = DBUtils.getConnection();
	    	sta = con.createStatement();
	    	String sql = "SELECT * FROM books";
	    	rs = sta.executeQuery(sql);
			while(rs.next()) {
				String id = rs.getString(1);
				String name = rs.getString(2);
				String type = rs.getString(3);
				String author = rs.getString(4);
				String translator = rs.getString(5);
				String publishHouse = rs.getString(6);
				String publishTime = rs.getString(7);
				int reserve = rs.getInt(8);
				int margin = rs.getInt(9);
				double price = rs.getDouble(10);
				String introduction = rs.getString(11);
				String shopin = rs.getString(12);
				bookData.add(new Book(id,name,type,author,translator,publishHouse,publishTime,reserve,margin,price,introduction,shopin));
				
			}
			con.close();
		}
	    catch(Exception e){
			e.printStackTrace();
	    }
		
		return bookData;
		}
	
	/**
	 * 获得所有书目信息
	 * @return ObservableList<Book>
	 */
	public ObservableList<SBook> getAllSBook() {
		try {
			pbookData.clear();
			con = DBUtils.getConnection();
	    	sta = con.createStatement();
	    	String sql = "SELECT * FROM books";
	    	rs = sta.executeQuery(sql);
			while(rs.next()) {
				String id = rs.getString(1);
				String name = rs.getString(2);
				String type = rs.getString(3);
				String author = rs.getString(4);
				String translator = rs.getString(5);
				String publishHouse = rs.getString(6);
				String publishTime = rs.getString(7);
				int reserve = rs.getInt(8);
				int margin = rs.getInt(9);
				double price = rs.getDouble(10);
				String introduction = rs.getString(11);
				String shopin = rs.getString(12);
				pbookData.add(new SBook(id,name,type,author,translator,publishHouse,publishTime,reserve,margin,price,introduction,shopin));
				
			}
			con.close();
		}
	    catch(Exception e){
			e.printStackTrace();
	    }
		
		return pbookData;
		}
	
	
	/**
	 * 根据输入内容查询图书
	 * @param text
	 * @return SBook
	 * @throws SQLException
	 */
	public ObservableList<SBook> searchBook(String text)  {
		try {
			pbookData.clear();
			con = DBUtils.getConnection();
	    	sta = con.createStatement();
	    	String searchBooksql = "SELECT * FROM books WHERE name like '%"+text +"%' or bookId like '%"+ text +"%' or type like '%"+text +"%'";
	    	rs = sta.executeQuery(searchBooksql);
			while(rs.next()) {
				
				String id = rs.getString(1);
				String name = rs.getString(2);
				String type = rs.getString(3);
				String author = rs.getString(4);
				String translator = rs.getString(5);
				String publishHouse = rs.getString(6);
				String publishTime = rs.getString(7);
				int reserve = rs.getInt(8);
				int margin = rs.getInt(9);
				double price = rs.getDouble(10);
				String introduction = rs.getString(11);
				String shopin = rs.getString(12);
				pbookData.add(new SBook(id,name,type,author,translator,publishHouse,publishTime,reserve,margin,price,introduction,shopin));
				
			}
			con.close();
			return pbookData;
		}
	    
	    catch(Exception e){
			e.printStackTrace();
	    }
		return null;
	}
	

	
	/**
	 * 根据输入内容在所有图书中查询图书
	 * @param text
	 * @return SBook
	 * @throws SQLException
	 */
	public ObservableList<Book> searchAllBook(String text)  {
		try {
			bookData.clear();
			con = DBUtils.getConnection();
	    	sta = con.createStatement();
	    	String searchBooksql = "SELECT * FROM books WHERE name like '%"+text +"%' or bookId like '%"+ text +"%' or type like '%"+text +"%' or author like '%"+ text +"%'";
	    	rs = sta.executeQuery(searchBooksql);
			while(rs.next()) {
				
				String id = rs.getString(1);
				String name = rs.getString(2);
				String type = rs.getString(3);
				String author = rs.getString(4);
				String translator = rs.getString(5);
				String publishHouse = rs.getString(6);
				String publishTime = rs.getString(7);
				int reserve = rs.getInt(8);
				int margin = rs.getInt(9);
				double price = rs.getDouble(10);
				String introduction = rs.getString(11);
				String shopin = rs.getString(12);
				bookData.add(new Book(id,name,type,author,translator,publishHouse,publishTime,reserve,margin,price,introduction,shopin));
				
			}
			con.close();
			return bookData;
		}
	    
	    catch(Exception e){
			e.printStackTrace();
	    }
		return null;
	}
	
	
	/**
	 * 根据编号查询图书
	 * @param text
	 * @return SBook
	 * @throws SQLException
	 */
	public Book searchBookById(String text)  {
		try {
			Book book = null;
			con = DBUtils.getConnection();
	    	sta = con.createStatement();
	    	String searchBooksql = "SELECT * FROM books WHERE bookId = '"+ text +"'";
	    	rs = sta.executeQuery(searchBooksql);
			while(rs.next()) {
				
				String id = rs.getString(1);
				String name = rs.getString(2);
				String type = rs.getString(3);
				String author = rs.getString(4);
				String translator = rs.getString(5);
				String publishHouse = rs.getString(6);
				String publishTime = rs.getString(7);
				int reserve = rs.getInt(8);
				int margin = rs.getInt(9);
				double price = rs.getDouble(10);
				String introduction = rs.getString(11);
				String shopin = rs.getString(12);
				book =  new Book(id,name,type,author,translator,publishHouse,publishTime,reserve,margin,price,introduction,shopin); 
				
			}
			return book;

		}
	    
	    catch(Exception e){
			e.printStackTrace();
	    }
		return null;
	}
	
	
	/**
	 * 根据图书名称获得图书
	 * @param text
	 * @return SBook
	 * @throws SQLException
	 */
	public Book searchBookByName(String text)  {
		try {
			Book book = null;
			con = DBUtils.getConnection();
	    	sta = con.createStatement();
	    	String searchBooksql = "SELECT * FROM books WHERE name = '"+text+"'";
	    	rs = sta.executeQuery(searchBooksql);
			while(rs.next()) {
				String id = rs.getString(1);
				String name = rs.getString(2);
				String type = rs.getString(3);
				String author = rs.getString(4);
				String translator = rs.getString(5);
				String publishHouse = rs.getString(6);
				String publishTime = rs.getString(7);
				int reserve = rs.getInt(8);
				int margin = rs.getInt(9);
				double price = rs.getDouble(10);
				String introduction = rs.getString(11);
				String shopin = rs.getString(12);
				book =  new Book(id,name,type,author,translator,publishHouse,publishTime,reserve,margin,price,introduction,shopin); 
			}
			return book;

		}
	    
	    catch(Exception e){
			e.printStackTrace();
	    }
		return null;
	}
	
	public void insertBook(String id,String name,String type,String author,
			String translator,String publishHouse,String publishTime,
			int reserve,int margin,double price,String introduction,String shopin) {
		try {
			con = DBUtils.getConnection();
	    	sta = con.createStatement();

		}
	    
	    catch(Exception e){
			e.printStackTrace();
	    }

		try {
    	String insertCondition = "INSERT INTO books VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
			pre = con.prepareStatement(insertCondition);
    	pre.setString(1, id);
    	pre.setString(2,name);
    	pre.setString(3,type);
    	pre.setString(4,author);
    	pre.setString(5,translator);
    	pre.setString(6,publishHouse);
    	pre.setDate(7,java.sql.Date.valueOf(publishTime));
    	pre.setInt(8,reserve);
    	pre.setInt(9,margin);
    	pre.setDouble(10,price);
    	pre.setString(11,introduction);
    	pre.setDate(12,java.sql.Date.valueOf(shopin));
		int m = pre.executeUpdate();
		con.close();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		

	}
	public void deleteBook(String id) {
		try {
			con = DBUtils.getConnection();
	    	sta = con.createStatement();
		}
	    
	    catch(Exception e){
			e.printStackTrace();
	    }
		try {
			sta = con.createStatement();
			String deleteCondition = "DELETE FROM books WHERE bookId = '"+id+"'";
			sta.executeUpdate(deleteCondition);
			
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	public void updateBook(String id,String name,String type,String author,
			String translator,String publishHouse,String publishTime,
			int reserve,int margin,double price,String introduction,String shopin) throws SQLException {
		try{deleteBook(id);
		insertBook(id,name,type,author,translator,publishHouse,publishTime,reserve,margin,price,introduction,shopin);
		con.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 被借出，余量减一
	 */
	public void bookNumDecrease(String bookId) {
		con = DBUtils.getConnection();
		try {
	    	String insertCondition = "update books set bookId = ?, name = ?, type = ?, author = ?, translator = ?, publishHouse = ?, "
	    			+ "publishTime = ?, reserve = ?, margin = ?, price = ?, introduction = ?, shopin = ? where bookId =?";
				pre = con.prepareStatement(insertCondition);
	    	pre.setString(1, searchBookById(bookId).getId());
	    	pre.setString(2,searchBookById(bookId).getName());
	    	pre.setString(3,searchBookById(bookId).getType());
	    	pre.setString(4,searchBookById(bookId).getAuthor());
	    	pre.setString(5,searchBookById(bookId).getTranslator());
	    	pre.setString(6,searchBookById(bookId).getPublishHouse());
	    	pre.setString(7,searchBookById(bookId).getPublishTime());
	    	pre.setInt(8,searchBookById(bookId).getReserve());
	    	pre.setInt(9,searchBookById(bookId).getMargin()-1);
	    	pre.setDouble(10,searchBookById(bookId).getPrice());
	    	pre.setString(11,searchBookById(bookId).getIntroduction());
	    	pre.setString(12,searchBookById(bookId).getShopin());
	    	pre.setString(13, bookId);
			int m = pre.executeUpdate();
			con.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
	}
	
	/**
	 * 被借出，余量加一
	 */
	public void bookNumIncrease(String bookName) {
		con = DBUtils.getConnection();
		try {
	    	String insertCondition = "update books set bookId = ?, name = ?, type = ?, author = ?, translator = ?, publishHouse = ?, "
	    			+ "publishTime = ?, reserve = ?, margin = ?, price = ?, introduction = ?, shopin = ? where name =?";
				pre = con.prepareStatement(insertCondition);
	    	pre.setString(1, searchBookByName(bookName).getId());
	    	pre.setString(2,searchBookByName(bookName).getName());
	    	pre.setString(3,searchBookByName(bookName).getType());
	    	pre.setString(4,searchBookByName(bookName).getAuthor());
	    	pre.setString(5,searchBookByName(bookName).getTranslator());
	    	pre.setString(6,searchBookByName(bookName).getPublishHouse());
	    	pre.setString(7,searchBookByName(bookName).getPublishTime());
	    	pre.setInt(8,searchBookByName(bookName).getReserve());
	    	pre.setInt(9,searchBookByName(bookName).getMargin()+1);
	    	pre.setDouble(10,searchBookByName(bookName).getPrice());
	    	pre.setString(11,searchBookByName(bookName).getIntroduction());
	    	pre.setString(12,searchBookByName(bookName).getShopin());
	    	pre.setString(13, bookName);
			int m = pre.executeUpdate();
			con.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
	}
}
