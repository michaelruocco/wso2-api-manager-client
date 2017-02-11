package uk.co.mruoc.wso2;

import org.testcontainers.containers.output.OutputFrame;

import java.util.function.Consumer;

public class Wso2ContainerStartupChecker implements Consumer<OutputFrame> {

    private static final int DELAY = 1000;

    private final Sleeper sleeper = new Sleeper();
    private final int waitTimeout;

    private boolean started = false;

    public Wso2ContainerStartupChecker(int waitTimeout) {
        this.waitTimeout = waitTimeout;
    }

    @Override
    public void accept(OutputFrame outputFrame) {
        String output = outputFrame.getUtf8String();
        if (output == null)
            return;
        if (output.contains("CarbonUIServiceComponent Mgt Console URL  :"))
            started = true;
    }

    public void waitForContainerToStart() {
        int slept = 0;
        while(!isStarted() && slept < waitTimeout) {
            sleeper.sleep(DELAY);
            slept += DELAY;
        }
        if (!isStarted())
            throw new RuntimeException("container did not start within timeout " + waitTimeout);
    }

    private boolean isStarted() {
        return started;
    }

}
