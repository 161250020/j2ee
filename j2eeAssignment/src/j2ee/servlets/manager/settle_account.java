package j2ee.servlets.manager;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import j2ee.factory.ServiceFactory;
import j2ee.model.Member_order_info;
import j2ee.model.Yummy_settle_accounts_info;

/**
 * Servlet implementation class settle_account
 */
@WebServlet("/manager/settle_account")
public class settle_account extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public settle_account() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	    //���һ�������¼
		HttpSession session=request.getSession(true);
        ServletContext context = getServletContext();
        Yummy_settle_accounts_info y=(Yummy_settle_accounts_info) session.getAttribute("account_info");
        ServiceFactory.getYummy_settle_accounts_infoManageService().addOneInfo(y);
        
        //�޸Ķ�������״̬
        //�����֧����������
        //��֧���Ľ���yummy���������м���---yummy�Ľ����һ��ʼ��ʱ����Ѿ������������У���������֧��
      	ArrayList<Member_order_info> order_infos=(ArrayList<Member_order_info>) session.getAttribute("account_orders");
		for(int i=0;i<order_infos.size();i++) {
			Member_order_info m=order_infos.get(i);
			m.setAccount(1);
			ServiceFactory.getMember_order_infoManageService().change_info(m);
			
			//��ò�����������Ϣ
			String bank_id=ServiceFactory.getRestaurant_infoManageService().getInfoBy7Chars(m.getRestaurant_id()).getBank_id();//����������ID
			double mon1=ServiceFactory.getCyber_bank_infoManageService().getMoneyById(bank_id);
			ServiceFactory.getCyber_bank_infoManageService().changeMoneyById(bank_id, mon1+m.getSum_price()/2);
			//���yummy��������Ϣ
			double mon2=ServiceFactory.getCyber_bank_infoManageService().getMoneyById("yummy");
			ServiceFactory.getCyber_bank_infoManageService().changeMoneyById("yummy", mon2-m.getSum_price()/2);
			
		}
        
		context.getRequestDispatcher("/jsps/manager/settle_accounts/settle_accounts.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
