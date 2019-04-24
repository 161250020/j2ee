package j2ee.dao;

import j2ee.model.Delivery_address_info;

import java.util.List;

public interface Delivery_address_infoDao extends BaseDao{

    /**
     * 修改会员地址，根据会员ID
     * */
    public void updateAddress(Delivery_address_info new_info);

    /**
     * 根据会员ID，获得该会员的送货地址信息
     * */
    public List getAllAddressByID(String id);

    /**
     * 获得所有送货地址
     * */
    public List getAllAdd();

    /**
     * 添加新地址
     * */
    public void addInfo(Delivery_address_info new_info);
}
