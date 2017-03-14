package uk.co.mruoc.wso2;

public class StoreLogoutUrlBuilder extends LogoutUrlBuilder {

    public StoreLogoutUrlBuilder(String host) {
        super(host + "/store/site/blocks/user/login/ajax/login.jag");
    }
    
}
