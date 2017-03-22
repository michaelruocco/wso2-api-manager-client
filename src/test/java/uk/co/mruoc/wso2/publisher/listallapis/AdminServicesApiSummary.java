package uk.co.mruoc.wso2.publisher.listallapis;

import uk.co.mruoc.wso2.publisher.ApiStatus;

public class AdminServicesApiSummary extends DefaultApiSummary {

    public AdminServicesApiSummary() {
        setApiName("admin-services");
        setApiVersion("v1");
        setProvider("admin");
        setStatus(ApiStatus.PUBLISHED);
        setThumbnailImageUrl("");
        setSubscriberCount(0);
    }

}
