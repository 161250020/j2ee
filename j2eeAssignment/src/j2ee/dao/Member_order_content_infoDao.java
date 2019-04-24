package j2ee.dao;

import j2ee.model.Member_order_content_info;

import java.util.List;

public interface Member_order_content_infoDao extends BaseDao{

    /**
     * 新订单创建之后：新增订单记录的餐厅商品的内容
     * */
    public void insertInfo(Member_order_content_info new_info);

    /**
     * 根据订单的order_list_id，获得订单内容的详细商品信息
     * */
    public List receiveInfoByOrder_list_id(String order_list_id);
}
