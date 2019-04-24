package j2ee.servlets.member;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import j2ee.factory.ServiceFactory;
import j2ee.model.Member_info;

/**
 * Servlet implementation class modify_pass
 */
@WebServlet("/self_info/modify_pass")
public class modify_pass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public modify_pass() {
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
		
		//会员基本信息
		Member_info m=(Member_info) session.getAttribute("user_info");
		//获得输入的信息
		String raw_pass=request.getParameter("raw_pass");
		String new_pass=request.getParameter("new_pass");
		String re_new_pass=request.getParameter("re_new_pass");
		
		//修改密码错误信息
		String modify_pass_err_info="修改密码错误信息：";
		if(raw_pass.equals(m.getPassword())) {
			if(new_pass.equals(re_new_pass)) {
				if(new_pass.equals("")) {
					modify_pass_err_info="新密码不可为空！";
				}
				else {
					//修改session的user_info
					m.setPassword(new_pass);
					session.setAttribute("user_info", m);
					//修改数据库中基本信息
					ServiceFactory.getMember_infoManageService().changeMemberInfo(m);
				}
			}
			else {
				modify_pass_err_info="两次新密码输入不同！";
			}
		}
		else {
			modify_pass_err_info="原密码输入错误！";
		}
		session.setAttribute("modify_pass_err_info", modify_pass_err_info);
		//跳转到修改信息的页面
		context.getRequestDispatcher("/jsps/member/self_info/modify_password.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
