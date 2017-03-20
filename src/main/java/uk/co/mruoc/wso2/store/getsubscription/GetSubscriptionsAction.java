package uk.co.mruoc.wso2.store.getsubscription;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import uk.co.mruoc.http.client.HttpClient;
import uk.co.mruoc.http.client.Response;
import uk.co.mruoc.wso2.ResponseErrorChecker;
import uk.co.mruoc.wso2.SelectApiParams;
import uk.co.mruoc.wso2.store.StoreResponseErrorChecker;

import java.lang.reflect.Type;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class GetSubscriptionsAction {

    private final ResponseErrorChecker errorChecker = new StoreResponseErrorChecker();
    private final HttpClient client;
    private final GetSubscriptionUrlBuilder urlBuilder;
    private final Gson gson = buildGson();

    public GetSubscriptionsAction(HttpClient client, String hostUrl) {
        this(client, new GetSubscriptionUrlBuilder(hostUrl));
    }

    public GetSubscriptionsAction(HttpClient client, GetSubscriptionUrlBuilder urlBuilder) {
        this.client = client;
        this.urlBuilder = urlBuilder;
    }

    public List<ApiSubscription> getSubscriptions(SelectApiParams params) {
        String url = urlBuilder.build(params);
        Response response = client.post(url, EMPTY);
        errorChecker.checkForError(response);
        return toSubscriptions(response);
    }

    private List<ApiSubscription> toSubscriptions(Response response) {
        JsonElement element = new JsonParser().parse(response.getBody());
        JsonObject json = element.getAsJsonObject();
        Type listType = new TypeToken<List<DefaultApiSubscription>>() {}.getType();
        return gson.fromJson(json.get("applications"), listType);
    }

    private static Gson buildGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(DefaultApiSubscription.class, new ApiSubscriptionDeserializer());
        return gsonBuilder.create();
    }

}
