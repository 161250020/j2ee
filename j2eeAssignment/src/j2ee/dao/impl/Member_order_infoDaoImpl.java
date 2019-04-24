package j2ee.dao.impl;


import j2ee.dao.BaseDao;
import j2ee.dao.Member_order_infoDao;
import j2ee.model.Member_order_info;

import java.util.ArrayList;
import java.util.List;

public class Member_order_infoDaoImpl extends BaseDaoImpl implements Member_order_infoDao {

    private static Member_order_infoDaoImpl member_order_infoDao=new Member_order_infoDaoImpl();

    private Member_order_infoDaoImpl() {

    }

    public static Member_order_infoDaoImpl getInstance() {
        return member_order_infoDao;
    }

    @Override
    public List getAllOrders() {

        ArrayList list=new ArrayList();
        list=(ArrayList) super.retByQuery("from Member_order_info");

        return list;
    }

    @Override
    public List getAllOrders_rest() {
        ArrayList list=new ArrayList();
        list=(ArrayList) super.retByQuery("from Member_order_info order by restaurant_id");

        return list;
    }

    @Override
    public List getAllOrders_member() {
        ArrayList list=new ArrayList();
        list=(ArrayList) super.retByQuery("from Member_order_info order by member_id");

        return list;
    }

    @Override
    public List getAllOrdersByMemberId(String member_id) {

        ArrayList<Member_order_info> list=new ArrayList();
        list=(ArrayList<Member_order_info>) super.retByQuery("from Member_order_info");

        ArrayList ret=new ArrayList();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getMember_id().equals(member_id)){
                ret.add(list.get(i));
            }
        }

        return ret;
    }

    @Override
    public List getAllOrdersByRestId(String rest_id) {
        ArrayList<Member_order_info> list=new ArrayList();
        list=(ArrayList<Member_order_info>) super.retByQuery("from Member_order_info");

        ArrayList ret=new ArrayList();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getRestaurant_id().equals(rest_id)){
                ret.add(list.get(i));
            }
        }

        return ret;
    }

    @Override
    public void updateInfo(Member_order_info new_info) {
        super.update(new_info);
    }

    @Override
    public Member_order_info getInfoById(String order_id) {
        Member_order_info ret=new Member_order_info();
        ret= (Member_order_info) super.load(Member_order_info.class, order_id);

        return ret;
    }

    @Override
    public void insertInfo(Member_order_info new_info) {
        super.save(new_info);
    }
}
