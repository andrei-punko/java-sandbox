package by.andd3dfx.dagger2.repository;

import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;

@Slf4j
public class UserRemoteDataSource {

    @Inject
    public UserRemoteDataSource() {
        log.info("user remote data source initialized");
    }
}
