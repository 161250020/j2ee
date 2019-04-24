package j2ee.service;

import j2ee.model.Restaurant_setmeal_info;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Restaurant_setmeal_infoManageService{
	public void sentErrorMessage(String message, HttpServletRequest req)
			throws ServletException,IOException;

public void sentMessage(String message, HttpServletRequest req)
			throws ServletException,IOException;

public void forwardPage(String page, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException,IOException;	

	/**
	 * 根据餐厅ID，获得该餐厅所有套餐信息
	 * */
	public List getAllSetmealById(String id);

	/**
	 * 餐厅发布套餐商品的信息
	 * */
	public void addSetmeal(Restaurant_setmeal_info new_info);

	/**
	 * 修改套餐数量
	 * */
	public void changeNum(Restaurant_setmeal_info new_info);
}
