package j2ee.servlets.member;

import java.io.IOException;
import java.util.ArrayList;

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
 * Servlet implementation class to4functions
 */
@WebServlet("/to4functions")
public class to4functions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public to4functions() {
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
		String func=request.getParameter("func");
		
		if(func.equals("order_meal")) {//去点餐
			context.getRequestDispatcher("/jsps/member/order_meal/choose_restaurant.jsp").forward(request, response);
		}
		else if(func.equals("orders")) {//去查看订单
			//修改数据库中的订单，自动确认订单送达
			ServiceFactory.getMember_order_infoManageService().ensure_order_arrived_os();
			//获得该会员所有订单的内容
			Member_info m=(Member_info) session.getAttribute("user_info");
			ArrayList<Member_order_info> all_order_info=(ArrayList<Member_order_info>) ServiceFactory.getMember_order_infoManageService().getAllOrdersByMemberId(m.getId());
			session.setAttribute("all_order_info", all_order_info);
			context.getRequestDispatcher("/jsps/member/orders/order_form.jsp").forward(request, response);
		}
		else if(func.equals("sum_info")) {//去查看统计信息
			//获得该会员信息
			Member_info m=(Member_info) session.getAttribute("user_info");
			//初始化的信息
			java.sql.Date date1=new java.sql.Date(new java.util.Date().getTime());
    		java.sql.Date date2=new java.sql.Date(new java.util.Date().getTime());
    		ArrayList<Member_order_info> mois=(ArrayList<Member_order_info>) ServiceFactory.getMember_order_infoManageService().getInfoBetDates(date1, date2);
    		ArrayList<Member_order_info> mois2=new ArrayList();
    		double order_start_mon=0;
    		double order_end_mon=0;
    		for(int i=0;i<mois.size();i++) {
    			if(mois.get(i).getMember_id().equals(m.getId())) {
    				double price=mois.get(i).getSum_price();
    				if((order_start_mon<=price)&&(order_end_mon>=price))
    					mois2.add(mois.get(i));
    			}
    		}
    		session.setAttribute("order_start_time", date1.toString());
    		session.setAttribute("order_end_time", date2.toString());
    		session.setAttribute("order_start_mon", order_start_mon);
    		session.setAttribute("order_end_mon", order_end_mon);
    		session.setAttribute("order_orders_list", mois2);
    		context.getRequestDispatcher("/jsps/member/statistics/order.jsp").forward(request, response);
		}
		else {//去查看个人信息
			context.getRequestDispatcher("/jsps/member/self_info/basic_info.jsp").forward(request, response);
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
