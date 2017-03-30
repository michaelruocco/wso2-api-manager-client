package uk.co.mruoc.wso2.store;

import uk.co.mruoc.http.client.insecure.InsecureSimpleHttpClient;

public class InsecureDefaultApiStoreClient extends DefaultApiStoreClient {

    public InsecureDefaultApiStoreClient(String hostUrl) {
        super(new InsecureSimpleHttpClient(), hostUrl);
    }

}
