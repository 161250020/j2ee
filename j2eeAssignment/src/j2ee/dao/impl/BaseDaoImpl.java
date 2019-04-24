package j2ee.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import j2ee.dao.BaseDao;
import utils.HibernateUtil;


public class BaseDaoImpl implements BaseDao {
	
	public BaseDaoImpl() {
	}

	public void flush() {
		HibernateUtil.getSession().flush();
	}

	public void clear() {
		HibernateUtil.getSession().clear();
	}

	/** * ���� * * @param bean * */
	public void save(Object bean) {
		Session session =HibernateUtil.getSession() ;
		Transaction tx=session.beginTransaction();
		try {
			session.merge(bean);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
            session.close();
        } 
	}
	
	/** * ���� id ��ѯ��Ϣ * * @param id * @return */
	public Object load(Class c, String id) {
		Session session = HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		try {
			Object o=session.get(c, id);
			tx.commit();
			return o;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
            session.close();
        }
	}

	/** * ���� * * @param bean * */
	public void update(Object bean) {
		Session session =HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		try {
			session.update(bean);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
            session.close();
        } 
	}

	/** * ɾ�� * * @param bean * */
	public void delete(Object bean) {
		Session session =HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		try {
			session.delete(bean);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
            session.close();
        }
	}
	
	/*   ����hql��䷵��    */
	public List retByQuery(String hql) {
		// TODO Auto-generated method stub
		Session session =HibernateUtil.getSession() ;
		Transaction tx=session.beginTransaction();
		try {
			Query query=session.createQuery(hql);
			List ret=query.list();
			tx.commit();
			return ret;
		}catch (Exception e){
			if(tx!=null){
				tx.rollback();
			}
			e.printStackTrace();
			return null;
		}finally {
			session.close();
		}
	}
	
}
