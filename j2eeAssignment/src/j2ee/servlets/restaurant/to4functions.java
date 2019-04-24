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
import j2ee.model.Member_order_info;
import j2ee.model.Restaurant_info;

/**
 * Servlet implementation class to4functions
 */
@WebServlet("/restaurant/to4functions")
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
		
		if(func.equals("orders")) {
			context.getRequestDispatcher("/jsps/restaurant/orders/order_list.jsp").forward(request, response);
		}
		else if(func.equals("publish_info")) {
			context.getRequestDispatcher("/jsps/restaurant/publish_info/single_product.jsp").forward(request, response);
		}
		else if(func.equals("look_over_sum_info")) {
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
    			if(mois.get(i).getRestaurant_id().equals(m.getLogin_id())) {
    				double price=mois.get(i).getSum_price();
    				if((order_start_mon<=price)&&(order_end_mon>=price))
    					mois2.add(mois.get(i));
    			}
    		}
    		session.setAttribute("order_rest_start_time", date1.toString());
    		session.setAttribute("order_rest_end_time", date2.toString());
    		session.setAttribute("order_rest_start_mon", order_start_mon);
    		session.setAttribute("order_rest_end_mon", order_end_mon);
    		session.setAttribute("order_rest_orders_list", mois2);
    		
			context.getRequestDispatcher("/jsps/restaurant/look_over_sum_info/order.jsp").forward(request, response);
		}
		else {
			context.getRequestDispatcher("/jsps/restaurant/personal_info/personal_info.jsp").forward(request, response);
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
