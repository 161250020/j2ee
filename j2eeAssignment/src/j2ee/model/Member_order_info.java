package j2ee.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="member_order_info")
public class Member_order_info implements Serializable{
	@Id
	private String id;
	private String order_list_id;
	private Timestamp order_time;
	private String delivery_time_defined;
	private String delivery_time_os;
	private Timestamp pay_time;
	private Timestamp delivery_time_received;
	private String member_id;
	private String restaurant_id;
	private double sum_price;
	private int result;
	private int state;
	private String delivery_address_id;
	private double ps_money_yummy;
	private int account;

	public String getDelivery_time_os() {
		return delivery_time_os;
	}

	public void setDelivery_time_os(String delivery_time_os) {
		this.delivery_time_os = delivery_time_os;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrder_list_id() {
		return order_list_id;
	}
	public void setOrder_list_id(String order_list_id) {
		this.order_list_id = order_list_id;
	}
	public Timestamp getOrder_time() {
		return order_time;
	}
	public void setOrder_time(Timestamp order_time) {
		this.order_time = order_time;
	}
	public String getDelivery_time_defined() {
		return delivery_time_defined;
	}
	public void setDelivery_time_defined(String delivery_time_defined) {
		this.delivery_time_defined = delivery_time_defined;
	}
	public Timestamp getPay_time() {
		return pay_time;
	}
	public void setPay_time(Timestamp pay_time) {
		this.pay_time = pay_time;
	}
	public Timestamp getDelivery_time_received() {
		return delivery_time_received;
	}
	public void setDelivery_time_received(Timestamp delivery_time_received) {
		this.delivery_time_received = delivery_time_received;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getRestaurant_id() {
		return restaurant_id;
	}
	public void setRestaurant_id(String restaurant_id) {
		this.restaurant_id = restaurant_id;
	}
	public double getSum_price() {
		return sum_price;
	}
	public void setSum_price(double sum_price) {
		this.sum_price = sum_price;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getDelivery_address_id() {
		return delivery_address_id;
	}
	public void setDelivery_address_id(String delivery_address_id) {
		this.delivery_address_id = delivery_address_id;
	}
	public double getPs_money_yummy() {
		return ps_money_yummy;
	}
	public void setPs_money_yummy(double ps_money_yummy) {
		this.ps_money_yummy = ps_money_yummy;
	}

	public int getAccount() {
		return account;
	}

	public void setAccount(int account) {
		this.account = account;
	}
}
