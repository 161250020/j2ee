package j2ee.dao.impl;

import j2ee.dao.BaseDao;
import j2ee.dao.Delivery_address_infoDao;
import j2ee.model.Delivery_address_info;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class Delivery_address_infoDaoImpl extends BaseDaoImpl implements Delivery_address_infoDao {

    private static Delivery_address_infoDaoImpl delivery_address_infoDao=new Delivery_address_infoDaoImpl();

    private Delivery_address_infoDaoImpl() {

    }

    public static Delivery_address_infoDaoImpl getInstance() {
        return delivery_address_infoDao;
    }

    @Override
    public void updateAddress(Delivery_address_info new_info) {
        super.update(new_info);
    }

    @Override
    public List getAllAddressByID(String id) {

        ArrayList<Delivery_address_info> list=new ArrayList();
        list=(ArrayList<Delivery_address_info>) super.retByQuery("from Delivery_address_info");

        ArrayList ret=new ArrayList();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getMember_id().equals(id)){
                ret.add(list.get(i));
            }
        }

        return ret;
    }

    @Override
    public List getAllAdd() {
        ArrayList list=list= (ArrayList) super.retByQuery("from Delivery_address_info");

        return list;
    }

    @Override
    public void addInfo(Delivery_address_info new_info) {
        super.save(new_info);
    }
}
