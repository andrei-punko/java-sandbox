
## Playground for Java coding experiments

* `akka-project`  
Example of Akka framework usage 

* `aspect-compile-time-weaving`  
* `aspect-post-compile-time-weaving`  
* `aspect-stub-sources-for-post-compile-weaving`  
* `aspect-load-time-weaving`  
Different cases of aspects usage mostly taken from [here](https://www.baeldung.com/aspectj)

* `db-versioning`  
Example of DB versioning with usage of Liquibase and Flyway

* `design-patterns`  
Design patterns examples

* `elasticsearch`  
Example of Elasticsearch usage

* `grpc-sample`  
Example of gRPC service and its client

* `hibernate-mappings`  
Example of different cases of Hibernate mappings

* `iot-rest-service-sample`  
Example of microservice with IoT purposes based on spring-boot related stack

* `pravtor-search`  
Parser for pravtor.ru site to extract some info from it

* `sandbox-core`  
Unordered staff with Java experiments. In addition contains tasks from interviews

* `swing-app-search-test-task`  
Test task with desktop application with usage of Google search engine

* `two-factor-authentification`  
Example of two-factor authentification application with usage of Google authentificator

* `unit-tests-debugging`  
Example of different cases of tests debugging


### Build notes
To allow `io.fabric8:docker-maven-plugin` manipulate with Docker images need to add 
`DOCKER_HOST=tcp://127.0.0.1:2375` to environment variables on Windows machine and switch on 
Docker option `Expose daemon on tcp://localhost:2375 without TLS`
