package uk.co.mruoc.wso2;

import java.util.Properties;

public class DefaultApiManagerProperties implements ApiManagerProperties {

    private final Properties properties;

    public DefaultApiManagerProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public String getApimUrl() {
        return properties.getProperty("apim.url");
    }

    @Override
    public String getApimPublisherUrl() {
        String value = properties.getProperty("apim.publisher.url");
        if (value == null)
            return getApimUrl();
        return value;
    }

    @Override
    public String getApimUsername() {
        return properties.getProperty("apim.username");
    }

    @Override
    public String getApimPassword() {
        return properties.getProperty("apim.password");
    }

    @Override
    public Credentials getApimCredentials() {
        return new Credentials(getApimUsername(), getApimPassword());
    }

}
