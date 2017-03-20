package uk.co.mruoc.wso2.publisher.setstatus;

import uk.co.mruoc.wso2.SelectApiParams;
import uk.co.mruoc.wso2.publisher.ApiStatus;

public interface SetStatusParams extends SelectApiParams {

    ApiStatus getStatus();

    boolean isPublishToGateway();

    boolean isRequireResubscription();

}
