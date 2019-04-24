package j2ee.service;

import j2ee.model.Member_info;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Member_infoManageService{
	public void sentErrorMessage(String message, HttpServletRequest req)
			throws ServletException,IOException;

public void sentMessage(String message, HttpServletRequest req)
			throws ServletException,IOException;

public void forwardPage(String page, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException,IOException;

	/**
	 * 获得所有会员信息---可用于：登录的时候，以免该会员不存在，也可以用于别的时候
	 * */
	public List getAllMembersInfo();

	/**
	 * 根据会员username，获得会员信息
	 * */
	public Member_info getInfoByUsername(String username);

	/**
	 * 修改会员基本信息/密码
	 * */
	public void changeMemberInfo(Member_info newMember);

	/**
	 * 会员注册，添加会员的记录
	 * */
	public void addMemberInfo(Member_info new_member);

}
