package uk.co.mruoc.wso2;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class DefaultSelectApiParams implements SelectApiParams {

    private String name = EMPTY;
    private String version = EMPTY;
    private String provider = EMPTY;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

}
