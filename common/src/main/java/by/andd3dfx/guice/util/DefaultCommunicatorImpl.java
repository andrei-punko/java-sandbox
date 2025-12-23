package by.andd3dfx.guice.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultCommunicatorImpl implements Communicator {

    @Override
    public void communicate() {
        log.info("Making friendly communication...");
    }
}
