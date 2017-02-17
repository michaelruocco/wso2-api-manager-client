package uk.co.mruoc.wso2;

public class TransportsArgumentBuilder {

    public String build(TransportParams params) {
        String result = buildHttpChecked(params);
        result += buildHttpsChecked(params);
        return result;
    }

    private String buildHttpChecked(TransportParams params) {
        String result = "http_checked=";
        if (params.isHttpChecked())
            result += "http";
        return result;
    }

    private String buildHttpsChecked(TransportParams params) {
        String result = "&https_checked=";
        if (params.isHttpsChecked())
            result += "https";
        return result;
    }

}
