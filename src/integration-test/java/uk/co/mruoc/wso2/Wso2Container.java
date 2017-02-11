package uk.co.mruoc.wso2;

import org.testcontainers.containers.GenericContainer;

import static java.util.Arrays.asList;

public class Wso2Container<SELF extends GenericContainer<SELF>> extends GenericContainer {

    public Wso2Container(String image) {
        super(image);
    }

    @Override
    public SELF withExposedPorts(Integer... ports) {
        this.setExposedPorts(asList(ports));
        for (Integer port : ports)
            addFixedExposedPort(port, port);
        return self();
    }

    @Override
    public SELF self() {
        return (SELF) this;
    }

}
