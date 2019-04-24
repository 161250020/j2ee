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
import j2ee.model.Member_info;
import j2ee.model.Member_order_info;

/**
 * Servlet implementation class to3functions
 */
@WebServlet("/to3functions")
public class to3functions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public to3functions() {
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
		
		//转到页面
		if(func.equals("order")) {
			context.getRequestDispatcher("/jsps/member/statistics/order.jsp").forward(request, response);
		}
		else if(func.equals("unsubscribe")) {
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
				if((mois.get(i).getMember_id().equals(m.getId()))&&(mois.get(i).getResult()==0)) {//为此会员的订单，且为退订的订单
					double price=mois.get(i).getSum_price();
					if((order_start_mon<=price)&&(order_end_mon>=price))
						mois2.add(mois.get(i));
				}
			}
			session.setAttribute("unsubscribe_start_time", date1.toString());
    		session.setAttribute("unsubscribe_end_time", date2.toString());
    		session.setAttribute("unsubscribe_start_mon", order_start_mon);
    		session.setAttribute("unsubscribe_end_mon", order_end_mon);
    		session.setAttribute("unsubscribe_orders_list", mois2);
			context.getRequestDispatcher("/jsps/member/statistics/unsubscribe.jsp").forward(request, response);
		}
		else {
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
				if((mois.get(i).getMember_id().equals(m.getId()))&&(mois.get(i).getState()==3)) {//为此会员的订单，且为已送达的订单
					double price=mois.get(i).getSum_price();
					if((order_start_mon<=price)&&(order_end_mon>=price))
						mois2.add(mois.get(i));
				}
			}
			session.setAttribute("consume_start_time", date1.toString());
    		session.setAttribute("consume_end_time", date2.toString());
    		session.setAttribute("consume_start_mon", order_start_mon);
    		session.setAttribute("consume_end_mon", order_end_mon);
    		session.setAttribute("consume_orders_list", mois2);
			context.getRequestDispatcher("/jsps/member/statistics/consume.jsp").forward(request, response);
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
