package uk.co.mruoc.wso2;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class DefaultSetStatusApiParams extends DefaultSelectApiParams implements SetStatusApiParams {

    private String status = EMPTY;
    private boolean publishToGateway = true;
    private boolean requireSubscription = true;

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public boolean isPublishToGateway() {
        return publishToGateway;
    }

    @Override
    public boolean isRequireSubscription() {
        return requireSubscription;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPublishToGateway(boolean publishToGateway) {
        this.publishToGateway = publishToGateway;
    }

    public void setRequireSubscription(boolean requireSubscription) {
        this.requireSubscription = requireSubscription;
    }

}
