Example of gatling-maven-plugin usage
=====================================
Based on https://gatling.io/docs/3.0/extensions/maven_plugin/

To test it out, simply execute the following command:

    $mvn gatling:test -pl :gatling-load-test -Dgatling.simulationClass=by.andd3dfx.advanced.AdvancedSimulationStep01

for particular test or simply:

    mvn gatling:test -pl :gatling-load-test -Dgatling.runMultipleSimulations=true

for all tests
