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
		
		if(value.equals("ע��")) {//��ת��ע��ҳ��
			session.setAttribute("register_err_info", "������Ϣ��");
			context.getRequestDispatcher("/jsps/member/register.jsp").forward(request, response);	
		}
		else {//ʵ�ֵ�¼���ܣ������¼�ɹ�����ת����ҳ��
			String in_username=request.getParameter("in_username");
			String in_pass=request.getParameter("in_pass");

			//������л�Ա��Ϣ
			ArrayList<Member_info> member_infos= (ArrayList<Member_info>) ServiceFactory.getMember_infoManageService().getAllMembersInfo();
			Member_info member=new Member_info();
			boolean exist=false;//���������û��Ƿ����
			String err_info="";//��¼������ʾ
			for(int i=0;i<member_infos.size();i++){
				//���������/�û�������
				if((member_infos.get(i).getUsername().equals(in_username))||(member_infos.get(i).getMail().equals(in_username))){
					exist=true;
					member=member_infos.get(i);
					if(member.getLogoff()==0) {//δע��
						//�����������ȷ
						if(member.getPassword().equals(in_pass)){
							//��ת����ҳ��---ѡ�����
							//������в�������Ϣ
							ArrayList<Restaurant_info> rests_info=(ArrayList<Restaurant_info>) ServiceFactory.getRestaurant_infoManageService().getAllRestsInfo();
							session.setAttribute("all_rest_info", rests_info);
							session.setAttribute("rest_type_id", 0);
							session.setAttribute("user_info", member);
							context.getRequestDispatcher("/jsps/member/order_meal/choose_restaurant.jsp").forward(request, response);
						}
						else{
							err_info="�����������";
							//���ص�¼ҳ��
							session.setAttribute("err_info", err_info);
							context.getRequestDispatcher("/jsps/member/login.jsp").forward(request, response);
						}
					}
					else {//��ע��
						err_info="��Ա��ע����";
						//���ص�¼ҳ��
						session.setAttribute("err_info", err_info);
						context.getRequestDispatcher("/jsps/member/login.jsp").forward(request, response);
					}
				}
			}

			//������û���/�����û�������
			if(!exist){
				err_info="���û������ڣ�";
				//���ص�¼ҳ��
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
