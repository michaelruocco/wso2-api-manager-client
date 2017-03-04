package uk.co.mruoc.wso2;

public class DefaultSetStatusParams extends DefaultSelectApiParams implements SetStatusParams {

    private ApiStatus status = ApiStatus.CREATED;
    private boolean publishToGateway = true;
    private boolean requireResubscription = false;

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
        return requireResubscription;
    }

    public void setStatus(ApiStatus status) {
        this.status = status;
    }

    public void setPublishToGateway(boolean publishToGateway) {
        this.publishToGateway = publishToGateway;
    }

    public void setRequireResubscription(boolean requireResubscription) {
        this.requireResubscription = requireResubscription;
    }

}
