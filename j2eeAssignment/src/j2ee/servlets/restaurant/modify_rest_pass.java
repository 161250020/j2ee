package j2ee.servlets.restaurant;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import j2ee.factory.ServiceFactory;
import j2ee.model.Restaurant_info;

/**
 * Servlet implementation class modify_rest_pass
 */
@WebServlet("/restaurant/modify_rest_pass")
public class modify_rest_pass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public modify_rest_pass() {
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

		String raw_pass=request.getParameter("raw_pass");
		String new_pass=request.getParameter("new_pass");
		String re_new_pass=request.getParameter("re_new_pass");
		
		String err_modify_info="";
		//��������������Ƿ�һ��
		if(!new_pass.equals(re_new_pass)) {
			err_modify_info="������������벻һ����";
		}
		//ԭ������������Ƿ���ȷ
		//��ò�����¼��ʱ�����Ϣ��������session���е���Ϣ
	    Restaurant_info r= (Restaurant_info) session.getAttribute("rest_info");
		if(!r.getPassword().equals(raw_pass)) {
			err_modify_info="�����ԭ�������";
		}
		
		if(err_modify_info.equals("")) {//û�д���
			//�޸����ݿ��еĲ���������
			r.setPassword(new_pass);
			ServiceFactory.getRestaurant_infoManageService().modify_info(r);
			//�޸�session���еĲ�������Ϣ
			session.setAttribute("rest_info", r);
			//�޸�session�����޸��������Ϣ
			session.setAttribute("err_modify_info", "�޸�����ɹ���");
			//ת���޸������ҳ��
			context.getRequestDispatcher("/jsps/restaurant/personal_info/modify_password.jsp").forward(request, response);
			
		}
		else {
			//�޸�session�����޸��������Ϣ
			session.setAttribute("err_modify_info", err_modify_info);
			//ת���޸������ҳ��
			context.getRequestDispatcher("/jsps/restaurant/personal_info/modify_password.jsp").forward(request, response);
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
