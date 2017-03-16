package uk.co.mruoc.wso2.publisher;

import uk.co.mruoc.wso2.SelectApiParams;

public interface SetStatusParams extends SelectApiParams {

    ApiStatus getStatus();

    boolean isPublishToGateway();

    boolean isRequireResubscription();

}
