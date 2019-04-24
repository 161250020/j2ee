package j2ee.dao;


import j2ee.model.Yummy_settle_accounts_info;

import java.util.List;

public interface Yummy_settle_accounts_infoDao extends BaseDao{

    /**
     * 获得所有结算的信息
     * */
    public List getAllInfo();

    /**
     * 添加一条结算记录
     * */
    public void insertInfo(Yummy_settle_accounts_info new_info);
}
