package j2ee.servlets.member;

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
import j2ee.model.Member_info;
import j2ee.model.Member_level_money_info;
import j2ee.model.Restaurant_info;

/**
 * Servlet implementation class register2_mail_ensure
 */
@WebServlet("/register2_mail_ensure")
public class register2_mail_ensure extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register2_mail_ensure() {
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
		String send_identifying_code=(String) session.getAttribute("identifyingCode");//原本发送的邮箱验证码
		String in_identifying_code=(String) request.getParameter("in_identify_code");//注册用户输入的验证码
		
		//比较两个验证码
		if(send_identifying_code.equals(in_identifying_code)) {//若相同：进入登录页面，注意设置session的user_info的信息
			ArrayList<Restaurant_info> rests_info=(ArrayList<Restaurant_info>) ServiceFactory.getRestaurant_infoManageService().getAllRestsInfo();
			session.setAttribute("all_rest_info", rests_info);
			session.setAttribute("rest_type_id", 0);
			Member_info m=(Member_info) session.getAttribute("register_new_member");
			
			//在member_info的表格当中存储新的信息
			ServiceFactory.getMember_infoManageService().addMemberInfo(m);
			//在member_level_money_info表格中添加一条消息
			Member_level_money_info mlmi=new Member_level_money_info();
			mlmi.setId(UUID.randomUUID().toString());
			mlmi.setLevel(0);
			mlmi.setMember_id(m.getId());
			mlmi.setSum_money(0);
			ServiceFactory.getMember_level_money_infoManageService().add_info(mlmi);
			
			//跳转到餐厅页面
			session.setAttribute("user_info", m);
			context.getRequestDispatcher("/jsps/member/order_meal/choose_restaurant.jsp").forward(request, response);
			
		}
		else {//若不同：返回注册页面
			session.setAttribute("register_err_info", "邮箱验证码错误！");
			context.getRequestDispatcher("/jsps/member/register.jsp").forward(request, response);	
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
