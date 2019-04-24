package j2ee.dao.impl;


import j2ee.dao.BaseDao;
import j2ee.dao.Restaurant_typesDao;

import java.util.ArrayList;
import java.util.List;

public class Restaurant_typesDaoImpl extends BaseDaoImpl implements Restaurant_typesDao {

    private static Restaurant_typesDaoImpl restaurant_typesDao=new Restaurant_typesDaoImpl();

    private Restaurant_typesDaoImpl() {

    }

    public static Restaurant_typesDaoImpl getInstance() {
        return restaurant_typesDao;
    }

    @Override
    public List getAllTypesInfo() {

        ArrayList list=new ArrayList();
        list=(ArrayList) super.retByQuery("from Restaurant_types");

        return list;
    }
}
