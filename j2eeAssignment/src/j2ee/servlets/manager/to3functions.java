package j2ee.servlets.manager;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class to3functions
 */
@WebServlet("/manager/to3functions")
public class to3functions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public to3functions() {
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
        
        if(value.equals("sum")) {
        	context.getRequestDispatcher("/jsps/manager/look_over_sum_info/look_over_sum_info_restaurant.jsp").forward(request, response);
        }
        else if(value.equals("settle")) {
        	context.getRequestDispatcher("/jsps/manager/settle_accounts/settle_accounts.jsp").forward(request, response);
        }
        else {
        	context.getRequestDispatcher("/jsps/manager/examine_and_approve/examine_list_register.jsp").forward(request, response);
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
