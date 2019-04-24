package j2ee.dao;

import j2ee.model.Restaurant_info;
import j2ee.model.Restaurant_modify_application_info;
import j2ee.model.Restaurant_register_application_info;

import java.util.List;

public interface Restaurant_infoDao extends BaseDao{

    /**
     * 根据餐厅7位编码，获得餐厅基本信息
     * */
    public Restaurant_info getInfoBy7Chars(String chars);

    /**
     * 修改餐厅信息，根据7位编码，将餐厅信息修改为新的信息
     * */
    public void updateRestInfoBy7Chars(Restaurant_info newInfo);

    /**
     * 添加商家记录
     * */
    public void insertInfo(Restaurant_info newRestInfo);

    /**
     * 获得所有餐厅的基本信息
     * */
    public List getAllRestsInfo();
}
