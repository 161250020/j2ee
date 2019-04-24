package j2ee.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import j2ee.model.Restaurant_modify_application_info;

public interface Restaurant_modify_application_infoManageService{
	public void sentErrorMessage(String message, HttpServletRequest req)
			throws ServletException,IOException;

public void sentMessage(String message, HttpServletRequest req)
			throws ServletException,IOException;

public void forwardPage(String page, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException,IOException;

	/**
	 * 根据id，获得要修改成的餐厅的内容
	 * */
	public Restaurant_modify_application_info getNewInfoById(String id);

	/**
	 * 添加新的要修改成为的餐厅信息
	 * */
	public void addNewModifyInfo(Restaurant_modify_application_info new_info);

	/**
	 * 修改：餐厅修改的审批（根据ID）信息的result一项的值，表示已审批过，以表示审批结果
	 * */
	public void changeModifyResult(String id, int result);

	/**
	 * 修改：餐厅修改的审批（根据ID）信息的manager_id一项的值，表示审批的经理是谁
	 * */
	public void changeModifyManager_id(String id, String manager_id);

	/**
	 * 获得所有未审批的商家修改的申请的信息
	 * */
	public List getAllModifyApplications();

}
