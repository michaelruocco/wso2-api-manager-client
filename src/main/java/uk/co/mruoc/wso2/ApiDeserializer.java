package uk.co.mruoc.wso2;

import com.google.gson.*;
import uk.co.mruoc.wso2.Api.ApiBuilder;

import java.lang.reflect.Type;

public class ApiDeserializer implements JsonDeserializer<Api> {

    @Override
    public Api deserialize(JsonElement element, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        PublisherJsonParser parser = new PublisherJsonParser(element);
        return new ApiBuilder()
                .setName(parser.getName())
                .setVersion(parser.getVersion())
                .setDescription(parser.getDescription())
                .setContext(parser.getContext())
                .setLastUpdated(parser.getLastUpdated())
                .setSubscriberCount(parser.getSubscriberCount())
                .setProvider(parser.getProvider())
                .setVisibility(parser.getVisibility())
                .setStatus(parser.getStatus())
                .setThumb(parser.getThumbnailImagePath())
                .setTags(parser.getTags())
                .setEndpointType(parser.getEndpointType())
                .setEndpointUsername(parser.getEndpointUsername())
                .setEndpointPassword(parser.getEndpointPassword())
                .setEndpointConfig(parser.getEndpointConfig())
                .setHttpChecked(parser.getHttpChecked())
                .setHttpsChecked(parser.getHttpsChecked())
                .setTiers(parser.getTiers())
                .setRoles(parser.getRoles())
                .build();
    }

}
