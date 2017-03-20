package uk.co.mruoc.wso2.publisher.getapi;

import org.joda.time.DateTime;
import uk.co.mruoc.wso2.ApiTierAvailability;
import uk.co.mruoc.wso2.publisher.ApiEndpointType;
import uk.co.mruoc.wso2.publisher.ApiStatus;
import uk.co.mruoc.wso2.publisher.ApiVisibility;

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

    ApiStatus getStatus();

    String getThumbnailImageUrl();

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
