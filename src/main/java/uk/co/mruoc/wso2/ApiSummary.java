package uk.co.mruoc.wso2;

public interface ApiSummary extends SelectApiParams {

    ApiStatus getStatus();

    String getThumbnailImageUrl();

    int getSubscriberCount();

}
