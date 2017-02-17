package uk.co.mruoc.wso2;

public interface AuthenticationUrlBuilder {

    String buildLoginUrl(Credentials credentials);

    String buildLogoutUrl();

}
