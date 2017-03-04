package uk.co.mruoc.wso2;

import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;
import java.util.List;

public class AddApiParamsToQueryStringConverter {

    private final StringArgumentBuilder nameArgumentBuilder = new StringArgumentBuilder("name");
    private final StringArgumentBuilder contextArgumentBuilder = new StringArgumentBuilder("context");
    private final StringArgumentBuilder versionArgumentBuilder = new StringArgumentBuilder("version");
    private final StringArgumentBuilder descriptionArgumentBuilder = new StringArgumentBuilder("description");
    private final StringArgumentBuilder swaggerArgumentBuilder = new StringArgumentBuilder("swagger");
    private final StringArgumentBuilder endpointConfigArgumentBuilder = new StringArgumentBuilder("endpoint_config");
    private final StringArgumentBuilder tagsArgumentBuilder = new StringArgumentBuilder("tags");
    private final StringArgumentBuilder tiersCollectionArgumentBuilder = new StringArgumentBuilder("tiersCollection");

    private final TransportsArgumentBuilder transportsArgumentBuilder = new TransportsArgumentBuilder();
    private final SequencesArgumentBuilder sequencesArgumentBuilder = new SequencesArgumentBuilder();
    private final ResponseCacheArgumentBuilder responseCacheBuilder = new ResponseCacheArgumentBuilder();
    private final DefaultVersionArgumentBuilder defaultVersionArgumentBuilder = new DefaultVersionArgumentBuilder();
    private final SubscriptionsArgumentBuilder subscriptionsArgumentBuilder = new SubscriptionsArgumentBuilder();
    private final EndpointSecurityArgumentBuilder endpointSecurityArgumentBuilder = new EndpointSecurityArgumentBuilder();
    private final VisibilityArgumentBuilder visibilityArgumentBuilder = new VisibilityArgumentBuilder();

    public String convert(AddApiParams params) {
        return "?action=addAPI" +
                nameArgumentBuilder.build(params.getApiName()) +
                contextArgumentBuilder.build(params.getContext()) +
                versionArgumentBuilder.build(params.getApiVersion()) +
                descriptionArgumentBuilder.build(params.getApiDescription()) +
                swaggerArgumentBuilder.build(params.getSwagger()) +
                endpointConfigArgumentBuilder.build(params.getEndpointConfig()) +
                tagsArgumentBuilder.build(params.getTags()) +
                tiersCollectionArgumentBuilder.build(toNames(params.getTiers())) +
                visibilityArgumentBuilder.build(params) +
                endpointSecurityArgumentBuilder.build(params) +
                responseCacheBuilder.build(params) +
                transportsArgumentBuilder.build(params) +
                sequencesArgumentBuilder.build(params) +
                defaultVersionArgumentBuilder.build(params) +
                subscriptionsArgumentBuilder.build(params);
    }

    private static List<String> toNames(List<ApiTierAvailability> values) {
        List<String> names = new ArrayList<>();
        values.forEach(v -> names.add(UrlEncoder.encode(WordUtils.capitalize(v.name().toLowerCase()))));
        return names;
    }

}
