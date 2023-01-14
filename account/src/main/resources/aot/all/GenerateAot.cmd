@echo off

:: Author Jmc

:: Module Name
set MODULE_NAME=account

:: Go to project dir
cd ../../../../..

:: Build jar
cmd /c "gradle %MODULE_NAME%:bootJar"

:: Generate AOT from jar
:: You need to wait for service starting
:: and then press ctrl + c to exit
cd %MODULE_NAME%
java ^
    -DspringAot=true ^
    -agentlib:native-image-agent=config-output-dir=src/main/resources/META-INF/native-image ^
    -jar ../docker/services/%MODULE_NAME%.jar --spring.profiles.active=dev

:: Build target jar with AOT info
cd .. && cmd /c "gradle %MODULE_NAME%:bootJar"
