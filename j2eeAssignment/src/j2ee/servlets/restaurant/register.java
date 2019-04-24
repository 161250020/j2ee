package j2ee.servlets.restaurant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import j2ee.factory.ServiceFactory;
import j2ee.model.Cyber_bank_info;
import j2ee.model.Restaurant_info;

/**
 * Servlet implementation class register
 */
@WebServlet("/rest/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
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
		
		//输入的内容
		String uuid=UUID.randomUUID().toString();
		String login_id=(String)session.getAttribute("new_7chars");
		String new_password=request.getParameter("new_pass");
		String new_re_password=request.getParameter("re_new_pass");
		String new_name=request.getParameter("new_name");
		String new_type=request.getParameter("new_type");
		String new_add=request.getParameter("new_add");
		String new_city=request.getParameter("new_city");
		String new_district=request.getParameter("new_district");
		String new_town=request.getParameter("new_town");
		String new_street=request.getParameter("new_street");
		String new_bank_id=request.getParameter("new_bank_id");
		String new_bank_pass=request.getParameter("new_bank_pass");
		String new_mail=request.getParameter("new_email");
		
		String register_err_info="";
		//检测餐厅名称不可为空
		if(new_name.equals("")) {
			register_err_info="餐厅名称不可为空！";
		}
		//检测mail不重复
		ArrayList<Restaurant_info> rests_info=(ArrayList<Restaurant_info>) ServiceFactory.getRestaurant_infoManageService().getAllRestsInfo();
		for(int i=0;i<rests_info.size();i++) {
			if(rests_info.get(i).getMail().equals(new_mail)) {
				register_err_info="该邮箱已注册！";
				break;
			}
		}
		//检测两次密码输入一致
		if(!new_password.equals(new_re_password)) {
			register_err_info="两次密码输入不一致！";
		}
		//检测网银存在且密码正确
		ArrayList<Cyber_bank_info> cyber_bank_info=(ArrayList<Cyber_bank_info>) ServiceFactory.getCyber_bank_infoManageService().getAllBankInfo();
		Cyber_bank_info c=new Cyber_bank_info();
		boolean exist=false;
		for(int i=0;i<cyber_bank_info.size();i++) {
			if(cyber_bank_info.get(i).getId().equals(new_bank_id)) {
				c=cyber_bank_info.get(i);
				exist=true;
				break;
			}
		}
		if(exist) {
			if(!c.getPassword().equals(new_bank_pass)) {
				register_err_info="网银账户密码输入错误！";
			}
		}
		else {
			register_err_info="该网银账户不存在！";
		}
		
		//若有错误信息，返回注册页面
		if(!register_err_info.equals("")) {//有错误
			session.setAttribute("register_err_info", register_err_info);
			context.getRequestDispatcher("/jsps/restaurant/register.jsp").forward(request, response);	
		}
		//若无错误信息，前往订单展示页面
		else {
			//存储注册信息在session当中
			Restaurant_info r=new Restaurant_info();
			r.setAddress(new_add);
			r.setBank_id(new_bank_id);
			r.setCity(new_city);
			r.setDate(new java.sql.Date(new java.util.Date().getTime()));
			r.setDistrict(new_district);
			r.setId(uuid);
			r.setLogin_id(login_id);
			r.setMail(new_mail);
			r.setName(new_name);
			r.setPassword(new_password);
			r.setStreet(new_street);
			r.setTown(new_town);
			r.setType_id(Integer.parseInt(new_type));
			session.setAttribute("register_new_rest", r);
			
			//生成验验证码
			String identifyingCode=UUID.randomUUID().toString();
			session.setAttribute("identifyingCode", identifyingCode);
			//发送验证码
			String smtpServer="smtp.nju.edu.cn";//邮件服务器主机名
			String protocol="smtp";
			String username="161250020@smail.nju.edu.cn";
			String password="Dww112358";
			String from="161250020@smail.nju.edu.cn";
			String to=new_mail;
			String subject="yummy验证码";
			String body=identifyingCode;
			  
			Properties properties = new Properties();
			properties.put(javax.naming.Context.PROVIDER_URL, "jnp:///");
			properties.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY,"org.apache.naming.java.javaURLContextFactory");
			  
			  //获取Session对象
			  try {
			   InitialContext jndiContext = new InitialContext(properties);
			   Context envCtx = (Context) jndiContext.lookup("java:comp/env");
			   Session mailSession = (Session) envCtx.lookup("mail/Session");
			   //Context iniCtx=new InitialContext();
			   //Context envCtx=(Context) iniCtx.lookup("java:comp/env");
			   //Session session=(Session) envCtx.lookup("mail/Session");
			   
			   //创建代表邮件的MimeMessage对象
			   MimeMessage msg=new MimeMessage(mailSession);
			   msg.setFrom(new InternetAddress(from));
			   msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			   msg.setSentDate(new Date());
			   msg.setSubject(subject);
			   msg.setText(body);
			   msg.saveChanges();
			   
			Transport transport=mailSession.getTransport();
			transport.connect(smtpServer, username, password);
			transport.sendMessage(msg, msg.getRecipients(Message.RecipientType.TO));
			transport.close();

			context.getRequestDispatcher("/jsps/restaurant/register2.jsp").forward(request, response);	
			   
			  } catch (NamingException e) {
			   e.printStackTrace();
			  } catch (AddressException e) {
			   e.printStackTrace();
			  } catch (MessagingException e) {
			   e.printStackTrace();
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
