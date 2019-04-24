package j2ee.dao;

import java.util.List;

public interface BaseDao {

	public void flush();

	public void clear() ;

	public Object load(Class c, String id) ;

	public void save(Object bean) ;

	public void update(Object bean) ;

	public void delete(Object bean) ;
	
	//hql���
	public List retByQuery(String hql);
	
}
