package uk.co.mruoc.wso2;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class DefaultApiApplication implements ApiApplication {

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
