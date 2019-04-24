package j2ee.service;

import j2ee.model.Member_order_content_info;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Member_order_content_infoManageService{
	public void sentErrorMessage(String message, HttpServletRequest req)
			throws ServletException,IOException;

public void sentMessage(String message, HttpServletRequest req)
			throws ServletException,IOException;

public void forwardPage(String page, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException,IOException;	


	/**
	 * 新订单创建之后：新增订单记录的餐厅商品的内容
	 * */
	public void addARecord(Member_order_content_info new_info);

	/**
	 * 根据order_list_id获得订单的商品的内容
	 * */
	public List orderContentByOrder_list_id(String order_list_id);
}
