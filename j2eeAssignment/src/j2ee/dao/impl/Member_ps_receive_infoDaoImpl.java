package j2ee.dao.impl;


import j2ee.dao.BaseDao;
import j2ee.dao.Member_ps_receive_infoDao;
import j2ee.model.Member_ps_receive_info;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Member_ps_receive_infoDaoImpl extends BaseDaoImpl implements Member_ps_receive_infoDao {

    private static Member_ps_receive_infoDaoImpl member_ps_receive_infoDao=new Member_ps_receive_infoDaoImpl();

    private Member_ps_receive_infoDaoImpl() {

    }

    public static Member_ps_receive_infoDaoImpl getInstance() {
        return member_ps_receive_infoDao;
    }

    @Override
    public void updateResultByMember_idAndDate(String member_id, String date) {
        ArrayList<Member_ps_receive_info> list=new ArrayList();
        list=(ArrayList<Member_ps_receive_info>) super.retByQuery("from Member_ps_receive_info");

        for(int i=0;i<list.size();i++){
            if(list.get(i).getMember_id().equals(member_id)){
                Date tempDate=list.get(i).getDate();
                String strDate=tempDate.toString();
                if(strDate.equals(date)){
                    Member_ps_receive_info new_info=list.get(i);
                    new_info.setResult(1);
                    super.update(new_info);
                }
            }
        }
    }

    @Override
    public Member_ps_receive_info getMemberPsMoney(String id, String date) {
        ArrayList<Member_ps_receive_info> list=new ArrayList();
        list=(ArrayList<Member_ps_receive_info>) super.retByQuery("from Member_ps_receive_info");

        Member_ps_receive_info info=new Member_ps_receive_info();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getMember_id().equals(id)){
                Date tempDate=list.get(i).getDate();
                String strDate=tempDate.toString();
                if(strDate.equals(date)){
                    info=list.get(i);
                }
            }
        }
        return info;
    }

    @Override
    public void insertInfo(Member_ps_receive_info new_info) {
        super.save(new_info);
    }
}
