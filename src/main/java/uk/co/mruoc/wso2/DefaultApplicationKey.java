package uk.co.mruoc.wso2;

import org.joda.time.DateTime;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class DefaultApplicationKey implements ApplicationKey {

    private DateTime validityTime = new DateTime(Long.MIN_VALUE);
    private String consumerKey = EMPTY;
    private String consumerSecret = EMPTY;
    private String accessToken = EMPTY;

    @Override
    public DateTime getValidityTime() {
        return validityTime;
    }

    @Override
    public String getConsumerKey() {
        return consumerKey;
    }

    @Override
    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public String getConsumerSecret() {
        return consumerSecret;
    }

    public void setValidityTime(DateTime validityTime) {
        this.validityTime = validityTime;
    }

    public void setConsumerKey(String consumerKey) {
        this.consumerKey = consumerKey;
    }

    public void setConsumerSecret(String consumerSecret) {
        this.consumerSecret = consumerSecret;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

}
