package by.andd3dfx.dagger2.repository;

/**
 * Example of Dagger 2 usage according to page:
 * <p>
 * https://developer.android.com/training/dependency-injection/dagger-basics
 */
public class Main {

    public static void main(String[] args) {
        ApplicationGraph applicationGraph = DaggerApplicationGraph.create();
        UserRepository userRepository = applicationGraph.userRepository();
        userRepository.useLocalDataSource();
        userRepository.useRemoteDataSource();
    }
}
