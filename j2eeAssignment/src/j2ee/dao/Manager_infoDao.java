package j2ee.dao;

import java.util.List;

public interface Manager_infoDao extends BaseDao{

    /**
     *根据经理的username获得经理的密码
     * */
    public String getPasswordByUsername(String username);

    /**
     * 获得所有经理的信息
     * */
    public List getAllManagers();

}
