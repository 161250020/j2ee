package j2ee.service.bean;

import j2ee.factory.DaoFactory;
import j2ee.model.Yummy_settle_accounts_info;
import j2ee.service.Yummy_settle_accounts_infoManageService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class Yummy_settle_accounts_infoManageServiceImpl implements Yummy_settle_accounts_infoManageService {

	private static Yummy_settle_accounts_infoManageService yummy_settle_accounts_infoManageService=new Yummy_settle_accounts_infoManageServiceImpl();

	public static Yummy_settle_accounts_infoManageService getInstance()
	{
		return yummy_settle_accounts_infoManageService;
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
	public String getLastAccountDateTime() {
		ArrayList<Yummy_settle_accounts_info> arr = (ArrayList<Yummy_settle_accounts_info>) DaoFactory.getYummy_settle_accounts_infoDao().getAllInfo();

		String ret="本次结算为第一次结算，未有上次结算时间！";//如果没有任何记录，就返回这个
		if(arr.size()>0) {
			Long max_time= arr.get(0).getAccount_time().getTime();
			ret=arr.get(0).getAccount_time().toString();
			for(int i=0;i<arr.size();i++){
				if(arr.get(i).getAccount_time().getTime()>max_time){
					max_time=arr.get(i).getAccount_time().getTime();
					ret=arr.get(i).getAccount_time().toString();
				}
			}
		}

		return ret;
	}

	@Override
	public void addOneInfo(Yummy_settle_accounts_info newInfo) {
		DaoFactory.getYummy_settle_accounts_infoDao().insertInfo(newInfo);
	}
}
