package uk.co.mruoc.wso2;

public interface SetStatusApiParams extends SelectApiParams {

    ApiStatus getStatus();

    boolean isPublishToGateway();

    boolean isRequireResubscription();

}
