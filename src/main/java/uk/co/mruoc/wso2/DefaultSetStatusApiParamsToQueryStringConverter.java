package uk.co.mruoc.wso2;

public class DefaultSetStatusApiParamsToQueryStringConverter extends SelectApiParamsToQueryStringConverter implements PublishApiParamsToQueryStringConverter {

    private final StringArgumentBuilder statusArgumentBuilder = new StringArgumentBuilder("status");
    private final StringArgumentBuilder publishArgumentBuilder = new StringArgumentBuilder("publishToGateway");
    private final StringArgumentBuilder subscriptionArgumentBuilder = new StringArgumentBuilder("requireResubscription");

    public DefaultSetStatusApiParamsToQueryStringConverter() {
        super("updateStatus");
    }

    public String convert(SetStatusApiParams params) {
        String queryString = super.convert(params);
        queryString += statusArgumentBuilder.build(params.getStatus());
        queryString += publishArgumentBuilder.build(params.isPublishToGateway());
        queryString += subscriptionArgumentBuilder.build(params.isRequireSubscription());
        return queryString;
    }

}
