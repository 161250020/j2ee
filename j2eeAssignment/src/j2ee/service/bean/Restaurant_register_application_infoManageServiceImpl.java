package j2ee.service.bean;

import j2ee.factory.DaoFactory;
import j2ee.model.Restaurant_register_application_info;
import j2ee.service.Restaurant_register_application_infoManageService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Restaurant_register_application_infoManageServiceImpl implements Restaurant_register_application_infoManageService {

	private static Restaurant_register_application_infoManageService restaurant_register_application_infoManageService=new Restaurant_register_application_infoManageServiceImpl();

	public static Restaurant_register_application_infoManageService getInstance()
	{
		return restaurant_register_application_infoManageService;
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
	public Restaurant_register_application_info getRestRegisterInfo(String id) {
		return DaoFactory.getRestaurant_register_application_infoDao().getRestRegisterInfo(id);
	}

	@Override
	public void changeRegisterResult(String id, int result) {
		DaoFactory.getRestaurant_register_application_infoDao().updateRegisterResult(id, result);
	}

	@Override
	public void changeRegisterManager_id(String id, String manager_id) {
		DaoFactory.getRestaurant_register_application_infoDao().updateRegisterManager_id(id, manager_id);
	}

	@Override
	public List getAllRegisterApplications() {
		ArrayList<Restaurant_register_application_info> arr= (ArrayList<Restaurant_register_application_info>) DaoFactory.getRestaurant_register_application_infoDao().getAllRegisterApplications();

		ArrayList ret=new ArrayList();
		for(int i=0;i<arr.size();i++){
			if(arr.get(i).getResult()==0){
				ret.add(arr.get(i));
			}
		}

		return ret;
	}

	@Override
	public List getAllRegisterApplications_total() {
		ArrayList<Restaurant_register_application_info> arr= (ArrayList<Restaurant_register_application_info>) DaoFactory.getRestaurant_register_application_infoDao().getAllRegisterApplications();

		return arr;
	}

	@Override
	public void addARegisterApplication(Restaurant_register_application_info new_info) {
		DaoFactory.getRestaurant_register_application_infoDao().insertARegisterApplication(new_info);
	}
}
