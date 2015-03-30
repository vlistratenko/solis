REM create variable that stores the project folder path. This variable will used in the subsequent statements.
set javaTestProjectPath=%~dp0
set TESTNGFILE=RunTests.xml
REM move to the project folder
cd /d %javaTestProjectPath%
REM set path to dir that contains javac.exe and java.exe
set path=C:\Program Files\Java\jdk1.7.0_11\bin
REM set the classpath, this tells java where to look for the library files, the project bin folder is adde as it will store the .class file after compile
set classpath=%javaTestProjectPath%\bin
REM execute testng framework by giving the path of the testng.xml file as a parameter. The xml file tells testng what test to run
java org.testng.TestNG "%javaTestProjectPath%\%TESTNGFILE%" -d "%javaTestProjectPath%\test-output" -listener memiana.web.tests.selenium.utils.MyTestListener
