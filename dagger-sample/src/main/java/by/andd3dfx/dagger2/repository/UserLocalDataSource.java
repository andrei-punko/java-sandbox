package by.andd3dfx.dagger2.repository;

import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;

@Slf4j
public class UserLocalDataSource {

    @Inject
    public UserLocalDataSource() {
        log.info("user local data source initialized");
    }
}
