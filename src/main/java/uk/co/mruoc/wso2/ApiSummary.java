package uk.co.mruoc.wso2;

public class ApiSummary implements SelectApiParams {

    private final String name;
    private final String version;
    private final String provider;
    private final ApiStatus status;
    private final String thumbnailImageUrl;
    private final int subscriberCount;

    public ApiSummary(ApiSummaryBuilder builder) {
        this.name = builder.name;
        this.version = builder.version;
        this.provider = builder.provider;
        this.status = builder.status;
        this.thumbnailImageUrl = builder.thumbnailImageUrl;
        this.subscriberCount = builder.subscriberCount;
    }

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

    public ApiStatus getStatus() {
        return status;
    }

    public String getThumbnailImageUrl() {
        return thumbnailImageUrl;
    }

    public int getSubscriberCount() {
        return subscriberCount;
    }

    public static class ApiSummaryBuilder {

        private String name;
        private String version;
        private String provider;
        private ApiStatus status;
        private String thumbnailImageUrl;
        private int subscriberCount;

        public ApiSummaryBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ApiSummaryBuilder setVersion(String version) {
            this.version = version;
            return this;
        }

        public ApiSummaryBuilder setProvider(String provider) {
            this.provider = provider;
            return this;
        }

        public ApiSummaryBuilder setStatus(ApiStatus status) {
            this.status = status;
            return this;
        }

        public ApiSummaryBuilder setThumbnailImageUrl(String thumbnailImageUrl) {
            this.thumbnailImageUrl = thumbnailImageUrl;
            return this;
        }

        public ApiSummaryBuilder setSubscriberCount(int subscriberCount) {
            this.subscriberCount = subscriberCount;
            return this;
        }

        public ApiSummary build() {
            return new ApiSummary(this);
        }

    }

}
