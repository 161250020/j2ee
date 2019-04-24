package j2ee.servlets.member;

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

/**
 * Servlet implementation class statistic_unsubscribe
 */
@WebServlet("/member/statistic_unsubscribe")
public class statistic_unsubscribe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public statistic_unsubscribe() {
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
		String order_by_restaurant=request.getParameter("order_by_restaurant");
		
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
        
        //获得该会员信息
		Member_info m=(Member_info) session.getAttribute("user_info");
		//初始化的信息
		ArrayList<Member_order_info> mois=new ArrayList();
		if(order_by_restaurant.equals("1")) {
			mois=(ArrayList<Member_order_info>) ServiceFactory.getMember_order_infoManageService().getInfoBetDates_rest(d1, d2);
		}
		else {
			mois=(ArrayList<Member_order_info>) ServiceFactory.getMember_order_infoManageService().getInfoBetDates(d1, d2);
		}
		ArrayList<Member_order_info> mois2=new ArrayList();
		double order_start_mon=Double.parseDouble(order_start_mon1);
		double order_end_mon=Double.parseDouble(order_end_mon1);
		for(int i=0;i<mois.size();i++) {
			if(mois.get(i).getMember_id().equals(m.getId())&&(mois.get(i).getResult()==0)) {
				double price=mois.get(i).getSum_price();
				if((order_start_mon<=price)&&(order_end_mon>=price))
					mois2.add(mois.get(i));
			}
		}
		
		session.setAttribute("unsubscribe_start_time", order_start_time);
		session.setAttribute("unsubscribe_end_time", order_end_time);
		session.setAttribute("unsubscribe_start_mon", order_start_mon);
		session.setAttribute("unsubscribe_end_mon", order_end_mon);
		session.setAttribute("unsubscribe_orders_list", mois2);
		context.getRequestDispatcher("/jsps/member/statistics/unsubscribe.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
