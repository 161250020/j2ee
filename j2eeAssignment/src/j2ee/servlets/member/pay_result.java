package j2ee.servlets.member;

import java.io.IOException;
import java.sql.Date;
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
import j2ee.model.Cyber_bank_info;
import j2ee.model.Member_info;
import j2ee.model.Member_level_money_info;
import j2ee.model.Member_level_preferencialstrategies;
import j2ee.model.Member_order_content_info;
import j2ee.model.Member_order_info;
import j2ee.model.Restaurant_info;
import j2ee.model.Restaurant_preferencial_info;
import j2ee.model.Restaurant_setmeal_info;
import j2ee.model.Restaurant_singleproduct_info;

/**
 * Servlet implementation class pay_result
 */
@WebServlet("/order_meal/pay_result")
public class pay_result extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public pay_result() {
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
		
		//��ò����Ļ�����Ϣ
		Restaurant_info rest_info=(Restaurant_info) session.getAttribute("choose_rest_info");
		        //���������Ʒ���ڵ���Ϣ---�ڸ����ڼ����ݿ��������
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
				
	    //���������Ʒ������Ϣ��ҳ���������ʾ����>0����Ʒ��
	    ArrayList<Integer> num1= (ArrayList<Integer>) session.getAttribute("num1");
	    ArrayList<Integer> num2= (ArrayList<Integer>) session.getAttribute("num2");
	    ArrayList<Integer> num3= (ArrayList<Integer>) session.getAttribute("num3");
		
		//����ʹ��ʱ��---�޸Ķ�������
		String get_time=request.getParameter("get_time");
		session.setAttribute("get_time", get_time);
		Member_order_info moi=(Member_order_info) session.getAttribute("order_info");
		moi.setDelivery_time_defined(get_time);
		
		//�޸Ķ���֧�����---���ݿ��еĶ���֧�������session�е�����
		Member_info user_info=(Member_info) session.getAttribute("user_info");
		double money=ServiceFactory.getCyber_bank_infoManageService().getMoneyById(user_info.getBank_id());
		
		//������㹻֧����
		if(money>=moi.getSum_price()) {
			//�޸Ķ�������
			moi.setResult(2);
			moi.setState(0);
			moi.setPay_time(new Timestamp(new java.util.Date().getTime()));
			ServiceFactory.getMember_order_infoManageService().change_info(moi);
			//�޸Ļ�Ա�����������Լ���Ա�ȼ�
			Member_level_money_info mlmi=ServiceFactory.getMember_level_money_infoManageService().getInfoById(user_info.getId());
			ServiceFactory.getMember_level_money_infoManageService().changeSum_money(user_info.getId(), mlmi.getSum_money()+moi.getSum_price());//�޸������ѽ��
			ServiceFactory.getMember_level_money_infoManageService().upLevelOrNot(user_info.getId());//������Ա�ȼ����������Զ�������
			//�޸���Ӧ��Ա�ȼ���Ϣ�洢��session
			mlmi=ServiceFactory.getMember_level_money_infoManageService().getInfoById(user_info.getId());
			session.setAttribute("member_level", mlmi.getLevel());
			ArrayList<Member_level_preferencialstrategies> mlp=(ArrayList<Member_level_preferencialstrategies>) ServiceFactory.getMember_level_preferencialstrategiesManageService().getAllPss();
			String comment="";
			for(int i=0;i<mlp.size();i++) {
				if(mlp.get(i).getLevel()==mlmi.getLevel()) {//�ȼ���ͬ
					comment=mlp.get(i).getComment();
					break;
				}
			}
			session.setAttribute("member_level_comment", comment);
			//�޸�����Ǯ��---��Ա
			ServiceFactory.getCyber_bank_infoManageService().changeMoneyById(user_info.getBank_id(), money-moi.getSum_price());
			////�޸�����Ǯ��---yummy
			double pre_money_yummy=ServiceFactory.getCyber_bank_infoManageService().getMoneyById("yummy");
			ServiceFactory.getCyber_bank_infoManageService().changeMoneyById("yummy", pre_money_yummy+moi.getSum_price());
			//��ȥ���ݿ�����Ʒ�Ŀ�棬˳�����session������
			for(int i=0;i<num1.size();i++) {
				if(num1.get(i)>0) {//���һ��member_order_content_info��Ϣ
					//��ȥ���ݿ�����Ʒ�Ŀ�棬˳�����session������
					Restaurant_singleproduct_info r=restaurant_singleproduct_infos.get(i);
					r.setCom_num(r.getCom_num()-num1.get(i));
					ServiceFactory.getRestaurant_singleproduct_infoManageService().changeNum(r);
					restaurant_singleproduct_infos.set(i, r);
					session.setAttribute("choose_rest_singleproduct", restaurant_singleproduct_infos);
				}
			}
			for(int i=0;i<num2.size();i++) {
				if(num2.get(i)>0) {//���һ��member_order_content_info��Ϣ
					//��ȥ���ݿ�����Ʒ�Ŀ�棬˳�����session������
					Restaurant_setmeal_info r=restaurant_setmeal_infos.get(i);
					r.setSetmeal_num(r.getSetmeal_num()-num2.get(i));
					ServiceFactory.getRestaurant_setmeal_infoManageService().changeNum(r);
					restaurant_setmeal_infos.set(i, r);
					session.setAttribute("choose_rest_setmeal", restaurant_setmeal_infos);
				}
			}
			for(int i=0;i<num3.size();i++) {
				if(num3.get(i)>0) {//���һ��member_order_content_info��Ϣ
					//��ȥ���ݿ�����Ʒ�Ŀ�棬˳�����session������
					Restaurant_preferencial_info r=restaurant_preferencial_infos.get(i);
					r.setNum(r.getNum()-num3.get(i));
					ServiceFactory.getRestaurant_preferencial_infoManageService().changeNum(r);
					restaurant_preferencial_infos.set(i, r);
					session.setAttribute("choose_rest_preferencial", restaurant_preferencial_infos);
				}
			}
			//����session-order_pay_result������
			session.setAttribute("order_pay_result", "2");
			context.getRequestDispatcher("/jsps/member/order_meal/pay_result.jsp").forward(request, response);
		}
		else {
			//�޸Ķ�������
			moi.setResult(4);
			ServiceFactory.getMember_order_infoManageService().change_info(moi);
			//����session-order_pay_result������
			session.setAttribute("order_pay_result", "1");
			context.getRequestDispatcher("/jsps/member/order_meal/pay_result.jsp").forward(request, response);
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
