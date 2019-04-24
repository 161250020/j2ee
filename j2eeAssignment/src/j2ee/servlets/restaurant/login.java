package j2ee.servlets.restaurant;

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
import j2ee.model.Member_order_info;
import j2ee.model.Restaurant_info;
import j2ee.model.Restaurant_register_application_info;

/**
 * Servlet implementation class login
 */
@WebServlet("/restaurant/login")
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
		ServletContext context = getServletContext();
		String value=request.getParameter("function");
		
		if(value.equals("ע��")) {//��ת��ע��ҳ��
			session.setAttribute("register_err_info", "������Ϣ��");
			//����µ�ע�������7λ����
			String chars7=ServiceFactory.getRestaurant_infoManageService().get7Chars();
			session.setAttribute("new_7chars", chars7);
			context.getRequestDispatcher("/jsps/restaurant/register.jsp").forward(request, response);	
		}
		else if(value.equals("login")) {//ʵ�ֵ�¼���ܣ������¼�ɹ�����ת����ҳ��
			String in_username=request.getParameter("in_username");
			String in_pass=request.getParameter("in_pass");
			
			//������в�����Ϣ
			ArrayList<Restaurant_info> Restaurant_infos= (ArrayList<Restaurant_info>) ServiceFactory.getRestaurant_infoManageService().getAllRestsInfo();
			Restaurant_info rest=new Restaurant_info();
			boolean exist=false;//�������Ĳ����Ƿ����
			String err_info="";//��¼������ʾ
			for(int i=0;i<Restaurant_infos.size();i++){
				//���������/�û�������
				if((Restaurant_infos.get(i).getLogin_id().equals(in_username))||(Restaurant_infos.get(i).getMail().equals(in_username))){
					exist=true;
					rest=Restaurant_infos.get(i);
					//�����������ȷ
					if(rest.getPassword().equals(in_pass)){
						session.setAttribute("rest_info", rest);
						//��ת����ҳ��---����
						//��øò������ж�����Ϣ
						ArrayList<Member_order_info> orders=(ArrayList<Member_order_info>) ServiceFactory.getMember_order_infoManageService().getAllOrdersByRestId(rest.getLogin_id());
						session.setAttribute("rest_orders_list", orders);
						context.getRequestDispatcher("/jsps/restaurant/orders/order_list.jsp").forward(request, response);
					}
					else{
						err_info="�����������";
						//���ص�¼ҳ��
						session.setAttribute("err_info", err_info);
						context.getRequestDispatcher("/jsps/restaurant/login.jsp").forward(request, response);
					}
				}
			}

			//������û���/�����û�������
			if(!exist){
				err_info="�ò��������ڣ�";
				//���ص�¼ҳ��
				session.setAttribute("err_info", err_info);
				context.getRequestDispatcher("/jsps/restaurant/login.jsp").forward(request, response);
			}

		}
		else {//�鿴����ע����������
			ArrayList<Restaurant_register_application_info> arr=new ArrayList();
			session.setAttribute("examine_result", arr);
			context.getRequestDispatcher("/jsps/restaurant/examine_result.jsp").forward(request, response);
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
