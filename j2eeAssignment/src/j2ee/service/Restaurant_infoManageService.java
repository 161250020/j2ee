package j2ee.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import j2ee.model.Restaurant_info;
import j2ee.model.Restaurant_modify_application_info;
import j2ee.model.Restaurant_register_application_info;

public interface Restaurant_infoManageService{
public void sentErrorMessage(String message, HttpServletRequest req)
			throws ServletException,IOException;

public void sentMessage(String message, HttpServletRequest req)
			throws ServletException,IOException;

public void forwardPage(String page, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException,IOException;


	/**
	 * 获得当前餐厅信息（根据7位编码）
	 * */
	public Restaurant_info getInfoBy7Chars(String chars);

	/**
	 * 修改当前餐厅信息（根据7位编码）
	 * */
	public void modify_info(Restaurant_info new_info);

	/**
	 * 修改餐厅信息，根据7位编码，将餐厅信息修改为新的信息
	 * */
	public void modifyRestInfoBy7Chars(Restaurant_modify_application_info newInfo);

	/**
	 * 如果商家注册的申请被审批通过，就在这个对应的表格当中添加一条商家的记录
	 * */
	public void addNewRestInfo(Restaurant_register_application_info newRestInfo);

	/**
	 * 获得所有餐厅的基本信息
	 * */
	public List getAllRestsInfo();

	/**
	 * 获得新注册餐厅的7位编码，注意不和以前注册过的餐厅的7位编码重复
	 * */
	public String get7Chars();

	/**
	 * 计算商家注册数目
	 * */
	public int countRegisterNum(Date date1, Date date2);

}
