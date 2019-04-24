package j2ee.dao;


import j2ee.model.Restaurant_singleproduct_info;

import java.util.List;

public interface Restaurant_singleproduct_infoDao extends BaseDao{

    /**
     * 根据餐厅ID（7位），获得该餐厅所有单品信息
     * */
    public List getAllSingleproductById(String id);

    /**
     * 餐厅发布单品商品的信息，新增一条单品商品的记录
     * */
    public void insertSingleproduct(Restaurant_singleproduct_info new_info);

    /**
     * 更新单品信息
     * */
    public void updateSingleproduct(Restaurant_singleproduct_info new_info);
}
