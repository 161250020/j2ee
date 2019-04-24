package j2ee.service;

import j2ee.model.Member_level_money_info;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Member_level_money_infoManageService{
	public void sentErrorMessage(String message, HttpServletRequest req)
			throws ServletException,IOException;

public void sentMessage(String message, HttpServletRequest req)
			throws ServletException,IOException;

public void forwardPage(String page, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException,IOException;


	/**
	 * 会员下单成功之后，修改会员消费的金额，将本次消费的金额（money）添加上去
	 * */
	public void changeSum_money(String member_id, double money);

	/**
	 * 会员下单成功之后，计算会员是否升级（根据会员消费总金额），若升级，则修改会员的信息
	 * */
	public void upLevelOrNot(String member_id);

	/**
	 * 获得会员（根据会员ID）的该对应表格的信息
	 * */
	public Member_level_money_info getInfoById(String member_id);

	/**
	 * 插入一条消息
	 * */
	public void add_info(Member_level_money_info new_info);
}
