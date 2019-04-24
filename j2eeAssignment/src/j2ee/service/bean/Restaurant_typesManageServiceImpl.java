package j2ee.service.bean;

import j2ee.factory.DaoFactory;
import j2ee.model.Restaurant_types;
import j2ee.service.Restaurant_typesManageService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class Restaurant_typesManageServiceImpl implements Restaurant_typesManageService {

	private static Restaurant_typesManageService restaurant_typesManageService=new Restaurant_typesManageServiceImpl();

	public static Restaurant_typesManageService getInstance()
	{
		return restaurant_typesManageService;
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
	public String getTypeNameById(int id) {
		ArrayList<Restaurant_types> arr= (ArrayList<Restaurant_types>) DaoFactory.getRestaurant_typesDao().getAllTypesInfo();

		String ret="";
		for(int i=0;i<arr.size();i++){
			if(arr.get(i).getId()==id){
				ret=arr.get(i).getType_name();
				break;
			}
		}

		return ret;
	}

	@Override
	public int getIdByTypeName(String name) {
		ArrayList<Restaurant_types> arr= (ArrayList<Restaurant_types>) DaoFactory.getRestaurant_typesDao().getAllTypesInfo();

		int ret=0;
		for(int i=0;i<arr.size();i++){
			if(arr.get(i).getType_name().equals(name)){
				ret=arr.get(i).getId();
				break;
			}
		}

		return ret;
	}
}
