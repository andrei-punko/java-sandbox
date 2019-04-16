
## Debugging [tests](http://maven.apache.org/surefire/maven-surefire-plugin/examples/debugging.html) and [integration tests](http://maven.apache.org/surefire/maven-failsafe-plugin/examples/debugging.html)

#### Note: `mvnDebug` command works properly in Maven 3.5.0+

#### Debug usual tests (ends with `Test` word)

`mvn -Dmaven.surefire.debug test`  
 Tests run by maven-surefire-plugin and use 5005 port by default

`mvnDebug -DforkCount=0 test`  
Tests run by maven-surefire-plugin and use 8000 port by default

#### Debug using non-default remote port

`mvn -Dmaven.surefire.debug="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=8000 -Xnoagent -Djava.compiler=NONE" test`

#### Debug one particular test class:

`mvn -Dmaven.surefire.debug test -Dtest=SomeTest`

#### Debug one particular test method

`mvn -Dmaven.surefire.debug test -Dtest=SomeTest#someMethod`

#### Debug integration tests

`mvn -Dmaven.failsafe.debug verify`

Tests run by maven-failsafe-plugin and use 5005 port

Without particular configuration failsafe plugin consider that integration tests are all tests which have `IT` in name

#### Debug using non-default remote port

`mvn -Dmaven.failsafe.debug="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=8000 -Xnoagent 
-Djava.compiler=NONE" verify`

#### Debug one particular test class

`mvn -Dmaven.failsafe.debug verify -Dit.test=SomeIT`  

#### Debug one particular test method

`mvn -Dmaven.failsafe.debug verify -Dit.test=SomeIT#someMethod`  
