package j2ee.servlets.restaurant;

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
import j2ee.model.Restaurant_info;
import j2ee.model.Restaurant_modify_application_info;

/**
 * Servlet implementation class ensure_modify_rest_info
 */
@WebServlet("/restaurant/ensure_modify_rest_info")
public class ensure_modify_rest_info extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ensure_modify_rest_info() {
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

		//获得餐厅的基本信息
	    Restaurant_info r= (Restaurant_info) session.getAttribute("rest_info");
	    
		//获得输入的新信息
		String new_name=request.getParameter("new_name");
		if(new_name.equals("")) {
			new_name=r.getName();
		}
		String new_add=request.getParameter("new_add");
		if(new_add.equals("")) {
			new_add=r.getAddress();
		}
		String new_city=request.getParameter("new_city");
		if(new_city.equals("")) {
			new_city=r.getCity();
		}
		String new_district=request.getParameter("new_district");
		if(new_district.equals("")) {
			new_district=r.getDistrict();
		}
		String new_town=request.getParameter("new_town");
		if(new_town.equals("")) {
			new_town=r.getTown();
		}
		String new_street=request.getParameter("new_street");
		if(new_street.equals("")) {
			new_street=r.getStreet();
		}
		String new_type=request.getParameter("new_type");
		
	    //添加修改餐厅信息的审批申请
	    Restaurant_modify_application_info r_modify=new Restaurant_modify_application_info();
	    r_modify.setId(UUID.randomUUID().toString());
	    r_modify.setNew_address(new_add);
	    r_modify.setNew_city(new_city);
	    r_modify.setNew_district(new_district);
	    r_modify.setNew_name(new_name);
	    r_modify.setNew_street(new_street);
	    r_modify.setNew_town(new_town);
	    r_modify.setNew_type_id(Integer.parseInt(new_type));
	    r_modify.setRestaurant_id(r.getLogin_id());
	    r_modify.setResult(0);
	    ServiceFactory.getRestaurant_modify_application_infoManageService().addNewModifyInfo(r_modify);
	    
	    //跳转到个人信息页面
	    context.getRequestDispatcher("/jsps/restaurant/personal_info/personal_info.jsp").forward(request, response);
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
