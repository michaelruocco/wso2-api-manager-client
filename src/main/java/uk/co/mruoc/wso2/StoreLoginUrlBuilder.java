package uk.co.mruoc.wso2;

public class StoreLoginUrlBuilder extends LoginUrlBuilder {

    public StoreLoginUrlBuilder(String hostUrl) {
        super(hostUrl + "/store/site/blocks/user/login/ajax/login.jag");
    }

}
