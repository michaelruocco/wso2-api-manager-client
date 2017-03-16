package uk.co.mruoc.wso2.publisher;

import uk.co.mruoc.wso2.LogoutUrlBuilder;

public class PublisherLogoutUrlBuilder extends LogoutUrlBuilder {

    public PublisherLogoutUrlBuilder(String hostUrl) {
        super(hostUrl + "/publisher/site/blocks/user/login/ajax/login.jag");
    }

}
