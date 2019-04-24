package j2ee.dao.impl;


import j2ee.dao.BaseDao;
import j2ee.dao.Restaurant_singleproduct_infoDao;
import j2ee.model.Member_ps_receive_info;
import j2ee.model.Restaurant_singleproduct_info;

import java.util.ArrayList;
import java.util.List;

public class Restaurant_singleproduct_infoDaoImpl extends BaseDaoImpl implements Restaurant_singleproduct_infoDao {

    private static Restaurant_singleproduct_infoDaoImpl restaurant_singleproduct_infoDao=new Restaurant_singleproduct_infoDaoImpl();

    private Restaurant_singleproduct_infoDaoImpl() {

    }

    public static Restaurant_singleproduct_infoDaoImpl getInstance() {
        return restaurant_singleproduct_infoDao;
    }

    @Override
    public List getAllSingleproductById(String id) {
        ArrayList<Restaurant_singleproduct_info> list=new ArrayList();
        list=(ArrayList<Restaurant_singleproduct_info>) super.retByQuery("from Restaurant_singleproduct_info");

        ArrayList ret=new ArrayList();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getRestaurant_id().equals(id)){
                ret.add(list.get(i));
            }
        }

        return ret;
    }

    @Override
    public void insertSingleproduct(Restaurant_singleproduct_info new_info) {
        super.save(new_info);
    }

    @Override
    public void updateSingleproduct(Restaurant_singleproduct_info new_info) {
        super.update(new_info);
    }
}
