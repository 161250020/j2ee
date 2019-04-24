package j2ee.service.bean;

import j2ee.factory.DaoFactory;
import j2ee.model.Restaurant_modify_application_info;
import j2ee.service.Restaurant_modify_application_infoManageService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Restaurant_modify_application_infoManageServiceImpl implements Restaurant_modify_application_infoManageService {

	private static Restaurant_modify_application_infoManageService restaurant_modify_application_infoManageService=new Restaurant_modify_application_infoManageServiceImpl();

	public static Restaurant_modify_application_infoManageService getInstance()
	{
		return restaurant_modify_application_infoManageService;
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
	public Restaurant_modify_application_info getNewInfoById(String id) {
		return DaoFactory.getRestaurant_modify_application_infoDao().getNewInfoById(id);
	}

	@Override
	public void addNewModifyInfo(Restaurant_modify_application_info new_info) {
		DaoFactory.getRestaurant_modify_application_infoDao().insertInfo(new_info);
	}

	@Override
	public void changeModifyResult(String id, int result) {
		Restaurant_modify_application_info r=DaoFactory.getRestaurant_modify_application_infoDao().getNewInfoById(id);
		r.setResult(result);
		DaoFactory.getRestaurant_modify_application_infoDao().changeModifyResult(r);
	}

	@Override
	public void changeModifyManager_id(String id, String manager_id) {
		Restaurant_modify_application_info r=DaoFactory.getRestaurant_modify_application_infoDao().getNewInfoById(id);
		r.setManager_id(manager_id);
		DaoFactory.getRestaurant_modify_application_infoDao().changeModifyResult(r);
	}

	@Override
	public List getAllModifyApplications() {
		ArrayList<Restaurant_modify_application_info> arr= (ArrayList<Restaurant_modify_application_info>) DaoFactory.getRestaurant_modify_application_infoDao().getAllModifyApplications();

		ArrayList ret=new ArrayList();
		for(int i=0;i<arr.size();i++){
			if(arr.get(i).getResult()==0){
				ret.add(arr.get(i));
			}
		}

		return ret;
	}

}
