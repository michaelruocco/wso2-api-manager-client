package uk.co.mruoc.wso2;

public class AddApplicationParamsToQueryStringConverter {

    private final StringArgumentBuilder nameArgumentBuilder = new StringArgumentBuilder("application");
    private final StringArgumentBuilder tierArgumentBuilder = new StringArgumentBuilder("tier");
    private final StringArgumentBuilder descriptionArgumentBuilder = new StringArgumentBuilder("description");
    private final StringArgumentBuilder callbackUrlArgumentBuilder = new StringArgumentBuilder("callbackUrl");

    public String convert(AddApplicationParams params) {
        return "?action=addApplication" +
                nameArgumentBuilder.build(params.getApplicationName()) +
                tierArgumentBuilder.build(ApiAvailabilityTierNameFormatter.formatName(params.getTier())) +
                descriptionArgumentBuilder.build(params.getApplicationDescription()) +
                callbackUrlArgumentBuilder.build(params.getCallbackUrl());
    }

}
