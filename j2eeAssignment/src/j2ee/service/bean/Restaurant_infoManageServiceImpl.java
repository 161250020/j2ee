package j2ee.service.bean;

import j2ee.factory.DaoFactory;
import j2ee.factory.ServiceFactory;
import j2ee.model.Restaurant_info;
import j2ee.model.Restaurant_modify_application_info;
import j2ee.model.Restaurant_register_application_info;
import j2ee.service.Restaurant_infoManageService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class Restaurant_infoManageServiceImpl implements Restaurant_infoManageService {

	private static Restaurant_infoManageService restaurant_infoManageService=new Restaurant_infoManageServiceImpl();

	public static Restaurant_infoManageService getInstance()
	{
		return restaurant_infoManageService;
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
	public Restaurant_info getInfoBy7Chars(String chars) {
		return DaoFactory.getRestaurant_infoDao().getInfoBy7Chars(chars);
	}

	@Override
	public void modify_info(Restaurant_info new_info) {
		DaoFactory.getRestaurant_infoDao().updateRestInfoBy7Chars(new_info);
	}

	@Override
	public void modifyRestInfoBy7Chars(Restaurant_modify_application_info newInfo) {
		Restaurant_info r=DaoFactory.getRestaurant_infoDao().getInfoBy7Chars(newInfo.getRestaurant_id());
		r.setName(newInfo.getNew_name());
		r.setAddress(newInfo.getNew_address());
		r.setType_id(newInfo.getNew_type_id());
		r.setCity(newInfo.getNew_city());
		r.setDistrict(newInfo.getNew_district());
		r.setTown(newInfo.getNew_town());
		r.setStreet(newInfo.getNew_street());
		DaoFactory.getRestaurant_infoDao().updateRestInfoBy7Chars(r);
	}

	@Override
	public void addNewRestInfo(Restaurant_register_application_info newRestInfo) {
		Restaurant_info r=new Restaurant_info();
		String uuid= UUID.randomUUID().toString();
		r.setId(uuid);
		r.setLogin_id(newRestInfo.getLogin_id());
		r.setPassword(newRestInfo.getPassword());
		r.setMail(newRestInfo.getMail());
		r.setName(newRestInfo.getName());
		r.setDate(newRestInfo.getDate());
		r.setAddress(newRestInfo.getAddress());
		r.setType_id(newRestInfo.getType_id());
		r.setCity(newRestInfo.getCity());
		r.setDistrict(newRestInfo.getDistrict());
		r.setTown(newRestInfo.getTown());
		r.setStreet(newRestInfo.getStreet());
		r.setBank_id(newRestInfo.getBank_id());
		DaoFactory.getRestaurant_infoDao().insertInfo(r);
	}

	@Override
	public List getAllRestsInfo() {
		return DaoFactory.getRestaurant_infoDao().getAllRestsInfo();
	}

	@Override
	public String get7Chars() {
		ArrayList<Restaurant_info> arr= (ArrayList<Restaurant_info>) DaoFactory.getRestaurant_infoDao().getAllRestsInfo();
		ArrayList<String> arr2=new ArrayList();//存储已生成的随机数
		for(int i=0;i<arr.size();i++){
			arr2.add(arr.get(i).getLogin_id());
		}

		String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		boolean get=false;
		String newChar="";
		while(!get){
			StringBuffer sb = new StringBuffer();
			Random random = new Random();
			for (int i = 0; i < 7; i++) {
				sb.append(allChar.charAt(random.nextInt(allChar.length())));
			}
			newChar=sb.toString();
			if(!arr2.contains(newChar)){
				get=true;
			}
		}

		return newChar;
	}

	@Override
	public int countRegisterNum(Date date1, Date date2) {
		long d1=date1.getTime();
		long d2=date2.getTime();
		int ret=0;

		ArrayList<Restaurant_info> rest_infos= (ArrayList<Restaurant_info>) ServiceFactory.getRestaurant_infoManageService().getAllRestsInfo();
		for(int i=0;i<rest_infos.size();i++){
			long temp=rest_infos.get(i).getDate().getTime();
			if((temp>=d1)&&(temp<=d2)){
				ret=ret+1;
			}
		}

		return ret;
	}
}
