package j2ee.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="restaurant_preferencial_info")
public class Restaurant_preferencial_info implements Serializable{
	@Id
	private String id;
	private String restaurant_id;
	private String com_name;
	private double raw_price;
	private double now_price;
	private int num;
	private Date start_date;
	private Date end_date;
	
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
	public String getCom_name() {
		return com_name;
	}
	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}
	public double getRaw_price() {
		return raw_price;
	}
	public void setRaw_price(double raw_price) {
		this.raw_price = raw_price;
	}
	public double getNow_price() {
		return now_price;
	}
	public void setNow_price(double now_price) {
		this.now_price = now_price;
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
}
