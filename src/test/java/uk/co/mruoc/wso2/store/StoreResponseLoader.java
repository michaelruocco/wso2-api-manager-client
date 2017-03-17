package uk.co.mruoc.wso2.store;

import uk.co.mruoc.wso2.ResponseLoader;

public class StoreResponseLoader extends ResponseLoader {

    @Override
    public String getPath() {
        return super.getPath() + "store/";
    }

}
