package uk.co.mruoc.wso2;

import uk.co.mruoc.wso2.DefaultGetApiParams.DefaultGetApiParamsBuilder;

public class StubGetApiParamsBuilder {

    public static GetApiParams build() {
        return new DefaultGetApiParamsBuilder()
                .setName("rest-product")
                .setVersion("v1")
                .setProvider("admin")
                .build();
    }

}
