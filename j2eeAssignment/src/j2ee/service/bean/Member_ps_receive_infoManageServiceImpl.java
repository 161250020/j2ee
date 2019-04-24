package j2ee.service.bean;

import j2ee.factory.DaoFactory;
import j2ee.model.Member_info;
import j2ee.model.Member_level_money_info;
import j2ee.model.Member_level_preferencialstrategies;
import j2ee.model.Member_ps_receive_info;
import j2ee.service.Member_ps_receive_infoManageService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Member_ps_receive_infoManageServiceImpl implements Member_ps_receive_infoManageService {

	private static Member_ps_receive_infoManageService member_ps_receive_infoManageService=new Member_ps_receive_infoManageServiceImpl();

	public static Member_ps_receive_infoManageService getInstance()
	{
		return member_ps_receive_infoManageService;
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
	public void changeResultByMember_idAndDate(String member_id, String date) {
		DaoFactory.getMember_ps_receive_infoDao().updateResultByMember_idAndDate(member_id, date);
	}

	@Override
	public Member_ps_receive_info getMemberPsMoney(String id) {
		Date day=new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String date=formatter.format(day);
		Member_ps_receive_info m=DaoFactory.getMember_ps_receive_infoDao().getMemberPsMoney(id,date);

		return m;
	}

	@Override
	public void send_ps(String id) {
		Member_info member_info=DaoFactory.getMember_infoDao().getInfoById(id);
		Member_level_money_info mlmi=DaoFactory.getMember_level_money_infoDao().getInfoById(member_info.getId());
		ArrayList<Member_level_preferencialstrategies> mlps= (ArrayList<Member_level_preferencialstrategies>) DaoFactory.getMember_level_preferencialstrategiesDao().getAllPss();
		Member_level_preferencialstrategies mlp=new Member_level_preferencialstrategies();//现实当中优惠等级
		for(int i=0;i<mlps.size();i++){
			if(mlps.get(i).getLevel()==mlmi.getLevel()){
				mlp=mlps.get(i);
				break;
			}
		}

		Member_ps_receive_info m=new Member_ps_receive_info();
		m.setId(UUID.randomUUID().toString());
		m.setResult(0);
		m.setDate(new java.sql.Date(new java.util.Date().getTime()));
		m.setMember_id(id);
		m.setPs_money(mlp.getPs_money());
		DaoFactory.getMember_ps_receive_infoDao().insertInfo(m);
	}

}
