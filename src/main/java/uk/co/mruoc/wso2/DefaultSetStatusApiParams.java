package uk.co.mruoc.wso2;

public class DefaultSetStatusApiParams extends DefaultSelectApiParams implements SetStatusApiParams {

    private ApiStatus status = ApiStatus.CREATED;
    private boolean publishToGateway = true;
    private boolean requireSubscription = true;

    @Override
    public ApiStatus getStatus() {
        return status;
    }

    @Override
    public boolean isPublishToGateway() {
        return publishToGateway;
    }

    @Override
    public boolean isRequireResubscription() {
        return requireSubscription;
    }

    public void setStatus(ApiStatus status) {
        this.status = status;
    }

    public void setPublishToGateway(boolean publishToGateway) {
        this.publishToGateway = publishToGateway;
    }

    public void setRequireSubscription(boolean requireSubscription) {
        this.requireSubscription = requireSubscription;
    }

}
