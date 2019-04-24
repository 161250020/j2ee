package j2ee.servlets.member;

import java.io.IOException;
import java.sql.Timestamp;
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
 * Servlet implementation class order
 */
@WebServlet("/order_meal/order")
public class order extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public order() {
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
		
		//��Ա����Ϣ
		Member_info m=(Member_info)session.getAttribute("user_info");
		//�����ܼ�
		double sum=(double) session.getAttribute("order_sum_money");
		//�Żݽ��
		double ps_money=(double) session.getAttribute("ps_money");
		//���������Ʒ��Ϣ
	    ArrayList<Restaurant_singleproduct_info> restaurant_singleproduct_infos=(ArrayList<Restaurant_singleproduct_info>)session.getAttribute("choose_rest_singleproduct");
	    ArrayList<Restaurant_setmeal_info> restaurant_setmeal_infos=(ArrayList<Restaurant_setmeal_info>)session.getAttribute("choose_rest_setmeal");
	    ArrayList<Restaurant_preferencial_info> restaurant_preferencial_infos=(ArrayList<Restaurant_preferencial_info>)session.getAttribute("choose_rest_preferencial");
	    //���������Ʒ������Ϣ��ҳ���������ʾ����>0����Ʒ��
	    ArrayList<Integer> num1= (ArrayList<Integer>) session.getAttribute("num1");
	    ArrayList<Integer> num2= (ArrayList<Integer>) session.getAttribute("num2");
	    ArrayList<Integer> num3= (ArrayList<Integer>) session.getAttribute("num3");
		
		//ѡ��ĵ�ַ
		String add_id=request.getParameter("select_address");
		ArrayList<Delivery_address_info> addresses=(ArrayList<Delivery_address_info>) session.getAttribute("member_addresses");
		Delivery_address_info dai=new Delivery_address_info();//�����ĵ�ַ����Ϣ
		for(int i=0;i<addresses.size();i++) {
			if(addresses.get(i).getId().equals(add_id)) {
				dai=addresses.get(i);
				break;
			}
		}
		session.setAttribute("order_address_info", dai);
		
		//ϵͳ�жϵ��Ͳ�ʱ��
		//����ͬ�ֵ�����ͬ�򣬲�ͬ������ͬ�У��Ͳ�ʱ�䲻������
		Restaurant_info rest_info=(Restaurant_info) session.getAttribute("choose_rest_info");
		int min_mins=0;
		if(dai.getCity().equals(rest_info.getCity())) {
			if(dai.getDistrict().equals(rest_info.getDistrict())) {
				if(dai.getTown().equals(rest_info.getTown())) {
					if(dai.getStreet().equals(rest_info.getStreet())) {
						min_mins=20;//����Ͳ�ʱ�䣺20����
					}
					else {//��ͬ�ֵ�
						min_mins=30;//����Ͳ�ʱ�䣺30����
					}
				}
				else {//��ͬ��
					min_mins=40;//����Ͳ�ʱ�䣺40����
				}
			}
			else {//��ͬ��
				min_mins=60;//����Ͳ�ʱ�䣺60����
			}
		}
		else {//��ͬ��
			min_mins=120;//����Ͳ�ʱ�䣺120����
		}
		session.setAttribute("min_mins", min_mins);
		
		//���ɶ�����Ϣ---�洢��������Ϣ������content���ű���
		//����һ��member_order_info����Ϣ
		Member_order_info moi=new Member_order_info();
		moi.setId(UUID.randomUUID().toString());
		String order_list_id=UUID.randomUUID().toString();
		moi.setOrder_list_id(order_list_id);
		moi.setOrder_time(new Timestamp(new java.util.Date().getTime()));
		moi.setDelivery_time_defined(min_mins+"");
		moi.setMember_id(m.getId());
		moi.setRestaurant_id(rest_info.getLogin_id());
		moi.setSum_price(sum);
		moi.setResult(1);
		moi.setDelivery_address_id(dai.getId());
		moi.setPs_money_yummy(ps_money);
		moi.setDelivery_time_os(min_mins+"");
		moi.setAccount(0);
		session.setAttribute("order_info", moi);
		ServiceFactory.getMember_order_infoManageService().addANewOrder(moi);
		//�޸��Ż�ʹ�óɹ��Ľ��
		String date=new java.sql.Date(new java.util.Date().getTime()).toString();
		ServiceFactory.getMember_ps_receive_infoManageService().changeResultByMember_idAndDate(m.getId(), date);
		//���ɶ���member_order_content_info����Ϣ
		for(int i=0;i<num1.size();i++) {
			if(num1.get(i)>0) {//���һ��member_order_content_info��Ϣ
				Member_order_content_info moci=new Member_order_content_info();
				moci.setId(UUID.randomUUID().toString());
				moci.setName(restaurant_singleproduct_infos.get(i).getCommodity_name());
				moci.setNum(num1.get(i));
				moci.setOrder_list_id(order_list_id);
				moci.setPrice(num1.get(i)*restaurant_singleproduct_infos.get(i).getCom_price());
				moci.setType(0);
				ServiceFactory.getMember_order_content_infoManageService().addARecord(moci);
				
			}
		}
		for(int i=0;i<num2.size();i++) {
			if(num2.get(i)>0) {//���һ��member_order_content_info��Ϣ
				Member_order_content_info moci=new Member_order_content_info();
				moci.setId(UUID.randomUUID().toString());
				moci.setName(restaurant_setmeal_infos.get(i).getSet_meal_name());
				moci.setNum(num2.get(i));
				moci.setOrder_list_id(order_list_id);
				moci.setPrice(num2.get(i)*restaurant_setmeal_infos.get(i).getSetmeal_price());
				moci.setType(1);
				ServiceFactory.getMember_order_content_infoManageService().addARecord(moci);

			}
		}
		for(int i=0;i<num3.size();i++) {
			if(num3.get(i)>0) {//���һ��member_order_content_info��Ϣ
				Member_order_content_info moci=new Member_order_content_info();
				moci.setId(UUID.randomUUID().toString());
				moci.setName(restaurant_preferencial_infos.get(i).getCom_name());
				moci.setNum(num3.get(i));
				moci.setOrder_list_id(order_list_id);
				moci.setPrice(num3.get(i)*restaurant_preferencial_infos.get(i).getNow_price());
				moci.setType(2);
				ServiceFactory.getMember_order_content_infoManageService().addARecord(moci);

			}
		}
		
		context.getRequestDispatcher("/jsps/member/order_meal/pay.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
