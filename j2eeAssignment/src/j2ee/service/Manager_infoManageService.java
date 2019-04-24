package j2ee.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Manager_infoManageService{
	public void sentErrorMessage(String message, HttpServletRequest req)
			throws ServletException,IOException;

public void sentMessage(String message, HttpServletRequest req)
			throws ServletException,IOException;

public void forwardPage(String page, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException,IOException;

	/**
	 * 根据经理的username获得经理的密码，用于登录
	 * */
	public String getPasswordByUsername(String username);

	/**
	 * 登录：获得所有经理的ID，以免登录的经理的ID是不存在的
	 * */
	public List getAllManagers();
}
