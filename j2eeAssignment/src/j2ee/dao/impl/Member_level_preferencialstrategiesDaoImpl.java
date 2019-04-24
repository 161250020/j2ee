package j2ee.dao.impl;


import j2ee.dao.BaseDao;
import j2ee.dao.Member_level_preferencialstrategiesDao;

import java.util.ArrayList;
import java.util.List;

public class Member_level_preferencialstrategiesDaoImpl extends BaseDaoImpl implements Member_level_preferencialstrategiesDao {


    private static Member_level_preferencialstrategiesDaoImpl member_level_preferencialstrategiesDao=new Member_level_preferencialstrategiesDaoImpl();

    private Member_level_preferencialstrategiesDaoImpl() {

    }

    public static Member_level_preferencialstrategiesDaoImpl getInstance() {
        return member_level_preferencialstrategiesDao;
    }


    @Override
    public List getAllPss() {

        ArrayList list=new ArrayList();
        list=(ArrayList) super.retByQuery("from Member_level_preferencialstrategies");

        return list;
    }
}
