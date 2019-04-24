package j2ee.servlets.member;

import java.io.IOException;
import java.sql.Timestamp;
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
 * Servlet implementation class order
 */
@WebServlet("/order_meal/order")
public class order extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public order() {
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
		
		//会员的信息
		Member_info m=(Member_info)session.getAttribute("user_info");
		//订单总价
		double sum=(double) session.getAttribute("order_sum_money");
		//优惠金额
		double ps_money=(double) session.getAttribute("ps_money");
		//获得所有商品信息
	    ArrayList<Restaurant_singleproduct_info> restaurant_singleproduct_infos=(ArrayList<Restaurant_singleproduct_info>)session.getAttribute("choose_rest_singleproduct");
	    ArrayList<Restaurant_setmeal_info> restaurant_setmeal_infos=(ArrayList<Restaurant_setmeal_info>)session.getAttribute("choose_rest_setmeal");
	    ArrayList<Restaurant_preferencial_info> restaurant_preferencial_infos=(ArrayList<Restaurant_preferencial_info>)session.getAttribute("choose_rest_preferencial");
	    //获得所有商品数量信息（页面上面仅显示数量>0的商品）
	    ArrayList<Integer> num1= (ArrayList<Integer>) session.getAttribute("num1");
	    ArrayList<Integer> num2= (ArrayList<Integer>) session.getAttribute("num2");
	    ArrayList<Integer> num3= (ArrayList<Integer>) session.getAttribute("num3");
		
		//选择的地址
		String add_id=request.getParameter("select_address");
		ArrayList<Delivery_address_info> addresses=(ArrayList<Delivery_address_info>) session.getAttribute("member_addresses");
		Delivery_address_info dai=new Delivery_address_info();//订单的地址的信息
		for(int i=0;i<addresses.size();i++) {
			if(addresses.get(i).getId().equals(add_id)) {
				dai=addresses.get(i);
				break;
			}
		}
		session.setAttribute("order_address_info", dai);
		
		//系统判断的送餐时间
		//若不同街道，不同镇，不同区，不同市，送餐时间不断增加
		Restaurant_info rest_info=(Restaurant_info) session.getAttribute("choose_rest_info");
		int min_mins=0;
		if(dai.getCity().equals(rest_info.getCity())) {
			if(dai.getDistrict().equals(rest_info.getDistrict())) {
				if(dai.getTown().equals(rest_info.getTown())) {
					if(dai.getStreet().equals(rest_info.getStreet())) {
						min_mins=20;//最低送餐时间：20分钟
					}
					else {//不同街道
						min_mins=30;//最低送餐时间：30分钟
					}
				}
				else {//不同镇
					min_mins=40;//最低送餐时间：40分钟
				}
			}
			else {//不同区
				min_mins=60;//最低送餐时间：60分钟
			}
		}
		else {//不同市
			min_mins=120;//最低送餐时间：120分钟
		}
		session.setAttribute("min_mins", min_mins);
		
		//生成订单信息---存储到订单信息，订单content两张表当中
		//生成一条member_order_info的信息
		Member_order_info moi=new Member_order_info();
		moi.setId(UUID.randomUUID().toString());
		String order_list_id=UUID.randomUUID().toString();
		moi.setOrder_list_id(order_list_id);
		moi.setOrder_time(new Timestamp(new java.util.Date().getTime()));
		moi.setDelivery_time_defined(min_mins+"");
		moi.setMember_id(m.getId());
		moi.setRestaurant_id(rest_info.getLogin_id());
		moi.setSum_price(sum);
		moi.setResult(1);
		moi.setDelivery_address_id(dai.getId());
		moi.setPs_money_yummy(ps_money);
		moi.setDelivery_time_os(min_mins+"");
		moi.setAccount(0);
		session.setAttribute("order_info", moi);
		ServiceFactory.getMember_order_infoManageService().addANewOrder(moi);
		//修改优惠使用成功的结果
		String date=new java.sql.Date(new java.util.Date().getTime()).toString();
		ServiceFactory.getMember_ps_receive_infoManageService().changeResultByMember_idAndDate(m.getId(), date);
		//生成多条member_order_content_info的信息
		for(int i=0;i<num1.size();i++) {
			if(num1.get(i)>0) {//添加一条member_order_content_info信息
				Member_order_content_info moci=new Member_order_content_info();
				moci.setId(UUID.randomUUID().toString());
				moci.setName(restaurant_singleproduct_infos.get(i).getCommodity_name());
				moci.setNum(num1.get(i));
				moci.setOrder_list_id(order_list_id);
				moci.setPrice(num1.get(i)*restaurant_singleproduct_infos.get(i).getCom_price());
				moci.setType(0);
				ServiceFactory.getMember_order_content_infoManageService().addARecord(moci);
				
			}
		}
		for(int i=0;i<num2.size();i++) {
			if(num2.get(i)>0) {//添加一条member_order_content_info信息
				Member_order_content_info moci=new Member_order_content_info();
				moci.setId(UUID.randomUUID().toString());
				moci.setName(restaurant_setmeal_infos.get(i).getSet_meal_name());
				moci.setNum(num2.get(i));
				moci.setOrder_list_id(order_list_id);
				moci.setPrice(num2.get(i)*restaurant_setmeal_infos.get(i).getSetmeal_price());
				moci.setType(1);
				ServiceFactory.getMember_order_content_infoManageService().addARecord(moci);

			}
		}
		for(int i=0;i<num3.size();i++) {
			if(num3.get(i)>0) {//添加一条member_order_content_info信息
				Member_order_content_info moci=new Member_order_content_info();
				moci.setId(UUID.randomUUID().toString());
				moci.setName(restaurant_preferencial_infos.get(i).getCom_name());
				moci.setNum(num3.get(i));
				moci.setOrder_list_id(order_list_id);
				moci.setPrice(num3.get(i)*restaurant_preferencial_infos.get(i).getNow_price());
				moci.setType(2);
				ServiceFactory.getMember_order_content_infoManageService().addARecord(moci);

			}
		}
		
		context.getRequestDispatcher("/jsps/member/order_meal/pay.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
