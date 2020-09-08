package by.andd3dfx.dagger2.repository;

import javax.inject.Inject;

public class UserLocalDataSource {

    @Inject
    public UserLocalDataSource() {
        System.out.println("user local data source initialized");
    }
}
