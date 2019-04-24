package j2ee.service.bean;

import j2ee.factory.DaoFactory;
import j2ee.model.Restaurant_singleproduct_info;
import j2ee.service.Restaurant_singleproduct_infoManageService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class Restaurant_singleproduct_infoManageServiceImpl implements Restaurant_singleproduct_infoManageService {

	private static Restaurant_singleproduct_infoManageService restaurant_singleproduct_infoManageService=new Restaurant_singleproduct_infoManageServiceImpl();

	public static Restaurant_singleproduct_infoManageService getInstance()
	{
		return restaurant_singleproduct_infoManageService;
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
	public List getAllSingleproductById(String id) {
		return DaoFactory.getRestaurant_singleproduct_infoDao().getAllSingleproductById(id);
	}

	@Override
	public void addSingleproduct(Restaurant_singleproduct_info new_info) {
		DaoFactory.getRestaurant_singleproduct_infoDao().insertSingleproduct(new_info);
	}

	@Override
	public void changeNum(Restaurant_singleproduct_info new_info) {
		DaoFactory.getRestaurant_singleproduct_infoDao().updateSingleproduct(new_info);
	}
}
