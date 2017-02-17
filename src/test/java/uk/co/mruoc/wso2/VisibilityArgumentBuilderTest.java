package uk.co.mruoc.wso2;

import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static uk.co.mruoc.wso2.ApiVisibility.PRIVATE;
import static uk.co.mruoc.wso2.ApiVisibility.PUBLIC;
import static uk.co.mruoc.wso2.ApiVisibility.RESTRICTED;

public class VisibilityArgumentBuilderTest {

    private static final String PREFIX = "visibility=";

    private final VisibilityArgumentBuilder builder = new VisibilityArgumentBuilder();

    private final ApiVisibilityParams params = mock(ApiVisibilityParams.class);

    @Test
    public void shouldBuildPublic() {
        given(params.getVisibility()).willReturn(PUBLIC);

        assertThat(builder.build(params)).isEqualTo(PREFIX + "public");
    }

    @Test
    public void shouldBuildPrivate() {
        given(params.getVisibility()).willReturn(PRIVATE);

        assertThat(builder.build(params)).isEqualTo(PREFIX + "private");
    }

    @Test
    public void shouldBuildRestrictedWithNoRoles() {
        given(params.getVisibility()).willReturn(RESTRICTED);

        assertThat(builder.build(params)).isEqualTo(PREFIX + "restricted&roles=");
    }

    @Test
    public void shouldBuildRestrictedWithRoles() {
        given(params.getVisibility()).willReturn(RESTRICTED);
        given(params.getRoles()).willReturn(Arrays.asList("role1", "role2"));

        assertThat(builder.build(params)).isEqualTo(PREFIX + "restricted&roles=role1,role2");
    }

}