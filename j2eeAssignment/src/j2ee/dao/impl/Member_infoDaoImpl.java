package j2ee.dao.impl;

import j2ee.dao.BaseDao;
import j2ee.dao.Member_infoDao;
import j2ee.model.Member_info;

import java.util.ArrayList;
import java.util.List;

public class Member_infoDaoImpl extends BaseDaoImpl implements Member_infoDao {

    private static Member_infoDaoImpl member_infoDao=new Member_infoDaoImpl();

    private Member_infoDaoImpl() {

    }

    public static Member_infoDaoImpl getInstance() {
        return member_infoDao;
    }

    @Override
    public List getAllMembersInfo() {

        ArrayList list=new ArrayList();
        list=(ArrayList) super.retByQuery("from Member_info");

        return list;
    }

    @Override
    public Member_info getInfoByUsername(String username) {

        ArrayList<Member_info> list=new ArrayList();
        list=(ArrayList<Member_info>) super.retByQuery("from Member_info");

        Member_info ret=new Member_info();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getUsername().equals(username)){
                ret=list.get(i);
                break;
            }
        }

        return ret;
    }

    @Override
    public Member_info getInfoById(String id) {
        ArrayList<Member_info> list=new ArrayList();
        list=(ArrayList<Member_info>) super.retByQuery("from Member_info");

        Member_info ret=new Member_info();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getId().equals(id)){
                ret=list.get(i);
                break;
            }
        }

        return ret;
    }

    @Override
    public void updateMemberInfo(Member_info newMember) {
        super.update(newMember);
    }

    @Override
    public void insertMemberInfo(Member_info new_member) {
        super.save(new_member);
    }
}
