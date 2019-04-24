package j2ee.service.bean;

import j2ee.factory.DaoFactory;
import j2ee.service.Member_level_preferencialstrategiesManageService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class Member_level_preferencialstrategiesManageServiceImpl implements Member_level_preferencialstrategiesManageService {

	private static Member_level_preferencialstrategiesManageService member_level_preferencialstrategiesManageService=new Member_level_preferencialstrategiesManageServiceImpl();

	public static Member_level_preferencialstrategiesManageService getInstance()
	{
		return member_level_preferencialstrategiesManageService;
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
	public List getAllPss() {
		return DaoFactory.getMember_level_preferencialstrategiesDao().getAllPss();
	}
}
