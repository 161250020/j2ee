package j2ee.dao;


import java.util.List;

public interface Restaurant_typesDao extends BaseDao{

    /**
     * 获得所有餐厅类型的信息
     * */
    public List getAllTypesInfo();
}
