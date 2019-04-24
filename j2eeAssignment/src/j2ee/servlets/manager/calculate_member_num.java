package j2ee.servlets.manager;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
 * Servlet implementation class calculate_member_num
 */
@WebServlet("/manager/calculate_member_num")
public class calculate_member_num extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public calculate_member_num() {
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
        
        //需要计算的商家注册的时间段
        String start_date=request.getParameter("start_date");
        String end_date=request.getParameter("end_date");
        
        SimpleDateFormat  df = new SimpleDateFormat("yyyy-MM-dd");  
        java.util.Date d1=new java.util.Date();
        java.util.Date d2=new java.util.Date();
        try {
			d1 = df.parse(start_date);
			d2 = df.parse(end_date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        
        //获得商家数目
        session.setAttribute("member_start_date", start_date);
	    session.setAttribute("member_end_date", end_date);
        ArrayList<Double> nums=(ArrayList<Double>) ServiceFactory.getMember_order_infoManageService().getInfoByDate(d1, d2);
        session.setAttribute("member_sum_nums", nums);
        context.getRequestDispatcher("/jsps/manager/look_over_sum_info/look_over_sum_info_member.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
