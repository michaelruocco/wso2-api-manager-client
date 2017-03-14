package uk.co.mruoc.wso2;

public class PublisherLoginUrlBuilder extends LoginUrlBuilder {

    public PublisherLoginUrlBuilder(String hostUrl) {
        super(hostUrl + "/publisher/site/blocks/user/login/ajax/login.jag");
    }

}
