package uk.co.mruoc.wso2.publisher;

import uk.co.mruoc.wso2.DefaultSelectApiParams;

public class DefaultApiSummary extends DefaultSelectApiParams implements ApiSummary {

    private ApiStatus status;
    private String thumbnailImageUrl;
    private int subscriberCount;

    @Override
    public ApiStatus getStatus() {
        return status;
    }

    @Override
    public String getThumbnailImageUrl() {
        return thumbnailImageUrl;
    }

    @Override
    public int getSubscriberCount() {
        return subscriberCount;
    }

    public void setStatus(ApiStatus status) {
        this.status = status;
    }

    public void setThumbnailImageUrl(String thumbnailImageUrl) {
        this.thumbnailImageUrl = thumbnailImageUrl;
    }

    public void setSubscriberCount(int subscriberCount) {
        this.subscriberCount = subscriberCount;
    }

}
