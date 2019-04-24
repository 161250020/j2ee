package j2ee.dao.impl;


import j2ee.dao.BaseDao;
import j2ee.dao.Yummy_settle_accounts_infoDao;
import j2ee.model.Yummy_settle_accounts_info;

import java.util.ArrayList;
import java.util.List;

public class Yummy_settle_accounts_infoDaoImpl extends BaseDaoImpl implements Yummy_settle_accounts_infoDao {

    private static Yummy_settle_accounts_infoDaoImpl yummy_settle_accounts_infoDao=new Yummy_settle_accounts_infoDaoImpl();

    private Yummy_settle_accounts_infoDaoImpl() {

    }

    public static Yummy_settle_accounts_infoDaoImpl getInstance() {
        return yummy_settle_accounts_infoDao;
    }

    @Override
    public List getAllInfo() {

        ArrayList list=new ArrayList();
        list=(ArrayList) super.retByQuery("from Yummy_settle_accounts_info");

        return list;
    }

    @Override
    public void insertInfo(Yummy_settle_accounts_info new_info) {
        super.save(new_info);
    }
}
