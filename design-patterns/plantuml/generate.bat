
rem installation of graphviz on Windows: `choco install graphviz`
rem check is it works: `java -jar plantuml.jar -testdot`

java -jar plantuml.jar -charset UTF-8 -tsvg ../src/main/java/creational/factorymethod/factory-method.txt
