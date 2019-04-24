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
import j2ee.model.Member_info;
import j2ee.model.Member_order_info;
import j2ee.model.Restaurant_info;
import j2ee.model.Restaurant_register_application_info;

/**
 * Servlet implementation class login
 */
@WebServlet("/restaurant/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
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
		String value=request.getParameter("function");
		
		if(value.equals("注册")) {//跳转到注册页面
			session.setAttribute("register_err_info", "错误信息：");
			//获得新的注册餐厅的7位编码
			String chars7=ServiceFactory.getRestaurant_infoManageService().get7Chars();
			session.setAttribute("new_7chars", chars7);
			context.getRequestDispatcher("/jsps/restaurant/register.jsp").forward(request, response);	
		}
		else if(value.equals("login")) {//实现登录功能，如果登录成功，跳转到主页面
			String in_username=request.getParameter("in_username");
			String in_pass=request.getParameter("in_pass");
			
			//获得所有餐厅信息
			ArrayList<Restaurant_info> Restaurant_infos= (ArrayList<Restaurant_info>) ServiceFactory.getRestaurant_infoManageService().getAllRestsInfo();
			Restaurant_info rest=new Restaurant_info();
			boolean exist=false;//检测输入的餐厅是否存在
			String err_info="";//登录错误提示
			for(int i=0;i<Restaurant_infos.size();i++){
				//输入的邮箱/用户名存在
				if((Restaurant_infos.get(i).getLogin_id().equals(in_username))||(Restaurant_infos.get(i).getMail().equals(in_username))){
					exist=true;
					rest=Restaurant_infos.get(i);
					//输入的密码正确
					if(rest.getPassword().equals(in_pass)){
						session.setAttribute("rest_info", rest);
						//跳转到主页面---订单
						//获得该餐厅所有订单信息
						ArrayList<Member_order_info> orders=(ArrayList<Member_order_info>) ServiceFactory.getMember_order_infoManageService().getAllOrdersByRestId(rest.getLogin_id());
						session.setAttribute("rest_orders_list", orders);
						context.getRequestDispatcher("/jsps/restaurant/orders/order_list.jsp").forward(request, response);
					}
					else{
						err_info="输入密码错误！";
						//返回登录页面
						session.setAttribute("err_info", err_info);
						context.getRequestDispatcher("/jsps/restaurant/login.jsp").forward(request, response);
					}
				}
			}

			//输入的用户名/邮箱用户不存在
			if(!exist){
				err_info="该餐厅不存在！";
				//返回登录页面
				session.setAttribute("err_info", err_info);
				context.getRequestDispatcher("/jsps/restaurant/login.jsp").forward(request, response);
			}

		}
		else {//查看餐厅注册的审批结果
			ArrayList<Restaurant_register_application_info> arr=new ArrayList();
			session.setAttribute("examine_result", arr);
			context.getRequestDispatcher("/jsps/restaurant/examine_result.jsp").forward(request, response);
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
