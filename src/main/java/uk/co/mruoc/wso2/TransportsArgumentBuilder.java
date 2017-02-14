package uk.co.mruoc.wso2;

public class TransportsArgumentBuilder {

    public String build(AddApiParams params) {
        String result = buildHttpChecked(params);
        result += buildHttpsChecked(params);
        return result;
    }

    private String buildHttpChecked(AddApiParams params) {
        String result = "&http_checked=";
        if (params.httpChecked())
            result += "http";
        return result;
    }

    private String buildHttpsChecked(AddApiParams params) {
        String result = "&https_checked=";
        if (params.httpsChecked())
            result += "https";
        return result;
    }

}
