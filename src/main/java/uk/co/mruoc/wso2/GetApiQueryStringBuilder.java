package uk.co.mruoc.wso2;

public class GetApiQueryStringBuilder {

    private static final String QUERY_STRING = "?action=getAPI&name=%s&version=%s&provider=%s";

    private String name;
    private String version;
    private String provider;

    public GetApiQueryStringBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public GetApiQueryStringBuilder setVersion(String version) {
        this.version = version;
        return this;
    }

    public GetApiQueryStringBuilder setProvider(String provider) {
        this.provider = provider;
        return this;
    }

    public String build() {
        return String.format(QUERY_STRING, name, version, provider);
    }

}
