package by.andd3dfx.guice.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SpawnerImpl implements Spawner {

    @Override
    public void spawn() {
        log.info("Spawn something useful...");
    }
}
