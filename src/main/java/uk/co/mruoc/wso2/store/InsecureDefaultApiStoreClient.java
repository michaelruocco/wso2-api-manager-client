package uk.co.mruoc.wso2.store;

import uk.co.mruoc.http.client.insecure.InsecureSimpleHttpClient;
import uk.co.mruoc.wso2.publisher.DefaultApiPublisherClient;

public class InsecureDefaultApiStoreClient extends DefaultApiPublisherClient {

    public InsecureDefaultApiStoreClient(String hostUrl) {
        super(new InsecureSimpleHttpClient(), hostUrl);
    }

}
