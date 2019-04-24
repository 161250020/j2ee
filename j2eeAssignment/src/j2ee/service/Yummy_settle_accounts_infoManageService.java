package j2ee.service;

import j2ee.model.Yummy_settle_accounts_info;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Yummy_settle_accounts_infoManageService{
	public void sentErrorMessage(String message, HttpServletRequest req)
			throws ServletException,IOException;

public void sentMessage(String message, HttpServletRequest req)
			throws ServletException,IOException;

public void forwardPage(String page, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException,IOException;


	/**
	 * 获得经理上次结算的时间（datetime）xxxx-xx-xx xx:xx:xx
	 * */
	public String getLastAccountDateTime();

	/**
	 * 添加一条结算的记录
	 * */
	public void addOneInfo(Yummy_settle_accounts_info newInfo);

}
