package j2ee.service.bean;

import j2ee.factory.DaoFactory;
import j2ee.service.Manager_infoManageService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class Manager_infoManageServiceImpl implements Manager_infoManageService {

	private static Manager_infoManageService manager_infoManageService=new Manager_infoManageServiceImpl();

	public static Manager_infoManageService getInstance()
	{
		return manager_infoManageService;
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
	public String getPasswordByUsername(String username) {
		return DaoFactory.getManager_infoDao().getPasswordByUsername(username);
	}

	@Override
	public List getAllManagers() {
		return DaoFactory.getManager_infoDao().getAllManagers();
	}
}
