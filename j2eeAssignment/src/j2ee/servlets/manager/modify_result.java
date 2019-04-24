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
import j2ee.model.Restaurant_register_application_info;

/**
 * Servlet implementation class modify_result
 */
@WebServlet("/manager/modify_result")
public class modify_result extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public modify_result() {
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
		//���ע��Ĳ�������Ϣ
	    Restaurant_modify_application_info r= (Restaurant_modify_application_info) session.getAttribute("modify_info");
	    
		if(func.equals("pass")) {
			//�޸������Ľ��
		    ServiceFactory.getRestaurant_modify_application_infoManageService().changeModifyResult(r.getId(), 2);
			//���manager��ID
		    Manager_info m=(Manager_info) session.getAttribute("manager_info");
		    //�޸�������manager��ID
		    ServiceFactory.getRestaurant_modify_application_infoManageService().changeModifyManager_id(r.getId(), m.getId());
		    //�ڲ�������Ϣ�����������
		    ServiceFactory.getRestaurant_infoManageService().modifyRestInfoBy7Chars(r);
		    context.getRequestDispatcher("/jsps/manager/examine_and_approve/examine_list_modify.jsp").forward(request, response);
		}
		else {
			//�޸������Ľ��
			ServiceFactory.getRestaurant_modify_application_infoManageService().changeModifyResult(r.getId(), 1);
			//���manager��ID
		    Manager_info m=(Manager_info) session.getAttribute("manager_info");
		    //�޸�������manager��ID
		    ServiceFactory.getRestaurant_modify_application_infoManageService().changeModifyManager_id(r.getId(), m.getId());
		    context.getRequestDispatcher("/jsps/manager/examine_and_approve/examine_list_modify.jsp").forward(request, response);
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
