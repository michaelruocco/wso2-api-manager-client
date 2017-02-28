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

    @Override
    public String getVersion() {
        return version;
    }

    @Override
    public String getProvider() {
        return provider;
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

}
