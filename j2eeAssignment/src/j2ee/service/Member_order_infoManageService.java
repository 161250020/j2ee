package j2ee.service;

import j2ee.model.Member_order_info;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Member_order_infoManageService{
	public void sentErrorMessage(String message, HttpServletRequest req)
			throws ServletException,IOException;

	public void sentMessage(String message, HttpServletRequest req)
			throws ServletException,IOException;

	public void forwardPage(String page, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException,IOException;
	/**
	 * 根据订单时间获得订单的信息
	 * */
	public List getInfoBetDates(Date date1, Date date2);

	/**
	 * 获得所有订单成功的订单信息，用于结算时计算：共计获得金额
	 * */
	public List getSuccessOrders();
	
	/**
	 * 根据订单时间获得订单的信息---按餐厅排序
	 * */
	public List getInfoBetDates_rest(Date date1, Date date2);

	/**
	 * 根据订单时间获得订单的信息---按会员排序
	 * */
	public List getInfoBetDates_member(Date date1, Date date2);

	/**
	 * 根据月份获得赚取金额（成功的订单），发出且已使用的优惠
	 * 存储在ArrayList<Double>当中
	 * */
	public List getInfoByDate2(Date date1, Date date2);

	/**
	 * 根据订单id获得订单的信息
	 * */
	public Member_order_info getInfoById(String id);

	/**
	 * 根据会员的ID，获得会员的所有订单信息
	 * */
	public List getAllOrdersByMemberId(String member_id);

	/**
	 * 根据餐厅的ID，获得餐厅的所有订单信息
	 * */
	public List getAllOrdersByRestId(String rest_id);

	/**
	 * 退单：
	 * 根据订单的ID，修改订单的信息---
	 * 根据state来退货，state为2则无法退货，state为1则可以退款一半，state为0可全额退款，
	 * 且修改result为0，以表示退货
	 * 记得将钱款修改到会员账户上面
	 * */
	public void changeAfterUnsubscribeByIdAndState(String id);

	/**
	 * 下单成功，创建新的订单基本信息
	 * */
	public void addANewOrder(Member_order_info new_order_info);

	/**
	 * 修改订单信息
	 * */
	public void change_info(Member_order_info new_order_info);

	/**
	 * 系统自动确认订单送达
	 * 支付时间超过系统推荐时间，自动确认订单送达
	 * */
	public void ensure_order_arrived_os();

	/**
	 * 根据月份获得总订单数，总消费金额，总退单数，总退单金额
	 * 存储在ArrayList<Double>当中
	 * */
	public List getInfoByDate(java.util.Date time1,java.util.Date time2);
}
