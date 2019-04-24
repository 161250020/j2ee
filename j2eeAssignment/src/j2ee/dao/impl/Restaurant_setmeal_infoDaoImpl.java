package j2ee.dao.impl;

import j2ee.dao.BaseDao;
import j2ee.dao.Restaurant_setmeal_infoDao;
import j2ee.model.Restaurant_setmeal_info;
import j2ee.model.Restaurant_singleproduct_info;

import java.util.ArrayList;
import java.util.List;

public class Restaurant_setmeal_infoDaoImpl extends BaseDaoImpl implements Restaurant_setmeal_infoDao {

    private static Restaurant_setmeal_infoDaoImpl restaurant_setmeal_infoDao=new Restaurant_setmeal_infoDaoImpl();

    private Restaurant_setmeal_infoDaoImpl() {

    }

    public static Restaurant_setmeal_infoDaoImpl getInstance() {
        return restaurant_setmeal_infoDao;
    }

    @Override
    public List getAllSetmealById(String id) {
        ArrayList<Restaurant_setmeal_info> list=new ArrayList();
        list=(ArrayList<Restaurant_setmeal_info>) super.retByQuery("from Restaurant_setmeal_info");

        ArrayList ret=new ArrayList();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getRestaurant_id().equals(id)){
                ret.add(list.get(i));
            }
        }

        return ret;
    }

    @Override
    public void insertSetmeal(Restaurant_setmeal_info new_info) {
        super.save(new_info);
    }

    @Override
    public void updateSetmeal(Restaurant_setmeal_info new_info) {
        super.update(new_info);
    }
}
