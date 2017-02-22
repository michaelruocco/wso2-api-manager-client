package uk.co.mruoc.wso2;

import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;
import java.util.List;

public class DefaultUpdateApiParamsToQueryStringConverter implements UpdateApiParamsToQueryStringConverter {

    private final StringArgumentBuilder nameArgumentBuilder = new StringArgumentBuilder("name");
    private final StringArgumentBuilder contextArgumentBuilder = new StringArgumentBuilder("context");
    private final StringArgumentBuilder versionArgumentBuilder = new StringArgumentBuilder("version");
    private final StringArgumentBuilder providerArgumentBuilder = new StringArgumentBuilder("provider");
    private final StringArgumentBuilder descriptionArgumentBuilder = new StringArgumentBuilder("description");
    private final StringArgumentBuilder swaggerArgumentBuilder = new StringArgumentBuilder("swagger");
    private final StringArgumentBuilder endpointConfigArgumentBuilder = new StringArgumentBuilder("endpoint_config");
    private final StringArgumentBuilder tagsArgumentBuilder = new StringArgumentBuilder("tags");
    private final StringArgumentBuilder tiersCollectionArgumentBuilder = new StringArgumentBuilder("tiersCollection");
    private final StringArgumentBuilder thumbArgumentBuilder = new StringArgumentBuilder("thumbUrl");

    private final TransportsArgumentBuilder transportsArgumentBuilder = new TransportsArgumentBuilder();
    private final EndpointSecurityArgumentBuilder endpointSecurityArgumentBuilder = new EndpointSecurityArgumentBuilder();
    private final VisibilityArgumentBuilder visibilityArgumentBuilder = new VisibilityArgumentBuilder();

    @Override
    public String convert(UpdateApiParams params) {
        return "?action=updateAPI" +
                nameArgumentBuilder.build(params.getName()) +
                contextArgumentBuilder.build(params.getContext()) +
                versionArgumentBuilder.build(params.getVersion()) +
                providerArgumentBuilder.build(params.getProvider()) +
                visibilityArgumentBuilder.build(params) +
                thumbArgumentBuilder.build(params.getThumbnailImagePath()) +
                descriptionArgumentBuilder.build(params.getDescription()) +
                tagsArgumentBuilder.build(params.getTags()) +
                endpointSecurityArgumentBuilder.build(params) +
                tiersCollectionArgumentBuilder.build(toNames(params.getTiers())) +
                transportsArgumentBuilder.build(params) +
                swaggerArgumentBuilder.build(params.getSwagger()) +
                endpointConfigArgumentBuilder.build(params.getEndpointConfig());
    }

    private static List<String> toNames(List<ApiTierAvailability> values) {
        List<String> names = new ArrayList<>();
        values.forEach(v -> names.add(UrlEncoder.encode(WordUtils.capitalize(v.name().toLowerCase()))));
        return names;
    }

}
