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
 * Servlet implementation class basic_function
 */
@WebServlet("/self_info/basic_function")
public class basic_function extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public basic_function() {
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
		
		//��ù��ܵ�����
		String func2=request.getParameter("func2");
		if(func2.equals("logout")) {//�˳���¼
			//�޸�session����
			if (null != session) {
            	session.invalidate();
                session = null;
            }
		}
		else {//ע���ʺ�
			//��Ա������Ϣ
			Member_info m=(Member_info) session.getAttribute("user_info");
			
			//�޸����ݿ��л�Ա��Ϣ����
			m.setLogoff(1);
			ServiceFactory.getMember_infoManageService().changeMemberInfo(m);
			
			//�޸�session����
			if (null != session) {
            	session.invalidate();
                session = null;
            }
		}
		
		//��¼������Ϣ
		String err_info="������Ϣ��";
		//���ص�¼ҳ��
		session=request.getSession(true);
		session.setAttribute("err_info", err_info);
		//ת����¼����
		context.getRequestDispatcher("/jsps/member/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
