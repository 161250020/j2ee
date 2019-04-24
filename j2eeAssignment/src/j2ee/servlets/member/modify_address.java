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
		
		//获得方法的类型
		String func=request.getParameter("func");
		//获得要修改的地址的ID
		String add_id=(String) session.getAttribute("modify_address_id");
		//根据地址的ID获得地址的信息
		Delivery_address_info dai=(Delivery_address_info) ServiceFactory.getDelivery_address_infoManageService().getDelivery_address_infoById(add_id);
		
		if(func.equals("abandon")) {//注销此地址，不再使用此地址
			//修改session当中的member_addresses内容
			ArrayList<Delivery_address_info> dais=(ArrayList<Delivery_address_info>) session.getAttribute("member_addresses");
			ArrayList<Delivery_address_info> dais2=new ArrayList();
			for(int i=0;i<dais.size();i++) {
				if(!dais.get(i).getId().equals(add_id)) {//不为要注销的地址的ID
					dais2.add(dais.get(i));
				}
			}
			session.setAttribute("member_addresses", dais2);
			//修改数据库内容
			dai.setIn_use(0);
			ServiceFactory.getDelivery_address_infoManageService().changeAddressInfo(dai);
		}
		else {//修改地址信息
			//获得输入的新内容
			String new_address=request.getParameter("new_address");
			String new_city=request.getParameter("new_city");
			String new_district=request.getParameter("new_district");
			String new_town=request.getParameter("new_town");
			String new_street=request.getParameter("new_street");
			
			//修改为对应的新的address信息
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
			
			//修改session当中的member_addresses
			ArrayList<Delivery_address_info> dais=(ArrayList<Delivery_address_info>) session.getAttribute("member_addresses");
			ArrayList<Delivery_address_info> dais2=new ArrayList();
			for(int i=0;i<dais.size();i++) {
				if(dais.get(i).getId().equals(add_id)) {//为要修改的地址的ID
					dais2.add(dai);//添加的是要修改的地址
				}
			}
			session.setAttribute("member_addresses", dais2);
			//修改数据库内容
			ServiceFactory.getDelivery_address_infoManageService().changeAddressInfo(dai);
		}
		
		//跳转到地址的页面
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
