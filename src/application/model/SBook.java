package application.model;

import java.util.Date;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SBook {

	private final StringProperty id;			//ͼ����
	private final StringProperty name;			//ͼ������
	private final StringProperty type;			//ͼ������
	private final StringProperty author;		//����
	private final StringProperty translator;	//����
	private final StringProperty publishHouse;	//������
	private final StringProperty publishTime;	//����ʱ��
	private final IntegerProperty reserve;		//����
	private final IntegerProperty margin;		//����
	private final DoubleProperty price;			//�۸�
	private final StringProperty introduction;	//���
	private final StringProperty shopin;	//����ʱ��
	
	//���ʱ��
	//����ʱ��
	
	public SBook(String id,String name,String type,String author,
			String translator,String publishHouse,String publishTime,
			int reserve,int margin,double price,String introduction,String shopin) {
	this.id   	= new SimpleStringProperty(id);
	this.name 	= new SimpleStringProperty(name);
	this.type 	= new SimpleStringProperty(type);
	this.author = new SimpleStringProperty(author);
	this.translator	   = new SimpleStringProperty(translator);
	this.publishHouse  = new SimpleStringProperty(publishHouse);
	this.publishTime   = new SimpleStringProperty(publishTime);
	this.reserve 	   = new SimpleIntegerProperty(reserve);
	this.margin 	   = new SimpleIntegerProperty(margin);
	this.price 		   = new SimpleDoubleProperty(price);
	this.introduction  = new SimpleStringProperty(introduction);
	this.shopin 	   = new SimpleStringProperty(shopin);
	
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
    
    public String getName() {
    	return name.get();
    }
    
    public StringProperty getNameProperty() {
    	return name;
    }
    
    public void setName(String name) {
    	this.name.set(name);
    }
    
    public String getType() {
    	return type.get();
    }
    
    public StringProperty getTypeProperty() {
    	return type;
    }
    
    public void setType(String type) {
    	this.type.set(type);
    }
    
    public String getAuthor() {
    	return author.get();
    }
    
    public StringProperty getAuthorProperty() {
    	return author;
    }
    
    public void setAuthor(String author) {
    	this.author.set(author);
    }
    
    public String getTranslator() {
    	return translator.get();
    }
    
    public StringProperty getTranslatorProperty() {
    	return translator;
    }
    
    public void set(String translator) {
    	this.translator.set(translator);
    }
    
    public String getPublishHouse() {
    	return publishHouse.get();
    }
    
    public StringProperty getPublishHouseProperty() {
    	return publishHouse;
    }
    
    public void setPublishHouse(String publishHouse) {
    	this.publishHouse.set(publishHouse);
    }
    
    public String getPublishTime() {
    	return publishTime.get();
    }
    
    public StringProperty getPublishTimeProperty() {
    	return publishTime;
    }
    
    public void setPublishTime(String publishtime) {
    	this.publishTime.set(publishtime);
    }
    
    public int getReserve() {
    	return reserve.get();
    }
    
    public IntegerProperty getReserveProperty() {
    	return reserve;
    }
    
    public void setReserveProperty(int reserve) {
    	this.reserve.set(reserve);
    }
    
    public int getMargin() {
    	return margin.get();
    }
    
    public IntegerProperty getMarginProperty() {
    	return margin;
    }
    
    public void setMargin(int margin) {
    	this.margin.set(margin);
    }
    
    public double getPrice() {
    	return price.get();
    }
    
    public DoubleProperty getPriceProperty() {
    	return price;
    }
    
    public void setPrice(double price) {
    	this.price.set(price);
    }
    
    public String getIntroduction() {
    	return introduction.get();
    }
    
    public StringProperty getIntroductionProperty() {
    	return introduction;
    }
    
    public void setIntroduction(String introduction) {
    	this.introduction.set(introduction);
    }
    
    public String getShopin() {
    	return shopin.get();
    }
    
    public StringProperty getShopinProperty() {
    	return shopin;
    }
    
    public void setShopin(String shopin) {
    	this.shopin.set(shopin);
    }
	

}
