package uk.co.mruoc.wso2.store;

import uk.co.mruoc.wso2.LogoutUrlBuilder;

public class StoreLogoutUrlBuilder extends LogoutUrlBuilder {

    public StoreLogoutUrlBuilder(String host) {
        super(host + "/store/site/blocks/user/login/ajax/login.jag");
    }

}
