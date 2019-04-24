package j2ee.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="member_ps_receive_info")
public class Member_ps_receive_info implements Serializable{
	@Id
	private String id;
	private String member_id;
	private Date date;
	private double ps_money;
	private int result;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getPs_money() {
		return ps_money;
	}
	public void setPs_money(double ps_money) {
		this.ps_money = ps_money;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}
}
