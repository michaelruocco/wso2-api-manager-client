package uk.co.mruoc.wso2;

public class ColleagueApiSummary extends ApiSummary {

    public ColleagueApiSummary() {
        super(new ApiSummaryBuilder()
                .setName("Colleague-ApiSummary")
                .setVersion("v2")
                .setProvider("mruoc")
                .setStatus("CREATED")
                .setThumbnailImagePath("value")
                .setSubscriberCount(3));
    }

}
