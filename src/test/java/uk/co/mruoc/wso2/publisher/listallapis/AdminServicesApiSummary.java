package uk.co.mruoc.wso2.publisher.listallapis;

import uk.co.mruoc.wso2.publisher.ApiStatus;

public class AdminServicesApiSummary extends DefaultApiSummary {

    public AdminServicesApiSummary() {
        setName("admin-services");
        setVersion("v1");
        setProvider("admin");
        setStatus(ApiStatus.PUBLISHED);
        setThumbnailImageUrl("");
        setSubscriberCount(0);
    }

}
