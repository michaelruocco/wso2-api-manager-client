package uk.co.mruoc.wso2.publisher;

import uk.co.mruoc.wso2.ResponseLoader;

public class PublisherResponseLoader extends ResponseLoader {

    @Override
    public String getPath() {
        return super.getPath() + "publisher/";
    }

}
