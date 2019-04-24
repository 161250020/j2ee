package j2ee.dao;

import j2ee.model.Cyber_bank_info;

import java.util.List;

public interface Cyber_bank_infoDao extends BaseDao
{
    /**
     *根据ID，修改网银钱数
     * */
    public void updateMoney(String id, double changeToMoney);

    /**
     * 根据网银ID，获得网银内容
     * */
    public Cyber_bank_info getInfoById(String id);

    /**
     * 获得所有网银信息
     * */
    public List getAllInfo();

}
