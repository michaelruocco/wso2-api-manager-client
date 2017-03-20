package uk.co.mruoc.wso2.publisher;

import uk.co.mruoc.wso2.publisher.listallapis.DefaultApiSummary;

public class RestProductApiSummary extends DefaultApiSummary {

    public RestProductApiSummary() {
        setName("rest-product");
        setVersion("v1");
        setProvider("admin");
        setStatus(ApiStatus.CREATED);
        setThumbnailImageUrl("");
        setSubscriberCount(0);
    }

}
