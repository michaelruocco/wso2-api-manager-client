package uk.co.mruoc.wso2;

public class SelectApiParamsToQueryStringConverter {

    private static final String QUERY_STRING = "?action=%s";

    private final StringArgumentBuilder nameArgumentBuilder;
    private final StringArgumentBuilder versionArgumentBuilder = new StringArgumentBuilder("version");
    private final StringArgumentBuilder providerArgumentBuilder = new StringArgumentBuilder("provider");

    private final String action;

    public SelectApiParamsToQueryStringConverter(String action) {
        this(action, new StringArgumentBuilder("name"));
    }

    public SelectApiParamsToQueryStringConverter(String action, StringArgumentBuilder nameArgumentBuilder) {
        this.action = action;
        this.nameArgumentBuilder = nameArgumentBuilder;
    }
    public String convert(SelectApiParams params) {
        String queryString = String.format(QUERY_STRING, action);
        queryString += nameArgumentBuilder.build(params.getApiName());
        queryString += versionArgumentBuilder.build(params.getApiVersion());
        queryString += providerArgumentBuilder.build(params.getProvider());
        return queryString;
    }

}
