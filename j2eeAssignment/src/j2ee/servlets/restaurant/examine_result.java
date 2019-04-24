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
import j2ee.model.Restaurant_register_application_info;

/**
 * Servlet implementation class examine_result
 */
@WebServlet("/restaurant/examine_result")
public class examine_result extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public examine_result() {
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
		
		String chars7=request.getParameter("chars7");
		
		//获得审批的信息
		ArrayList<Restaurant_register_application_info> rrais=(ArrayList<Restaurant_register_application_info>) ServiceFactory.getRestaurant_register_application_infoManageService().getAllRegisterApplications_total();
		ArrayList<Restaurant_register_application_info> arr=new ArrayList();
		for(int i=0;i<rrais.size();i++) {
			if(rrais.get(i).getLogin_id().equals(chars7)) {
				arr.add(rrais.get(i));
			}
		}
		//添加到session当中
		session.setAttribute("examine_result", arr);
		//再次跳转到审批结果的页面
		context.getRequestDispatcher("/jsps/restaurant/examine_result.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
