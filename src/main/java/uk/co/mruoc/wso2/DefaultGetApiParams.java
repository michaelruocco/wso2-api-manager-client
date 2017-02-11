package uk.co.mruoc.wso2;

public class DefaultGetApiParams implements GetApiParams {

    private final String name;
    private final String version;
    private final String provider;

    public DefaultGetApiParams(String name, String version, String provider) {
        this.name = name;
        this.version = version;
        this.provider = provider;
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

}
