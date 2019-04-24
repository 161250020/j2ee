package j2ee.dao;


import j2ee.model.Restaurant_preferencial_info;

import java.util.List;

public interface Restaurant_preferencial_infoDao extends BaseDao{

    /**
     * 根据餐厅ID（7位），获得该餐厅所有优惠商品信息
     * */
    public List getAllPreferencialById(String rest_id);

    /**
     * 餐厅发布优惠商品的信息，新增一条优惠商品的记录
     * */
    public void insertPreferencial(Restaurant_preferencial_info new_info);

    /**
     * 更新优惠信息
     * */
    public void updatePreferencial(Restaurant_preferencial_info new_info);
}
