package uk.co.mruoc.wso2;

public class DefaultGetApiParams implements GetApiParams {

    private final String name;
    private final String version;
    private final String provider;

    private DefaultGetApiParams(DefaultGetApiParamsBuilder builder) {
        this.name = builder.name;
        this.version = builder.version;
        this.provider = builder.provider;
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

    public static class DefaultGetApiParamsBuilder {

        private String name;
        private String version;
        private String provider;

        public DefaultGetApiParamsBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public DefaultGetApiParamsBuilder setVersion(String version) {
            this.version = version;
            return this;
        }

        public DefaultGetApiParamsBuilder setProvider(String provider) {
            this.provider = provider;
            return this;
        }

        public GetApiParams build() {
            return new DefaultGetApiParams(this);
        }

    }

}
