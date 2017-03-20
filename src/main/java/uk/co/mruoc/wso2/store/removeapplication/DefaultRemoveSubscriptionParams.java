package uk.co.mruoc.wso2.store.removeapplication;

import uk.co.mruoc.wso2.DefaultSelectApiParams;
import uk.co.mruoc.wso2.store.removesubscription.RemoveSubscriptionParams;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class DefaultRemoveSubscriptionParams extends DefaultSelectApiParams implements RemoveSubscriptionParams {

    private String name = EMPTY;

    @Override
    public String getApplicationName() {
        return name;
    }

    public void setApplicationName(String name) {
        this.name = name;
    }

}
