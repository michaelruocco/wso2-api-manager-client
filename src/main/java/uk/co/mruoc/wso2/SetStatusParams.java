package uk.co.mruoc.wso2;

public interface SetStatusParams extends SelectApiParams {

    ApiStatus getStatus();

    boolean isPublishToGateway();

    boolean isRequireResubscription();

}
