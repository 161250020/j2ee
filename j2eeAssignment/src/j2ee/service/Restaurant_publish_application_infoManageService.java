package j2ee.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import j2ee.model.Restaurant_publish_application_info;


public interface Restaurant_publish_application_infoManageService {
	public void sentErrorMessage(String message, HttpServletRequest req)
			throws ServletException,IOException;

public void sentMessage(String message, HttpServletRequest req)
			throws ServletException,IOException;

public void forwardPage(String page, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException,IOException;

/**
 * 根据id，获得要添加的餐厅的商品的审批的内容
 * */
public Restaurant_publish_application_info getNewInfoById(String id);

/**
 * 添加一条修改餐厅信息的审批申请
 * */
public void addNewPublishInfo(Restaurant_publish_application_info new_info);

/**
 * 修改：餐厅修改的审批（根据ID）信息的result一项的值，表示已审批过，以表示审批结果
 * 如果审批通过，顺便将商品添加到数据库的表格当中（按照type来添加）
 * 修改：餐厅修改的审批（根据ID）信息的manager_id一项的值，表示审批的经理是谁
 * */
public void changePublishResult(String id, int result, String manager_id);

/**
 * 获得所有未审批的商家修改的申请的信息
 * */
public List getAllPublishApplications();

}
