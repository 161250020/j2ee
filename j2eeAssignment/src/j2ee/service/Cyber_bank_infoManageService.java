package j2ee.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Cyber_bank_infoManageService
{
	public void sentErrorMessage(String message, HttpServletRequest req)
			throws ServletException,IOException;

public void sentMessage(String message, HttpServletRequest req)
			throws ServletException,IOException;

public void forwardPage(String page, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException,IOException;

	/**
	 * 修改网银的钱数，根据ID
	 * */
	public void changeMoneyById(String id, double changeToMoney);

	/**
	 * 根据网银ID，获得金钱数量
	 * */
	public double getMoneyById(String id);

	/**
	 * 获得所有网银内容---注册的时候绑定网银使用，以免网银的账号不存在
	 * */
	public List getAllBankInfo();

	/**
	 * 根据网银ID，获得网银密码
	 * */
	public String getPassById(String id);
}
