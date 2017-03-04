package uk.co.mruoc.wso2;

public class DefaultSetStatusApiParamsToQueryStringConverter extends SelectApiParamsToQueryStringConverter implements SetStatusApiParamsToQueryStringConverter {

    private final StatusArgumentBuilder statusArgumentBuilder = new StatusArgumentBuilder();
    private final StringArgumentBuilder publishArgumentBuilder = new StringArgumentBuilder("publishToGateway");
    private final StringArgumentBuilder subscriptionArgumentBuilder = new StringArgumentBuilder("requireResubscription");

    public DefaultSetStatusApiParamsToQueryStringConverter() {
        super("updateStatus");
    }

    public String convert(SetStatusApiParams params) {
        String queryString = super.convert(params);
        queryString += statusArgumentBuilder.build(params);
        if (params.isPublishToGateway())
            queryString += publishArgumentBuilder.build(params.isPublishToGateway());
        if (params.isRequireResubscription())
            queryString += subscriptionArgumentBuilder.build(params.isRequireResubscription());
        return queryString;
    }

}
