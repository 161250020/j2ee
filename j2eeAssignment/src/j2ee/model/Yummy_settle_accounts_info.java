package j2ee.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="yummy_settle_accounts_info")
public class Yummy_settle_accounts_info implements Serializable{
	@Id
	private String id;
	private String account_id;
	private double total_in_money;
	private double total_out_money;
	private double rest_money;
	private String manager_id;
	private Timestamp account_time;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccount_id() {
		return account_id;
	}
	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}
	public double getTotal_in_money() {
		return total_in_money;
	}
	public void setTotal_in_money(double total_in_money) {
		this.total_in_money = total_in_money;
	}
	public double getTotal_out_money() {
		return total_out_money;
	}
	public void setTotal_out_money(double total_out_money) {
		this.total_out_money = total_out_money;
	}
	public double getRest_money() {
		return rest_money;
	}
	public void setRest_money(double rest_money) {
		this.rest_money = rest_money;
	}
	public String getManager_id() {
		return manager_id;
	}
	public void setManager_id(String manager_id) {
		this.manager_id = manager_id;
	}

	public Timestamp getAccount_time() {
		return account_time;
	}

	public void setAccount_time(Timestamp account_time) {
		this.account_time = account_time;
	}
}
