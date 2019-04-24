package j2ee.servlets.member;

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
import j2ee.model.Restaurant_info;

/**
 * Servlet implementation class login
 */
@WebServlet("/member/login")
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
		String value=request.getParameter("function");
		ServletContext context = getServletContext();
		
		if(value.equals("注册")) {//跳转到注册页面
			session.setAttribute("register_err_info", "错误信息：");
			context.getRequestDispatcher("/jsps/member/register.jsp").forward(request, response);	
		}
		else {//实现登录功能，如果登录成功，跳转到主页面
			String in_username=request.getParameter("in_username");
			String in_pass=request.getParameter("in_pass");

			//获得所有会员信息
			ArrayList<Member_info> member_infos= (ArrayList<Member_info>) ServiceFactory.getMember_infoManageService().getAllMembersInfo();
			Member_info member=new Member_info();
			boolean exist=false;//检测输入的用户是否存在
			String err_info="";//登录错误提示
			for(int i=0;i<member_infos.size();i++){
				//输入的邮箱/用户名存在
				if((member_infos.get(i).getUsername().equals(in_username))||(member_infos.get(i).getMail().equals(in_username))){
					exist=true;
					member=member_infos.get(i);
					if(member.getLogoff()==0) {//未注销
						//输入的密码正确
						if(member.getPassword().equals(in_pass)){
							//跳转到主页面---选择餐厅
							//获得所有餐厅的信息
							ArrayList<Restaurant_info> rests_info=(ArrayList<Restaurant_info>) ServiceFactory.getRestaurant_infoManageService().getAllRestsInfo();
							session.setAttribute("all_rest_info", rests_info);
							session.setAttribute("rest_type_id", 0);
							session.setAttribute("user_info", member);
							context.getRequestDispatcher("/jsps/member/order_meal/choose_restaurant.jsp").forward(request, response);
						}
						else{
							err_info="输入密码错误！";
							//返回登录页面
							session.setAttribute("err_info", err_info);
							context.getRequestDispatcher("/jsps/member/login.jsp").forward(request, response);
						}
					}
					else {//已注销
						err_info="会员已注销！";
						//返回登录页面
						session.setAttribute("err_info", err_info);
						context.getRequestDispatcher("/jsps/member/login.jsp").forward(request, response);
					}
				}
			}

			//输入的用户名/邮箱用户不存在
			if(!exist){
				err_info="该用户不存在！";
				//返回登录页面
				session.setAttribute("err_info", err_info);
				context.getRequestDispatcher("/jsps/member/login.jsp").forward(request, response);
			}

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
