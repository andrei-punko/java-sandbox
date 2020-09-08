package by.andd3dfx.dagger2.repository;

import javax.inject.Inject;

public class UserRepository {

    private final UserLocalDataSource userLocalDataSource;
    private final UserRemoteDataSource userRemoteDataSource;

    // @Inject lets Dagger know how to create instances of this object
    @Inject
    public UserRepository(UserLocalDataSource userLocalDataSource, UserRemoteDataSource userRemoteDataSource) {
        this.userLocalDataSource = userLocalDataSource;
        this.userRemoteDataSource = userRemoteDataSource;

        System.out.println("user repository initialized");
    }

    public String useLocalDataSource() {
        System.out.println("Local data source used");
        return "Local data source used";
    }

    public String useRemoteDataSource() {
        System.out.println("Remote data source used");
        return "Remote data source used";
    }
}
