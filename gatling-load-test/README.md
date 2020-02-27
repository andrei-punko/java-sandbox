
# Example of gatling-maven-plugin usage

Based on https://gatling.io/docs/3.0/extensions/maven_plugin/

To run load test execute the following command for particular test:

    $ mvn gatling:test -pl :gatling-load-test -Dgatling.simulationClass=by.andd3dfx.simulations.advanced.AdvancedSimulationStep01

Or for all tests:

    $ mvn gatling:test -pl :gatling-load-test -Dgatling.runMultipleSimulations=true
