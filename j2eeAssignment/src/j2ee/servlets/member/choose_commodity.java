package j2ee.servlets.member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import j2ee.factory.ServiceFactory;
import j2ee.model.*;

/**
 * Servlet implementation class choose_commodity
 */
@WebServlet("/order_meal/choose_commodity")
public class choose_commodity extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public choose_commodity() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession(true);
		ServletContext context = getServletContext();
		
		//错误原因
		String choose_commodity_err_info="";
		//获得餐厅的基本信息
		Restaurant_info rest_info=(Restaurant_info) session.getAttribute("choose_rest_info");
		//获得显示的商品的信息---从数据库中获得商品现在的信息
		//单品的基本信息
		ArrayList<Restaurant_singleproduct_info> restaurant_singleproduct_infos2=(ArrayList<Restaurant_singleproduct_info>) ServiceFactory.getRestaurant_singleproduct_infoManageService().getAllSingleproductById(rest_info.getLogin_id());
		ArrayList<Restaurant_singleproduct_info> restaurant_singleproduct_infos=new ArrayList();
		for(int i=0;i<restaurant_singleproduct_infos2.size();i++) {
			long today=new java.util.Date().getTime();
			if((restaurant_singleproduct_infos2.get(i).getStart_date()!=null)&&(restaurant_singleproduct_infos2.get(i).getEnd_date()!=null)) {
				long t1=restaurant_singleproduct_infos2.get(i).getStart_date().getTime();
				long t2=restaurant_singleproduct_infos2.get(i).getEnd_date().getTime();
				if((t1<=today)&&(t2>=today)) {
					restaurant_singleproduct_infos.add(restaurant_singleproduct_infos2.get(i));
				}
			}
		}
		session.setAttribute("choose_rest_singleproduct", restaurant_singleproduct_infos);
		//套餐的基本信息
		ArrayList<Restaurant_setmeal_info> restaurant_setmeal_infos2=(ArrayList<Restaurant_setmeal_info>) ServiceFactory.getRestaurant_setmeal_infoManageService().getAllSetmealById(rest_info.getLogin_id());
		ArrayList<Restaurant_setmeal_info> restaurant_setmeal_infos=new ArrayList();
		for(int i=0;i<restaurant_setmeal_infos2.size();i++) {
			long today=new java.util.Date().getTime();
			if((restaurant_setmeal_infos2.get(i).getStart_date()!=null)&&(restaurant_setmeal_infos2.get(i).getEnd_date()!=null)) {
				long t1=restaurant_setmeal_infos2.get(i).getStart_date().getTime();
				long t2=restaurant_setmeal_infos2.get(i).getEnd_date().getTime();
				if((t1<=today)&&(t2>=today)) {
					restaurant_setmeal_infos.add(restaurant_setmeal_infos2.get(i));
				}
			}
		}
		session.setAttribute("choose_rest_setmeal", restaurant_setmeal_infos);
		//优惠的基本信息
		ArrayList<Restaurant_preferencial_info> restaurant_preferencial_infos2=(ArrayList<Restaurant_preferencial_info>) ServiceFactory.getRestaurant_preferencial_infoManageService().getAllPreferencialById(rest_info.getLogin_id());
		ArrayList<Restaurant_preferencial_info> restaurant_preferencial_infos=new ArrayList();
		for(int i=0;i<restaurant_preferencial_infos2.size();i++) {
			long today=new java.util.Date().getTime();
			if((restaurant_preferencial_infos2.get(i).getStart_date()!=null)&&(restaurant_preferencial_infos2.get(i).getEnd_date()!=null)) {
				long t1=restaurant_preferencial_infos2.get(i).getStart_date().getTime();
				long t2=restaurant_preferencial_infos2.get(i).getEnd_date().getTime();
				if((t1<=today)&&(t2>=today)) {
					restaurant_preferencial_infos.add(restaurant_preferencial_infos2.get(i));
				}
			}
		}
		session.setAttribute("choose_rest_preferencial", restaurant_preferencial_infos);
		//填写商品数量的内容里面必须是数字，而且不可以超过库存的数量
		//单品，套餐，优惠的数量
		ArrayList<Integer> num1=new ArrayList();
		ArrayList<Integer> num2=new ArrayList();
		ArrayList<Integer> num3=new ArrayList();
				
		try {
			for(int i=0;i<restaurant_singleproduct_infos.size();i++) {
				String in=request.getParameter(restaurant_singleproduct_infos.get(i).getId()+"singleproduct");
				if(in.equals("")) {//输入的数量为空
					num1.add(0);
				}
				else {
					int n=Integer.parseInt(in);
					if(n>=0) {
						num1.add(n);
					}
					else {
						choose_commodity_err_info="填写的商品数量不可以小于0！";
						break;
					}
				}
			}
			for(int i=0;i<restaurant_setmeal_infos.size();i++) {
				String in=request.getParameter(restaurant_setmeal_infos.get(i).getId()+"setmeal");
				if(in.equals("")) {//输入的数量为空
					num2.add(0);
				}
				else {
					int n=Integer.parseInt(in);
					if(n>=0) {
						num2.add(n);
					}
					else {
						choose_commodity_err_info="填写的商品数量不可以小于0！";
						break;
					}
				}
			}
			for(int i=0;i<restaurant_preferencial_infos.size();i++) {
				String in=request.getParameter(restaurant_preferencial_infos.get(i).getId()+"preferencial");
				if(in.equals("")) {//输入的数量为空
					num3.add(0);
				}
				else {
					int n=Integer.parseInt(in);
					if(n>=0) {
						num3.add(n);
					}
					else {
						choose_commodity_err_info="填写的商品数量不可以小于0！";
						break;
					}
				}
			}
		}
		catch(Exception e) {//填写的商品数量必须是数字
			choose_commodity_err_info="填写的商品数量有非数字的存在！";
			System.out.println("填写商品数量的时候，存在非数字的输入！");
		}
		
		//数量超过库存总量
		for(int i=0;i<num1.size();i++) {
			if(!(num1.get(i)<=restaurant_singleproduct_infos.get(i).getCom_num())) {
				//商品输入的数量超过了库存的数量
				choose_commodity_err_info="填写的商品数量超过库存总量！";
				break;
			}
		}
		for(int i=0;i<num2.size();i++) {
			if(!(num2.get(i)<=restaurant_setmeal_infos.get(i).getSetmeal_num())) {
				//商品输入的数量超过了库存的数量
				choose_commodity_err_info="填写的商品数量超过库存总量！";
				break;
			}
		}
		for(int i=0;i<num3.size();i++) {
			if(!(num3.get(i)<=restaurant_preferencial_infos.get(i).getNum())) {
				//商品输入的数量超过了库存的数量
				choose_commodity_err_info="填写的商品数量超过库存总量！";
				break;
			}
		}
		
		//如果没有错误，跳转到订单详情的页面
		if(choose_commodity_err_info.equals("")) {
			//根据member的信息获得该会员有哪些支付的地址，地址需要是可以正在使用的那些
			Member_info m=(Member_info) session.getAttribute("user_info");
			ArrayList<Delivery_address_info> addresses1=(ArrayList<Delivery_address_info>) ServiceFactory.getDelivery_address_infoManageService().getAllAddressByID(m.getId());
			ArrayList<Delivery_address_info> addresses=new ArrayList();//正在使用的地址
			for(int i=0;i<addresses1.size();i++) {
				if(addresses1.get(i).getIn_use()==1) {
					addresses.add(addresses1.get(i));
				}
			}
			session.setAttribute("member_addresses", addresses);
			//显示需要显示的商品数量（数量=0的就不显示了）
			session.setAttribute("num1", num1);
			session.setAttribute("num2", num2);
			session.setAttribute("num3", num3);
			//获得会员当日的优惠
			double ps_money=0;
			Member_ps_receive_info getmps=ServiceFactory.getMember_ps_receive_infoManageService().getMemberPsMoney(m.getId());
			if(getmps.getId()==null) {//若会员没有当日优惠：则新增优惠
				ServiceFactory.getMember_ps_receive_infoManageService().send_ps(m.getId());
				getmps=ServiceFactory.getMember_ps_receive_infoManageService().getMemberPsMoney(m.getId());
			}
			//获得会员的今日可以优惠的价格，已使用则没有优惠
			//未使用优惠，则获得优惠的金额，使用掉优惠，即便后续支付不成功也是用掉
			session.setAttribute("change_ps", new Member_ps_receive_info());
			if(getmps.getResult()==0) {
				ps_money=getmps.getPs_money();
				session.setAttribute("change_ps", getmps);
			}
			session.setAttribute("ps_money", ps_money);
			context.getRequestDispatcher("/jsps/member/order_meal/order.jsp").forward(request, response);
		}
		//如果有错误，返回餐厅的选择商品的页面
		else {
			session.setAttribute("choose_commodity_err_info", choose_commodity_err_info);
			context.getRequestDispatcher("/jsps/member/order_meal/choose_commodity.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
