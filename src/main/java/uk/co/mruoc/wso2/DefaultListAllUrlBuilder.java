package uk.co.mruoc.wso2;

public class DefaultListAllUrlBuilder implements ListAllUrlBuilder {

    private final String url;

    public DefaultListAllUrlBuilder(String hostUrl) {
        url = hostUrl + "/publisher/site/blocks/listing/ajax/item-list.jag?action=getAllAPIs";
    }

    @Override
    public String build() {
        return url;
    }

}
