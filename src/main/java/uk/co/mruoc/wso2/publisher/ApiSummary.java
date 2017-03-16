package uk.co.mruoc.wso2.publisher;

import uk.co.mruoc.wso2.SelectApiParams;

public interface ApiSummary extends SelectApiParams {

    ApiStatus getStatus();

    String getThumbnailImageUrl();

    int getSubscriberCount();

}
