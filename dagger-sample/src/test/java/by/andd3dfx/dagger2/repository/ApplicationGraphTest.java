package by.andd3dfx.dagger2.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ApplicationGraphTest {

    @Test
    public void testDaggerApplication() {
        ApplicationGraph applicationGraph = DaggerApplicationGraph.create();
        UserRepository userRepository = applicationGraph.userRepository();

        assertThat(userRepository.useLocalDataSource(), is("Local data source used"));
        assertThat(userRepository.useRemoteDataSource(), is("Remote data source used"));
    }
}