
## Playground for Java coding experiments

* `akka-project`  
App example with Akka framework usage 

* `dagger-sample`  
Example of Dagger usage

* `db-versioning`  
Example of DB versioning with usage of Liquibase and Flyway

* `elasticsearch`  
Example of Elasticsearch usage

* `grpc-sample`  
Example of gRPC service and its client

* `hibernate-mappings`  
Example of different cases of Hibernate mappings

* `messaging-stomp-websocket`
Example of STOMP messaging over WebSockets for building interactive web application

* `pravtor-search`  
Parser for pravtor.ru site to extract some info from it

* `sandbox-core`  
Unordered staff with Java experiments. In addition, contains tasks from interviews

* `swing-app-search-test-task`  
Test task with desktop application with usage of Google search engine

* `test-containets`  
Example of Java Test containers usage with help of Docker

* `two-factor-authentification`  
Example of two-factor authentication application with usage of Google authenticator

* `unit-tests-debugging`  
Example of different cases of tests debugging


### Build notes
To allow `io.fabric8:docker-maven-plugin` manipulate with Docker images need to add 
`DOCKER_HOST=tcp://127.0.0.1:2375` to environment variables on Windows machine and switch on 
Docker option `Expose daemon on tcp://localhost:2375 without TLS`
