package by.andd3dfx.dagger2.repository;

import javax.inject.Inject;

public class UserRemoteDataSource {

    @Inject
    public UserRemoteDataSource() {
        System.out.println("user remote data source initialized");
    }
}
