package j2ee.servlets.restaurant;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import j2ee.factory.ServiceFactory;
import j2ee.model.Restaurant_info;
import j2ee.model.Restaurant_publish_application_info;
import j2ee.model.Restaurant_singleproduct_info;

/**
 * Servlet implementation class new_single_product
 */
@WebServlet("/restaurant/new_single_product")
public class new_single_product extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public new_single_product() {
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
		
		//获得信息
		String new_name=request.getParameter("new_name");
		String new_price=request.getParameter("new_price");
		String new_num=request.getParameter("new_num");
		String new_start_time=request.getParameter("new_start_time");
		String new_end_time=request.getParameter("new_end_time");
		
		//获得该餐厅信息
		Restaurant_info m=(Restaurant_info) session.getAttribute("rest_info");
		
		//修改日期格式
		SimpleDateFormat  df = new SimpleDateFormat("yyyy-MM-dd");  
        java.util.Date d1=new java.util.Date();
        java.util.Date d2=new java.util.Date();
        try {
			d1 = df.parse(new_start_time);
			d2 = df.parse(new_end_time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		//进行存储---type=1
        Restaurant_publish_application_info rpai=new Restaurant_publish_application_info();
        rpai.setId(UUID.randomUUID().toString());
        rpai.setCom_type(1);
        rpai.setEnd_date(new java.sql.Date(d2.getTime()));
        rpai.setName(new_name);
        rpai.setNum(Integer.parseInt(new_num));
        rpai.setPrice(Double.parseDouble(new_price));
        rpai.setRestaurant_id(m.getLogin_id());
        rpai.setStart_date(new java.sql.Date(d1.getTime()));
        rpai.setResult(0);
		ServiceFactory.getRestaurant_publish_application_infoManageService().addNewPublishInfo(rpai);
		
		context.getRequestDispatcher("/jsps/restaurant/publish_info/single_product.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
