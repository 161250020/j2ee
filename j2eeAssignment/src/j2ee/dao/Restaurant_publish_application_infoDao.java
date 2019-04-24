package j2ee.dao;

import java.util.List;

import j2ee.model.Restaurant_publish_application_info;


public interface Restaurant_publish_application_infoDao extends BaseDao {
	 /**
     * 根据id，获得要添加的餐厅的商品的审批的内容
     * */
    public Restaurant_publish_application_info getNewInfoById(String id);

    /**
     * 根据，审批的ID，修改审批信息（审批结果，审批经理等等）
     * */
    public void changeInfo(Restaurant_publish_application_info new_info);

    /**
     * 获得所有商家修改的申请的信息
     * */
    public List getAllPublishApplications();

    /**
     * 添加一条修改餐厅信息的审批申请
     * */
    public void insertInfo(Restaurant_publish_application_info new_info);
}
