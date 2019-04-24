package j2ee.dao.impl;

import j2ee.dao.BaseDao;
import j2ee.dao.Member_order_content_infoDao;
import j2ee.model.Member_order_content_info;

import java.util.ArrayList;
import java.util.List;

public class Member_order_content_infoDaoImpl extends BaseDaoImpl implements Member_order_content_infoDao {

    private static Member_order_content_infoDaoImpl member_order_content_infoDao=new Member_order_content_infoDaoImpl();

    private Member_order_content_infoDaoImpl() {

    }

    public static Member_order_content_infoDaoImpl getInstance() {
        return member_order_content_infoDao;
    }

    @Override
    public void insertInfo(Member_order_content_info new_info) {
        super.save(new_info);
    }

    @Override
    public List receiveInfoByOrder_list_id(String order_list_id) {
        ArrayList<Member_order_content_info> list=new ArrayList();
        list=(ArrayList<Member_order_content_info>) super.retByQuery("from Member_order_content_info");

        ArrayList ret=new ArrayList();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getOrder_list_id().equals(order_list_id)){
                ret.add(list.get(i));
            }
        }

        return ret;
    }
}
