package uk.co.mruoc.wso2.store;

import java.util.Properties;

public class PropertiesKeyGeneratorParams extends DefaultKeyGeneratorParams {

    public PropertiesKeyGeneratorParams(Properties properties) {
        setApiName(properties.getProperty("api.name"));
        setApiVersion(properties.getProperty("api.version"));
        setProvider(properties.getProperty("api.provider"));
        setApplicationName(properties.getProperty("api.application.name"));
    }
    
}
