package j2ee.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="member_level_preferencialstrategies")
public class Member_level_preferencialstrategies implements Serializable{
	@Id
	private String id;
	private double sum_money;
	private int level;
	private double ps_money;
	private String comment;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getSum_money() {
		return sum_money;
	}
	public void setSum_money(double sum_money) {
		this.sum_money = sum_money;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public double getPs_money() {
		return ps_money;
	}
	public void setPs_money(double ps_money) {
		this.ps_money = ps_money;
	}
	
}
