package by.andd3dfx.dagger2.repository;

import dagger.Component;

// @Component makes Dagger create a graph of dependencies
@Component
public interface ApplicationGraph {

    // The return type  of functions inside the component interface is
    // what can be consumed from the graph
    UserRepository userRepository();
}
