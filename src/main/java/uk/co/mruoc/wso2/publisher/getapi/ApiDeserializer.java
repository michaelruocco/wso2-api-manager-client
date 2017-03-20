package uk.co.mruoc.wso2.publisher.getapi;

import com.google.gson.*;
import uk.co.mruoc.wso2.publisher.PublisherJsonParser;

import java.lang.reflect.Type;

public class ApiDeserializer implements JsonDeserializer<Api> {

    @Override
    public Api deserialize(JsonElement element, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        PublisherJsonParser parser = new PublisherJsonParser(element);
        DefaultApi api = new DefaultApi();
        api.setName(parser.getName());
        api.setVersion(parser.getVersion());
        api.setDescription(parser.getDescription());
        api.setContext(parser.getContext());
        api.setLastUpdated(parser.getLastUpdated());
        api.setSubscriberCount(parser.getSubscriberCount());
        api.setProvider(parser.getProvider());
        api.setVisibility(parser.getVisibility());
        api.setStatus(parser.getStatus());
        api.setThumbnailImageUrl(parser.getThumbnailImagePath());
        api.setTags(parser.getTags());
        api.setEndpointType(parser.getEndpointType());
        api.setEndpointUsername(parser.getEndpointUsername());
        api.setEndpointPassword(parser.getEndpointPassword());
        api.setEndpointConfig(parser.getEndpointConfig());
        api.setHttpChecked(parser.getHttpChecked());
        api.setHttpsChecked(parser.getHttpsChecked());
        api.setTiers(parser.getTiers());
        api.setRoles(parser.getRoles());
        return api;
    }

}
