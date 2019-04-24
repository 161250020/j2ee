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
 * Servlet implementation class modify_info
 */
@WebServlet("/self_info/modify_info")
public class modify_info extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public modify_info() {
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
		
		//����������Ϣ
		String new_name=request.getParameter("new_name");
		String new_phone=request.getParameter("new_phone");
		
		//�޸Ļ�Ա����Ϣ
		Member_info m=(Member_info) session.getAttribute("user_info");
		if(!new_name.equals("")) {//new_name��Ϊ��
			m.setName(new_name);
		}
		if(!new_phone.equals("")) {//new_phone��Ϊ��
			m.setPhone_number(new_phone);
		}
		ServiceFactory.getMember_infoManageService().changeMemberInfo(m);
		//����session�е�user_info����Ϣ
		session.setAttribute("user_info", m);
		
		//��ת���޸���Ϣ��ҳ��
		context.getRequestDispatcher("/jsps/member/self_info/modify_info.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
