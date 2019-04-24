package j2ee.dao.impl;


import j2ee.dao.BaseDao;
import j2ee.dao.Restaurant_register_application_infoDao;
import j2ee.model.Restaurant_register_application_info;
import j2ee.model.Restaurant_setmeal_info;

import java.util.ArrayList;
import java.util.List;

public class Restaurant_register_application_infoDaoImpl extends BaseDaoImpl implements Restaurant_register_application_infoDao {

    private static Restaurant_register_application_infoDaoImpl restaurant_register_application_infoDao=new Restaurant_register_application_infoDaoImpl();

    private Restaurant_register_application_infoDaoImpl() {

    }

    public static Restaurant_register_application_infoDaoImpl getInstance() {
        return restaurant_register_application_infoDao;
    }

    @Override
    public Restaurant_register_application_info getRestRegisterInfo(String id) {
        ArrayList<Restaurant_register_application_info> list=new ArrayList();
        list=(ArrayList<Restaurant_register_application_info>) super.retByQuery("from Restaurant_register_application_info");

        Restaurant_register_application_info ret=new Restaurant_register_application_info();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getId().equals(id)){
                ret=list.get(i);
            }
        }

        return ret;
    }

    @Override
    public void updateRegisterResult(String id, int result) {
        ArrayList<Restaurant_register_application_info> list=new ArrayList();
        list=(ArrayList<Restaurant_register_application_info>) super.retByQuery("from Restaurant_register_application_info");

        Restaurant_register_application_info ret=new Restaurant_register_application_info();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getId().equals(id)){
                ret=list.get(i);
            }
        }

        ret.setResult(result);
        super.update(ret);
    }

    @Override
    public void updateRegisterManager_id(String id, String manager_id) {
        ArrayList<Restaurant_register_application_info> list=new ArrayList();
        list=(ArrayList<Restaurant_register_application_info>) super.retByQuery("from Restaurant_register_application_info");

        Restaurant_register_application_info ret=new Restaurant_register_application_info();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getId().equals(id)){
                ret=list.get(i);
            }
        }

        ret.setManager_id(manager_id);
        super.update(ret);
    }

    @Override
    public List getAllRegisterApplications() {

        ArrayList list=new ArrayList();
        list=(ArrayList) super.retByQuery("from Restaurant_register_application_info");

        return list;
    }

    @Override
    public void insertARegisterApplication(Restaurant_register_application_info new_info) {
        super.save(new_info);
    }
}
