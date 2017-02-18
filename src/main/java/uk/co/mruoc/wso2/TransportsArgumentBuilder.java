package uk.co.mruoc.wso2;

public class TransportsArgumentBuilder {

    private final StringArgumentBuilder httpArgumentBuilder = new StringArgumentBuilder("http_checked");
    private final StringArgumentBuilder httpsArgumentBuilder = new StringArgumentBuilder("https_checked");

    public String build(TransportParams params) {
        String result = buildHttpChecked(params);
        result += buildHttpsChecked(params);
        return result;
    }

    private String buildHttpChecked(TransportParams params) {
        if (params.isHttpChecked())
            return httpArgumentBuilder.build("http");
        return "";
    }

    private String buildHttpsChecked(TransportParams params) {
        if (params.isHttpsChecked())
            return httpsArgumentBuilder.build("https");
        return "";
    }

}
