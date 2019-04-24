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
import j2ee.model.Member_level_money_info;
import j2ee.model.Member_level_preferencialstrategies;

/**
 * Servlet implementation class to5functions
 */
@WebServlet("/self_info/to5functions")
public class to5functions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public to5functions() {
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
		
		if(func.equals("basic_info")) {
			context.getRequestDispatcher("/jsps/member/self_info/basic_info.jsp").forward(request, response);
		}
		else if(func.equals("delivery_add")) {
			context.getRequestDispatcher("/jsps/member/self_info/delivery_address.jsp").forward(request, response);
		}
		else if(func.equals("change_pass")) {
			session.setAttribute("modify_pass_err_info", "修改密码错误信息：");
			context.getRequestDispatcher("/jsps/member/self_info/modify_password.jsp").forward(request, response);
		}
		else if(func.equals("member_level")) {
			//会员的基本信息
			Member_info user_info=(Member_info) session.getAttribute("user_info");
			//将相应会员等级信息存储在request
			Member_level_money_info mlmi=ServiceFactory.getMember_level_money_infoManageService().getInfoById(user_info.getId());
			session.setAttribute("member_level", mlmi.getLevel());
			ArrayList<Member_level_preferencialstrategies> mlp=(ArrayList<Member_level_preferencialstrategies>) ServiceFactory.getMember_level_preferencialstrategiesManageService().getAllPss();
			String comment="";
			for(int i=0;i<mlp.size();i++) {
				if(mlp.get(i).getLevel()==mlmi.getLevel()) {//等级相同
					comment=mlp.get(i).getComment();
					break;
				}
			}
			session.setAttribute("member_level_comment", comment);
			context.getRequestDispatcher("/jsps/member/self_info/member_level.jsp").forward(request, response);
		}
		else {
			context.getRequestDispatcher("/jsps/member/self_info/basic_function.jsp").forward(request, response);
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
