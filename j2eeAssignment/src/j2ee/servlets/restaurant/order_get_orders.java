package j2ee.servlets.restaurant;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
 * Servlet implementation class order_get_orders
 */
@WebServlet("/restaurant/order_get_orders")
public class order_get_orders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public order_get_orders() {
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

		String order_start_time=request.getParameter("start_date");
		String order_end_time=request.getParameter("end_date");
		String order_start_mon1=request.getParameter("start_money");
		String order_end_mon1=request.getParameter("end_money");
		String order_by_restaurant=request.getParameter("order_by_member");
		
		SimpleDateFormat  df = new SimpleDateFormat("yyyy-MM-dd");  
        java.util.Date d1=new java.util.Date();
        java.util.Date d2=new java.util.Date();
        try {
			d1 = df.parse(order_start_time);
			d2 = df.parse(order_end_time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        
        //获得该餐厅信息
		Restaurant_info m=(Restaurant_info) session.getAttribute("rest_info");
		//初始化的信息
		ArrayList<Member_order_info> mois=new ArrayList();
		if(order_by_restaurant.equals("1")) {
			mois=(ArrayList<Member_order_info>) ServiceFactory.getMember_order_infoManageService().getInfoBetDates_member(d1, d2);
		}
		else {
			mois=(ArrayList<Member_order_info>) ServiceFactory.getMember_order_infoManageService().getInfoBetDates(d1, d2);
		}
		ArrayList<Member_order_info> mois2=new ArrayList();
		double order_start_mon=Double.parseDouble(order_start_mon1);
		double order_end_mon=Double.parseDouble(order_end_mon1);
		for(int i=0;i<mois.size();i++) {
			if(mois.get(i).getRestaurant_id().equals(m.getLogin_id())) {
				double price=mois.get(i).getSum_price();
				if((order_start_mon<=price)&&(order_end_mon>=price))
					mois2.add(mois.get(i));
			}
		}
		
		session.setAttribute("order_rest_start_time", order_start_time);
		session.setAttribute("order_rest_end_time", order_end_time);
		session.setAttribute("order_rest_start_mon", order_start_mon);
		session.setAttribute("order_rest_end_mon", order_end_mon);
		session.setAttribute("order_rest_orders_list", mois2);
		context.getRequestDispatcher("/jsps/restaurant/look_over_sum_info/order.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
