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
import j2ee.model.Restaurant_info;
import j2ee.model.Restaurant_preferencial_info;
import j2ee.model.Restaurant_setmeal_info;
import j2ee.model.Restaurant_singleproduct_info;

/**
 * Servlet implementation class choose_restaurant
 */
@WebServlet("/order_meal/choose_restaurant")
public class choose_restaurant extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public choose_restaurant() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String rest_login_id=request.getParameter("rest_id");
		HttpSession session=request.getSession(true);
		ServletContext context = getServletContext();
		
		//根据餐厅的7位ID，获得餐厅的基本信息
		Restaurant_info rest_info=ServiceFactory.getRestaurant_infoManageService().getInfoBy7Chars(rest_login_id);
		//将餐厅的基本信息存储在session当中，跳转到餐厅的详情页面
		session.setAttribute("choose_rest_info", rest_info);
		//单品的基本信息
		ArrayList<Restaurant_singleproduct_info> restaurant_singleproduct_infos2=(ArrayList<Restaurant_singleproduct_info>) ServiceFactory.getRestaurant_singleproduct_infoManageService().getAllSingleproductById(rest_login_id);
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
		//套餐的基本信息
		ArrayList<Restaurant_setmeal_info> restaurant_setmeal_infos2=(ArrayList<Restaurant_setmeal_info>) ServiceFactory.getRestaurant_setmeal_infoManageService().getAllSetmealById(rest_login_id);
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
		//优惠的基本信息
		ArrayList<Restaurant_preferencial_info> restaurant_preferencial_infos2=(ArrayList<Restaurant_preferencial_info>) ServiceFactory.getRestaurant_preferencial_infoManageService().getAllPreferencialById(rest_login_id);
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
		session.setAttribute("choose_commodity_err_info", "商品选择错误原因：");
		context.getRequestDispatcher("/jsps/member/order_meal/choose_commodity.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
