package uk.co.mruoc.wso2;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;
import java.util.List;

import static uk.co.mruoc.wso2.ListToCommaSeparatedStringConverter.toCommaSeparatedString;

public class AddApiParamsToQueryStringConverter {

    private final TransportsArgumentBuilder transportsArgumentBuilder = new TransportsArgumentBuilder();
    private final SequencesArgumentBuilder sequencesArgumentBuilder = new SequencesArgumentBuilder();
    private final ResponseCacheArgumentBuilder responseCacheBuilder = new ResponseCacheArgumentBuilder();
    private final DefaultVersionArgumentBuilder defaultVersionArgumentBuilder = new DefaultVersionArgumentBuilder();
    private final SubscriptionsArgumentBuilder subscriptionsArgumentBuilder = new SubscriptionsArgumentBuilder();
    private final EndpointSecurityArgumentBuilder endpointSecurityArgumentBuilder = new EndpointSecurityArgumentBuilder();
    private final VisibilityArgumentBuilder visibilityArgumentBuilder = new VisibilityArgumentBuilder();

    public String toQueryString(AddApiParams params) {
        return "?action=addAPI" +
                "&name=" + format(params.getName()) +
                "&context=" + format(params.getContext()) +
                "&version=" + format(params.getVersion()) +
                "&description=" + format(params.getDescription()) +
                "&swagger=" + format(params.getSwagger()) +
                "&endpoint_config=" + format(params.getEndpointConfig()) +
                "&tags=" + toCommaSeparatedString(params.getTags()) +
                "&tiersCollection=" + toCommaSeparatedString(toNames(params.getTiers())) +
                "&" + visibilityArgumentBuilder.build(params) +
                endpointSecurityArgumentBuilder.build(params) +
                "&" + responseCacheBuilder.build(params) +
                "&" + transportsArgumentBuilder.build(params) +
                "&" + sequencesArgumentBuilder.build(params) +
                "&" + defaultVersionArgumentBuilder.build(params) +
                "&" + subscriptionsArgumentBuilder.build(params);
    }

    private static List<String> toNames(List<ApiTierAvailability> values) {
        List<String> names = new ArrayList<>();
        values.forEach(v -> names.add(UrlEncoder.encode(WordUtils.capitalize(v.name().toLowerCase()))));
        return names;
    }

    private static String format(String value) {
        if (StringUtils.isNotEmpty(value))
            return UrlEncoder.encode(value);
        return "";
    }

}
