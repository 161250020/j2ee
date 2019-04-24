package j2ee.servlets.restaurant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import j2ee.factory.ServiceFactory;
import j2ee.model.Member_order_info;
import j2ee.model.Restaurant_info;
import j2ee.model.Restaurant_register_application_info;

/**
 * Servlet implementation class register2
 */
@WebServlet("/rest/register2")
public class register2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register2() {
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
		
		//获得输入的邮箱验证码
		String in_identifycode=request.getParameter("in_identifycode");
		String identifyingCode=(String)session.getAttribute("identifyingCode");
		if(in_identifycode.equals(identifyingCode)) {//验证码正确
			//获得注册的餐厅的基本信息
			Restaurant_info r=(Restaurant_info)session.getAttribute("register_new_rest");
			
			//添加审批信息---餐厅
			Restaurant_register_application_info rrai=new Restaurant_register_application_info();
			rrai.setId(UUID.randomUUID().toString());
			rrai.setAddress(r.getAddress());
			rrai.setBank_id(r.getBank_id());
			rrai.setCity(r.getCity());
			rrai.setDate(r.getDate());
			rrai.setDistrict(r.getDistrict());
			rrai.setLogin_id(r.getLogin_id());
			rrai.setMail(r.getMail());
			rrai.setName(r.getName());
			rrai.setPassword(r.getPassword());
			rrai.setResult(0);
			rrai.setStreet(r.getStreet());
			rrai.setTown(r.getTown());
			rrai.setType_id(r.getType_id());
			ServiceFactory.getRestaurant_register_application_infoManageService().addARegisterApplication(rrai);
			
			//跳转到提示页面---正在审批
			context.getRequestDispatcher("/jsps/restaurant/examing.jsp").forward(request, response);
		}
		else {
			String register_err_info="验证码错误！";
			session.setAttribute("register_err_info", register_err_info);
			context.getRequestDispatcher("/jsps/restaurant/register.jsp").forward(request, response);	
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
