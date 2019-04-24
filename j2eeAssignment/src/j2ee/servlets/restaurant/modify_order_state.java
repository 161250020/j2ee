package j2ee.servlets.restaurant;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import j2ee.factory.ServiceFactory;
import j2ee.model.Member_order_info;

/**
 * Servlet implementation class modify_order_state
 */
@WebServlet("/restaurant/modify_order_state")
public class modify_order_state extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public modify_order_state() {
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
		
		String func=request.getParameter("order_id").split("_")[0];
		String id=request.getParameter("order_id").split("_")[1];
		System.out.println("-------id��"+id);
		
		if(func.equals("������")) {
			//�޸Ķ���״̬Ϊ������
			Member_order_info order=ServiceFactory.getMember_order_infoManageService().getInfoById(id);
			order.setState(1);
			ServiceFactory.getMember_order_infoManageService().change_info(order);
			//ת����ʾ������ҳ��
			context.getRequestDispatcher("/jsps/restaurant/orders/order_list.jsp").forward(request, response);
		}
		else if(func.equals("������")) {
			//�޸Ķ���״̬Ϊ������
			Member_order_info order=ServiceFactory.getMember_order_infoManageService().getInfoById(id);
			order.setState(2);
			ServiceFactory.getMember_order_infoManageService().change_info(order);
			//ת����ʾ������ҳ��
			context.getRequestDispatcher("/jsps/restaurant/orders/order_list.jsp").forward(request, response);
		}
		else {//ת�����������ҳ��
			Member_order_info order=ServiceFactory.getMember_order_infoManageService().getInfoById(id);
	    	session.setAttribute("order_content_info", order);
	    	context.getRequestDispatcher("/jsps/restaurant/orders/order_info.jsp").forward(request, response);
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
