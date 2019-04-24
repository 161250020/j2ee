package j2ee.servlets.restaurant;

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
import j2ee.model.Member_info;
import j2ee.model.Member_order_info;
import j2ee.model.Restaurant_info;


/**
 * Servlet implementation class to3functions3
 */
@WebServlet("/restaurant/to3functions3")
public class to3functions3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public to3functions3() {
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
		
		if(func.equals("order")) {
			context.getRequestDispatcher("/jsps/restaurant/look_over_sum_info/order.jsp").forward(request, response);
		}
		else if(func.equals("unsubscribe")) {
			//获得该餐厅信息
			Restaurant_info m=(Restaurant_info) session.getAttribute("rest_info");
			//初始化的信息
			java.sql.Date date1=new java.sql.Date(new java.util.Date().getTime());
			java.sql.Date date2=new java.sql.Date(new java.util.Date().getTime());
			ArrayList<Member_order_info> mois=(ArrayList<Member_order_info>) ServiceFactory.getMember_order_infoManageService().getInfoBetDates(date1, date2);
			ArrayList<Member_order_info> mois2=new ArrayList();
			double order_start_mon=0;
			double order_end_mon=0;
			for(int i=0;i<mois.size();i++) {
				if((mois.get(i).getRestaurant_id().equals(m.getLogin_id()))&&(mois.get(i).getResult()==0)) {//为此会员的订单，且为退订的订单
					double price=mois.get(i).getSum_price();
					if((order_start_mon<=price)&&(order_end_mon>=price))
						mois2.add(mois.get(i));
				}
			}
			session.setAttribute("unsubscribe_rest_start_time", date1.toString());
    		session.setAttribute("unsubscribe_rest_end_time", date2.toString());
    		session.setAttribute("unsubscribe_rest_start_mon", order_start_mon);
    		session.setAttribute("unsubscribe_rest_end_mon", order_end_mon);
    		session.setAttribute("unsubscribe_rest_orders_list", mois2);
    		
			context.getRequestDispatcher("/jsps/restaurant/look_over_sum_info/unsubscribe.jsp").forward(request, response);
		}
		else {
			//获得该餐厅信息
			Restaurant_info m=(Restaurant_info) session.getAttribute("rest_info");
			//初始化的信息
			java.sql.Date date1=new java.sql.Date(new java.util.Date().getTime());
			java.sql.Date date2=new java.sql.Date(new java.util.Date().getTime());
			ArrayList<Member_order_info> mois=(ArrayList<Member_order_info>) ServiceFactory.getMember_order_infoManageService().getInfoBetDates(date1, date2);
			ArrayList<Member_order_info> mois2=new ArrayList();
			double order_start_mon=0;
			double order_end_mon=0;
			for(int i=0;i<mois.size();i++) {
				if((mois.get(i).getRestaurant_id().equals(m.getLogin_id()))&&(mois.get(i).getState()==3)) {//为此会员的订单，且为已送达的订单
					double price=mois.get(i).getSum_price();
					if((order_start_mon<=price)&&(order_end_mon>=price))
						mois2.add(mois.get(i));
				}
			}
			session.setAttribute("finance_rest_start_time", date1.toString());
    		session.setAttribute("finance_rest_end_time", date2.toString());
    		session.setAttribute("finance_rest_start_mon", order_start_mon);
    		session.setAttribute("finance_rest_end_mon", order_end_mon);
    		session.setAttribute("finance_rest_orders_list", mois2);
    		
			context.getRequestDispatcher("/jsps/restaurant/look_over_sum_info/finance.jsp").forward(request, response);
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
