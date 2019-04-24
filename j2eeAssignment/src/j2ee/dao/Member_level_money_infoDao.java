package j2ee.dao;

import j2ee.model.Member_level_money_info;

public interface Member_level_money_infoDao extends BaseDao{

    /**
     * 会员下单成功之后，修改会员消费的金额，将本次消费的金额（money）添加上去
     * */
    public void updateSum_money(String member_id, double changeToMoney);

    /**
     * 会员下单成功之后，修改该会员对应等级
     * */
    public void updateLevel(String member_id, int level);

    /**
     * 获得会员（根据会员ID）的信息
     * */
    public Member_level_money_info getInfoById(String member_id);

    /**
     * 插入一条消息
     * */
    public void insert_info(Member_level_money_info new_info);

}
