package j2ee.dao;


import j2ee.model.Member_ps_receive_info;

import java.util.List;

public interface Member_ps_receive_infoDao extends BaseDao{

    /**
     * 修改红包使用信息，根据member_id和date（格式：xxxx-xx-xx）
     * */
    public void updateResultByMember_idAndDate(String member_id, String date);

    /**
     * 获得该用户当天优惠券使用情况
     * */
    public Member_ps_receive_info getMemberPsMoney(String id, String date);

    /**
     * 插入一条红包记录---未使用的
     * */
    public void insertInfo(Member_ps_receive_info new_info);
}
