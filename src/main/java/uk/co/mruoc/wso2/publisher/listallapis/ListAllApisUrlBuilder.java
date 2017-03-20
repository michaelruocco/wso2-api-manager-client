package uk.co.mruoc.wso2.publisher.listallapis;

public class ListAllApisUrlBuilder {

    private final String url;

    public ListAllApisUrlBuilder(String hostUrl) {
        url = hostUrl + "/publisher/site/blocks/listing/ajax/item-list.jag?action=getAllAPIs";
    }

    public String build() {
        return url;
    }

}
