package j2ee.factory;

import j2ee.dao.*;
import j2ee.dao.impl.*;
import j2ee.model.Restaurant_publish_application_info;

public class DaoFactory {
    public static Cyber_bank_infoDao getCyber_bank_infoDao(){
        return Cyber_bank_infoDaoImpl.getInstance();
    }
    public static Delivery_address_infoDao getDelivery_address_infoDao(){
        return Delivery_address_infoDaoImpl.getInstance();
    }
   
    public static Member_level_preferencialstrategiesDao getMember_level_preferencialstrategiesDao(){
        return Member_level_preferencialstrategiesDaoImpl.getInstance();
    }
    public static Manager_infoDao getManager_infoDao(){
        return Manager_infoDaoImpl.getInstance();
    }
    public static Member_level_money_infoDao getMember_level_money_infoDao(){
        return Member_level_money_infoDaoImpl.getInstance();
    }
    public static Member_infoDao getMember_infoDao(){
        return Member_infoDaoImpl.getInstance();
    }
    public static Member_order_infoDao getMember_order_infoDao(){
        return Member_order_infoDaoImpl.getInstance();
    }
    public static Restaurant_typesDao getRestaurant_typesDao(){
        return Restaurant_typesDaoImpl.getInstance();
    }
    public static Member_order_content_infoDao  getMember_order_content_infoDao(){
        return Member_order_content_infoDaoImpl.getInstance();
    }
    public static Restaurant_infoDao getRestaurant_infoDao(){
        return Restaurant_infoDaoImpl.getInstance();
    }
    public static Restaurant_preferencial_infoDao getRestaurant_preferencial_infoDao(){
        return Restaurant_preferencial_infoDaoImpl.getInstance();
    }
    public static Restaurant_modify_application_infoDao getRestaurant_modify_application_infoDao(){
        return Restaurant_modify_application_infoDaoImpl.getInstance();
    }
    public static Restaurant_register_application_infoDao getRestaurant_register_application_infoDao(){
        return Restaurant_register_application_infoDaoImpl.getInstance();
    }
    public static Member_ps_receive_infoDao getMember_ps_receive_infoDao(){
        return Member_ps_receive_infoDaoImpl.getInstance();
    }
    public static Restaurant_setmeal_infoDao getRestaurant_setmeal_infoDao(){
        return Restaurant_setmeal_infoDaoImpl.getInstance();
    }
    public static Restaurant_singleproduct_infoDao getRestaurant_singleproduct_infoDao(){
        return Restaurant_singleproduct_infoDaoImpl.getInstance();
    }
   
    public static Yummy_settle_accounts_infoDao getYummy_settle_accounts_infoDao(){
        return Yummy_settle_accounts_infoDaoImpl.getInstance();
    }
    public static Restaurant_publish_application_infoDao getRestaurant_publish_application_infoDao(){
        return Restaurant_publish_application_infoDaoImpl.getInstance();
    }
}
