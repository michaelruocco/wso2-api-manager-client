package uk.co.mruoc.wso2.publisher;

import uk.co.mruoc.http.client.insecure.InsecureSimpleHttpClient;

public class InsecureDefaultApiPublisherClient extends DefaultApiPublisherClient {

    public InsecureDefaultApiPublisherClient(String hostUrl) {
        super(new InsecureSimpleHttpClient(), hostUrl);
    }

}
