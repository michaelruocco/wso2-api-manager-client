package uk.co.mruoc.wso2.publisher.listallapis;

import uk.co.mruoc.wso2.SelectApiParams;
import uk.co.mruoc.wso2.publisher.ApiStatus;

public interface ApiSummary extends SelectApiParams {

    ApiStatus getStatus();

    String getThumbnailImageUrl();

    int getSubscriberCount();

}
