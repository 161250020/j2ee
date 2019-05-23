package j2ee.servlets.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import j2ee.model.Member_info;

/**
 * Servlet implementation class register_mailOrNot
 */
@WebServlet("/register_mailOrNot")
public class register_mailOrNot extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register_mailOrNot() {
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
		String new_username=request.getParameter("new_username");
		String new_password=request.getParameter("new_password");
		String new_re_password=request.getParameter("new_re_password");
		String new_name=request.getParameter("new_name");
		String new_phone=request.getParameter("new_phone");
		String new_bank_id=request.getParameter("new_bank_id");
		String new_bank_pass=request.getParameter("new_bank_pass");
		String new_mail=request.getParameter("new_mail");
		
		String register_err_info="";
		//检测用户名不重复
		ArrayList<Member_info> members_info=(ArrayList<Member_info>) ServiceFactory.getMember_infoManageService().getAllMembersInfo();
		for(int i=0;i<members_info.size();i++) {
			if(members_info.get(i).getUsername().equals(new_username)) {
				register_err_info="用户名重复！";
				break;
			}
			else if(new_username.length()<=5) {
				register_err_info="用户名太短！";
				break;
			}
		}
		//检测邮箱不重复
		for(int i=0;i<members_info.size();i++) {
			if(members_info.get(i).getMail().equals(new_mail)) {
				register_err_info="该邮箱已注册！";
				break;
			}
		}
		//检测两次密码输入一致
		if(!new_password.equals(new_re_password)) {
			register_err_info="两次密码输入不一致！";
		}
		if(new_password.length()<=5) {
			register_err_info="密码太短！";
		}
		//检测网银存在，网银ID和密码符合
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
		//若有错误：返回错误信息
		if(!register_err_info.equals("")) {//有错误
			session.setAttribute("register_err_info", register_err_info);
			context.getRequestDispatcher("/jsps/member/register.jsp").forward(request, response);	
			
		}
		else {//若没有任何错误：将基本信息存储在session当中，发送验证码给邮箱，跳转到register2.jsp页面上面
			//存储注册信息在session当中
			Member_info new_member=new Member_info();
			new_member.setId(uuid);
			new_member.setLogoff(0);
			new_member.setBank_id(new_bank_id);
			//获得当前时间
			new_member.setCreate_date(new java.sql.Date(new java.util.Date().getTime()));
			new_member.setMail(new_mail);
			new_member.setName(new_name);
			new_member.setPassword(new_password);
			new_member.setPhone_number(new_phone);
			new_member.setUsername(new_username);
			session.setAttribute("register_new_member", new_member);
			
			//生成验验证码
			String identifyingCode=UUID.randomUUID().toString();
			session.setAttribute("identifyingCode", identifyingCode);
			//发送验证码
			String smtpServer="smtp.nju.edu.cn";//邮件服务器主机名
			String protocol="smtp";
			String username="161250020@smail.nju.edu.cn";
			String password="";//开启SMTP服务授权码/邮箱密码
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

			context.getRequestDispatcher("/jsps/member/register2.jsp").forward(request, response);	
			   
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
