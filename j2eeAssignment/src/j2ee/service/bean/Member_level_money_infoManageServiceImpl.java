package j2ee.service.bean;

import j2ee.factory.DaoFactory;
import j2ee.model.Member_level_money_info;
import j2ee.model.Member_level_preferencialstrategies;
import j2ee.service.Member_level_money_infoManageService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class Member_level_money_infoManageServiceImpl implements Member_level_money_infoManageService {

	private static Member_level_money_infoManageService member_level_money_infoManageService=new Member_level_money_infoManageServiceImpl();

	public static Member_level_money_infoManageService getInstance()
	{
		return member_level_money_infoManageService;
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
	public void changeSum_money(String member_id, double money) {
		DaoFactory.getMember_level_money_infoDao().updateSum_money(member_id, money);
	}

	@Override
	public void upLevelOrNot(String member_id) {
		//获得升级标准
		ArrayList<Member_level_preferencialstrategies> level= (ArrayList<Member_level_preferencialstrategies>) DaoFactory.getMember_level_preferencialstrategiesDao().getAllPss();
		//获得会员当前状态
		Member_level_money_info m=DaoFactory.getMember_level_money_infoDao().getInfoById(member_id);
		double sum_money=m.getSum_money();
		int true_level=0;
		for(int i=0;i<level.size();i++){
			if(sum_money>=level.get(i).getSum_money()){//实际消费达到金钱数
				true_level=level.get(i).getLevel();//修改真实的等级
			}
		}
		//更新会员的当前状态
		DaoFactory.getMember_level_money_infoDao().updateLevel(member_id, true_level);
	}

	@Override
	public Member_level_money_info getInfoById(String member_id) {
		return DaoFactory.getMember_level_money_infoDao().getInfoById(member_id);
	}

	@Override
	public void add_info(Member_level_money_info new_info) {
		DaoFactory.getMember_level_money_infoDao().insert_info(new_info);
	}
}
