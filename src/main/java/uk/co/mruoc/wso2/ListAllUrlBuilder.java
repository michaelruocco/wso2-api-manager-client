package uk.co.mruoc.wso2;

public class ListAllUrlBuilder {

    private final String url;

    public ListAllUrlBuilder(String hostUrl) {
        url = hostUrl + "/publisher/site/blocks/listing/ajax/item-list.jag?action=getAllAPIs";
    }

    public String build() {
        return url;
    }

}
