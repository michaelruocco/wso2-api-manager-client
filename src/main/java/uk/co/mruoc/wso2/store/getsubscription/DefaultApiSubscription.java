package uk.co.mruoc.wso2.store.getsubscription;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class DefaultApiSubscription implements ApiSubscription {

    private String name = EMPTY;
    private int id;

    @Override
    public String getApplicationName() {
        return name;
    }

    @Override
    public int getApplicationId() {
        return id;
    }

    public void setApplicationName(String name) {
        this.name = name;
    }

    public void setApplicationId(int id) {
        this.id = id;
    }

}
