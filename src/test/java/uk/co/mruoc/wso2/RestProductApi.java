package uk.co.mruoc.wso2;

import org.joda.time.DateTime;

public class RestProductApi extends Api {

    public RestProductApi() {
        super(new ApiBuilder()
                .setName("rest-product")
                .setVersion("v1")
                .setDescription("Product REST API")
                .setContext("/products/v1")
                .setLastUpdated(new DateTime(1486464366000l))
                .setSubscriberCount(1));
    }

}
