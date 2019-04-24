package j2ee.servlets.member;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Enumeration;

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
 * Servlet implementation class ensure_arrived
 */
@WebServlet("/member/ensure_arrived_or_refund")
public class ensure_arrived_or_refund extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ensure_arrived_or_refund() {
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
		
		//获得该用户所有订单信息---后面的时候，根据订单的信息决定显示的内容是否有button
	    ArrayList<Member_order_info> all_order_info=(ArrayList<Member_order_info>)session.getAttribute("all_order_info");
		//确认订单送达---获得订单的index
		//退单---获得订单的index
	    //查看订单内容的index
	    String index1="";
	    String index2="";
	    String index3="";
	    for(Enumeration e = request.getParameterNames(); e.hasMoreElements();){
	        Object o=e.nextElement();
	        String name= (String) o;
	        if(name.equals("ensure_arrived")){
	        	index1=request.getParameter("ensure_arrived");
	            break;
	        }
	        if(name.equals("refund_order")){
	        	index2=request.getParameter("refund_order");
	            break;
	        }
	        if(name.equals("order_info")){
	        	index3=request.getParameter("order_info");
	            break;
	        }
	    }
		
		//修改订单状态*2
	    if(!index1.equals("")) {//订单已送达
	    	Member_order_info m=all_order_info.get(Integer.parseInt(index1)-1);
	    	m.setDelivery_time_received(new Timestamp(new java.util.Date().getTime()));
	    	m.setState(3);
	    	ServiceFactory.getMember_order_infoManageService().change_info(m);
	    	
	    	//修改数据库中的订单，自动确认订单送达
			ServiceFactory.getMember_order_infoManageService().ensure_order_arrived_os();
			//获得该用户所有订单信息
			//修改该用户所有订单信息的session
			//转到订单展示页面
			Member_info m2=(Member_info) session.getAttribute("user_info");
			ArrayList<Member_order_info> all_order_info2=(ArrayList<Member_order_info>) ServiceFactory.getMember_order_infoManageService().getAllOrdersByMemberId(m2.getId());
			session.setAttribute("all_order_info", all_order_info2);
			context.getRequestDispatcher("/jsps/member/orders/order_form.jsp").forward(request, response);
	    }
	    if(!index2.equals("")) {//退单
	    	Member_order_info m=all_order_info.get(Integer.parseInt(index2)-1);
	    	//修改订单状态
	    	//将退订的金额打到账户上面，增加会员和餐厅的金额，减少yummy的金额
	    	ServiceFactory.getMember_order_infoManageService().changeAfterUnsubscribeByIdAndState(m.getId());
	    	
	    	//修改数据库中的订单，自动确认订单送达
			ServiceFactory.getMember_order_infoManageService().ensure_order_arrived_os();
			//获得该用户所有订单信息
			//修改该用户所有订单信息的session
			//转到订单展示页面
			Member_info m2=(Member_info) session.getAttribute("user_info");
			ArrayList<Member_order_info> all_order_info2=(ArrayList<Member_order_info>) ServiceFactory.getMember_order_infoManageService().getAllOrdersByMemberId(m2.getId());
			session.setAttribute("all_order_info", all_order_info2);
			context.getRequestDispatcher("/jsps/member/orders/order_form.jsp").forward(request, response);
	    }
	    if(!index3.equals("")) {//查看订单基本内容
	    	Member_order_info m=all_order_info.get(Integer.parseInt(index3)-1);
	    	session.setAttribute("order_content_info", m);
	    	context.getRequestDispatcher("/jsps/member/orders/order_info.jsp").forward(request, response);
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
