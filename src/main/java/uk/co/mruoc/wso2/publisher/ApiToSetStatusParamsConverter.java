package uk.co.mruoc.wso2.publisher;

import uk.co.mruoc.wso2.publisher.getapi.Api;

public class ApiToSetStatusParamsConverter {

    public DefaultSetStatusParams convert(Api api) {
        DefaultSetStatusParams params = new DefaultSetStatusParams();
        params.setApiName(api.getName());
        params.setApiVersion(api.getVersion());
        params.setProvider(api.getProvider());
        return params;
    }

}
