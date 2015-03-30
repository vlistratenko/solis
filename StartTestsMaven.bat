REM  Updating project
REM create variable that stores the project folder path. This variable will used in the subsequent statements.
set javaTestProjectPath=%~dp0
set TESTNGFILE=RunTests.xml
REM move to the project folder
cd /d %javaTestProjectPath%
REM set path to dir that contains javac.exe and java.exe
@echo off
CHOICE /T 10 /D N /M "Do you want use specific admin user?"
IF ERRORLEVEL 2 GOTO TWO
set /p uName=Enter login:
set /p uPass=Enter password:
:TWO
CHOICE /C 12 /T 15 /D 1 /M "Select server. 1-TEST, 2-DEV or 3-UAT"
IF ERRORLEVEL 1 set n="TEST"
IF ERRORLEVEL 2 set n="DEV"
IF ERRORLEVEL 3 set n="UAT"
CHOICE /C 12 /T 15 /D 1 /M "Select server. 1-LOCAL or 2-REMOTE"
IF ERRORLEVEL 1 set s="LOCAL"
IF ERRORLEVEL 2 set s="REMOTE"
echo Select suite for execution:
echo Run_AcceptanceTests.xml type 1
echo Run_Donations_Suite_Completed.xml type 2
echo Run_EmailKPI_Suite_Completed.xml type 3
CHOICE /C 123 /M "Select suite:"
IF ERRORLEVEL 1 set suite="Run_AcceptanceTests.xml"
IF ERRORLEVEL 2 set suite="Run_Donations_Suite_Completed.xml"
IF ERRORLEVEL 3 set suite="Run_EmailKPI_Suite_Completed.xml"
echo test will run with following parameters: environment=%n%, server=%s%, testSuite=%suite%, adminLogin=%uName%
mvn -f pom.xml install -Denvironment=%n% -Dserver=%s% -DtestSuite=%suite% -DadminLogin=%uName% -DadminPass=%uPass%