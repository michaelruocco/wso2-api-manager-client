package uk.co.mruoc.wso2;

import org.testcontainers.containers.Container;

public class TestUrlBuilder {

    private static final String URL = "https://%s:%d";

    public String build(Container container, int port) {
        return String.format(URL, container.getContainerIpAddress(), container.getMappedPort(port));
    }

}
