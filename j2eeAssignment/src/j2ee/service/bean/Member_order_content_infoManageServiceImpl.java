package j2ee.service.bean;

import j2ee.factory.DaoFactory;
import j2ee.model.Member_order_content_info;
import j2ee.service.Member_order_content_infoManageService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class Member_order_content_infoManageServiceImpl implements Member_order_content_infoManageService {

	private static Member_order_content_infoManageService member_order_content_infoManageService=new Member_order_content_infoManageServiceImpl();

	public static Member_order_content_infoManageService getInstance()
	{
		return member_order_content_infoManageService;
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
	public void addARecord(Member_order_content_info new_info) {
		DaoFactory.getMember_order_content_infoDao().insertInfo(new_info);
	}

	@Override
	public List orderContentByOrder_list_id(String order_list_id) {
		return DaoFactory.getMember_order_content_infoDao().receiveInfoByOrder_list_id(order_list_id);
	}
}
