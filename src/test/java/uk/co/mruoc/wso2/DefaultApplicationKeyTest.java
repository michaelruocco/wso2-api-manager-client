package uk.co.mruoc.wso2;

import org.joda.time.DateTime;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultApplicationKeyTest {

    private final DefaultApplicationKey key = new DefaultApplicationKey();

    @Test
    public void validityTimeShouldDefaultToMinLongDate() {
        assertThat(key.getValidityTime()).isEqualTo(new DateTime(Long.MIN_VALUE));
    }

    @Test
    public void shouldReturnValidityTime() {
        DateTime validityTime = new DateTime();

        key.setValidityTime(validityTime);

        assertThat(key.getValidityTime()).isEqualTo(validityTime);
    }

    @Test
    public void consumerKeyShouldDefaultToEmpty() {
        assertThat(key.getConsumerKey()).isEmpty();
    }

    @Test
    public void shouldReturnConsumerKey() {
        String consumerKey = "key";

        key.setConsumerKey(consumerKey);

        assertThat(key.getConsumerKey()).isEqualTo(consumerKey);
    }

    @Test
    public void consumerSecretShouldDefaultToEmpty() {
        assertThat(key.getConsumerSecret()).isEmpty();
    }

    @Test
    public void shouldReturnConsumerSecret() {
        String consumerSecret = "key";

        key.setConsumerSecret(consumerSecret);

        assertThat(key.getConsumerSecret()).isEqualTo(consumerSecret);
    }

    @Test
    public void accessTokenShouldDefaultToEmpty() {
        assertThat(key.getAccessToken()).isEmpty();
    }

    @Test
    public void shouldReturnAccessToken() {
        String accessToken = "key";

        key.setAccessToken(accessToken);

        assertThat(key.getAccessToken()).isEqualTo(accessToken);
    }

}
