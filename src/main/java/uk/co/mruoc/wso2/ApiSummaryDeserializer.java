package uk.co.mruoc.wso2;

import com.google.gson.*;
import uk.co.mruoc.wso2.ApiSummary.ApiSummaryBuilder;

import java.lang.reflect.Type;

public class ApiSummaryDeserializer implements JsonDeserializer<ApiSummary> {

    @Override
    public ApiSummary deserialize(JsonElement element, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        PublisherJsonParser parser = new PublisherJsonParser(element);
        return new ApiSummaryBuilder()
                .setName(parser.getName())
                .setVersion(parser.getVersion())
                .setProvider(parser.getProvider())
                .setStatus(parser.getStatus())
                .setThumbnailImageUrl(parser.getThumbnailImagePath())
                .setSubscriberCount(parser.getSubscriberCount())
                .build();
    }

}
