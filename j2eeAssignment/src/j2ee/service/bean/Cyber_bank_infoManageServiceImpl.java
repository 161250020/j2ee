package j2ee.service.bean;

import j2ee.factory.DaoFactory;
import j2ee.service.Cyber_bank_infoManageService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class Cyber_bank_infoManageServiceImpl implements Cyber_bank_infoManageService {

	private static Cyber_bank_infoManageService cyber_bank_infoManageService=new Cyber_bank_infoManageServiceImpl();

	public static Cyber_bank_infoManageService getInstance()
	{
		return cyber_bank_infoManageService;
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
	public void changeMoneyById(String id, double changeToMoney) {
		DaoFactory.getCyber_bank_infoDao().updateMoney(id, changeToMoney);
	}

	@Override
	public double getMoneyById(String id) {
		double ret=DaoFactory.getCyber_bank_infoDao().getInfoById(id).getMoney();
		return ret;
	}

	@Override
	public List getAllBankInfo() {
		return DaoFactory.getCyber_bank_infoDao().getAllInfo();
	}

	@Override
	public String getPassById(String id) {
		String pass=DaoFactory.getCyber_bank_infoDao().getInfoById(id).getPassword();
		return pass;
	}
}
