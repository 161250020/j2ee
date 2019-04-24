package j2ee.servlets.member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import j2ee.factory.ServiceFactory;
import j2ee.model.*;

/**
 * Servlet implementation class choose_commodity
 */
@WebServlet("/order_meal/choose_commodity")
public class choose_commodity extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public choose_commodity() {
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
		
		//����ԭ��
		String choose_commodity_err_info="";
		//��ò����Ļ�����Ϣ
		Restaurant_info rest_info=(Restaurant_info) session.getAttribute("choose_rest_info");
		//�����ʾ����Ʒ����Ϣ---�����ݿ��л����Ʒ���ڵ���Ϣ
		//��Ʒ�Ļ�����Ϣ
		ArrayList<Restaurant_singleproduct_info> restaurant_singleproduct_infos2=(ArrayList<Restaurant_singleproduct_info>) ServiceFactory.getRestaurant_singleproduct_infoManageService().getAllSingleproductById(rest_info.getLogin_id());
		ArrayList<Restaurant_singleproduct_info> restaurant_singleproduct_infos=new ArrayList();
		for(int i=0;i<restaurant_singleproduct_infos2.size();i++) {
			long today=new java.util.Date().getTime();
			if((restaurant_singleproduct_infos2.get(i).getStart_date()!=null)&&(restaurant_singleproduct_infos2.get(i).getEnd_date()!=null)) {
				long t1=restaurant_singleproduct_infos2.get(i).getStart_date().getTime();
				long t2=restaurant_singleproduct_infos2.get(i).getEnd_date().getTime();
				if((t1<=today)&&(t2>=today)) {
					restaurant_singleproduct_infos.add(restaurant_singleproduct_infos2.get(i));
				}
			}
		}
		session.setAttribute("choose_rest_singleproduct", restaurant_singleproduct_infos);
		//�ײ͵Ļ�����Ϣ
		ArrayList<Restaurant_setmeal_info> restaurant_setmeal_infos2=(ArrayList<Restaurant_setmeal_info>) ServiceFactory.getRestaurant_setmeal_infoManageService().getAllSetmealById(rest_info.getLogin_id());
		ArrayList<Restaurant_setmeal_info> restaurant_setmeal_infos=new ArrayList();
		for(int i=0;i<restaurant_setmeal_infos2.size();i++) {
			long today=new java.util.Date().getTime();
			if((restaurant_setmeal_infos2.get(i).getStart_date()!=null)&&(restaurant_setmeal_infos2.get(i).getEnd_date()!=null)) {
				long t1=restaurant_setmeal_infos2.get(i).getStart_date().getTime();
				long t2=restaurant_setmeal_infos2.get(i).getEnd_date().getTime();
				if((t1<=today)&&(t2>=today)) {
					restaurant_setmeal_infos.add(restaurant_setmeal_infos2.get(i));
				}
			}
		}
		session.setAttribute("choose_rest_setmeal", restaurant_setmeal_infos);
		//�ŻݵĻ�����Ϣ
		ArrayList<Restaurant_preferencial_info> restaurant_preferencial_infos2=(ArrayList<Restaurant_preferencial_info>) ServiceFactory.getRestaurant_preferencial_infoManageService().getAllPreferencialById(rest_info.getLogin_id());
		ArrayList<Restaurant_preferencial_info> restaurant_preferencial_infos=new ArrayList();
		for(int i=0;i<restaurant_preferencial_infos2.size();i++) {
			long today=new java.util.Date().getTime();
			if((restaurant_preferencial_infos2.get(i).getStart_date()!=null)&&(restaurant_preferencial_infos2.get(i).getEnd_date()!=null)) {
				long t1=restaurant_preferencial_infos2.get(i).getStart_date().getTime();
				long t2=restaurant_preferencial_infos2.get(i).getEnd_date().getTime();
				if((t1<=today)&&(t2>=today)) {
					restaurant_preferencial_infos.add(restaurant_preferencial_infos2.get(i));
				}
			}
		}
		session.setAttribute("choose_rest_preferencial", restaurant_preferencial_infos);
		//��д��Ʒ����������������������֣����Ҳ����Գ�����������
		//��Ʒ���ײͣ��Żݵ�����
		ArrayList<Integer> num1=new ArrayList();
		ArrayList<Integer> num2=new ArrayList();
		ArrayList<Integer> num3=new ArrayList();
				
		try {
			for(int i=0;i<restaurant_singleproduct_infos.size();i++) {
				String in=request.getParameter(restaurant_singleproduct_infos.get(i).getId()+"singleproduct");
				if(in.equals("")) {//���������Ϊ��
					num1.add(0);
				}
				else {
					int n=Integer.parseInt(in);
					if(n>=0) {
						num1.add(n);
					}
					else {
						choose_commodity_err_info="��д����Ʒ����������С��0��";
						break;
					}
				}
			}
			for(int i=0;i<restaurant_setmeal_infos.size();i++) {
				String in=request.getParameter(restaurant_setmeal_infos.get(i).getId()+"setmeal");
				if(in.equals("")) {//���������Ϊ��
					num2.add(0);
				}
				else {
					int n=Integer.parseInt(in);
					if(n>=0) {
						num2.add(n);
					}
					else {
						choose_commodity_err_info="��д����Ʒ����������С��0��";
						break;
					}
				}
			}
			for(int i=0;i<restaurant_preferencial_infos.size();i++) {
				String in=request.getParameter(restaurant_preferencial_infos.get(i).getId()+"preferencial");
				if(in.equals("")) {//���������Ϊ��
					num3.add(0);
				}
				else {
					int n=Integer.parseInt(in);
					if(n>=0) {
						num3.add(n);
					}
					else {
						choose_commodity_err_info="��д����Ʒ����������С��0��";
						break;
					}
				}
			}
		}
		catch(Exception e) {//��д����Ʒ��������������
			choose_commodity_err_info="��д����Ʒ�����з����ֵĴ��ڣ�";
			System.out.println("��д��Ʒ������ʱ�򣬴��ڷ����ֵ����룡");
		}
		
		//���������������
		for(int i=0;i<num1.size();i++) {
			if(!(num1.get(i)<=restaurant_singleproduct_infos.get(i).getCom_num())) {
				//��Ʒ��������������˿�������
				choose_commodity_err_info="��д����Ʒ�����������������";
				break;
			}
		}
		for(int i=0;i<num2.size();i++) {
			if(!(num2.get(i)<=restaurant_setmeal_infos.get(i).getSetmeal_num())) {
				//��Ʒ��������������˿�������
				choose_commodity_err_info="��д����Ʒ�����������������";
				break;
			}
		}
		for(int i=0;i<num3.size();i++) {
			if(!(num3.get(i)<=restaurant_preferencial_infos.get(i).getNum())) {
				//��Ʒ��������������˿�������
				choose_commodity_err_info="��д����Ʒ�����������������";
				break;
			}
		}
		
		//���û�д�����ת�����������ҳ��
		if(choose_commodity_err_info.equals("")) {
			//����member����Ϣ��øû�Ա����Щ֧���ĵ�ַ����ַ��Ҫ�ǿ�������ʹ�õ���Щ
			Member_info m=(Member_info) session.getAttribute("user_info");
			ArrayList<Delivery_address_info> addresses1=(ArrayList<Delivery_address_info>) ServiceFactory.getDelivery_address_infoManageService().getAllAddressByID(m.getId());
			ArrayList<Delivery_address_info> addresses=new ArrayList();//����ʹ�õĵ�ַ
			for(int i=0;i<addresses1.size();i++) {
				if(addresses1.get(i).getIn_use()==1) {
					addresses.add(addresses1.get(i));
				}
			}
			session.setAttribute("member_addresses", addresses);
			//��ʾ��Ҫ��ʾ����Ʒ����������=0�ľͲ���ʾ�ˣ�
			session.setAttribute("num1", num1);
			session.setAttribute("num2", num2);
			session.setAttribute("num3", num3);
			//��û�Ա���յ��Ż�
			double ps_money=0;
			Member_ps_receive_info getmps=ServiceFactory.getMember_ps_receive_infoManageService().getMemberPsMoney(m.getId());
			if(getmps.getId()==null) {//����Աû�е����Żݣ��������Ż�
				ServiceFactory.getMember_ps_receive_infoManageService().send_ps(m.getId());
				getmps=ServiceFactory.getMember_ps_receive_infoManageService().getMemberPsMoney(m.getId());
			}
			//��û�Ա�Ľ��տ����Żݵļ۸���ʹ����û���Ż�
			//δʹ���Żݣ������ŻݵĽ�ʹ�õ��Żݣ��������֧�����ɹ�Ҳ���õ�
			session.setAttribute("change_ps", new Member_ps_receive_info());
			if(getmps.getResult()==0) {
				ps_money=getmps.getPs_money();
				session.setAttribute("change_ps", getmps);
			}
			session.setAttribute("ps_money", ps_money);
			context.getRequestDispatcher("/jsps/member/order_meal/order.jsp").forward(request, response);
		}
		//����д��󣬷��ز�����ѡ����Ʒ��ҳ��
		else {
			session.setAttribute("choose_commodity_err_info", choose_commodity_err_info);
			context.getRequestDispatcher("/jsps/member/order_meal/choose_commodity.jsp").forward(request, response);
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
