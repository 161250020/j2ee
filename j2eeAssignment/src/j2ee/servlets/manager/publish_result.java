package j2ee.servlets.manager;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import j2ee.factory.ServiceFactory;
import j2ee.model.Manager_info;
import j2ee.model.Restaurant_modify_application_info;
import j2ee.model.Restaurant_publish_application_info;

/**
 * Servlet implementation class publish_result
 */
@WebServlet("/manager/publish_result")
public class publish_result extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public publish_result() {
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
		//获得注册的餐厅的信息
	    Restaurant_publish_application_info r= (Restaurant_publish_application_info) session.getAttribute("publish_info");
	    
		if(func.equals("pass")) {
			//修改审批的结果，审批的manager的ID，
			Manager_info m=(Manager_info) session.getAttribute("manager_info");
		    ServiceFactory.getRestaurant_publish_application_infoManageService().changePublishResult(r.getId(), 2, m.getId());
			context.getRequestDispatcher("/jsps/manager/examine_and_approve/examine_list_publish.jsp").forward(request, response);
		}
		else {
			//修改审批的结果，审批的manager的ID，
			Manager_info m=(Manager_info) session.getAttribute("manager_info");
		    ServiceFactory.getRestaurant_publish_application_infoManageService().changePublishResult(r.getId(), 1, m.getId());
		    context.getRequestDispatcher("/jsps/manager/examine_and_approve/examine_list_publish.jsp").forward(request, response);
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
