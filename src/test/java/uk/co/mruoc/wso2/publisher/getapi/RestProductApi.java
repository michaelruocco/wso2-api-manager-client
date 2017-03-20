package uk.co.mruoc.wso2.publisher.getapi;

import org.joda.time.DateTime;
import uk.co.mruoc.wso2.ApiTierAvailability;
import uk.co.mruoc.wso2.publisher.ApiEndpointType;
import uk.co.mruoc.wso2.publisher.ApiStatus;
import uk.co.mruoc.wso2.publisher.ApiVisibility;
import uk.co.mruoc.wso2.publisher.getapi.DefaultApi;

public class RestProductApi extends DefaultApi {

    public RestProductApi() {
        setName("rest-product");
        setVersion("v1");
        setDescription("Product REST API");
        setContext("/products/v1");
        setLastUpdated(new DateTime(1486464366000l));
        setSubscriberCount(1);
        setProvider("admin");
        setVisibility(ApiVisibility.PUBLIC);
        setStatus(ApiStatus.PUBLISHED);
        setThumbnailImageUrl("");
        setTags("product", "products");
        setEndpointType(ApiEndpointType.UNSECURED);
        setEndpointUsername("");
        setEndpointPassword("");
        setEndpointConfig("{\"production_endpoints\": {\"url\":\"http://ws-sit1.dev.tppim.co.uk/pimwebservices/services/rest-product-entity-v1/products\", \"config\": null},\"endpoint_type\":\"http\"}");
        setHttpChecked(true);
        setHttpsChecked(true);
        setTiers(ApiTierAvailability.UNLIMITED);
        setRoles("");
    }

}
