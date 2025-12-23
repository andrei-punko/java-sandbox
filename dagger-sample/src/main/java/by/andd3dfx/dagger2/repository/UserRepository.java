package by.andd3dfx.dagger2.repository;

import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;

@Slf4j
public class UserRepository {

    private final UserLocalDataSource userLocalDataSource;
    private final UserRemoteDataSource userRemoteDataSource;

    // @Inject lets Dagger know how to create instances of this object
    @Inject
    public UserRepository(UserLocalDataSource userLocalDataSource, UserRemoteDataSource userRemoteDataSource) {
        this.userLocalDataSource = userLocalDataSource;
        this.userRemoteDataSource = userRemoteDataSource;

        log.info("user repository initialized");
    }

    public String useLocalDataSource() {
        log.info("Local data source used");
        return "Local data source used";
    }

    public String useRemoteDataSource() {
        log.info("Remote data source used");
        return "Remote data source used";
    }
}
