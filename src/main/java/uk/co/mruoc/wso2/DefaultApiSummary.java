package uk.co.mruoc.wso2;

public class DefaultApiSummary implements ApiSummary {

    private String name;
    private String version;
    private String provider;
    private ApiStatus status;
    private String thumbnailImageUrl;
    private int subscriberCount;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getVersion() {
        return version;
    }

    @Override
    public String getProvider() {
        return provider;
    }

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

    public void setName(String name) {
        this.name = name;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setProvider(String provider) {
        this.provider = provider;
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
