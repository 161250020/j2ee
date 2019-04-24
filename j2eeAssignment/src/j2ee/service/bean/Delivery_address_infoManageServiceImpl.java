package j2ee.service.bean;

import j2ee.factory.DaoFactory;
import j2ee.model.Delivery_address_info;
import j2ee.service.Delivery_address_infoManageService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Delivery_address_infoManageServiceImpl implements Delivery_address_infoManageService {

	private static Delivery_address_infoManageService delivery_address_infoManageService=new Delivery_address_infoManageServiceImpl();

	public static Delivery_address_infoManageService getInstance()
	{
		return delivery_address_infoManageService;
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
	public void changeAddressInfo(Delivery_address_info new_info) {
		DaoFactory.getDelivery_address_infoDao().updateAddress(new_info);
	}

	@Override
	public List getAllAddressByID(String id) {
		return DaoFactory.getDelivery_address_infoDao().getAllAddressByID(id);
	}

	@Override
	public Delivery_address_info getDelivery_address_infoById(String id) {
		Delivery_address_info ret=new Delivery_address_info();
		ArrayList<Delivery_address_info> dais= (ArrayList<Delivery_address_info>) DaoFactory.getDelivery_address_infoDao().getAllAdd();

		for(int i=0;i<dais.size();i++){
			if(dais.get(i).getId().equals(id)){
				ret=dais.get(i);
			}
		}

		return ret;
	}

	@Override
	public void add_new_address(Delivery_address_info new_info) {
		DaoFactory.getDelivery_address_infoDao().addInfo(new_info);
	}
}
