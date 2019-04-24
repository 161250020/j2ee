package j2ee.service;

import j2ee.model.Member_ps_receive_info;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Member_ps_receive_infoManageService{
	public void sentErrorMessage(String message, HttpServletRequest req)
			throws ServletException,IOException;

public void sentMessage(String message, HttpServletRequest req)
			throws ServletException,IOException;

public void forwardPage(String page, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException,IOException;	


	/**
	 * 获得发出红包并被使用了的优惠信息，用于结算时计算：发出优惠总金额
	 * */
	//public List getAllUsedPss();

	/**
	 * 修改红包使用信息，根据member_id和date
	 * */
	public void changeResultByMember_idAndDate(String member_id, String date);

	/**
	 * 获得该用户当天优惠券使用情况
	 * */
	public Member_ps_receive_info getMemberPsMoney(String id);

	/**
	 * 为用户发送当天的红包，id为用户的ID
	 * */
	public void send_ps(String id);
}
