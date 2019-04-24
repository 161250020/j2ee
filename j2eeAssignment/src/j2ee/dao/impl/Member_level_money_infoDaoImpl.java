package j2ee.dao.impl;

import j2ee.dao.BaseDao;
import j2ee.dao.Member_level_money_infoDao;
import j2ee.model.Member_level_money_info;

import java.util.ArrayList;

public class Member_level_money_infoDaoImpl extends BaseDaoImpl implements Member_level_money_infoDao {

    private static Member_level_money_infoDaoImpl member_level_money_infoDao=new Member_level_money_infoDaoImpl();

    private Member_level_money_infoDaoImpl() {

    }

    public static Member_level_money_infoDaoImpl getInstance() {
        return member_level_money_infoDao;
    }

    @Override
    public void updateSum_money(String member_id, double changeToMoney) {

        ArrayList<Member_level_money_info> list=new ArrayList();
        list=(ArrayList<Member_level_money_info>) super.retByQuery("from Member_level_money_info");

        for(int i=0;i<list.size();i++){
            if(list.get(i).getMember_id().equals(member_id)){
                Member_level_money_info ret=list.get(i);
                ret.setSum_money(changeToMoney);
                super.update(ret);
            }
        }
    }

    @Override
    public void updateLevel(String member_id, int level) {

        ArrayList<Member_level_money_info> list=new ArrayList();
        list=(ArrayList<Member_level_money_info>) super.retByQuery("from Member_level_money_info");

        for(int i=0;i<list.size();i++){
            if(list.get(i).getMember_id().equals(member_id)){
                Member_level_money_info ret=list.get(i);
                ret.setLevel(level);
                super.update(ret);
            }
        }
    }

    @Override
    public Member_level_money_info getInfoById(String member_id) {
        ArrayList<Member_level_money_info> list=new ArrayList();
        list=(ArrayList<Member_level_money_info>) super.retByQuery("from Member_level_money_info");

        Member_level_money_info ret=new Member_level_money_info();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getMember_id().equals(member_id)){
                ret=list.get(i);
                break;
            }
        }

        return ret;
    }

    @Override
    public void insert_info(Member_level_money_info new_info) {
        super.save(new_info);
    }
}
