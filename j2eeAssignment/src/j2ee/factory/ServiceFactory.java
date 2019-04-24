package j2ee.factory;

import j2ee.model.Restaurant_publish_application_info;
import j2ee.service.*;
import j2ee.service.bean.*;
import org.hibernate.loader.custom.Return;

public class ServiceFactory {
    public static Member_level_preferencialstrategiesManageService getMember_level_preferencialstrategiesManageService(){
        return Member_level_preferencialstrategiesManageServiceImpl.getInstance();
    }
    public static Cyber_bank_infoManageService getCyber_bank_infoManageService(){
        return Cyber_bank_infoManageServiceImpl.getInstance();
    }
    public static Delivery_address_infoManageService getDelivery_address_infoManageService(){
        return Delivery_address_infoManageServiceImpl.getInstance();
    }
   
    public static Manager_infoManageService getManager_infoManageService(){
        return Manager_infoManageServiceImpl.getInstance();
    }
    public static Member_infoManageService getMember_infoManageService(){
        return Member_infoManageServiceImpl.getInstance();
    }
    public static Member_level_money_infoManageService getMember_level_money_infoManageService(){
        return Member_level_money_infoManageServiceImpl.getInstance();
    }
    public static Restaurant_infoManageService getRestaurant_infoManageService(){
        return Restaurant_infoManageServiceImpl.getInstance();
    }
    public static Member_order_content_infoManageService getMember_order_content_infoManageService(){
        return Member_order_content_infoManageServiceImpl.getInstance();
    }
    public static Member_order_infoManageService getMember_order_infoManageService(){
        return Member_order_infoManageServiceImpl.getInstance();
    }
    public static Member_ps_receive_infoManageService getMember_ps_receive_infoManageService(){
        return Member_ps_receive_infoManageServiceImpl.getInstance();
    }
    
    public static Restaurant_modify_application_infoManageService getRestaurant_modify_application_infoManageService(){
        return Restaurant_modify_application_infoManageServiceImpl.getInstance();
    }
    public static Restaurant_preferencial_infoManageService getRestaurant_preferencial_infoManageService(){
        return Restaurant_preferencial_infoManageServiceImpl.getInstance();
    }
    
    public static Restaurant_setmeal_infoManageService getRestaurant_setmeal_infoManageService(){
        return Restaurant_setmeal_infoManageServiceImpl.getInstance();
    }
    public static Restaurant_register_application_infoManageService getRestaurant_register_application_infoManageService(){
        return Restaurant_register_application_infoManageServiceImpl.getInstance();
    }
    public static Restaurant_singleproduct_infoManageService getRestaurant_singleproduct_infoManageService(){
        return Restaurant_singleproduct_infoManageServiceImpl.getInstance();
    }
    public static Restaurant_typesManageService getRestaurant_typesManageService(){
        return Restaurant_typesManageServiceImpl.getInstance();
    }
    public static Yummy_settle_accounts_infoManageService getYummy_settle_accounts_infoManageService(){
        return Yummy_settle_accounts_infoManageServiceImpl.getInstance();
    }
    public static Restaurant_publish_application_infoManageService getRestaurant_publish_application_infoManageService(){
        return Restaurant_publish_application_infoManageServiceImpl.getInstance();
    }
}
