package j2ee.servlets.member;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import j2ee.factory.ServiceFactory;
import j2ee.model.Member_info;
import j2ee.model.Member_level_money_info;
import j2ee.model.Member_level_preferencialstrategies;
import j2ee.model.Member_order_content_info;
import j2ee.model.Member_order_info;
import j2ee.model.Restaurant_preferencial_info;
import j2ee.model.Restaurant_setmeal_info;
import j2ee.model.Restaurant_singleproduct_info;

/**
 * Servlet implementation class pay_orders
 */
@WebServlet("/member/pay_orders")
public class pay_orders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public pay_orders() {
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
		
		String id=request.getParameter("order_to_pay_id");
		Member_order_info moi=ServiceFactory.getMember_order_infoManageService().getInfoById(id);
		
		//֧�������Ľ��
		Member_info user_info=(Member_info) session.getAttribute("user_info");
		double money=ServiceFactory.getCyber_bank_infoManageService().getMoneyById(user_info.getBank_id());
		//������
		if(money<moi.getSum_price()) {
			//�޸Ķ�������
			moi.setResult(4);
			ServiceFactory.getMember_order_infoManageService().change_info(moi);
			//����session-order_pay_result������
			session.setAttribute("order_pay_result", "1");
			context.getRequestDispatcher("/jsps/member/order_meal/pay_result.jsp").forward(request, response);
			
		}
		//��֧���ɹ�
		else {
			//�޸Ķ�������
			moi.setResult(2);
			moi.setState(0);
			moi.setPay_time(new Timestamp(new java.util.Date().getTime()));
			ServiceFactory.getMember_order_infoManageService().change_info(moi);
			//�޸��Ż�ʹ�óɹ��Ľ��
			String date=new java.sql.Date(new java.util.Date().getTime()).toString();
			ServiceFactory.getMember_ps_receive_infoManageService().changeResultByMember_idAndDate(user_info.getId(), date);
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
			//�޸�����Ǯ��---yummy
			double pre_money_yummy=ServiceFactory.getCyber_bank_infoManageService().getMoneyById("yummy");
			ServiceFactory.getCyber_bank_infoManageService().changeMoneyById("yummy", pre_money_yummy+moi.getSum_price());
			//��ȥ���ݿ�����Ʒ�Ŀ��
			//�����е�����
			ArrayList<Member_order_content_info> arr=(ArrayList<Member_order_content_info>) ServiceFactory.getMember_order_content_infoManageService().orderContentByOrder_list_id(moi.getOrder_list_id());
			//�������̼ҵ���Ʒ����
			ArrayList<Restaurant_singleproduct_info> com1=(ArrayList<Restaurant_singleproduct_info>) ServiceFactory.getRestaurant_singleproduct_infoManageService().getAllSingleproductById(moi.getRestaurant_id());
			ArrayList<Restaurant_setmeal_info> com2=(ArrayList<Restaurant_setmeal_info>) ServiceFactory.getRestaurant_setmeal_infoManageService().getAllSetmealById(moi.getRestaurant_id());
			ArrayList<Restaurant_preferencial_info> com3=(ArrayList<Restaurant_preferencial_info>) ServiceFactory.getRestaurant_preferencial_infoManageService().getAllPreferencialById(moi.getRestaurant_id());
			for(int i=0;i<arr.size();i++) {
				if(arr.get(i).getType()==0) {//��Ʒ
					for(int j=0;j<com1.size();j++) {//�������̼ҵĵ�Ʒ
						if(com1.get(j).getCommodity_name().equals(arr.get(i).getName())) {
							//��ȥ���ݿ�����Ʒ�Ŀ��
							Restaurant_singleproduct_info r=com1.get(j);
							r.setCom_num(r.getCom_num()-arr.get(i).getNum());
							ServiceFactory.getRestaurant_singleproduct_infoManageService().changeNum(r);
							break;
						}
					}
				}
				else if(arr.get(i).getType()==1) {//�ײ�
					for(int j=0;j<com2.size();j++) {//�������̼ҵĵ�Ʒ
						if(com2.get(j).getSet_meal_name().equals(arr.get(i).getName())) {
							//��ȥ���ݿ�����Ʒ�Ŀ��
							Restaurant_setmeal_info r=com2.get(j);
							r.setSetmeal_num(r.getSetmeal_num()-arr.get(i).getNum());
							ServiceFactory.getRestaurant_setmeal_infoManageService().changeNum(r);
							break;
						}
					}
				}
				else {//�Ż�
					for(int j=0;j<com3.size();j++) {//�������̼ҵĵ�Ʒ
						if(com3.get(j).getCom_name().equals(arr.get(i).getName())) {
							//��ȥ���ݿ�����Ʒ�Ŀ��
							Restaurant_preferencial_info r=com3.get(j);
							r.setNum(r.getNum()-arr.get(i).getNum());
							ServiceFactory.getRestaurant_preferencial_infoManageService().changeNum(r);
							break;
						}
					}
				}
			}
			
			//����session-order_pay_result������
			session.setAttribute("order_pay_result", "2");
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
