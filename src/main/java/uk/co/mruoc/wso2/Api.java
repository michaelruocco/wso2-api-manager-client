package uk.co.mruoc.wso2;

import org.joda.time.DateTime;

import java.util.List;

public interface Api {

    String getName();

    String getVersion();

    String getDescription();

    String getContext();

    DateTime getLastUpdated();

    int getSubscriberCount();

    String getProvider();

    ApiVisibility getVisibility();

    String getStatus();

    String getThumb();

    List<String> getTags();

    ApiEndpointType getEndpointType();

    String getEndpointUsername();

    String getEndpointPassword();

    String getEndpointConfig();

    boolean isHttpChecked();

    boolean isHttpsChecked();

    List<ApiTierAvailability> getTiers();

    List<String> getRoles();

}
