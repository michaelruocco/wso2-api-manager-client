package uk.co.mruoc.wso2.publisher;

import uk.co.mruoc.wso2.publisher.addapi.AddApiParams;
import uk.co.mruoc.wso2.publisher.addapi.DefaultAddApiParams;

public class StubAddApiParamsBuilder {

    public static AddApiParams build() {
        DefaultAddApiParams params = new DefaultAddApiParams();
        params.setApiName("rest-product");
        params.setContext("/product");
        params.setApiVersion("v1");
        params.setProvider("admin");
        params.setDescription("Rest Product API");
        params.setTags("prod", "rest-product", "product");
        params.setSwagger("{\"consumes\":[\"application/json\"],\"info\":{\"description\":\"rest-taxonomy : (build 20170103134850)\",\"title\":\"rest-taxonomy\",\"version\":\"v1\"},\"paths\":{\"/z20170103134850\":{\"get\":{\"responses\":{\"200\":{}},\"x-auth-type\":\"Application\",\"x-throttling-tier\":\"Unlimited\"}},\"/{catalog}/paged*\":{\"get\":{\"parameters\":[{\"description\":\"Catalog id (for ex. GroupMasterProductCatalog)\",\"in\":\"path\",\"name\":\"catalog\",\"required\":true,\"type\":\"string\"},{\"in\":\"query\",\"name\":\"limit\",\"required\":true,\"type\":\"integer\"},{\"in\":\"query\",\"name\":\"offset\",\"required\":true,\"type\":\"integer\"}],\"responses\":{\"200\":{}},\"x-auth-type\":\"Application\",\"x-throttling-tier\":\"Unlimited\"}}},\"produces\":[\"application/json\"],\"schemes\":[\"https\"],\"swagger\":\"2.0\"}");
        params.setEndpointConfig("{\"production_endpoints\": {\"url\":\"http://ws-uat1.dev.tppim.co.uk/pimwebservices/services/rest-taxonomy-entity-v1/taxonomy\", \"config\": null},\"endpoint_type\":\"http\"}");
        return params;
    }

}
