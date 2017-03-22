package uk.co.mruoc.wso2.publisher.listallapis;

import com.google.gson.*;
import uk.co.mruoc.wso2.publisher.PublisherJsonParser;

import java.lang.reflect.Type;

public class ApiSummaryDeserializer implements JsonDeserializer<ApiSummary> {

    @Override
    public ApiSummary deserialize(JsonElement element, Type typeOfT, JsonDeserializationContext context) {
        PublisherJsonParser parser = new PublisherJsonParser(element);
        DefaultApiSummary summary = new DefaultApiSummary();
        summary.setApiName(parser.getName());
        summary.setApiVersion(parser.getVersion());
        summary.setProvider(parser.getProvider());
        summary.setStatus(parser.getStatus());
        summary.setThumbnailImageUrl(parser.getThumbnailImagePath());
        summary.setSubscriberCount(parser.getSubscriberCount());
        return summary;
    }

}
