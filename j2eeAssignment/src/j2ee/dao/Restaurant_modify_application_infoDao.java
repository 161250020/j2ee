package j2ee.dao;

import j2ee.model.Restaurant_modify_application_info;

import java.util.List;

public interface Restaurant_modify_application_infoDao extends BaseDao{

    /**
     * 根据id，获得要修改成的餐厅的内容
     * */
    public Restaurant_modify_application_info getNewInfoById(String id);

    /**
     * 根据，审批的ID，修改审批信息（审批结果）
     * */
    public void changeModifyResult(Restaurant_modify_application_info new_info);

    /**
     * 获得所有商家修改的申请的信息
     * */
    public List getAllModifyApplications();

    /**
     * 添加一条修改餐厅信息的审批申请
     * */
    public void insertInfo(Restaurant_modify_application_info new_info);
}
