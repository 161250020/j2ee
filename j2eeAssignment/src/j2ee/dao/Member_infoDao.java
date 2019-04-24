package j2ee.dao;

import j2ee.model.Member_info;

import java.util.List;

public interface Member_infoDao extends BaseDao{

    /**
     * 获得所有会员信息
     * */
    public List getAllMembersInfo();

    /**
     * 根据会员username，获得会员信息
     * */
    public Member_info getInfoByUsername(String username);

    /**
     * 根据会员id，获得会员信息
     * */
    public Member_info getInfoById(String id);

    /**
     * 修改会员基本信息/密码
     * */
    public void updateMemberInfo(Member_info newMember);

    /**
     * 会员注册，添加会员的记录
     * */
    public void insertMemberInfo(Member_info new_member);
}
