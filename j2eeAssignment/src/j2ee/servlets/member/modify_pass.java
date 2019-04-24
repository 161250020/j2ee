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
		
		//��Ա������Ϣ
		Member_info m=(Member_info) session.getAttribute("user_info");
		//����������Ϣ
		String raw_pass=request.getParameter("raw_pass");
		String new_pass=request.getParameter("new_pass");
		String re_new_pass=request.getParameter("re_new_pass");
		
		//�޸����������Ϣ
		String modify_pass_err_info="�޸����������Ϣ��";
		if(raw_pass.equals(m.getPassword())) {
			if(new_pass.equals(re_new_pass)) {
				if(new_pass.equals("")) {
					modify_pass_err_info="�����벻��Ϊ�գ�";
				}
				else {
					//�޸�session��user_info
					m.setPassword(new_pass);
					session.setAttribute("user_info", m);
					//�޸����ݿ��л�����Ϣ
					ServiceFactory.getMember_infoManageService().changeMemberInfo(m);
				}
			}
			else {
				modify_pass_err_info="�������������벻ͬ��";
			}
		}
		else {
			modify_pass_err_info="ԭ�����������";
		}
		session.setAttribute("modify_pass_err_info", modify_pass_err_info);
		//��ת���޸���Ϣ��ҳ��
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
