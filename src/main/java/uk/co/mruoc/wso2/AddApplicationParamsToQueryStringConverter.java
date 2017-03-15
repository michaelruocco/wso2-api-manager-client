package uk.co.mruoc.wso2;

import org.apache.commons.lang3.text.WordUtils;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class AddApplicationParamsToQueryStringConverter {

    private final StringArgumentBuilder nameArgumentBuilder = new StringArgumentBuilder("application");
    private final StringArgumentBuilder tierArgumentBuilder = new StringArgumentBuilder("tier");
    private final StringArgumentBuilder descriptionArgumentBuilder = new StringArgumentBuilder("description");
    private final StringArgumentBuilder callbackUrlArgumentBuilder = new StringArgumentBuilder("callbackUrl");

    public String convert(AddApplicationParams params) {
        return "?action=addApplication" +
                nameArgumentBuilder.build(params.getApplicationName()) +
                tierArgumentBuilder.build(formatName(params.getTier())) +
                descriptionArgumentBuilder.build(params.getApplicationDescription()) +
                callbackUrlArgumentBuilder.build(params.getCallbackUrl());
    }

    private static String formatName(ApiTierAvailability tier) {
        if (tier == null)
            return EMPTY;
        return WordUtils.capitalize(tier.name().toLowerCase());
    }

}
