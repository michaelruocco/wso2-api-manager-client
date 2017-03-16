package uk.co.mruoc.wso2.publisher;

import uk.co.mruoc.wso2.LoginUrlBuilder;

public class PublisherLoginUrlBuilder extends LoginUrlBuilder {

    public PublisherLoginUrlBuilder(String hostUrl) {
        super(hostUrl + "/publisher/site/blocks/user/login/ajax/login.jag");
    }

}
