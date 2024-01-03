
## Sandbox for Java coding experiments

[![Java CI with Maven](https://github.com/andrei-punko/java-sandbox/actions/workflows/maven.yml/badge.svg)](https://github.com/andrei-punko/java-sandbox/actions/workflows/maven.yml)
[![Coverage](.github/badges/jacoco.svg)](https://github.com/andrei-punko/java-sandbox/actions/workflows/maven.yml)
[![Branches](.github/badges/branches.svg)](https://github.com/andrei-punko/java-sandbox/actions/workflows/maven.yml)

* **akka-project**  
App example with Akka framework usage 


* **common**  
  - Digital signing of string/file/xml
  - Encryption example
  - Guice usage example
  - JMS connector to get queue size by JMX
  - MapStruct mapper example
  - Example of annotation-driven masker
  - Working with sockets


* **custom-spring-boot-starter**  
  Spring Boot custom Starter example

  See its [README](custom-spring-boot-starter/README.md) for details


* **dagger-sample**  
Example of Dagger usage


* **db-versioning**  
Example of DB versioning with usage of [Liquibase](db-versioning/liquibase-db) and [Flyway](db-versioning/flyway-db)


* **elasticsearch**  
Example of Elasticsearch usage


* **graphql-spring-boot-sample**  
  Example of GraphQL usage in Spring Boot application.
  
  See its [README](graphql-spring-boot-sample/README.md) for details


* **grpc-sample**  
Example of gRPC service and its client


* **hibernate-mappings**  
Example of different cases of Hibernate mappings


* **messaging-stomp-websocket**  
Example of STOMP messaging over WebSockets for building interactive web application


* **sound-recorder-n-spectrum-analyzer**  
  - [Application](sound-recorder-n-spectrum-analyzer/src/main/java/by/andd3dfx/capturesound/AudioCaptureApp.java) to record a microphone's sound and play after that
  - [Application](sound-recorder-n-spectrum-analyzer/src/main/java/by/andd3dfx/capturesound/ShowRealTimeSpectrumApp.java) to analyze a spectrum of microphone's sound 


* **test-containers**  
Example of Java Test containers usage with help of Docker


* **two-factor-authentication**  
Example of two-factor authentication application with usage of Google authenticator


* **unit-tests-debugging**  
Example of different cases of tests debugging

## Prerequisites
- Maven 3
- JDK 17

### Build notes
To allow `io.fabric8:docker-maven-plugin` manipulate with Docker images need to add 
`DOCKER_HOST=tcp://127.0.0.1:2375` to environment variables on Windows machine and switch on 
Docker option `Expose daemon on tcp://localhost:2375 without TLS`
