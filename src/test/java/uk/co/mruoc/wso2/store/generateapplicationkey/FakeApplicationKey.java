package uk.co.mruoc.wso2.store.generateapplicationkey;

import org.joda.time.DateTime;

public class FakeApplicationKey extends DefaultApplicationKey {

    public FakeApplicationKey() {
        setValidityTime(new DateTime(9223372036854775807L));
        setConsumerKey("d5Noh1gRT4GSlyBFkfhLyZKSWqEa");
        setConsumerSecret("UOEa2jy3KVasfQ_BWMg_O_26ywIa");
        setAccessToken("ca9e489c1fca6c68f31f324433866f6c");
    }

}
