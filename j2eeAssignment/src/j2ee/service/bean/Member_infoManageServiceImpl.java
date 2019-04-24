package j2ee.service.bean;

import j2ee.factory.DaoFactory;
import j2ee.model.Member_info;
import j2ee.service.Member_infoManageService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class Member_infoManageServiceImpl implements Member_infoManageService {

	private static Member_infoManageService member_infoManageService=new Member_infoManageServiceImpl();

	public static Member_infoManageService getInstance()
	{
		return member_infoManageService;
	}

	public void sentErrorMessage(String message,HttpServletRequest req)
			throws ServletException,IOException
	{
		req.setAttribute("message",message);
	}

	public void sentMessage(String message,HttpServletRequest req)
			throws ServletException,IOException
	{
		req.setAttribute("message",message);
	}

	public void forwardPage(String page,HttpServletRequest req,HttpServletResponse resp)
			throws ServletException,IOException
	{
		RequestDispatcher dispater=req.getRequestDispatcher(resp.encodeURL(page));
		dispater.forward(req,resp);
	}

	@Override
	public List getAllMembersInfo() {
		return DaoFactory.getMember_infoDao().getAllMembersInfo();
	}

	@Override
	public Member_info getInfoByUsername(String username) {
		return DaoFactory.getMember_infoDao().getInfoByUsername(username);
	}

	@Override
	public void changeMemberInfo(Member_info newMember) {
		DaoFactory.getMember_infoDao().updateMemberInfo(newMember);
	}

	@Override
	public void addMemberInfo(Member_info new_member) {
		DaoFactory.getMember_infoDao().insertMemberInfo(new_member);
	}
}
