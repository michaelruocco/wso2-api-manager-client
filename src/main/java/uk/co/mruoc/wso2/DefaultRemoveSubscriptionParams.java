package uk.co.mruoc.wso2;

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
