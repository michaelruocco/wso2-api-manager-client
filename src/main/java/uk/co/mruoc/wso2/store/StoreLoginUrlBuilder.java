package uk.co.mruoc.wso2.store;

import uk.co.mruoc.wso2.LoginUrlBuilder;

public class StoreLoginUrlBuilder extends LoginUrlBuilder {

    public StoreLoginUrlBuilder(String hostUrl) {
        super(hostUrl + "/store/site/blocks/user/login/ajax/login.jag");
    }

}
