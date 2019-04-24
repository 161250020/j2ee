package j2ee.service.bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import j2ee.factory.DaoFactory;
import j2ee.model.Restaurant_preferencial_info;
import j2ee.model.Restaurant_publish_application_info;
import j2ee.model.Restaurant_setmeal_info;
import j2ee.model.Restaurant_singleproduct_info;
import j2ee.service.Restaurant_publish_application_infoManageService;

public class Restaurant_publish_application_infoManageServiceImpl implements  Restaurant_publish_application_infoManageService{

	private static Restaurant_publish_application_infoManageService restaurant_publish_application_infoManageService=new Restaurant_publish_application_infoManageServiceImpl();

	public static Restaurant_publish_application_infoManageService getInstance()
	{
		return restaurant_publish_application_infoManageService;
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
	public Restaurant_publish_application_info getNewInfoById(String id) {
		// TODO Auto-generated method stub
		Restaurant_publish_application_info ret=new Restaurant_publish_application_info();
		ret=DaoFactory.getRestaurant_publish_application_infoDao().getNewInfoById(id);
		
		return ret;
	}

	@Override
	public void addNewPublishInfo(Restaurant_publish_application_info new_info) {
		// TODO Auto-generated method stub
		DaoFactory.getRestaurant_publish_application_infoDao().insertInfo(new_info);
	}

	@Override
	public void changePublishResult(String id, int result, String manager_id) {
		// TODO Auto-generated method stub
		Restaurant_publish_application_info ret=new Restaurant_publish_application_info();
		ret=DaoFactory.getRestaurant_publish_application_infoDao().getNewInfoById(id);
		ret.setResult(result);
		ret.setManager_id(manager_id);
		DaoFactory.getRestaurant_publish_application_infoDao().changeInfo(ret);
		
		if(result==2) {
			//若审批通过，将商品添加到对应的数据库当中
			if(ret.getCom_type()==1) {//单品
				Restaurant_singleproduct_info r=new Restaurant_singleproduct_info();
				r.setId(UUID.randomUUID().toString());
				r.setCom_num(ret.getNum());
				r.setCom_price(ret.getPrice());
				r.setCommodity_name(ret.getName());
				r.setEnd_date(ret.getEnd_date());
				r.setRestaurant_id(ret.getRestaurant_id());
				r.setStart_date(ret.getStart_date());
				DaoFactory.getRestaurant_singleproduct_infoDao().insertSingleproduct(r);
			}
			else if(ret.getCom_type()==2) {//套餐
				Restaurant_setmeal_info r=new Restaurant_setmeal_info();
				r.setId(UUID.randomUUID().toString());
				r.setSetmeal_num(ret.getNum());
				r.setSetmeal_price(ret.getPrice());
				r.setSet_meal_name(ret.getName());
				r.setEnd_date(ret.getEnd_date());
				r.setRestaurant_id(ret.getRestaurant_id());
				r.setStart_date(ret.getStart_date());
				DaoFactory.getRestaurant_setmeal_infoDao().insertSetmeal(r);
			}
			else {//优惠
				Restaurant_preferencial_info r=new Restaurant_preferencial_info();
				r.setId(UUID.randomUUID().toString());
				r.setNum(ret.getNum());
				r.setRaw_price(ret.getRaw_price());
				r.setNow_price(ret.getPrice());
				r.setCom_name(ret.getName());
				r.setEnd_date(ret.getEnd_date());
				r.setRestaurant_id(ret.getRestaurant_id());
				r.setStart_date(ret.getStart_date());
				DaoFactory.getRestaurant_preferencial_infoDao().insertPreferencial(r);
			}
		}
	}

	@Override
	public List getAllPublishApplications() {
		// TODO Auto-generated method stub
		ArrayList<Restaurant_publish_application_info> ret=new ArrayList();
		ArrayList<Restaurant_publish_application_info> arr= (ArrayList<Restaurant_publish_application_info>) DaoFactory.getRestaurant_publish_application_infoDao().getAllPublishApplications();

		if(arr!=null){
			for(int i=0;i<arr.size();i++){
				if(arr.get(i).getResult()==0){//未审批
					ret.add(arr.get(i));
				}
			}
		}

		return ret;
	}

}
