package uk.co.mruoc.wso2.publisher.listallapis;

import uk.co.mruoc.wso2.publisher.ApiStatus;

public class ColleagueApiSummary extends DefaultApiSummary {

    public ColleagueApiSummary() {
        setApiName("Colleague-ApiSummary");
        setApiVersion("v2");
        setProvider("mruoc");
        setStatus(ApiStatus.CREATED);
        setThumbnailImageUrl("value");
        setSubscriberCount(3);
    }

}
