package utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import j2ee.model.*;

public class HibernateUtil {
	private static SessionFactory sessionFactory;
	
	 public static SessionFactory getSessionFactory(){
		 try {
				Configuration config;
				ServiceRegistry serviceRegistry;
				config = new Configuration().configure();
				config.addAnnotatedClass(Cyber_bank_info.class);
				config.addAnnotatedClass(Delivery_address_info.class);
				config.addAnnotatedClass(Manager_info.class);
				config.addAnnotatedClass(Member_info.class);
				config.addAnnotatedClass(Member_level_money_info.class);
			 	config.addAnnotatedClass(Member_level_preferencialstrategies.class);
			 	config.addAnnotatedClass(Member_order_content_info.class);
			 	config.addAnnotatedClass(Member_order_info.class);
			 	config.addAnnotatedClass(Member_ps_receive_info.class);
			 	config.addAnnotatedClass(Restaurant_info.class);
			 	config.addAnnotatedClass(Restaurant_modify_application_info.class);
			 	config.addAnnotatedClass(Restaurant_preferencial_info.class);
			 	config.addAnnotatedClass(Restaurant_publish_application_info.class);
			 	config.addAnnotatedClass(Restaurant_register_application_info.class);
			 	config.addAnnotatedClass(Restaurant_setmeal_info.class);
			 	config.addAnnotatedClass(Restaurant_singleproduct_info.class);
			 	config.addAnnotatedClass(Restaurant_types.class);
			 	config.addAnnotatedClass(Yummy_settle_accounts_info.class);
			 	config.addAnnotatedClass(Yummy_settle_accounts_info.class);
				serviceRegistry =new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
				sessionFactory=config.buildSessionFactory(serviceRegistry);	
				return sessionFactory;
			}catch (Exception e) {
				e.printStackTrace();
				return null;
			}
	 }
	 
	 /** * gerCurrentSession 会自动关闭session，使用的是当前的session事务 * * @return */
	 public static Session getSession(){
		 return getSessionFactory().getCurrentSession();
	 }
}
