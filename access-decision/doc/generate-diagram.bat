@echo off
setlocal
cd /d "%~dp0"

if not defined PLANTUML_JAR (
    echo PLANTUML_JAR is not set. Set it to the full path of plantuml.jar, e.g.:
    echo   set PLANTUML_JAR=C:\tools\plantuml\plantuml.jar
    exit /b 1
)

if not exist "%PLANTUML_JAR%" (
    echo PLANTUML_JAR points to a missing file: "%PLANTUML_JAR%"
    exit /b 1
)

java -jar "%PLANTUML_JAR%" "access-decision-class-model.puml"
exit /b %errorlevel%
