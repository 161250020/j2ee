package j2ee.servlets.manager;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import j2ee.factory.ServiceFactory;
import j2ee.model.Manager_info;
import j2ee.model.Restaurant_info;
import j2ee.model.Restaurant_register_application_info;

/**
 * Servlet implementation class register_result
 */
@WebServlet("/manager/register_result")
public class register_result extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register_result() {
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
	    Restaurant_register_application_info r= (Restaurant_register_application_info) session.getAttribute("register_info");
	    
		if(func.equals("pass")) {
			//修改审批的结果
		    ServiceFactory.getRestaurant_register_application_infoManageService().changeRegisterResult(r.getId(), 2);
		    //获得manager的ID
		    Manager_info m=(Manager_info) session.getAttribute("manager_info");
		    //修改审批的manager的ID
		    ServiceFactory.getRestaurant_register_application_infoManageService().changeRegisterManager_id(r.getId(), m.getId());
		    //在餐厅的信息里面添加内容
		    ServiceFactory.getRestaurant_infoManageService().addNewRestInfo(r);
		    context.getRequestDispatcher("/jsps/manager/examine_and_approve/examine_list_register.jsp").forward(request, response);
		}
		else {
			//修改审批的结果
		    ServiceFactory.getRestaurant_register_application_infoManageService().changeRegisterResult(r.getId(), 1);
		    //获得manager的ID
		    Manager_info m=(Manager_info) session.getAttribute("manager_info");
		    //修改审批的manager的ID
		    ServiceFactory.getRestaurant_register_application_infoManageService().changeRegisterManager_id(r.getId(), m.getId());
		    context.getRequestDispatcher("/jsps/manager/examine_and_approve/examine_list_register.jsp").forward(request, response);
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
