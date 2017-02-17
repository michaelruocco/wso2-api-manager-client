package uk.co.mruoc.wso2;

import uk.co.mruoc.wso2.DefaultAddApiParams.DefaultAddApiParamsBuilder;

public class StubAddApiParamsBuilder {

    public static AddApiParams build() {
        return new DefaultAddApiParamsBuilder()
                .setName("rest-product")
                .setContext("/product")
                .setVersion("v1")
                .setDescription("Rest Product API")
                .setTags("prod", "rest-product", "product")
                .setSwagger("{\"consumes\":[\"application/json\"],\"info\":{\"description\":\"rest-taxonomy : (build 20170103134850)\",\"title\":\"rest-taxonomy\",\"version\":\"v1\"},\"paths\":{\"/z20170103134850\":{\"get\":{\"responses\":{\"200\":{}},\"x-auth-type\":\"Application\",\"x-throttling-tier\":\"Unlimited\"}},\"/{catalog}/paged*\":{\"get\":{\"parameters\":[{\"description\":\"Catalog id (for ex. GroupMasterProductCatalog)\",\"in\":\"path\",\"name\":\"catalog\",\"required\":true,\"type\":\"string\"},{\"in\":\"query\",\"name\":\"limit\",\"required\":true,\"type\":\"integer\"},{\"in\":\"query\",\"name\":\"offset\",\"required\":true,\"type\":\"integer\"}],\"responses\":{\"200\":{}},\"x-auth-type\":\"Application\",\"x-throttling-tier\":\"Unlimited\"}}},\"produces\":[\"application/json\"],\"schemes\":[\"https\"],\"swagger\":\"2.0\"}")
                .setEndpointConfig("{\"production_endpoints\": {\"url\":\"http://ws-uat1.dev.tppim.co.uk/pimwebservices/services/rest-taxonomy-entity-v1/taxonomy\", \"config\": null},\"endpoint_type\":\"http\"}")
                .build();
    }

}
