package uk.co.mruoc.wso2;

import org.joda.time.DateTime;

public class Api {

    private final String name;
    private final String version;
    private final String description;
    private final String context;
    private final DateTime lastUpdated;
    private final int subscriberCount;

    public Api(ApiBuilder builder) {
        this.name = builder.name;
        this.version = builder.version;
        this.description = builder.description;
        this.context = builder.context;
        this.lastUpdated = builder.lastUpdated;
        this.subscriberCount = builder.subscriberCount;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getDescription() {
        return description;
    }

    public String getContext() {
        return context;
    }

    public DateTime getLastUpdated() {
        return lastUpdated;
    }

    public int getSubscriberCount() {
        return subscriberCount;
    }

    public static class ApiBuilder {

        private String name;
        private String version;
        private String description;
        private String context;
        private DateTime lastUpdated;
        private int subscriberCount;

        public ApiBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ApiBuilder setVersion(String version) {
            this.version = version;
            return this;
        }

        public ApiBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public ApiBuilder setContext(String context) {
            this.context = context;
            return this;
        }

        public ApiBuilder setLastUpdated(DateTime lastUpdated) {
            this.lastUpdated = lastUpdated;
            return this;
        }

        public ApiBuilder setSubscriberCount(int subscriberCount) {
            this.subscriberCount = subscriberCount;
            return this;
        }

        public Api build() {
            return new Api(this);
        }

    }

}
