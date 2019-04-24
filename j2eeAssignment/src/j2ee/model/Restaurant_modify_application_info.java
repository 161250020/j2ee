package j2ee.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="restaurant_modify_application_info")
public class Restaurant_modify_application_info implements Serializable{
	@Id
	private String id;
	private String new_name;
	private String new_address;
	private int new_type_id;
	private String new_city;//�µĵ�ַ
	private String new_district;//�µĵ�ַ
	private String new_town;//�µĵ�ַ
	private String new_street;//�µĵ�ַ
	private String restaurant_id;
	private int result;
	private String manager_id;
	
	public String getManager_id() {
		return manager_id;
	}
	public void setManager_id(String manager_id) {
		this.manager_id = manager_id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNew_name() {
		return new_name;
	}
	public void setNew_name(String new_name) {
		this.new_name = new_name;
	}
	public String getNew_address() {
		return new_address;
	}
	public void setNew_address(String new_address) {
		this.new_address = new_address;
	}
	public int getNew_type_id() {
		return new_type_id;
	}
	public void setNew_type_id(int new_type_id) {
		this.new_type_id = new_type_id;
	}
	public String getNew_city() {
		return new_city;
	}
	public void setNew_city(String new_city) {
		this.new_city = new_city;
	}
	public String getNew_district() {
		return new_district;
	}
	public void setNew_district(String new_district) {
		this.new_district = new_district;
	}
	public String getNew_town() {
		return new_town;
	}
	public void setNew_town(String new_town) {
		this.new_town = new_town;
	}
	public String getNew_street() {
		return new_street;
	}
	public void setNew_street(String new_street) {
		this.new_street = new_street;
	}
	public String getRestaurant_id() {
		return restaurant_id;
	}
	public void setRestaurant_id(String restaurant_id) {
		this.restaurant_id = restaurant_id;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	
}
