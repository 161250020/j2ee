package j2ee.dao.impl;


import j2ee.dao.BaseDao;
import j2ee.dao.Restaurant_preferencial_infoDao;
import j2ee.model.Restaurant_preferencial_info;
import j2ee.model.Restaurant_singleproduct_info;

import java.util.ArrayList;
import java.util.List;

public class Restaurant_preferencial_infoDaoImpl extends BaseDaoImpl implements Restaurant_preferencial_infoDao {

    private static Restaurant_preferencial_infoDaoImpl restaurant_preferencial_infoDao=new Restaurant_preferencial_infoDaoImpl();

    private Restaurant_preferencial_infoDaoImpl() {

    }

    public static Restaurant_preferencial_infoDaoImpl getInstance() {
        return restaurant_preferencial_infoDao;
    }

    @Override
    public List getAllPreferencialById(String rest_id) {
        ArrayList<Restaurant_preferencial_info> list=new ArrayList();
        list=(ArrayList<Restaurant_preferencial_info>) super.retByQuery("from Restaurant_preferencial_info");

        ArrayList ret=new ArrayList();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getRestaurant_id().equals(rest_id)){
                ret.add(list.get(i));
            }
        }

        return ret;
    }

    @Override
    public void insertPreferencial(Restaurant_preferencial_info new_info) {
        super.save(new_info);
    }

    @Override
    public void updatePreferencial(Restaurant_preferencial_info new_info) {
        super.update(new_info);
    }
}
