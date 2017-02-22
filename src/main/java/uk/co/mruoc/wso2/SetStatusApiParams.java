package uk.co.mruoc.wso2;

public interface SetStatusApiParams extends SelectApiParams {

    String getStatus();

    boolean isPublishToGateway();

    boolean isRequireSubscription();

}
