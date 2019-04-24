package j2ee.dao;

import j2ee.model.Restaurant_setmeal_info;

import java.util.List;

public interface Restaurant_setmeal_infoDao extends BaseDao{

    /**
     * 根据餐厅ID（7位），获得该餐厅所有套餐信息
     * */
    public List getAllSetmealById(String id);

    /**
     * 餐厅发布套餐商品的信息，新增一条套餐商品的记录
     * */
    public void insertSetmeal(Restaurant_setmeal_info new_info);

    /**
     * 更新套餐信息
     * */
    public void updateSetmeal(Restaurant_setmeal_info new_info);
}
