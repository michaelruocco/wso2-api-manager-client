package uk.co.mruoc.wso2;

public class SetStatusParamsToQueryStringConverter extends SelectApiParamsToQueryStringConverter {

    private final StatusArgumentBuilder statusArgumentBuilder = new StatusArgumentBuilder();
    private final StringArgumentBuilder publishArgumentBuilder = new StringArgumentBuilder("publishToGateway");
    private final StringArgumentBuilder subscriptionArgumentBuilder = new StringArgumentBuilder("requireResubscription");

    public SetStatusParamsToQueryStringConverter() {
        super("updateStatus");
    }

    public String convert(SetStatusParams params) {
        String queryString = super.convert(params);
        queryString += statusArgumentBuilder.build(params);
        if (params.isPublishToGateway())
            queryString += publishArgumentBuilder.build(params.isPublishToGateway());
        if (params.isRequireResubscription())
            queryString += subscriptionArgumentBuilder.build(params.isRequireResubscription());
        return queryString;
    }

}
