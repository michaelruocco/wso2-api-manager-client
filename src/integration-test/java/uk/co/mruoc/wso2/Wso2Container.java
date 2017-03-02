package uk.co.mruoc.wso2;

import org.testcontainers.containers.GenericContainer;

import static java.util.Arrays.asList;

public class Wso2Container<T extends GenericContainer<T>> extends GenericContainer {

    public Wso2Container(String image) {
        super(image);
    }

    @Override
    public T withExposedPorts(Integer... ports) {
        this.setExposedPorts(asList(ports));
        for (Integer port : ports)
            addFixedExposedPort(port, port);
        return self();
    }

    @Override
    public T self() {
        return (T) this;
    }

}
