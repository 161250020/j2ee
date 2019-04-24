package j2ee.servlets.restaurant;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class to2functions
 */
@WebServlet("/restaurant/to2functions")
public class to2functions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public to2functions() {
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
		
		if(func.equals("modify_pass")) {
			session.setAttribute("err_modify_info", "修改密码结果：");
			context.getRequestDispatcher("/jsps/restaurant/personal_info/modify_password.jsp").forward(request, response);
		}
		else if(func.equals("personal_info")) {
			context.getRequestDispatcher("/jsps/restaurant/personal_info/personal_info.jsp").forward(request, response);
		}
		else {
			//清空session
			if (null != session) {
            	session.invalidate();
                session = null;
            }
			
			//登录错误信息
			String err_info="错误信息：";
			//返回登录页面
			session=request.getSession(true);
			session.setAttribute("err_info", err_info);
			//转到登录界面
			context.getRequestDispatcher("/jsps/restaurant/login.jsp").forward(request, response);
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
