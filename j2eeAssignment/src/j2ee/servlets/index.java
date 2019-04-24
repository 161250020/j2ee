package j2ee.servlets;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/index")
public class index extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session=request.getSession(true);
        String value=request.getParameter("submit");
        ServletContext context = getServletContext();
        
        if(value.equals("member")) {//ȥ��member�ĵ�¼����
        	session.setAttribute("err_info", "������ʾ��");
        	context.getRequestDispatcher("/jsps/member/login.jsp").forward(request, response);	
        }
        else if(value.equals("restaurant")) {//ȥ��restaurant�ĵ�¼����
        	session.setAttribute("err_info", "������ʾ��");
        	context.getRequestDispatcher("/jsps/restaurant/login.jsp").forward(request, response);	
        }
        else {//ȥ��manager�ĵ�¼����
        	session.setAttribute("err_info", "������ʾ��");
        	context.getRequestDispatcher("/jsps/manager/login.jsp").forward(request, response);	
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
