package j2ee.dao;


import j2ee.model.Restaurant_register_application_info;

import java.util.List;

public interface Restaurant_register_application_infoDao extends BaseDao{

    /**
     * 获得商家注册的审批的信息（根据ID）
     * */
    public Restaurant_register_application_info getRestRegisterInfo(String id);

    /**
     * 修改商家注册申请（根据ID）的result
     * */
    public void updateRegisterResult(String id, int result);

    /**
     * 修改商家注册申请（根据ID）的manager_id
     * */
    public void updateRegisterManager_id(String id, String manager_id);

    /**
     * 获得所有商家注册的申请的信息
     * */
    public List getAllRegisterApplications();

    /**
     * 餐厅注册后：添加餐厅注册的申请
     * */
    public void insertARegisterApplication(Restaurant_register_application_info new_info);
}
