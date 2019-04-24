package j2ee.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="restaurant_publish_application_info")
public class Restaurant_publish_application_info {
	@Id
	private String id;
	private String restaurant_id;
	private int com_type;
	private String name;
	private double price;
	private int num;
	private Date start_date;
	private Date end_date;
	private int result;
	private String manager_id;
	private double raw_price;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRestaurant_id() {
		return restaurant_id;
	}
	public void setRestaurant_id(String restaurant_id) {
		this.restaurant_id = restaurant_id;
	}
	public int getCom_type() {
		return com_type;
	}
	public void setCom_type(int com_type) {
		this.com_type = com_type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public String getManager_id() {
		return manager_id;
	}
	public void setManager_id(String manager_id) {
		this.manager_id = manager_id;
	}
	public double getRaw_price() {
		return raw_price;
	}
	public void setRaw_price(double raw_price) {
		this.raw_price = raw_price;
	}
	
	
}
