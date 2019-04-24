package j2ee.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="restaurant_setmeal_info")
public class Restaurant_setmeal_info implements Serializable{
	@Id
	private String id;
	private String restaurant_id;
	private String set_meal_name;
	private double setmeal_price;
	private int setmeal_num;
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
	public String getSet_meal_name() {
		return set_meal_name;
	}
	public void setSet_meal_name(String set_meal_name) {
		this.set_meal_name = set_meal_name;
	}
	public double getSetmeal_price() {
		return setmeal_price;
	}
	public void setSetmeal_price(double setmeal_price) {
		this.setmeal_price = setmeal_price;
	}
	public int getSetmeal_num() {
		return setmeal_num;
	}
	public void setSetmeal_num(int setmeal_num) {
		this.setmeal_num = setmeal_num;
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
