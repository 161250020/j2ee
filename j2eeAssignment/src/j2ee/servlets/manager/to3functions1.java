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

/**
 * Servlet implementation class to3functions
 */
@WebServlet("/manager/to3functions1")
public class to3functions1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public to3functions1() {
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
        String value=request.getParameter("func");
        
        if(value.equals("restaurant")) {
        	context.getRequestDispatcher("/jsps/manager/look_over_sum_info/look_over_sum_info_restaurant.jsp").forward(request, response);
        }
        else if(value.equals("member")) {
        	//获得需要展示的数据
        	java.sql.Date date1=new java.sql.Date(new java.util.Date().getTime());
    		java.sql.Date date2=new java.sql.Date(new java.util.Date().getTime());
        	ArrayList<Double> nums=(ArrayList<Double>) ServiceFactory.getMember_order_infoManageService().getInfoByDate(date1, date2);
        	session.setAttribute("member_start_date", date1.toString());
        	session.setAttribute("member_end_date", date2.toString());
        	session.setAttribute("member_sum_nums", nums);
        	context.getRequestDispatcher("/jsps/manager/look_over_sum_info/look_over_sum_info_member.jsp").forward(request, response);
        }
        else {
        	//获得需要展示的数据
        	java.sql.Date date1=new java.sql.Date(new java.util.Date().getTime());
    		java.sql.Date date2=new java.sql.Date(new java.util.Date().getTime());
        	session.setAttribute("yummy_start_date", date1.toString());
        	session.setAttribute("yummy_end_date", date2.toString());
        	ArrayList<Double> nums=(ArrayList<Double>) ServiceFactory.getMember_order_infoManageService().getInfoByDate2(date1, date2);
        	session.setAttribute("yummy_sum_nums", nums);
        	context.getRequestDispatcher("/jsps/manager/look_over_sum_info/look_over_sum_info_yummy.jsp").forward(request, response);
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
