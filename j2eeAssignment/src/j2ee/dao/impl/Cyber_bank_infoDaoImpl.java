package j2ee.dao.impl;

import j2ee.dao.Cyber_bank_infoDao;
import j2ee.model.Cyber_bank_info;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class Cyber_bank_infoDaoImpl extends BaseDaoImpl implements Cyber_bank_infoDao
{

    private static Cyber_bank_infoDaoImpl cyber_bank_infoDao=new Cyber_bank_infoDaoImpl();

    private Cyber_bank_infoDaoImpl() {

    }

    public static Cyber_bank_infoDaoImpl getInstance() {
        return cyber_bank_infoDao;
    }

    @Override
    public void updateMoney(String id, double changeToMoney) {
        Session session = HibernateUtil.getSession() ;
        Transaction tx=session.beginTransaction();
        try {
            session.createQuery("update Cyber_bank_info c set c.money=? where id=?")
                    .setParameter(0, changeToMoney).setParameter(1, id).executeUpdate();
            tx.commit();
        }catch (Exception e){
            if(tx!=null){
                tx.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    @Override
    public Cyber_bank_info getInfoById(String id) {
        Cyber_bank_info ret=new Cyber_bank_info();

        ArrayList<Cyber_bank_info> list=new ArrayList();
        list=(ArrayList<Cyber_bank_info>) super.retByQuery("from Cyber_bank_info");

        for(int i=0;i<list.size();i++){
            if(list.get(i).getId().equals(id)){
                ret=list.get(i);
                break;
            }
        }

        return ret;
    }

    @Override
    public List getAllInfo() {

        ArrayList list=new ArrayList();
        list=(ArrayList) super.retByQuery("from Cyber_bank_info");

        return list;
    }
}
