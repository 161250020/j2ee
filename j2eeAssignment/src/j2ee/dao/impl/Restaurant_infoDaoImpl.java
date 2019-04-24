package j2ee.dao.impl;

import j2ee.dao.BaseDao;
import j2ee.dao.Restaurant_infoDao;
import j2ee.model.Restaurant_info;
import j2ee.model.Restaurant_modify_application_info;
import j2ee.model.Restaurant_register_application_info;

import java.util.ArrayList;
import java.util.List;

public class Restaurant_infoDaoImpl extends BaseDaoImpl implements Restaurant_infoDao {

    private static Restaurant_infoDaoImpl restaurant_infoDao=new Restaurant_infoDaoImpl();

    private Restaurant_infoDaoImpl() {

    }

    public static Restaurant_infoDaoImpl getInstance() {
        return restaurant_infoDao;
    }

    @Override
    public Restaurant_info getInfoBy7Chars(String chars) {
        ArrayList<Restaurant_info> list=new ArrayList();
        list=(ArrayList<Restaurant_info>) super.retByQuery("from Restaurant_info");

        Restaurant_info ret=new Restaurant_info();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getLogin_id().equals(chars)){
                ret=list.get(i);
            }
        }

        return ret;
    }

    @Override
    public void updateRestInfoBy7Chars(Restaurant_info newInfo) {
        super.update(newInfo);
    }

    @Override
    public void insertInfo(Restaurant_info newRestInfo) {
        super.save(newRestInfo);
    }

    @Override
    public List getAllRestsInfo() {

        ArrayList list=new ArrayList();
        list=(ArrayList) super.retByQuery("from Restaurant_info");

        return list;
    }
}
