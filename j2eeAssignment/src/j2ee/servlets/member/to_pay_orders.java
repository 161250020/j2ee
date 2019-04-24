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
 * Servlet implementation class to_pay_orders
 */
@WebServlet("/member/to_pay_orders")
public class to_pay_orders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public to_pay_orders() {
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
		
		//获得该用户所有待支付的订单的信息---不超时
		Member_info m=(Member_info) session.getAttribute("user_info");
		ArrayList<Member_order_info> orders=(ArrayList<Member_order_info>) ServiceFactory.getMember_order_infoManageService().getAllOrdersByMemberId(m.getId());
		ArrayList<Member_order_info> orders_info=new ArrayList();
		long currentTime=new java.util.Date().getTime();
		for(int i=0;i<orders.size();i++) {
			long order_time=orders.get(i).getOrder_time().getTime();
			order_time+=1000*60*2;//需要在此之前支付
			if((order_time>currentTime)&&(orders.get(i).getResult()==1)) {//未支付
				orders_info.add(orders.get(i));
			}
		}
		
		session.setAttribute("to_pay_orders_info", orders_info);
		context.getRequestDispatcher("/jsps/member/orders/to_pay_order_form.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
