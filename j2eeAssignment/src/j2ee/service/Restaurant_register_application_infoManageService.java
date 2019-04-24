package j2ee.service;

import j2ee.model.Restaurant_register_application_info;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Restaurant_register_application_infoManageService{
	public void sentErrorMessage(String message, HttpServletRequest req)
			throws ServletException,IOException;

public void sentMessage(String message, HttpServletRequest req)
			throws ServletException,IOException;

public void forwardPage(String page, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException,IOException;	

	/**
	 * 获得商家注册的审批的信息（根据ID）
	 * */
	public Restaurant_register_application_info getRestRegisterInfo(String id);

	/**
	 * 修改商家注册申请（根据ID）的result
	 * */
	public void changeRegisterResult(String id, int result);

	/**
	 * 修改：餐厅修改的审批（根据ID）信息的manager_id一项的值，表示审批的经理是谁
	 * */
	public void changeRegisterManager_id(String id, String manager_id);

	/**
	 * 获得所有未审批的商家注册的申请的信息
	 * */
	public List getAllRegisterApplications();

	/**
	 * 获得所有的商家注册的申请的信息
	 * */
	public List getAllRegisterApplications_total();

	/**
	 * 餐厅注册后：添加餐厅注册的申请
	 * */
	public void addARegisterApplication(Restaurant_register_application_info new_info);
}
