package j2ee.dao;


import j2ee.model.Member_order_info;

import java.util.List;

public interface Member_order_infoDao extends BaseDao{

    /**
     * 获得所有订单信息
     * */
    public List getAllOrders();
    /**
     * 获得所有订单信息---按餐厅排序
     * */
    public List getAllOrders_rest();
    /**
     * 获得所有订单信息---按会员排序
     * */
    public List getAllOrders_member();

    /**
     * 根据会员的ID，获得会员的所有订单信息
     * */
    public List getAllOrdersByMemberId(String member_id);

    /**
     * 根据餐厅的ID，获得餐厅的所有订单信息
     * */
    public List getAllOrdersByRestId(String rest_id);

    /**
     * 根据订单ID，修改订单信息
     * */
    public void updateInfo(Member_order_info new_info);

    /**
     * 根据订单ID，获得订单信息
     * */
    public Member_order_info getInfoById(String order_id);

    /**
     * 新增订单信息
     * */
    public void insertInfo(Member_order_info new_info);

}
