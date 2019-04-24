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
import j2ee.model.Delivery_address_info;
import j2ee.model.Member_info;

/**
 * Servlet implementation class add_new_address
 */
@WebServlet("/self_info/add_new_address")
public class add_new_address extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public add_new_address() {
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
		
		//获得新地址的内容
		String new_address=request.getParameter("new_address");
		String new_city=request.getParameter("new_city");
		String new_district=request.getParameter("new_district");
		String new_town=request.getParameter("new_town");
		String new_street=request.getParameter("new_street");
		
		//获得当前会员的信息
		Member_info m=(Member_info) session.getAttribute("user_info");
		
		//需要添加的地址的内容
		Delivery_address_info dai=new Delivery_address_info();
		dai.setId(UUID.randomUUID().toString());
		dai.setAddress(new_address);
		dai.setCity(new_city);
		dai.setDistrict(new_district);
		dai.setIn_use(1);
		dai.setStreet(new_street);
		dai.setTown(new_town);
		dai.setMember_id(m.getId());
		
		//修改session当中的member_addresses
		ArrayList<Delivery_address_info> dais=(ArrayList<Delivery_address_info>) session.getAttribute("member_addresses");
		dais.add(dai);
		session.setAttribute("member_addresses", dais);
		//添加新地址到数据库
		ServiceFactory.getDelivery_address_infoManageService().add_new_address(dai);
		
		//跳转到地址
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
