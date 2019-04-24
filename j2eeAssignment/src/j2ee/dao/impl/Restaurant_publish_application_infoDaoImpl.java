package j2ee.dao.impl;

import java.util.ArrayList;
import java.util.List;

import j2ee.dao.Restaurant_publish_application_infoDao;
import j2ee.model.Restaurant_publish_application_info;


public class Restaurant_publish_application_infoDaoImpl extends BaseDaoImpl implements Restaurant_publish_application_infoDao {
	private static Restaurant_publish_application_infoDaoImpl restaurant_publish_application_infoDao=new Restaurant_publish_application_infoDaoImpl();

    private Restaurant_publish_application_infoDaoImpl() {

    }

    public static Restaurant_publish_application_infoDaoImpl getInstance() {
        return restaurant_publish_application_infoDao;
    }

	@Override
	public Restaurant_publish_application_info getNewInfoById(String id) {
		// TODO Auto-generated method stub
		Restaurant_publish_application_info ret=new Restaurant_publish_application_info();
		
		ArrayList<Restaurant_publish_application_info> list=new ArrayList();
        list=(ArrayList<Restaurant_publish_application_info>) super.retByQuery("from Restaurant_publish_application_info");

        for(int i=0;i<list.size();i++){
            if(list.get(i).getId().equals(id)){
                ret=list.get(i);
                break;
            }
        }

        return ret;
	}

	@Override
	public void changeInfo(Restaurant_publish_application_info new_info) {
		// TODO Auto-generated method stub
		super.update(new_info);
	}

	@Override
	public List getAllPublishApplications() {
		// TODO Auto-generated method stub
		
		ArrayList<Restaurant_publish_application_info> list=new ArrayList();
        list=(ArrayList<Restaurant_publish_application_info>) super.retByQuery("from Restaurant_publish_application_info");

		return list;
	}

	@Override
	public void insertInfo(Restaurant_publish_application_info new_info) {
		// TODO Auto-generated method stub
		super.save(new_info);
	}
    
	
}
