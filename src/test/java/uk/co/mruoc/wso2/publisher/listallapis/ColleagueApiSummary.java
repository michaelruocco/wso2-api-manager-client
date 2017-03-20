package uk.co.mruoc.wso2.publisher.listallapis;

import uk.co.mruoc.wso2.publisher.ApiStatus;

public class ColleagueApiSummary extends DefaultApiSummary {

    public ColleagueApiSummary() {
        setName("Colleague-ApiSummary");
        setVersion("v2");
        setProvider("mruoc");
        setStatus(ApiStatus.CREATED);
        setThumbnailImageUrl("value");
        setSubscriberCount(3);
    }

}
