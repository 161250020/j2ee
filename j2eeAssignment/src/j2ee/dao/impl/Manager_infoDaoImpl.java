package j2ee.dao.impl;

import j2ee.dao.BaseDao;
import j2ee.dao.Manager_infoDao;
import j2ee.model.Manager_info;

import java.util.ArrayList;
import java.util.List;

public class Manager_infoDaoImpl extends BaseDaoImpl implements Manager_infoDao{

    private static Manager_infoDaoImpl manager_infoDao=new Manager_infoDaoImpl();

    private Manager_infoDaoImpl() {

    }

    public static Manager_infoDaoImpl getInstance() {
        return manager_infoDao;
    }

    @Override
    public String getPasswordByUsername(String username) {

        ArrayList<Manager_info> list=new ArrayList();
        list=(ArrayList<Manager_info>) super.retByQuery("from Manager_info");

        String ret="";
        for(int i=0;i<list.size();i++){
            if(list.get(i).getUsername().equals(username)){
                ret=list.get(i).getPassword();
                break;
            }
        }

        return ret;
    }

    @Override
    public List getAllManagers() {

        ArrayList list=new ArrayList();
        list=(ArrayList) super.retByQuery("from Manager_info");

        return list;
    }
}
