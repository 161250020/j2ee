package j2ee.dao.impl;

import j2ee.dao.BaseDao;
import j2ee.dao.Restaurant_modify_application_infoDao;
import j2ee.model.Restaurant_modify_application_info;
import j2ee.model.Restaurant_singleproduct_info;

import java.util.ArrayList;
import java.util.List;

public class Restaurant_modify_application_infoDaoImpl extends BaseDaoImpl implements Restaurant_modify_application_infoDao {

    private static Restaurant_modify_application_infoDaoImpl restaurant_modify_application_infoDao=new Restaurant_modify_application_infoDaoImpl();

    private Restaurant_modify_application_infoDaoImpl() {

    }

    public static Restaurant_modify_application_infoDaoImpl getInstance() {
        return restaurant_modify_application_infoDao;
    }

    @Override
    public Restaurant_modify_application_info getNewInfoById(String id) {
        ArrayList<Restaurant_modify_application_info> list=new ArrayList();
        list=(ArrayList<Restaurant_modify_application_info>) super.retByQuery("from Restaurant_modify_application_info");

        ArrayList<Restaurant_modify_application_info> ret=new ArrayList();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getId().equals(id)){
                ret.add(list.get(i));
            }
        }

        return ret.get(0);
    }

    @Override
    public void changeModifyResult(Restaurant_modify_application_info new_info) {
        super.update(new_info);
    }

    @Override
    public List getAllModifyApplications() {
        ArrayList list=new ArrayList();
        list=(ArrayList) super.retByQuery("from Restaurant_modify_application_info");

        return list;
    }

    @Override
    public void insertInfo(Restaurant_modify_application_info new_info) {
        super.save(new_info);
    }
}
