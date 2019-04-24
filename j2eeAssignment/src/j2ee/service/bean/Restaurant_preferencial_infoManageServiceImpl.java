package j2ee.service.bean;

import j2ee.factory.DaoFactory;
import j2ee.model.Restaurant_preferencial_info;
import j2ee.service.Restaurant_preferencial_infoManageService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class Restaurant_preferencial_infoManageServiceImpl implements Restaurant_preferencial_infoManageService {

	private static Restaurant_preferencial_infoManageService restaurant_preferencial_infoManageService=new Restaurant_preferencial_infoManageServiceImpl();

	public static Restaurant_preferencial_infoManageService getInstance()
	{
		return restaurant_preferencial_infoManageService;
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
	public List getAllPreferencialById(String id) {
		return DaoFactory.getRestaurant_preferencial_infoDao().getAllPreferencialById(id);
	}

	@Override
	public void addPreferencial(Restaurant_preferencial_info new_info) {
		DaoFactory.getRestaurant_preferencial_infoDao().insertPreferencial(new_info);
	}

	@Override
	public void changeNum(Restaurant_preferencial_info new_info) {
		DaoFactory.getRestaurant_preferencial_infoDao().updatePreferencial(new_info);
	}
}
