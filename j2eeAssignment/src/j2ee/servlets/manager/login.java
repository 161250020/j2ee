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
import j2ee.model.Manager_info;

/**
 * Servlet implementation class login
 */
@WebServlet("/manager/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
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
        
        //获得输入的内容
        String in_username=request.getParameter("in_username");
        String in_pass=request.getParameter("in_pass");
        
        //获得所有的经理的信息
        ArrayList<Manager_info> manager_infos=(ArrayList<Manager_info>) ServiceFactory.getManager_infoManageService().getAllManagers();
        Manager_info manager=new Manager_info();
        boolean exist=false;//检测经理是否存在
        String err_info="";//登录的错误提示
        for(int i=0;i<manager_infos.size();i++) {
        	if(manager_infos.get(i).getUsername().equals(in_username)) {
        		//该经理存在
        		manager=manager_infos.get(i);
        		exist=true;
        		break;
        	}
        }
        if(!exist) {//该经理不存在
        	err_info="该用户名的经理不存在！";
        	session.setAttribute("err_info", err_info);
        	context.getRequestDispatcher("/jsps/manager/login.jsp").forward(request, response);
        }
        else {//该经理存在
        	if(manager.getPassword().equals(in_pass)) {//密码输入正确
        		session.setAttribute("manager_info", manager);
        		//一开始需要显示的商家注册的时间段
        		java.sql.Date date1=new java.sql.Date(new java.util.Date().getTime());
        		java.sql.Date date2=new java.sql.Date(new java.util.Date().getTime());
        	    String start_date=date1.toString();
        	    String end_date=date2.toString();
        		session.setAttribute("start_date", start_date);
        	    session.setAttribute("end_date", end_date);
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
                int num=ServiceFactory.getRestaurant_infoManageService().countRegisterNum(d1, d2);
                session.setAttribute("register_rest_num", num);
        		context.getRequestDispatcher("/jsps/manager/look_over_sum_info/look_over_sum_info_restaurant.jsp").forward(request, response);
        	}
        	else {//密码输入错误
        		err_info="密码输入错误！";
            	session.setAttribute("err_info", err_info);
            	context.getRequestDispatcher("/jsps/manager/login.jsp").forward(request, response);
        	}
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
