
## Sandbox for Java coding experiments

[![Java CI with Maven](https://github.com/andrei-punko/java-sandbox/actions/workflows/maven.yml/badge.svg)](https://github.com/andrei-punko/java-sandbox/actions/workflows/maven.yml)
[![Coverage](.github/badges/jacoco.svg)](https://github.com/andrei-punko/java-sandbox/actions/workflows/maven.yml)
[![Branches](.github/badges/branches.svg)](https://github.com/andrei-punko/java-sandbox/actions/workflows/maven.yml)

## Prerequisites
- Maven 3
- JDK 21

## Modules

* **akka-project**  
[Example](akka-project) of app with Akka framework usage


* **common**  
  - [Digital signing](common/src/main/java/by/andd3dfx/digitalsignature) of string/file/xml
  - Encryption [example](common/src/main/java/by/andd3dfx/encrypt)
  - Guice usage [example](common/src/main/java/by/andd3dfx/guice)
  - [JMS connector](common/src/main/java/by/andd3dfx/jms) to get queue size by JMX
  - MapStruct mapper [example](common/src/main/java/by/andd3dfx/mapper)
  - [Example](common/src/main/java/by/andd3dfx/masking) of annotation-driven masker
  - [Example](common/src/main/java/by/andd3dfx/sockets) of work with sockets
  - [Example](common/src/main/java/by/andd3dfx/testing) of testing classes using Mockito / JMock mocks


* **custom-spring-boot-starter**  
[Example](custom-spring-boot-starter/README.md) of custom Spring Boot Starter


* **dagger-sample**  
[Example](dagger-sample) of Dagger usage


* **db-versioning**  
Example of DB versioning usage with help of
  * [Liquibase](db-versioning/liquibase-db)
  * [Flyway](db-versioning/flyway-db)


* **elasticsearch**  
[Example](elasticsearch) of Elasticsearch usage


* **graphql-spring-boot-sample**  
[Example](graphql-spring-boot-sample/README.md) of GraphQL usage in Spring Boot application


* **grpc-sample**  
[Example](grpc-sample) of gRPC service and its client


* **hibernate-mappings**  
[Example](hibernate-mappings) of different cases of Hibernate mappings


* **openapi-generator-sample**  
Example of OpenApi generation:
  * [Usual Java app](openapi-generator-sample/openapi-generator-java)
  * [Spring Boot app](openapi-generator-sample/openapi-generator-spring)


* **messaging-stomp-websocket**  
[Example](messaging-stomp-websocket) of STOMP messaging over WebSockets for building interactive web application


* **sound-recorder-n-spectrum-analyzer**  
  - [Application](sound-recorder-n-spectrum-analyzer/src/main/java/by/andd3dfx/capturesound/AudioCaptureApp.java) to record a microphone's sound and play after that
  - [Application](sound-recorder-n-spectrum-analyzer/src/main/java/by/andd3dfx/capturesound/ShowRealTimeSpectrumApp.java) to analyze a spectrum of microphone's sound 


* **test-containers**  
[Example](test-containers) of Java Test containers usage with help of Docker


* **two-factor-authentication**  
[Example](two-factor-authentication) of two-factor authentication application with usage of Google authenticator


* **unit-tests-debugging**  
[Example](unit-tests-debugging) of different cases of tests debugging


### Build notes
To allow `io.fabric8:docker-maven-plugin` manipulate with Docker images need to add 
`DOCKER_HOST=tcp://127.0.0.1:2375` to environment variables on Windows machine and switch on 
Docker option `Expose daemon on tcp://localhost:2375 without TLS`
