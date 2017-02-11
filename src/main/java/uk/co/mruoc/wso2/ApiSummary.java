package uk.co.mruoc.wso2;

public class ApiSummary implements GetApiParams {

    private final String name;
    private final String version;
    private final String provider;
    private final String status;
    private final String thumbnailImagePath;
    private final int subscriberCount;

    public ApiSummary(ApiSummaryBuilder builder) {
        this.name = builder.name;
        this.version = builder.version;
        this.provider = builder.provider;
        this.status = builder.status;
        this.thumbnailImagePath = builder.thumbnailImagePath;
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

    public String getStatus() {
        return status;
    }

    public String getThumbnailImagePath() {
        return thumbnailImagePath;
    }

    public int getSubscriberCount() {
        return subscriberCount;
    }

    public static class ApiSummaryBuilder {

        private String name;
        private String version;
        private String provider;
        private String status;
        private String thumbnailImagePath;
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

        public ApiSummaryBuilder setStatus(String status) {
            this.status = status;
            return this;
        }

        public ApiSummaryBuilder setThumbnailImagePath(String thumbnailImagePath) {
            this.thumbnailImagePath = thumbnailImagePath;
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
