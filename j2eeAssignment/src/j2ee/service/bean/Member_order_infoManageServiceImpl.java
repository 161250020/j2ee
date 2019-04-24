package j2ee.service.bean;

import j2ee.factory.DaoFactory;
import j2ee.factory.ServiceFactory;
import j2ee.model.Cyber_bank_info;
import j2ee.model.Member_info;
import j2ee.model.Member_order_info;
import j2ee.model.Restaurant_info;
import j2ee.service.Member_order_infoManageService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Member_order_infoManageServiceImpl implements Member_order_infoManageService {

	private static Member_order_infoManageService member_order_infoManageService=new Member_order_infoManageServiceImpl();

	public static Member_order_infoManageService getInstance()
	{
		return member_order_infoManageService;
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
	public List getSuccessOrders() {
		ArrayList<Member_order_info> arr=new ArrayList();
		arr= (ArrayList<Member_order_info>) DaoFactory.getMember_order_infoDao().getAllOrders();

		ArrayList ret=new ArrayList();
		for(int i=0;i<arr.size();i++){
			if((arr.get(i).getResult()==2)&&(arr.get(i).getState()==3)){
				ret.add(arr.get(i));
			}
		}
		return ret;
	}
	
	@Override
	public List getInfoBetDates(Date date1, Date date2) {
		ArrayList<Member_order_info> ret=new ArrayList();

		long d1=date1.getTime();
		long d2=date2.getTime();

		ArrayList<Member_order_info> orders= (ArrayList<Member_order_info>) DaoFactory.getMember_order_infoDao().getAllOrders();

		for(int i=0;i<orders.size();i++){
			long temp=orders.get(i).getOrder_time().getTime();
			if((temp>=d1)&&(temp<=d2)){//符合时间的订单
				ret.add(orders.get(i));
			}
		}

		return ret;
	}

	@Override
	public List getInfoBetDates_rest(Date date1, Date date2) {
		ArrayList<Member_order_info> ret=new ArrayList();

		long d1=date1.getTime();
		long d2=date2.getTime();

		ArrayList<Member_order_info> orders= (ArrayList<Member_order_info>) DaoFactory.getMember_order_infoDao().getAllOrders_rest();

		for(int i=0;i<orders.size();i++){
			long temp=orders.get(i).getOrder_time().getTime();
			if((temp>=d1)&&(temp<=d2)){//符合时间的订单
				ret.add(orders.get(i));
			}
		}

		return ret;
	}

	@Override
	public List getInfoBetDates_member(Date date1, Date date2) {
		ArrayList<Member_order_info> ret=new ArrayList();

		long d1=date1.getTime();
		long d2=date2.getTime();

		ArrayList<Member_order_info> orders= (ArrayList<Member_order_info>) DaoFactory.getMember_order_infoDao().getAllOrders_member();

		for(int i=0;i<orders.size();i++){
			long temp=orders.get(i).getOrder_time().getTime();
			if((temp>=d1)&&(temp<=d2)){//符合时间的订单
				ret.add(orders.get(i));
			}
		}

		return ret;
	}

	@Override
	public List getInfoByDate2(Date date1, Date date2) {//根据月份获得赚取金额（成功的订单），发出且已使用的优惠
		ArrayList<Double> ret=new ArrayList();

		long d1=date1.getTime();
		long d2=date2.getTime();

		ArrayList<Member_order_info> orders= (ArrayList<Member_order_info>) DaoFactory.getMember_order_infoDao().getAllOrders();
		double num1=0;
		double num2=0;

		//计算数字
		for(int i=0;i<orders.size();i++){
			long temp=orders.get(i).getOrder_time().getTime();
			if((temp>=d1)&&(temp<=d2)){//符合时间的订单
				if((orders.get(i).getResult()==2)&&(orders.get(i).getState()==3)){//成功的订单
					num1=num1+orders.get(i).getSum_price();
				}
				if((orders.get(i).getResult()==0)||(orders.get(i).getResult()==2)){//支付成功的订单
					num2=num2+orders.get(i).getPs_money_yummy();
				}
			}
		}

		ret.add(num1);
		ret.add(num2);
		return ret;
	}

	@Override
	public Member_order_info getInfoById(String id) {
		Member_order_info m=new Member_order_info();
		m=DaoFactory.getMember_order_infoDao().getInfoById(id);
		return m;
	}

	@Override
	public List getAllOrdersByMemberId(String member_id) {
		ArrayList<Member_order_info> arr=new ArrayList();
		arr= (ArrayList<Member_order_info>) DaoFactory.getMember_order_infoDao().getAllOrders();

		ArrayList ret=new ArrayList();
		for(int i=0;i<arr.size();i++){
			if(arr.get(i).getMember_id().equals(member_id)){
				ret.add(arr.get(i));
			}
		}
		return ret;
	}

	@Override
	public List getAllOrdersByRestId(String rest_id) {
		ArrayList<Member_order_info> arr=new ArrayList();
		arr= (ArrayList<Member_order_info>) DaoFactory.getMember_order_infoDao().getAllOrders();

		ArrayList ret=new ArrayList();
		for(int i=0;i<arr.size();i++){
			if(arr.get(i).getRestaurant_id().equals(rest_id)){
				ret.add(arr.get(i));
			}
		}
		return ret;
	}

	@Override
	public void changeAfterUnsubscribeByIdAndState(String id) {
		Member_order_info m=DaoFactory.getMember_order_infoDao().getInfoById(id);
		m.setResult(0);
		//修改订单结果
		DaoFactory.getMember_order_infoDao().updateInfo(m);
		//退款给用户和付款给餐厅
		int state=m.getState();
		//可退款金额
		double ret_money=0;
		double rest_money=0;
		if(state==0){
			ret_money=m.getSum_price();//全退给会员
		}
		else if(state==1){//一半给会员，一半给餐厅
			ret_money=m.getSum_price()/2;
			rest_money=ret_money;
		}
		else{//一半给餐厅，一半留在yummy
			rest_money=m.getSum_price()/2;
		}
		//yummy公司金额减少
		double pre_money_yummy=ServiceFactory.getCyber_bank_infoManageService().getMoneyById("yummy");
		ServiceFactory.getCyber_bank_infoManageService().changeMoneyById("yummy",pre_money_yummy-ret_money-rest_money);
		//将退款金额返回给会员的账户
		Member_info member=DaoFactory.getMember_infoDao().getInfoById(m.getMember_id());//会员信息
		String bank_member_id=member.getBank_id();//会员银行的ID
		Cyber_bank_info bank_member_info=DaoFactory.getCyber_bank_infoDao().getInfoById(bank_member_id);//会员银行信息
		DaoFactory.getCyber_bank_infoDao().updateMoney(bank_member_id, bank_member_info.getMoney()+ret_money);//退款给用户
		//将部分金额给餐厅当作损失的补偿，如果（state为2，3状态）是仅退款一半给餐厅，一半留在yummy公司
		Restaurant_info rest=DaoFactory.getRestaurant_infoDao().getInfoBy7Chars(m.getRestaurant_id());
		String rest_bank_id=rest.getBank_id();
		Cyber_bank_info bank_rest_info=DaoFactory.getCyber_bank_infoDao().getInfoById(rest_bank_id);//餐厅银行信息
		DaoFactory.getCyber_bank_infoDao().updateMoney(rest_bank_id, bank_rest_info.getMoney()+rest_money);//退款给餐厅
	}

	@Override
	public void addANewOrder(Member_order_info new_order_info) {
		DaoFactory.getMember_order_infoDao().insertInfo(new_order_info);
	}

	@Override
	public void change_info(Member_order_info new_order_info) {
		DaoFactory.getMember_order_infoDao().updateInfo(new_order_info);
	}

	@Override
	public void ensure_order_arrived_os() {
		//获得所有订单信息
		ArrayList<Member_order_info> Member_order_infos= (ArrayList<Member_order_info>) DaoFactory.getMember_order_infoDao().getAllOrders();

		for(int i=0;i<Member_order_infos.size();i++){
			//获得当前时间
			Timestamp ts1=new Timestamp(new Date().getTime());

			//获得订单支付时间
			Timestamp ts2=Member_order_infos.get(i).getPay_time();
			if(ts2!=null){
				long time_ts2=ts2.getTime();//获得改时间的时间戳
				if(Member_order_infos.get(i).getDelivery_time_os()!=null){
					//获得订单系统推荐送达的分钟
					int timeos= Integer.parseInt(Member_order_infos.get(i).getDelivery_time_os());
					//计算可以确认送达的时间
					time_ts2=time_ts2+timeos*60*1000;//延时timeos分钟
					Timestamp ts3=new Timestamp(time_ts2);//计算延时之后的时间
					if(ts3.getTime()<=ts1.getTime()){//计算延时之后的时间<=当前时间
						//修改订单的状态---系统自动确认送达
						Member_order_info m=Member_order_infos.get(i);
						m.setState(3);
						DaoFactory.getMember_order_infoDao().updateInfo(m);
					}

				}
			}
		}
	}

	@Override
	public List getInfoByDate(java.util.Date time1,java.util.Date time2) {//根据月份获得总订单数，总消费金额，总退单数，总优惠金额
		ArrayList<Double> ret=new ArrayList();

		long d1=time1.getTime();
		long d2=time2.getTime();

		ArrayList<Member_order_info> orders= (ArrayList<Member_order_info>) DaoFactory.getMember_order_infoDao().getAllOrders();
		double num1=0;
		double num2=0;
		double num3=0;
		double num4=0;

		//计算数字
		for(int i=0;i<orders.size();i++){
			long temp=orders.get(i).getOrder_time().getTime();
			if((temp>=d1)&&(temp<=d2)){//符合时间的订单
				num1=num1+1;
				num2=num2+orders.get(i).getSum_price();
				if(orders.get(i).getResult()==0){
					num3=num3+1;
				}
				num4=num4+orders.get(i).getPs_money_yummy();
			}
		}

		ret.add(num1);
		ret.add(num2);
		ret.add(num3);
		ret.add(num4);
		return ret;
	}
}
