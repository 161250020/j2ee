package j2ee.service.bean;

import j2ee.factory.DaoFactory;
import j2ee.model.Restaurant_setmeal_info;
import j2ee.service.Restaurant_setmeal_infoManageService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class Restaurant_setmeal_infoManageServiceImpl implements Restaurant_setmeal_infoManageService {

	private static Restaurant_setmeal_infoManageService restaurant_setmeal_infoManageService=new Restaurant_setmeal_infoManageServiceImpl();

	public static Restaurant_setmeal_infoManageService getInstance()
	{
		return restaurant_setmeal_infoManageService;
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
	public List getAllSetmealById(String id) {
		return DaoFactory.getRestaurant_setmeal_infoDao().getAllSetmealById(id);
	}

	@Override
	public void addSetmeal(Restaurant_setmeal_info new_info) {
		DaoFactory.getRestaurant_setmeal_infoDao().insertSetmeal(new_info);
	}

	@Override
	public void changeNum(Restaurant_setmeal_info new_info) {
		DaoFactory.getRestaurant_setmeal_infoDao().updateSetmeal(new_info);
	}
}
