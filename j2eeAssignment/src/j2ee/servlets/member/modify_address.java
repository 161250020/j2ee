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
import j2ee.model.Delivery_address_info;

/**
 * Servlet implementation class modify_address
 */
@WebServlet("/self_info/modify_address")
public class modify_address extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public modify_address() {
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
		
		//��÷���������
		String func=request.getParameter("func");
		//���Ҫ�޸ĵĵ�ַ��ID
		String add_id=(String) session.getAttribute("modify_address_id");
		//���ݵ�ַ��ID��õ�ַ����Ϣ
		Delivery_address_info dai=(Delivery_address_info) ServiceFactory.getDelivery_address_infoManageService().getDelivery_address_infoById(add_id);
		
		if(func.equals("abandon")) {//ע���˵�ַ������ʹ�ô˵�ַ
			//�޸�session���е�member_addresses����
			ArrayList<Delivery_address_info> dais=(ArrayList<Delivery_address_info>) session.getAttribute("member_addresses");
			ArrayList<Delivery_address_info> dais2=new ArrayList();
			for(int i=0;i<dais.size();i++) {
				if(!dais.get(i).getId().equals(add_id)) {//��ΪҪע���ĵ�ַ��ID
					dais2.add(dais.get(i));
				}
			}
			session.setAttribute("member_addresses", dais2);
			//�޸����ݿ�����
			dai.setIn_use(0);
			ServiceFactory.getDelivery_address_infoManageService().changeAddressInfo(dai);
		}
		else {//�޸ĵ�ַ��Ϣ
			//��������������
			String new_address=request.getParameter("new_address");
			String new_city=request.getParameter("new_city");
			String new_district=request.getParameter("new_district");
			String new_town=request.getParameter("new_town");
			String new_street=request.getParameter("new_street");
			
			//�޸�Ϊ��Ӧ���µ�address��Ϣ
			if(!new_address.equals("")) {
				dai.setAddress(new_address);
			}
			if(!new_city.equals("")) {
				dai.setCity(new_city);
			}
			if(!new_district.equals("")) {
				dai.setDistrict(new_district);
			}
			if(!new_town.equals("")) {
				dai.setTown(new_town);
			}
			if(!new_street.equals("")) {
				dai.setStreet(new_street);
			}
			
			//�޸�session���е�member_addresses
			ArrayList<Delivery_address_info> dais=(ArrayList<Delivery_address_info>) session.getAttribute("member_addresses");
			ArrayList<Delivery_address_info> dais2=new ArrayList();
			for(int i=0;i<dais.size();i++) {
				if(dais.get(i).getId().equals(add_id)) {//ΪҪ�޸ĵĵ�ַ��ID
					dais2.add(dai);//��ӵ���Ҫ�޸ĵĵ�ַ
				}
			}
			session.setAttribute("member_addresses", dais2);
			//�޸����ݿ�����
			ServiceFactory.getDelivery_address_infoManageService().changeAddressInfo(dai);
		}
		
		//��ת����ַ��ҳ��
		context.getRequestDispatcher("/jsps/member/self_info/delivery_address.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
