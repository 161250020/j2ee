package j2ee.service;

import j2ee.model.Delivery_address_info;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Delivery_address_infoManageService{
	public void sentErrorMessage(String message, HttpServletRequest req)
			throws ServletException,IOException;

public void sentMessage(String message, HttpServletRequest req)
			throws ServletException,IOException;

public void forwardPage(String page, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException,IOException;

	/**
	 * 修改会员地址信息
	 * */
	public void changeAddressInfo(Delivery_address_info new_info);

	/**
	 * 根据会员ID，获得该会员的送货地址
	 * */
	public List getAllAddressByID(String id);

	/**
	 * 根据地址ID获得地址信息
	 * */
	public Delivery_address_info getDelivery_address_infoById(String id);

	/**
	 * 添加新地址
	 * */
	public void add_new_address(Delivery_address_info new_info);
}
