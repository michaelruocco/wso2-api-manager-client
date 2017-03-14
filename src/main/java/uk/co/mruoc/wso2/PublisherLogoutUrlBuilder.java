package uk.co.mruoc.wso2;

public class PublisherLogoutUrlBuilder extends LogoutUrlBuilder {

    public PublisherLogoutUrlBuilder(String hostUrl) {
        super(hostUrl + "/publisher/site/blocks/user/login/ajax/login.jag");
    }

}
